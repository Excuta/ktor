package io.ktor.client.features.tracing

import com.facebook.stetho.inspector.network.*
import io.ktor.client.request.*

internal class KtorInspectorWebSocketResponse(
    private val requestId: String,
    private val requestData: HttpRequestData,
    private val responseData: HttpResponseData
) : NetworkEventReporter.InspectorWebSocketResponse,
    NetworkEventReporter.InspectorHeaders by KtorInterceptorHeaders(responseData.headers) {

    override fun requestId(): String {
        return requestId
    }

    override fun reasonPhrase(): String {
        return responseData.statusCode.description
    }

    override fun statusCode(): Int {
        return responseData.statusCode.value
    }

    override fun requestHeaders(): NetworkEventReporter.InspectorHeaders? {
        return KtorInterceptorHeaders(requestData.headers)
    }
}
