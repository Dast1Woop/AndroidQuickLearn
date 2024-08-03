package com.example.myfirstapplication

import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException



class MyNetRequestTool {

    enum class RequestType {
        GET,POST
    }

    // Create OkHttpClient instance
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Generic network request function
    inline fun <reified T : Any> fetchData(url: String,
                                           requestType: RequestType = RequestType.GET,
                                           body: RequestBody? = null): Single<T> {
        return Single.create { emitter ->
            val requestBuilder = Request.Builder()
                .url(url)

            when (requestType) {
                RequestType.GET -> requestBuilder.get()
                else -> {
                    body?.let {
                        requestBuilder.post(body)
                    }?:run {
                        emitter.onError(IllegalArgumentException("Request body cannot be null for POST requests"))
                    }
                }
            }

            val request = requestBuilder.build()

            okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onResponse(call: Call, response: okhttp3.Response) {
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

                override fun onFailure(call: Call, e: IOException) {
                    emitter.onError(e)
                }
            })

        }
    }
}
