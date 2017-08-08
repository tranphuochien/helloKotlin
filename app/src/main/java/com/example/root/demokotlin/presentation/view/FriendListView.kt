/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.root.demokotlin.presentation.view

import com.example.root.demokotlin.data.model.Friend


interface FriendListView : LoadDataView {

    fun renderUserList(userModelCollection: Collection<Friend>)

    fun viewUser(model: Friend)

    fun showErrorMessage()

    fun stopRefreshAnimation()

    fun startRefreshAnimation()
}
