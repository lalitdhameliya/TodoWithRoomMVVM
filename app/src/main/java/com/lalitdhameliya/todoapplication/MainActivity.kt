package com.lalitdhameliya.todoapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalitdhameliya.todoapplication.adapter.TaskListAdapter
import com.lalitdhameliya.todoapplication.entity.Task
import com.lalitdhameliya.todoapplication.extension.toast
import com.lalitdhameliya.todoapplication.viewModel.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private val newTaskActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TaskListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in adapter.
            words?.let { adapter.setWords(words) }
        })

        fabAddNew.setOnClickListener {
            startActivityForResult(Intent(this, AddNewTaskActivity::class.java), newTaskActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddNewTaskActivity.EXTRA_TASK_TITLE)?.let {
                val desc = data.getStringExtra(AddNewTaskActivity.EXTRA_TASK_DESC)
                val word = Task(0, it, desc!!)
                taskViewModel.insert(word)
            }
        } else {
            toast("Not Saved")
        }
    }
}
