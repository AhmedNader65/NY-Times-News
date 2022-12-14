package com.organization.nytimes.data.api.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.organization.nytimes.data.api.ApiConstants
import com.organization.nytimes.utils.Logger
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.io.InputStream

class FakeServer {
    private val mockWebServer = MockWebServer()

    private val endpointSeparator = "/"
    private val articlesEndpointPath = endpointSeparator + ApiConstants.MOST_VIEWED_ENDPOINT

    val baseEndpoint
        get() = mockWebServer.url(endpointSeparator)

    fun start() {
        mockWebServer.start(8080)
    }

    fun setHappyPathDispatcher() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(200).setBody(getJson("articles.json"))
            }
        }
    }

    fun shutdown() {
        mockWebServer.shutdown()
    }

    private fun getJson(path: String): String {
        return try {
            val context = ApplicationProvider.getApplicationContext<Context>()
            val jsonStream: InputStream = context.assets.open("networkresponses/$path")
            String(jsonStream.readBytes())
        } catch (exception: IOException) {
            Logger.e(exception, "Error reading network response json asset")
            throw exception
        }
    }
}