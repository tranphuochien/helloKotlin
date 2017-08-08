package com.example.root.demokotlin.presentation.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.example.root.demokotlin.R
import com.example.root.demokotlin.presentation.presenter.LoginPresenter
import com.example.root.demokotlin.presentation.view.LoginView
import com.example.root.demokotlin.presentation.view.activities.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import javax.inject.Inject

/**
 * Created by root on 04/08/2017.
 */

class LoginFragment : BaseFragment(), LoginView {
    private lateinit var loginListener: LoginListener
    private lateinit var callbackManager: CallbackManager

    @BindView(R.id.btnShow)
    lateinit var btnShow: Button

    @BindView(R.id.login_button)
    lateinit var btnLogin: LoginButton

    @Inject
    lateinit var loginPresenter: LoginPresenter

    private var unBind : Unbinder? = null

    interface LoginListener {
        fun navigation()
    }

    override fun checkLogin() {
        val token = AccessToken.getCurrentAccessToken()

        if (token != null) {
            Log.d("Debug", token.userId)
            Log.d("Debug", token.token)
            //loginListener.navigation();
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is LoginListener) {
            loginListener = activity
        }
    }

    override fun onDetach() {
        super.onDetach()
        //loginListener
        //callbackManager = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.activity as MainActivity).getComponent().inject(this)

        loginPresenter.setView(this)
        checkLogin()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle?): View {
        val fragmentView = inflater.inflate(R.layout.fragment_login, container, false)
        unBind = ButterKnife.bind(this, fragmentView)

        callbackManager = CallbackManager.Factory.create()
        btnLogin.setFragment(this)
        btnLogin.setReadPermissions("email", "user_friends", "public_profile")


        // Callback registration
        btnLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                loginListener.navigation()
                Log.d("Debug", "success")
            }

            override fun onCancel() {
                Log.d("Debug", "onCancel")
            }

            override fun onError(exception: FacebookException) {
                Log.d("Debug", "onError")
            }
        })
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.loginPresenter.setView(this)
    }

    @OnClick(R.id.btnShow)
    internal fun navigateToList() {
        loginListener.navigation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.loginPresenter.destroy()
        this.unBind?.unbind()
    }
}
