package com.lalitdhameliya.todoapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lalitdhameliya.todoapplication.dao.TaskDao
import com.lalitdhameliya.todoapplication.entity.Task

// Annotates class to be a Room Database with a table (entity) of the Task class
@Database(entities = [Task::class], version = 1, exportSchema = false)
public abstract class TaskRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): TaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context): TaskRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}