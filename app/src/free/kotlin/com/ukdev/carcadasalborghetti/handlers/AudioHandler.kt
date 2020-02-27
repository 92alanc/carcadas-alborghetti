package com.ukdev.carcadasalborghetti.handlers

import com.ukdev.carcadasalborghetti.helpers.FileHelper
import com.ukdev.carcadasalborghetti.helpers.MediaHelper
import com.ukdev.carcadasalborghetti.model.Media
import com.ukdev.carcadasalborghetti.model.MediaType
import com.ukdev.carcadasalborghetti.utils.CrashReportManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AudioHandler(
        mediaHelper: MediaHelper,
        crashReportManager: CrashReportManager,
        fileSharingHelper: FileHelper
) : MediaHandler(mediaHelper, crashReportManager, fileSharingHelper) {

    override suspend fun play(media: Media, mediaType: MediaType) {
        try {
            mediaHelper.playAudio(media.uri)
        } catch (t: Throwable) {
            crashReportManager.logException(t)
        }
    }

    override suspend fun share(media: Media, mediaType: MediaType) {
        try {
            withContext(Dispatchers.IO) {
                fileHelper.getByteStream(media.uri)
            }.let { byteStream ->
                fileHelper.shareFile(byteStream, media, mediaType)
            }
        } catch (t: Throwable) {
            crashReportManager.logException(t)
        }
    }

}