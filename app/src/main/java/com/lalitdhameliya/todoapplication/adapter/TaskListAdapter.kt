package com.lalitdhameliya.todoapplication.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lalitdhameliya.todoapplication.R
import com.lalitdhameliya.todoapplication.entity.Task
import com.lalitdhameliya.todoapplication.extension.inflate
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.WordViewHolder>() {

    private var tasks = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        WordViewHolder(parent.inflate(R.layout.recyclerview_item))

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) = holder.bind(tasks[position])

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) = with(itemView) {
            val sampleDrawable = resources.getDrawable(R.drawable.drawable_task_tile)
            sampleDrawable.colorFilter =
                PorterDuffColorFilter(Color.parseColor(getRandomColor()), PorterDuff.Mode.MULTIPLY)
            recyclerview_item_main.setBackgroundDrawable(sampleDrawable)

            tvTitle.text = task.title
            tvDesc.text = task.description
        }
    }

    internal fun setWords(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    fun getRandomColor(): String {
        val colors = arrayOf(
            "#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#1E88E5", "#0288D1", "#0097A7",
            "#009688", "#43A047", "#558B2F", "#E65100", "#8D6E63", "#757575", "#78909C"
        )
        return colors.random()
    }
}