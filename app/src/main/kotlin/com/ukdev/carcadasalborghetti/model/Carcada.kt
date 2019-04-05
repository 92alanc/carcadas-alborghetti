package com.ukdev.carcadasalborghetti.model

import androidx.annotation.RawRes

data class Carcada(
        val title: String,
        val length: String,
        var position: Int,
        @RawRes val audioFileRes: Int
)