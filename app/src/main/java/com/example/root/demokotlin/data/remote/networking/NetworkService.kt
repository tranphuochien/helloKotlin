package com.example.root.demokotlin.data.remote.networking

import com.example.root.demokotlin.data.model.FriendListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by root on 03/08/2017.
 */
interface NetworkService {
    @GET("{user_id}/taggable_friends")
    fun getFriendList(
            @Path("user_id") userId: String?,
            @Query("access_token") access_token: String?,
            @Query("limit") limit: Int,
            @Query("after") after: String
    ): Observable<FriendListResponse>
}