package com.example.root.demokotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by root on 03/08/2017.
 */
class Paging {
    @SerializedName("cursors")
    @Expose
    var cursors: Cursors? = null
    @SerializedName("next")
    @Expose(serialize = false, deserialize = false)
    var next: String? = ""

    inner class Cursors {
        @SerializedName("before")
        @Expose
        var before: String? = ""

        @SerializedName("after")
        @Expose
        var after: String? = ""
    }

}