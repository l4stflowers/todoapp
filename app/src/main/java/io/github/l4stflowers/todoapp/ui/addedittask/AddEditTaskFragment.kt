package io.github.l4stflowers.todoapp.ui.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import io.github.l4stflowers.todoapp.R
import io.github.l4stflowers.todoapp.databinding.AddEditTaskFragBinding
import javax.inject.Inject

class AddEditTaskFragment : DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var addEditTaskViewModel: AddEditTaskViewModel

    lateinit var viewDataBinding: AddEditTaskFragBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        viewDataBinding = AddEditTaskFragBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addEditTaskViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddEditTaskViewModel::class.java)

        addEditTaskViewModel.errorText.observe(this, Observer { error ->
            viewDataBinding.inputTitleLayout.error = error
        })

        addEditTaskViewModel.dismiss.observe(this, Observer {
            dismiss()
        })

        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewmodel = addEditTaskViewModel

        setupToolbar()
    }

    private fun setupToolbar() {
        viewDataBinding.toolbar.apply {
            title = context.getString(R.string.add_task_view_title)
            setNavigationIcon(R.drawable.ic_close_black_24dp)
            setNavigationOnClickListener { dismiss() }
            inflateMenu(R.menu.add_edit_task_frag_menu)
            setOnMenuItemClickListener {
                addEditTaskViewModel.saveTask()
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    companion object {
        const val TAG = "AddEditTaskFragment"
    }
}