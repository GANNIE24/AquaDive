package tubig.amihan.aquadive

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var iv1: ImageView
    private lateinit var iv2: ImageView
    private lateinit var iv3: ImageView
    private lateinit var iv4: ImageView
    private lateinit var iv5: ImageView
    private lateinit var iv6: ImageView
    private lateinit var descriptionOverlay: RelativeLayout
    private lateinit var imageDescription: TextView
    private lateinit var viewPager2: ViewPager2
// pribadong lagayan ng mga nasawi sa pagmamahal ano ba
    @SuppressLint("AppCompatMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        viewPager2 = findViewById(R.id.viewPager2)
        iv1 = findViewById(R.id.iv1)
        iv2 = findViewById(R.id.iv2)
        iv3 = findViewById(R.id.iv3)
        iv4 = findViewById(R.id.iv4)
        iv5 = findViewById(R.id.iv5)
        iv6 = findViewById(R.id.iv6)
// publish mo naman aba lagi nalang ganyan
        descriptionOverlay = findViewById(R.id.descriptionOverlay)
        imageDescription = findViewById(R.id.imageDescription)

        val titles = listOf(
            "Forward Dive",
            "Backward Dive",
            "Reversed Dive",
            "Inward Dive",
            "Twisting Dive",
            "Armstand Dive"
        )
// ano ba isa dlaawa talga ayaw mo ba
        val descriptions = listOf(
            "The diver initiates the dive facing the pool, executing a forward rotation away from the board/platform.",
            "Starting with their back toward the pool, the diver performs a backward rotation away from the board/platform.",
            "Facing the pool, the diver executes a backward rotation toward the board/platform.",
            "With their back away from the pool, the diver rotates forward toward the board/platform.",
            "Any dive involving at least one twist, regardless of the starting position or direction.",
            "The diver commences the dive from a stationary handstand at the platform's edge, exclusive to the platform setting."
        )
// eto yung gusto ko kaso wala ako dito sa listahan mo
        val images = listOf(
            R.drawable.forward,
            R.drawable.backward,
            R.drawable.reversed,
            R.drawable.inward,
            R.drawable.twisting,
            R.drawable.armstand
        )

        val adapter = ViewPagerAdapter(images, titles, descriptions)
        viewPager2.adapter = adapter

// isa dalawa talaloga naman
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                changeColor()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
// ako pa talaga ha
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val descriptions = listOf(
                    "Forward Dive",
                    "Backward Dive",
                    "Reversed Dive",
                    "Inward Dive",
                    "Twisting Dive",
                    "Armstand Dive"
                )
                imageDescription.text = descriptions[position]
            }
// ano nga
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                changeColor()
            }
        })
    }
// dito ka mag focuse para di mo malaman na mahal na mahal kita
    fun changeColor() {
        for (i in 0 until 6) {
            val view = when (i) {
                0 -> iv1
                1 -> iv2
                2 -> iv3
                3 -> iv4
                4 -> iv5
                5 -> iv6
                else -> null
            }
// eto na malapit na mtapos ano ba iallagay ko para lang mapa sayo eto na yung activity na yun ano ba prolmea
            view?.setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    if (i == viewPager2.currentItem) R.color.active else R.color.grey
                )
            )
        }
    }
}
