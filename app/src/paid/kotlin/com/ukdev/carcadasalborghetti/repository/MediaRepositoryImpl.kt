package com.ukdev.carcadasalborghetti.repository

import com.crashlytics.android.Crashlytics
import com.ukdev.carcadasalborghetti.BuildConfig
import com.ukdev.carcadasalborghetti.api.DropboxApi
import com.ukdev.carcadasalborghetti.api.requests.MediaRequest
import com.ukdev.carcadasalborghetti.api.responses.MediaResponse
import com.ukdev.carcadasalborghetti.model.ErrorType
import com.ukdev.carcadasalborghetti.model.Media
import com.ukdev.carcadasalborghetti.model.MediaType
import com.ukdev.carcadasalborghetti.utils.getService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaRepositoryImpl : MediaRepository() {

    private val api by lazy { getService(DropboxApi::class, BuildConfig.BASE_URL) }

    override suspend fun getMedia(mediaType: MediaType): List<Media> = withContext(Dispatchers.IO) {
        val dir = if (mediaType == MediaType.AUDIO)
            DropboxApi.DIR_AUDIO
        else
            DropboxApi.DIR_VIDEO
        val request = MediaRequest(dir)
        api.listMedia(request).enqueue(object : Callback<MediaResponse> {
            override fun onResponse(call: Call<MediaResponse>, response: Response<MediaResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        val media = responseBody.entries.sort()
                        resultCallback.onMediaFound(media)
                    }
                } else {
                    Crashlytics.log("Error fetching media. Response code: ${response.code()} Error body: ${response.errorBody()?.string()}")
                    resultCallback.onError(ErrorType.UNKNOWN)
                }
            }

            override fun onFailure(call: Call<MediaResponse>, t: Throwable) {
                Crashlytics.log("Error fetching media. ${t.message}")
                resultCallback.onError(ErrorType.CONNECTION)
            }
        })
    }

}