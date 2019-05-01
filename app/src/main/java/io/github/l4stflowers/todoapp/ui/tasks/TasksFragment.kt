package io.github.l4stflowers.todoapp.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.AndroidSupportInjection
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.databinding.TasksFragBinding
import javax.inject.Inject

class TasksFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tasksViewModel: TasksViewModel
    lateinit var viewDataBinding: TasksFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        viewDataBinding = TasksFragBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tasksViewModel = ViewModelProviders.of(this, viewModelFactory).get(TasksViewModel::class.java)
        viewDataBinding.viewmodel = tasksViewModel
        setUpFab()
    }

    override fun onResume() {
        super.onResume()
        tasksViewModel.loadTasks()
    }

    private fun setUpFab() {
        activity?.findViewById<FloatingActionButton>(R.id.fab_add_task)?.let {
            it.setImageResource(R.drawable.ic_add)
            it.setOnClickListener {
                findNavController(this).navigate(R.id.action_tasksFragment_to_addEditTaskFragment)
            }
        }
    }
}