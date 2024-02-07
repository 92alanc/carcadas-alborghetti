package com.ukdev.carcadasalborghetti.ui.media

import android.net.Uri
import com.ukdev.carcadasalborghetti.data.remote.MediaRemoteDataSource
import com.ukdev.carcadasalborghetti.data.tools.Logger
import com.ukdev.carcadasalborghetti.data.tools.PaidFileHelper
import com.ukdev.carcadasalborghetti.domain.model.Media
import com.ukdev.carcadasalborghetti.domain.model.MediaType
import javax.inject.Inject

class FavouritesHandler @Inject constructor(
    mediaHelper: MediaHelper,
    logger: Logger,
    remoteDataSource: MediaRemoteDataSource,
    paidFileHelper: PaidFileHelper
) : PaidMediaHandler(
    mediaHelper,
    logger,
    remoteDataSource,
    paidFileHelper
) {

    override fun playMedia(link: Uri, media: Media) {
        when (media.type) {
            MediaType.AUDIO -> mediaHelper.playAudio(link)
            MediaType.VIDEO -> mediaHelper.playVideo(link, media.title)
            else -> return
        }
    }
}
