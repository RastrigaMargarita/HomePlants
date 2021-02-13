package margretcraft.homeplants.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ItemDetailBinding
import margretcraft.homeplants.model.Category
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.ui.getCategoryByInt
import margretcraft.homeplants.viewModel.DetailViewModel

class DetailFragment : Fragment() {

    lateinit var uidetail: ItemDetailBinding
    private var currentPlant: Plant? = Plant()
    val viewModelDetail: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    private lateinit var categoryArray: Array<String?>

    companion object {

        const val ARG_ITEM = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments?.containsKey(ARG_ITEM)!!) {
            currentPlant = arguments?.getParcelable(ARG_ITEM)
        }

        categoryArray = arrayOfNulls<String>(3)
        for (category in Category.values()) {
            categoryArray.set(category.ordinal, getString(category.catname))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        uidetail = ItemDetailBinding.inflate(inflater)
        val rootView = uidetail.root
        uidetail.fab.setOnClickListener {
            currentPlant?.rod = uidetail.itemRod.text.toString()
            currentPlant?.vid = uidetail.itemVid.text.toString()
            currentPlant?.sort = uidetail.itemSort.text.toString()
            currentPlant?.color = uidetail.itemColor.text.toString()
            currentPlant?.supplier = uidetail.itemSupplier.text.toString()
            currentPlant?.category = uidetail.itemCategory.selectedItemId.toInt().getCategoryByInt()
            currentPlant?.let {
                viewModelDetail.saveChanges(currentPlant!!)
                val imm: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0)
            }
        }

        var spinnerAdapter = ArrayAdapter<String>(
                activity!!.applicationContext, android.R.layout.simple_dropdown_item_1line, categoryArray)
        rootView.findViewById<Spinner>(R.id.item_category).adapter = spinnerAdapter

        if (currentPlant != null) {
            uidetail.itemRod.setText(currentPlant?.rod)
            uidetail.itemVid.setText(currentPlant?.vid)
            uidetail.itemSort.setText(currentPlant?.sort)
            uidetail.itemSupplier.setText(currentPlant?.supplier)
            uidetail.itemColor.setText(currentPlant?.color)
            currentPlant?.category?.ordinal?.let { uidetail.itemCategory.setSelection(it) }
        }
        return rootView
    }
}