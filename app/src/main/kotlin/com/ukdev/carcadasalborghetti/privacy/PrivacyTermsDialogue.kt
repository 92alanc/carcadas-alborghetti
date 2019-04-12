package com.ukdev.carcadasalborghetti.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ukdev.carcadasalborghetti.R

class PrivacyTermsDialogue : DialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialogue_privacy_terms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtPrivacy = view.findViewById<TextView>(R.id.txt_privacy)
        txtPrivacy.text = getString(R.string.privacy_terms)
    }

    companion object {
        const val TAG = "com.ukdev.carcadasalborghetti.privacy.PrivacyTermsDialogue"
    }

}