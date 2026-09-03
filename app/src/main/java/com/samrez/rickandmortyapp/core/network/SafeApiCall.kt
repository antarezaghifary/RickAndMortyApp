package com.samrez.rickandmortyapp.core.network

import com.samrez.rickandmortyapp.core.utils.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.ContentConvertException
import kotlinx.serialization.SerializationException
import java.net.SocketTimeoutException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.cancellation.CancellationException


suspend inline fun <reified T> safeApiCall(
    crossinline block: suspend () -> HttpResponse
): com.samrez.rickandmortyapp.core.utils.Result<T, DataError.Network> {
    val response = try {
        block()
    } catch (e: UnresolvedAddressException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: HttpRequestTimeoutException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (e: ConnectTimeoutException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (e: SocketTimeoutException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: ContentConvertException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: NoTransformationFoundException) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        return com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): com.samrez.rickandmortyapp.core.utils.Result<T, DataError.Network> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                val data = response.body<T>()
                com.samrez.rickandmortyapp.core.utils.Result.Success(data)
            } catch (e: SerializationException) {
                com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERIALIZATION)
            } catch (e: ContentConvertException) {
                com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERIALIZATION)
            } catch (e: NoTransformationFoundException) {
                com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERIALIZATION)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.UNKNOWN)
            }
        }
        404 -> com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.NOT_FOUND)
        408 -> com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.REQUEST_TIMEOUT)
        in 500..599 -> com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.SERVER_ERROR)
        else -> com.samrez.rickandmortyapp.core.utils.Result.Error(DataError.Network.UNKNOWN)
    }
}
