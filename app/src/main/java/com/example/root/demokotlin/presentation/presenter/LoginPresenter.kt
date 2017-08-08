package com.example.root.demokotlin.presentation.presenter

import com.example.root.demokotlin.presentation.view.LoginView
import com.example.root.demokotlin.presentation.view.fragments.LoginFragment
import javax.inject.Inject

/**
 * Created by root on 04/08/2017.
 */
class LoginPresenter @Inject
constructor() : Presenter {

    private var loginView: LoginView? = null


    fun setView(view: LoginFragment) {
        this.loginView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.loginView = null
    }


}
