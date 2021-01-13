package margretcraft.homeplants

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout

class ItemDetailFragment

    : Fragment() {

    private var mItem: ItemPlant? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments!!.containsKey(ARG_ITEM)) {

            mItem = arguments!!.getParcelable(ARG_ITEM)
            val activity: Activity? = this.activity
            val appBarLayout = activity!!.findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout

            appBarLayout.title = "" + mItem!!.nomer

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        if (mItem != null) {
            (rootView.findViewById<View>(R.id.item_detail) as TextView).text = mItem!!.rod
        }
        return rootView
    }

    companion object {

        const val ARG_ITEM = "item"
    }
}