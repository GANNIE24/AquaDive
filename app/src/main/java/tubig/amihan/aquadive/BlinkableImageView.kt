package tubig.amihan.aquadive

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class BlinkableImageView : AppCompatImageView {

    private val blinkHandler = Handler()
    private var isBlinking = false
    private val blinkInterval: Long = 1000 // Setting the blinking interval in milliseconds

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        startBlinking()
    }

    fun startBlinking() {
        isBlinking = true
        blinkHandler.postDelayed(object : Runnable {
            override fun run() {
                if (isBlinking) {
                    toggleVisibility()
                    blinkHandler.postDelayed(this, blinkInterval)
                }
            }
        }, blinkInterval)
    }

    private fun toggleVisibility() {
        visibility = if (visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }

    fun stopBlinking() {
        isBlinking = false
        visibility = View.VISIBLE // Ensure the view is visible when blinking stops
    }
}
