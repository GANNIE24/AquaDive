package tubig.amihan.aquadive

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

class GameActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private var isActivityRunning = false
// sige lang paglaruan mo lang ang puso ko sasapakin kita e
    @SuppressLint("SetJavaScriptEnabled", "ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        isActivityRunning = true

        webView = findViewById(R.id.webGame)

        val layoutParams = webView.layoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        webView.layoutParams = layoutParams

        val webSettings: WebSettings = webView.settings
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webSettings.javaScriptEnabled = true
        if (Build.VERSION.SDK_INT in 16..18) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView: View = window.decorView
            val uiOptions: Int = (
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
            decorView.systemUiVisibility = uiOptions
        }
        webView.webViewClient = object : WebViewClient() {
            @SuppressLint("ResourceType")
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                val url: String = request.url.toString()

                if (isBlockedUrl(url)) {
                    val emptyStream = ByteArrayInputStream("".toByteArray())
                    return WebResourceResponse("text/plain", "utf-8", emptyStream)
                }

                if (isActivityRunning) {
                    // Activity is running, use the context
                    if (url.contains("https://cdn.bubbleshooter.net/games/cliff-diving/sprites/200x200.jpg")) {
                        Log.e("IMAGE", "isIMAGE $url")
                        val drawableResource = R.drawable.loglog
                        val inputStream: InputStream = resources.openRawResource(drawableResource)
                        return WebResourceResponse("image/jpeg", "UTF-8", inputStream)
                    }
                }
                if (isActivityRunning) {
                    if (url.contains("https://cdn.bubbleshooter.net/games/cliff-diving/sprites/logo_menu.png")) {
                        Log.e("IMAGE", "isIMAGE $url")
                        val drawableResource = R.drawable.navnav
                        val inputStream: InputStream = resources.openRawResource(drawableResource)
                        return WebResourceResponse("image/jpeg", "UTF-8", inputStream)
                    }
                }
                if (isActivityRunning) {
                    if (url.contains("https://cdn.bubbleshooter.net/games/cliff-diving/sprites/logo_ctl.png")) {
                        Log.e("IMAGE", "isIMAGE $url")
                        val drawableResource = R.drawable.infofo
                        val inputStream: InputStream = resources.openRawResource(drawableResource)
                        return WebResourceResponse("image/jpeg", "UTF-8", inputStream)
                    }
                }
                if (isActivityRunning) {
                    if (url.contains("https://cdn.bubbleshooter.net/games/cliff-diving/sprites/plane.png")) {
                        Log.e("IMAGE", "isIMAGE $url")
                        val drawableResource = R.drawable.plane
                        val inputStream: InputStream = resources.openRawResource(drawableResource)
                        return WebResourceResponse("image/jpeg", "UTF-8", inputStream)
                    }
                }
                if (url.endsWith("https://cdn.bubbleshooter.net/games/cliff-diving/js/main.js")) {
                    try {
                        val inputStream: InputStream = assets.open("main.js")
                        val mimeType = "text/javascript"
                        val encoding = "UTF-8"
                        return WebResourceResponse(mimeType, encoding, inputStream)
                    } catch (e: IOException) {
                        Log.e("GameFragment", "Error in shouldInterceptRequest", e)
                    }
                }

                return super.shouldInterceptRequest(view, request)
            }
        }

        // Load the URL into the WebView
        webView.loadUrl("https://cdn.bubbleshooter.net/games/cliff-diving/")
        webView.clearCache(true)
        webView.clearHistory()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    // Handle the back key press
                    if (webView.canGoBack()) {
                        webView.goBack()
                        return true
                    }
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }

    private fun isBlockedUrl(url: String): Boolean {
        return url.contains("safeframe.googlesyndication.com/safeframe/1-0-40/html/container.html") ||
                url.contains(" http://www.codethislab.com/index.php?&l=en")
    }
}