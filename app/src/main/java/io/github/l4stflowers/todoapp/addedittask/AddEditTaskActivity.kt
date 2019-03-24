package io.github.l4stflowers.todoapp.addedittask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.l4stflowers.todoapp.R

class AddEditTaskActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)
        setUpActionBar()
    }

    private fun setUpActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
