package com.example.myfirstapplication

import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class MyNetRqstTool {

    // Create OkHttpClient instance
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Generic network request function
    inline fun <reified T : Any> fetchData(url: String): Single<T> {
        return Single.create { emitter ->
            val request = Request.Builder()
                .url(url)
                .build()

            okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    response.body?.let { responseBody ->
                        try {
                            val jsonString = responseBody.string()
                            val data = Gson().fromJson(jsonString, T::class.java)
                            emitter.onSuccess(data)
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    } ?: emitter.onError(IOException("Response body is null"))
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    emitter.onError(e)
                }
            })
        }
    }
}
