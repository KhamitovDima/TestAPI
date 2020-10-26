package com.h.testapi.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.h.testapi.model.Entry
import com.h.testapi.repo.Repository
import com.h.testapi.util.PrefsHelper
import com.h.testapi.util.Status
import javax.inject.Inject


class ListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    init {
        saveSession()
    }

    private val _status = repository.apiStatus
    val status: LiveData<Status>
        get() = _status

    fun saveSession() {
        if (PrefsHelper.isFirstSession()) {
            Log.d("TAG", "isFirstSession")
            repository.newSession()
            PrefsHelper.firstSession()
        }
    }

    fun getList(): MutableLiveData<List<Entry>> {
        return repository.getList()
    }

    fun getEntries() {
        repository.getEntries()
    }

    override fun onCleared() {
        repository.compositeDisposable.clear()
        super.onCleared()
    }
}