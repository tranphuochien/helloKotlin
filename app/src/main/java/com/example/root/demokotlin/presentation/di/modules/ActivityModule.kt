package com.example.root.demokotlin.presentation.di.modules

import android.app.Activity
import com.example.root.demokotlin.presentation.util.ImageManager.GlideLib
import com.example.root.demokotlin.presentation.util.ImageManager.ImageManager
import dagger.Module
import dagger.Provides

/**
 * Created by root on 03/08/2017.
 */

@Module
class ActivityModule(val activity : Activity){
    @Provides
    fun provideActivity() : Activity {
        return activity
    }

    @Provides
    fun provideImageManager() : ImageManager {
        return GlideLib()
    }
}