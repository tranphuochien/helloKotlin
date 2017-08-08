package com.example.root.demokotlin.data.remote.networking

import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.data.model.FriendListResponse
import com.facebook.AccessToken
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 03/08/2017.
 */

class Service @Inject
constructor(private val networkService: NetworkService) {

    companion object {
        private var curPage: String = ""
        private val LIMIT_ENTRY_REQUEST = 25
    }

    fun mFacebookAPI(): Observable<ArrayList<Friend>> {
        val token : AccessToken? = AccessToken.getCurrentAccessToken()

        val userId = token?.userId
        val tokenString = token?.token

        return networkService.getFriendList(userId, tokenString, LIMIT_ENTRY_REQUEST, curPage)
                .observeOn(Schedulers.io())
                .onErrorResumeNext(Function<Throwable, Observable<FriendListResponse>> { throwable -> Observable.error<FriendListResponse>(throwable) })
                .flatMap { friendListResponse ->
                    Observable.defer {
                        //Update afterPage
                        updatePage(friendListResponse)

                        val friendList = ArrayList<Friend>()

                        for (friend in friendListResponse.data) {
                            friendList.add(friend.convertToLocalModel())
                        }
                        Observable.just(friendList)
                    }
                }

    }

    private fun updatePage(friendListResponse: FriendListResponse) {
        curPage = friendListResponse.paging?.cursors?.after!!
    }



}