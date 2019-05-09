package io.github.l4stflowers.todoapp.ui.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import dagger.android.support.AndroidSupportInjection
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
        viewDataBinding = TaskDetailFragBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskDetailViewModel::class.java)

        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewmodel = taskDetailViewModel
    }

    override fun onResume() {
        super.onResume()
        taskDetailViewModel.loadTask(args.taskId)
    }
}