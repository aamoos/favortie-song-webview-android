package com.example.favortie_song_webview

 import android.content.Intent
 import android.net.Uri
 import android.os.Bundle
 import android.webkit.WebChromeClient
 import android.webkit.WebResourceRequest
 import android.webkit.WebView
 import android.webkit.WebViewClient
 import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()

                //소셜로그인 클릭시 인앱으로 띄우기
                if (url != null && !url.contains("oauth2")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                return false
            }
        }

        // 웹페이지 로드
        webView.settings.javaScriptEnabled = true // 자바스크립트 사용을 허용
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.getSettings().setUserAgentString("Chrome/56.0.0.0 Mobile");     //oauth2 403 에러 대응
        webView.loadUrl("https://favorite-song.store")
    }

    override fun onBackPressed() {
        // 웹뷰의 히스토리를 이용해 뒤로 가기
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}