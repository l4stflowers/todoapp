package io.github.l4stflowers.todoapp.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import io.github.l4stflowers.todoapp.databinding.AddEditTaskFragBinding
import javax.inject.Inject

class AddEditTaskFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var addEditTaskViewModel: AddEditTaskViewModel

    lateinit var viewDataBinding: AddEditTaskFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        viewDataBinding = AddEditTaskFragBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addEditTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddEditTaskViewModel::class.java)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewmodel = addEditTaskViewModel
    }
}