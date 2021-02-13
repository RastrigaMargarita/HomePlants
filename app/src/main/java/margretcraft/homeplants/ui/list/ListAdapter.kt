package margretcraft.homeplants.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ItemPlantBinding
import margretcraft.homeplants.model.Plant

interface CustomItemClickListener {
    fun onItemClick(view: View?, position: Int, plant: Plant)
}

class ListAdapter(val mItemClickListener: CustomItemClickListener) :
        RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    var plants: List<Plant> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_plant, parent, false)
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

        fun bind(plant: Plant) {
            this.plant = plant
            ui.itemCategory.text = itemView.context.getString(plant.category.catname)
            ui.itemNomer.text = plant.nomer.toString()
            ui.itemRod.text = plant.rod
            ui.itemVid.text = plant.vid
            ui.itemSort.text = plant.sort
            ui.itemSupplier.text = plant.supplier
            ui.itemColor.text = plant.color
            itemView.setOnClickListener { v ->
                mItemClickListener.onItemClick(v, adapterPosition, plants[adapterPosition])
            };
        }
    }
}

