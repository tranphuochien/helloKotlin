package com.example.root.demokotlin.data.remote.networking

import com.example.root.demokotlin.presentation.di.components.AppComponent
import com.example.root.demokotlin.presentation.util.NetworkManagement
import okhttp3.Interceptor

/**
 * Created by root on 03/08/2017.
 */
class FeedInterceptor {
    companion object {
        fun getOnlineInterceptor() : Interceptor {
            val interceptor = Interceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                val cacheControl = originalResponse.header("Cache-Control")
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + 5000)
                            .build()
                } else {
                    originalResponse
                }
            }

            return interceptor
        }

        fun getOfflineInterceptor(appComponent: AppComponent) : Interceptor {
            val interceptor = Interceptor { chain ->
                var request = chain.request()
                if (!NetworkManagement.isNetworkAvailable(appComponent)) {
                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached")
                            .build()
                }
                chain.proceed(request)
            }

            return interceptor
        }
    }
}