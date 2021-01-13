package margretcraft.homeplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ItemAdapter(var plants: ArrayList<ItemPlant>, val itemListActivity: ItemListActivity) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal lateinit var itemPlant: ItemPlant

        internal val id = 0
        internal val vid: TextView
        internal val rod: TextView
        internal val nomer: TextView
        internal val sort: TextView
        internal val supp: TextView
        internal val color: TextView

        init {

            nomer = itemView.findViewById(R.id.itemNomer)
            rod = itemView.findViewById(R.id.ItemRod)
            vid = itemView.findViewById(R.id.ItemVid)
            sort = itemView.findViewById(R.id.ItemSort)
            supp = itemView.findViewById(R.id.ItemSupplier)
            color = itemView.findViewById(R.id.ItemColor)

            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {

            if (v != null) {
                itemListActivity.setDetail(itemPlant)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemPlant = plants[position]
        holder.nomer.setText("" + plants[position].nomer)
        holder.rod.setText(plants[position].rod)
        holder.vid.setText(plants[position].vid)
        holder.sort.setText(plants[position].sort)
        holder.supp.setText(plants[position].supplier)
        holder.color.setText(plants[position].color)

    }

    override fun getItemCount(): Int {
        return plants.size
    }
}

