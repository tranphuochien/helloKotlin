package com.example.root.demokotlin.presentation.view.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.root.demokotlin.MyApplication
import com.example.root.demokotlin.R
import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.presentation.presenter.FriendListPresenter
import com.example.root.demokotlin.presentation.view.FriendListView
import com.example.root.demokotlin.presentation.view.activities.FriendListActivity
import com.example.root.demokotlin.presentation.view.adapter.EndlessRecyclerViewScrollListener
import com.example.root.demokotlin.presentation.view.adapter.FriendsAdapter
import javax.inject.Inject

/**
 * Created by root on 04/08/2017.
 */

class FriendListFragment : BaseFragment(), FriendListView, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        friendListPresenter.initialize()
    }

    interface FriendListListener {
        fun onFriendClicked(userModel: Friend)
    }

    @Inject
    lateinit var friendListPresenter: FriendListPresenter

    @Inject
    lateinit var friendsAdapter: FriendsAdapter

    @BindView(R.id.rv_users)
    lateinit var rv_users: RecyclerView

    @BindView(R.id.refresh_view)
    lateinit var pullToRefreshLayout: SwipeRefreshLayout

    private var unBind : Unbinder? = null

    private var friendListListener: FriendListListener? = null

    init {
        retainInstance = true
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is FriendListListener) {
            this.friendListListener = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity : FriendListActivity = this.activity as FriendListActivity
        activity.getComponent().inject(this)
        this.friendListPresenter.setRepository((activity.application as MyApplication)
                .appComponent.getRepository())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                     savedInstanceState: Bundle?): View {
        val fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false)
        unBind = ButterKnife.bind(this, fragmentView)

        setupRecyclerView()

        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.friendListPresenter.setView(this)

        setUpPullToRefresh()

        if (savedInstanceState == null) {
            this.loadUserList()
        }
    }

    override fun onResume() {
        super.onResume()
        this.friendListPresenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.friendListPresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_users.adapter = null

    }

    override fun onDestroy() {
        super.onDestroy()
        this.friendListPresenter.destroy()
        this.unBind?.unbind()
    }

    override fun onDetach() {
        super.onDetach()
        this.friendListListener = null
    }

    override fun renderUserList(userModelCollection: Collection<Friend>) {
        this.friendsAdapter.setUsersCollection(userModelCollection)
    }

    override fun viewUser(model: Friend) {
        if (this.friendListListener != null) {
            this.friendListListener!!.onFriendClicked(model)
        }
    }

    override fun context(): Context {
        return this.activity.applicationContext
    }

    private fun setupRecyclerView() {
        this.friendsAdapter.setOnItemClickListener(
                object : FriendsAdapter.OnItemClickListener{
                    override fun onUserItemClicked(userModel: Friend) {
                        this@FriendListFragment.friendListPresenter.onFriendItemClicked(userModel)

                    }
                })
        val linearLayoutManager = LinearLayoutManager(context())
        this.rv_users.layoutManager = linearLayoutManager
        this.rv_users.adapter = friendsAdapter
        this.rv_users.addOnScrollListener(
                object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                        // Triggered only when new data needs to be appended to the list
                        // Add whatever code is needed to append new items to the bottom of the list
                        loadNextDataFromApi(page)
                        Log.d("Debug", "On load more")

                    }
                }
        )
    }

    private fun loadNextDataFromApi(page: Int) {
        this.friendListPresenter.loadMore()
    }

    /**
     * Loads all users.
     */
    private fun loadUserList() {
        this.friendListPresenter.initialize()
    }

    /* PullToRefresh */
    private fun setUpPullToRefresh() {
        pullToRefreshLayout.setOnRefreshListener(this)
        pullToRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        pullToRefreshLayout.canChildScrollUp()
    }

    override fun showErrorMessage() {
        rv_users.visibility = View.INVISIBLE
        showSnackBar()
    }

    private fun showSnackBar() {
        Snackbar.make(pullToRefreshLayout, "Network not available!", Snackbar.LENGTH_LONG)
                .setAction("RETRY") { view ->
                    //startRefreshAnimation()
                    //configChangePresenter.getDataForView()
                }.show()
    }

    override fun startRefreshAnimation() {
        pullToRefreshLayout.post { pullToRefreshLayout.isRefreshing = true }

    }

    override fun stopRefreshAnimation() {
        pullToRefreshLayout.post { pullToRefreshLayout.isRefreshing = false }
    }
}