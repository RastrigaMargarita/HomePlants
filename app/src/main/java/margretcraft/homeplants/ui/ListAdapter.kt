package margretcraft.homeplants.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ItemPlantBinding
import margretcraft.homeplants.model.Plant


class ListAdapter(var mItemClickListener: CustomItemClickListener) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    var plants: List<Plant> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val obj = object : CustomItemClickListener {
        override fun onItemClick(view: View?, position: Int, plant: Plant) {}

    }

    interface CustomItemClickListener {
        fun onItemClick(view: View?, position: Int, plant: Plant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        val mViewHolder = ItemViewHolder(view);

        return mViewHolder
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var plant: Plant
        var ui: ItemPlantBinding = ItemPlantBinding.bind(itemView)
        internal val id = 0

        init {
            itemView.setOnClickListener { v ->
                mItemClickListener.onItemClick(v, adapterPosition, plants[adapterPosition])
            };
        }

        fun bind(plant: Plant) {
            this.plant = plant

            ui.itemNomer.text = "" + plant.nomer
            ui.ItemRod.text = plant.rod
            ui.ItemVid.text = plant.vid
            ui.ItemSort.text = plant.sort
            ui.ItemSupplier.text = plant.supplier
            ui.ItemColor.text = plant.color
        }
    }
}

