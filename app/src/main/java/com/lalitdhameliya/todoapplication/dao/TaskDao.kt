package com.lalitdhameliya.todoapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lalitdhameliya.todoapplication.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * from task_table")
    fun getAllWord(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE from task_table")
    suspend fun deleteAll()
}