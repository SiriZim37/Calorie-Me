package de.calorieme.modules.mainmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import de.calorieme.R
import kotlinx.android.synthetic.main.item_healthymenu.view.*

class HealthyOptionMenuAdapter (private val  items: List<HealthyOptionMenuViewModel.MenuItem>,
                          private val listener: Listener) : RecyclerView.Adapter<HealthyOptionMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_healthymenu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            val item = items[adapterPosition]

            itemView.txt_menu.text = item.mName
            if(item.mtype == "M1") {
                itemView.icon_type.setImageResource(R.drawable.item_calorien)
            }else if(item.mtype == "M2"){
                itemView.icon_type.setImageResource(R.drawable.item_trainning)
            }  else if(item.mtype == "M3"){
                itemView.icon_type.setImageResource(R.drawable.item_food_select)
            }else{
                itemView.icon_type.setImageResource(R.drawable.item_centercare)
            }


        }

        private val onDetailClick = View.OnClickListener {
            val item = items[adapterPosition]
            listener.onTopicClick(adapterPosition, item)
        }

        init {
            itemView.mLayout.setOnClickListener(onDetailClick)
        }
    }

    interface Listener {
        fun onTopicClick(index: Int, item: HealthyOptionMenuViewModel.MenuItem)

    }

}