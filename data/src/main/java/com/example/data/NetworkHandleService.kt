package com.example.data

import com.example.domain.Result
import retrofit2.Response
import javax.inject.Inject

class NetworkHandleService @Inject constructor() {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return Result.Error(t.toString())
        }

        return if (!response.isSuccessful) {
            Result.Error("Response is not successful")
        } else {
            return if (response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("response.body() can't be null")
            }
        }
    }
}