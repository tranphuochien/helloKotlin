package com.example.root.demokotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by root on 03/08/2017.
 */

class FriendResponse {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("picture")
    @Expose
    private var picture: PictureAvt? = null

    fun convertToLocalModel(): Friend {
        return Friend(id, this.name, this.picture?.data?.url)
    }

    inner class PictureAvt {
        var data: Data? = null
    }

    inner class Data {
        var is_silhouette: String? = null
        var url: String? = null
    }
}