package com.mozzartinterview.feature_kino_game.ui.live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.mozzartinterview.databinding.FragmentKinoLiveBinding

class KinoLiveFragment : Fragment() {

    private var _binding: FragmentKinoLiveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKinoLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.opap.gr/en/kino-live-draw")
    }
//    https://ds.opap.gr/web_kino/kinoIframe.html?link=https://ds.opap.gr/web_kino%20/kino/html/Internet_PRODUCTION/KinoDraw_201910.html&resolution=847x500
}