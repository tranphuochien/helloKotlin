package com.example.root.demokotlin.presentation.presenter

import android.util.Log
import com.example.root.demokotlin.data.Repository
import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.presentation.util.InternetConnection
import com.example.root.demokotlin.presentation.view.FriendListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 04/08/2017.
 */

class FriendListPresenter @Inject
constructor(var internetConnection: InternetConnection) : Presenter {
    private var repository: Repository? = null
    private var viewFriendsList: FriendListView? = null
    private val disposable: CompositeDisposable
    //private var internetConnection: InternetConnection? = null
    private var viewSubscription: Disposable? = null
    private var internetStatusSubscription: Disposable? = null

    init {
        disposable = CompositeDisposable()
        //internetConnection = InternetConnection()
    }

    fun setView(view: FriendListView) {
        this.viewFriendsList = view
    }

    fun setRepository(repository: Repository) {
        this.repository = repository
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.viewFriendsList = null
        stopWaitForInternetToComeBack()
        disposable.dispose()
    }

    fun initialize() {
        this.loadFriendList()
    }

    fun loadMore() {
        this.loadFriendList()
    }

    private fun loadFriendList() {
        val subscription = repository!!.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .subscribeWith(object : DisposableObserver<ArrayList<Friend>>() {
                    override fun onNext(@io.reactivex.annotations.NonNull friends: ArrayList<Friend>) {
                        Log.d("Debug", "load data")
                        if (friends.size == 0){
                            viewFriendsList?.showErrorMessage()
                            waitForInternetToComeBack()
                        } else {
                            showFriendsCollectionInView(friends)
                        }
                        viewFriendsList?.stopRefreshAnimation()
                    }

                    override fun onError(@io.reactivex.annotations.NonNull e: Throwable) {
                        Log.d("Debug", "error load data " + e.toString())
                        viewFriendsList?.stopRefreshAnimation()
                        waitForInternetToComeBack()
                    }

                    override fun onComplete() {

                    }
                })

        disposable.add(subscription)
    }

    private fun showFriendsCollectionInView(friendsCollection: Collection<Friend>) {
        this.viewFriendsList?.renderUserList(friendsCollection)
    }


    fun onFriendItemClicked(model: Friend) {
        this.viewFriendsList?.viewUser(model)
    }

    private fun waitForInternetToComeBack() {
        if (internetConnection.isInternetOn()) {
            return
        }

        internetConnection.registerBroadCastReceiver(viewFriendsList!!.context().applicationContext)

        if (internetStatusSubscription == null || internetStatusSubscription!!.isDisposed) {
            internetStatusSubscription = internetConnection.getInternetStatusHotObservable()
                    .filter({ internetConnectionStatus -> internetConnectionStatus })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ internetConnectionStatus ->
                        Log.d("Debug", "anim start")
                        viewFriendsList?.startRefreshAnimation()
                        stopWaitForInternetToComeBack()
                        Log.d("Debug", "reload")
                        loadFriendList()


                    })
        }
    }

    private fun stopWaitForInternetToComeBack() {
        if (internetStatusSubscription != null && !internetStatusSubscription!!.isDisposed) {
            internetStatusSubscription!!.dispose()
            internetConnection.unRegisterBroadCastReceiver(viewFriendsList!!.context().applicationContext)
        }
    }
}
