package com.efhem.byteworksassessment.data.remote

import com.efhem.byteworksassessment.domain.model.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException


suspend fun <T : Any> safeApiResult (
    dispatcher: CoroutineDispatcher,
    call: suspend () -> Response<T>
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            val response = call.invoke()
            when {
                response.isSuccessful -> { ResultWrapper.Success(response.body()!!) }
                response.code() == ErrorCode.BAD_REQUEST -> {
                    ResultWrapper.Error(Exception(message = "Bad Request, Check your input"))
                }
                else -> ResultWrapper.Error(Exception(message = "Server error"))
            }
        } catch (e: IOException) {
            ResultWrapper.Error(Exception(message = "There was a network connection error."))
        }catch (e: Throwable){
            ResultWrapper.NetworkError()
        }
    }
}

object ErrorCode {
    const val POOR_NETWORK = 0
    const val INVALID_TOKEN = 401
    const val BAD_REQUEST = 400
    const val SERVER_UNABLE_TO_PROCESS_REQUEST = 500
    const val INVALID_LOGIN_CREDENTIALS = 503
    const val PAGE_NOT_FOUND = 404
    const val REQUEST_TIME_OUT = 408
    const val TOO_MANY_REQUEST_SENT = 429
}

