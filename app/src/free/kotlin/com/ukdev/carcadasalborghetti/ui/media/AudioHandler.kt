package com.ukdev.carcadasalborghetti.ui.media

import com.ukdev.carcadasalborghetti.data.tools.FileHelper
import com.ukdev.carcadasalborghetti.data.tools.Logger
import com.ukdev.carcadasalborghetti.domain.model.Media
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioHandler @Inject constructor(
    mediaHelper: MediaHelper,
    logger: Logger,
    fileSharingHelper: FileHelper
) : MediaHandler(mediaHelper, logger, fileSharingHelper) {

    override suspend fun play(media: Media) {
        try {
            mediaHelper.playAudio(media.uri)
        } catch (t: Throwable) {
            logger.error(t)
        }
    }

    override suspend fun share(media: Media) {
        try {
            withContext(Dispatchers.IO) {
                fileHelper.getByteStream(media.uri)
            }.let { byteStream ->
                fileHelper.shareFile(byteStream, media)
            }
        } catch (t: Throwable) {
            logger.error(t)
        }
    }
}
