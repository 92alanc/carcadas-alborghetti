package com.ukdev.carcadasalborghetti

import com.google.android.gms.ads.MobileAds
import com.ukdev.carcadasalborghetti.repository.MediaRepository
import com.ukdev.carcadasalborghetti.repository.MediaRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Suppress("unused")
class FreeApplication : CarcadasAlborghettiApplication() {

    private val repositoryModule = module {
        factory<MediaRepository> { MediaRepositoryImpl(androidContext()) }
    }

    override fun onCreate() {
        modules.add(repositoryModule)
        super.onCreate()
        MobileAds.initialize(this)
    }

}