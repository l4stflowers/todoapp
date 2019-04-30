package io.github.l4stflowers.todoapp.ui.tasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.ui.addedittask.AddEditTaskActivity
import javax.inject.Inject

class TasksActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.tasks_act)
        setupFab()

        supportFragmentManager.findFragmentById(R.id.contentFrame)
            ?: supportFragmentManager.beginTransaction().apply {
                add(R.id.contentFrame, TasksFragment())
            }.commit()
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
