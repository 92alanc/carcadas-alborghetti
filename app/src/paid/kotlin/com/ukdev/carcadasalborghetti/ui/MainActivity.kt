package com.ukdev.carcadasalborghetti.ui

import android.os.Bundle
import com.ukdev.carcadasalborghetti.databinding.ActivityBaseBinding
import com.ukdev.carcadasalborghetti.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var _binding: ActivityBaseBinding? = null

    override val baseBinding: ActivityBaseBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        super.onCreate(savedInstanceState)
    }
}