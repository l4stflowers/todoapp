package io.github.l4stflowers.todoapp.ui.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.ui.addedittask.AddEditTaskActivity

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        setupFab()
    }

    private fun setupFab() {
        findViewById<FloatingActionButton>(R.id.fab_add_task)?.let {
            it.setImageResource(R.drawable.ic_add)
            it.setOnClickListener {
                val intent = Intent(this, AddEditTaskActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
