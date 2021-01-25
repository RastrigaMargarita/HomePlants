package margretcraft.homeplants.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivityItemDetailBinding
import margretcraft.homeplants.databinding.ItemDetailBinding
import margretcraft.homeplants.model.Plant

class DetailFragment

    : Fragment() {
    lateinit var ui: ActivityItemDetailBinding
    lateinit var uiDetail: ItemDetailBinding


    private var m: Plant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityItemDetailBinding.inflate(layoutInflater)
        uiDetail = ItemDetailBinding.inflate(layoutInflater)
        if (arguments?.containsKey(ARG_ITEM)!!) {

            m = arguments?.getParcelable(ARG_ITEM)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        if (m != null) {
            ui.toolbarLayout.title = "" + m?.nomer;
            uiDetail.itemRod.text = m?.rod
            uiDetail.itemVid.text = m?.vid
            uiDetail.itemSort.text = m?.sort
        }
        return rootView
    }

    companion object {

        const val ARG_ITEM = "item"
    }
}