package com.h.testapi.ui.add_entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.h.testapi.repo.Repository
import com.h.testapi.util.Status
import javax.inject.Inject

class AddEntryViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _status = repository.apiStatus
    val status: LiveData<Status>
        get() = _status

    fun addEntry(text: String) {
        repository.addEntry(text)
    }
}