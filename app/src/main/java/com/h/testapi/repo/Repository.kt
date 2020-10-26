package com.h.testapi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.h.testapi.model.Entry
import com.h.testapi.network.Api
import com.h.testapi.util.PrefsHelper
import com.h.testapi.util.Status
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: Api) {
    private val TAG = "Repository"
    private val list = MutableLiveData<List<Entry>>()
    val compositeDisposable = CompositeDisposable()
    val apiStatus: MutableLiveData<Status> = MutableLiveData(Status.LOADING)
    var id: String

    init {
        id = PrefsHelper.readSession()
    }

    fun getList(): MutableLiveData<List<Entry>> = list

    fun getEntries() {
        apiStatus.postValue(Status.LOADING)
        compositeDisposable.add(
            apiService.getEntries(session = id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    apiStatus.postValue(Status.DONE)
                    list.postValue(it.data[0])
                }, {
                    apiStatus.postValue(Status.ERROR)
                })
        )
    }

    fun addEntry(text: String) {
        apiStatus.postValue(Status.LOADING)
        compositeDisposable.add(
            apiService.addEntry(session = id, body = text)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    apiStatus.postValue(Status.DONE)
                    getEntries()

                }, {
                    apiStatus.postValue(Status.ERROR)
                })
        )
    }

    fun newSession() {
        apiStatus.postValue(Status.LOADING)
        compositeDisposable.add(
            apiService.newSession()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    apiStatus.postValue(Status.DONE)
                    id = it.data.session
                    PrefsHelper.writeSession(it.data.session)

                }, {
                    apiStatus.postValue(Status.SESSION_ERROR)
                })
            )
    }
}


