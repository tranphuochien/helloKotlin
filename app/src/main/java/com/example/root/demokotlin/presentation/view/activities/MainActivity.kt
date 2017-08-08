package com.example.root.demokotlin.presentation.view.activities

import android.os.Bundle
import com.example.root.demokotlin.R
import com.example.root.demokotlin.presentation.di.components.ActivityComponent
import com.example.root.demokotlin.presentation.view.fragments.LoginFragment
import com.example.root.demokotlin.presentation.view.navigation.Navigator
import javax.inject.Inject


class MainActivity : BaseActivity(), LoginFragment.LoginListener{
    @Inject
    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.fragmentContainer, LoginFragment())
    }

    override fun navigation() {
        navigator.navigateToFriendList(this)
    }

    fun getComponent() : ActivityComponent {
        return activityComponent
    }

    override fun onInject() {
        activityComponent.inject(this)
    }

}
