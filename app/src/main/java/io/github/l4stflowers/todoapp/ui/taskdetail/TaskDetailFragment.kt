package io.github.l4stflowers.todoapp.ui.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.android.support.AndroidSupportInjection
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.databinding.TaskDetailFragBinding
import javax.inject.Inject

class TaskDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var taskDetailViewModel: TaskDetailViewModel

    lateinit var viewDataBinding: TaskDetailFragBinding

    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)
        viewDataBinding = TaskDetailFragBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskDetailViewModel::class.java)
        taskDetailViewModel.onChangedTaskStatus.observe(this, Observer {
            findNavController().navigateUp()
        })

        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewmodel = taskDetailViewModel
    }

    override fun onResume() {
        super.onResume()
        taskDetailViewModel.loadTask(args.taskId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_detail_frag_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_done -> {
                taskDetailViewModel.completeTask()
                return true
            }
            R.id.action_edit -> {
                // TODO 編集画面への遷移を実装する
                return true
            }
            R.id.action_delete -> {
                taskDetailViewModel.deleteTask()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}