package tubig.amihan.aquadive

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(
    private val images: List<Int>,
    private val titles: List<String>,
    private val descriptions: List<String>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], titles[position], descriptions[position])

        if (position == itemCount - 1) {
            holder.btnNavigate.visibility = View.VISIBLE
            holder.btnNavigate.startBlinking()
            holder.btnNavigate.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, GameActivity::class.java)
                context.startActivity(intent)
            }
        } else {
            holder.btnNavigate.stopBlinking()
            holder.btnNavigate.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    // Inside the ViewHolder class in ViewPagerAdapter
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.imageTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.imageDescription)
        val btnNavigate: BlinkableImageView = itemView.findViewById(R.id.btnNavigate)

        fun bind(imageResId: Int, title: String, description: String) {
            imageView.setImageResource(imageResId)
            titleTextView.text = title
            descriptionTextView.text = description
        }
    }

}