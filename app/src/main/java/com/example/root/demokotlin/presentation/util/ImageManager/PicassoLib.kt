package com.example.root.demokotlin.presentation.util.ImageManager

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by root on 04/08/2017.
 */
class PicassoLib : ImageManager {
    override fun load(context: Context, source: String?, target: ImageView?) {
        Picasso.with(context)
                .load(source)
                .resize(200, 200)
                .into(target)

    }
}