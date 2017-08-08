package com.example.root.demokotlin.presentation.util.ImageManager

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.root.demokotlin.R

/**
 * Created by root on 04/08/2017.
 */
class GlideLib : ImageManager {
    override fun load(context: Context, source: String?, target: ImageView?) {
        Glide.with(context)
                .load(source)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .into(target)
    }
}
