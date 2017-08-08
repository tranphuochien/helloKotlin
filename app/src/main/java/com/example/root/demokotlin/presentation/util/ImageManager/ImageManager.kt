package com.example.root.demokotlin.presentation.util.ImageManager

import android.content.Context
import android.widget.ImageView

/**
 * Created by root on 04/08/2017.
 */

interface ImageManager {
    fun load(context: Context, source: String?, target: ImageView?)
}