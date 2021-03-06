package com.lalitdhameliya.todoapplication.repository

import androidx.lifecycle.LiveData
import com.lalitdhameliya.todoapplication.dao.TaskDao
import com.lalitdhameliya.todoapplication.entity.Task

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TaskRepository(private val taskDao: TaskDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Task>> = taskDao.getAllWord()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }
}