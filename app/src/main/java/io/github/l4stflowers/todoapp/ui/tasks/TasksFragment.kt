package io.github.l4stflowers.todoapp.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.AndroidSupportInjection
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.databinding.TasksFragBinding
import io.github.l4stflowers.todoapp.ui.addedittask.AddEditTaskFragment
import javax.inject.Inject

class TasksFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tasksViewModel: TasksViewModel
    lateinit var viewDataBinding: TasksFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)
        viewDataBinding = TasksFragBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tasksViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(TasksViewModel::class.java)
        tasksViewModel.addTaskCompleted.observe(this, Observer {
            tasksViewModel.loadTasks()
        })

        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewmodel = tasksViewModel
        setUpTaskList()
        setUpFab()
    }

    override fun onResume() {
        super.onResume()
        tasksViewModel.loadTasks()
    }

    private fun setUpTaskList() {
        val adapter = TasksAdapter {
            val action = TasksFragmentDirections.actionTasksFragmentToTaskDetailFragment(it.id!!)
            findNavController(this).navigate(action)
        }
        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        viewDataBinding.tasksList.adapter = adapter
        viewDataBinding.tasksList.layoutManager = layoutManager
        viewDataBinding.tasksList.addItemDecoration(dividerItemDecoration)
        viewDataBinding.tasksList.setHasFixedSize(true)
    }

    private fun setUpFab() {
        activity?.findViewById<FloatingActionButton>(R.id.fab_add_task)?.let {
            it.setImageResource(R.drawable.ic_add)
            it.setOnClickListener {
                val addEditTaskFragment = AddEditTaskFragment()
                addEditTaskFragment.show(fragmentManager!!, AddEditTaskFragment.TAG)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tasks_frag_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        showTaskFilterMenu()
        return super.onOptionsItemSelected(item)
    }

    private fun showTaskFilterMenu() {
        val popup = PopupMenu(context, activity?.findViewById(R.id.action_filter))
        popup.menuInflater.inflate(R.menu.task_filter_menu, popup.menu)
        popup.show()
    }
}