package com.example.root.demokotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by root on 03/08/2017.
 */
class FriendListResponse {
    @SerializedName("data")
    @Expose
    var data: List<FriendResponse> = ArrayList<FriendResponse>()

    @SerializedName("paging")
    @Expose
    val paging: Paging? = null


}