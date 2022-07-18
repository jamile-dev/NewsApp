package dev.jamile.newsapp.network.interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC

object RequestInterceptor {

    private fun logger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = BASIC
        println("LOGGERR >> $logger")

        return logger
    }

    fun setupOkHttp(): OkHttpClient.Builder {
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor(logger())
        okHttp.addInterceptor { chain ->
            println("\n======================")
            println("CHAIN >> $chain")
            return@addInterceptor chain.proceed(chain.request())
        }
        return okHttp
    }
}