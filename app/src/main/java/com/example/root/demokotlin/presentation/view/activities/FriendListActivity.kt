package com.example.root.demokotlin.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.root.demokotlin.R
import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.presentation.di.components.ActivityComponent
import com.example.root.demokotlin.presentation.view.fragments.FriendListFragment

class FriendListActivity : BaseActivity(), FriendListFragment.FriendListListener {
    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, FriendListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)


        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, FriendListFragment())
        }
    }

    fun getComponent() : ActivityComponent {
        return activityComponent
    }

    override fun onFriendClicked(userModel: Friend) {
        showToastMessage(userModel.name)
    }

    override fun onInject() {
    }
}
