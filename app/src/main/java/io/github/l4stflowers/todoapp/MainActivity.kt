package io.github.l4stflowers.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
        setContentView(R.layout.main_act)

        navController = findNavController(this, R.id.nav_host_fragment)
        setupNavigation(navController)
        setupActionBar(navController)
    }

    override fun supportFragmentInjector() = androidInjector

    private fun setupNavigation(navController: NavController) {
        val fab = findViewById<FloatingActionButton>(R.id.fab_add_task)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tasksFragment -> fab.isVisible = true
                else -> fab.isVisible = false
            }
        }
    }

    private fun setupActionBar(navController: NavController) {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
