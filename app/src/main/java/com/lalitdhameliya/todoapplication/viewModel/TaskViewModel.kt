package com.lalitdhameliya.todoapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lalitdhameliya.todoapplication.database.TaskRoomDatabase
import com.lalitdhameliya.todoapplication.entity.Task
import com.lalitdhameliya.todoapplication.repository.TaskRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: TaskRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Task>>

    init {
        val wordDao = TaskRoomDatabase.getDatabase(application).wordDao()
        repository = TaskRepository(wordDao)
        allWords = repository.allWords
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }
}