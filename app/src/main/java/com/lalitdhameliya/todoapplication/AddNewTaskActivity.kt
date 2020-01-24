package com.lalitdhameliya.todoapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_new_task.*

class AddNewTaskActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TASK_TITLE = "task_title"
        const val EXTRA_TASK_DESC = "task_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        btnSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edTaskName.text) || TextUtils.isEmpty(edTaskDesc.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = edTaskName.text.toString()
                val desc = edTaskDesc.text.toString()
                replyIntent.putExtra(EXTRA_TASK_TITLE, title)
                replyIntent.putExtra(EXTRA_TASK_DESC, desc)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
