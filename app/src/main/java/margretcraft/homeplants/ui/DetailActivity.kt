package margretcraft.homeplants.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivityItemDetailBinding
import margretcraft.homeplants.databinding.ItemDetailBinding
import margretcraft.homeplants.model.Category
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.viewModel.DetailViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    lateinit var ui: ActivityItemDetailBinding
    lateinit var uidetail: ItemDetailBinding
    private var currentPlant: Plant? = null
    private lateinit var viewModel: DetailViewModel
    private lateinit var categoryArray: Array<String?>

    companion object {
        const val EXTRA_PLANT = "DetailActivity.extra.PLANT"
        fun getStartIntent(context: Context, plant: Plant): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_PLANT, plant)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityItemDetailBinding.inflate(layoutInflater)
        uidetail = ItemDetailBinding.bind(ui.frameLayout.findViewById(R.id.item_detail))
        setContentView(ui.root)
        setSupportActionBar(ui.detailToolbar)
        categoryArray = arrayOfNulls<String>(3)
        for (category in Category.values()) {
            categoryArray.set(category.ordinal, getString(category.catname))
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        ui.itemDetailContainer.findViewById<FloatingActionButton?>(R.id.fab).setOnClickListener {
            currentPlant?.rod = uidetail.itemRod.text.toString()
            currentPlant?.vid = uidetail.itemVid.text.toString()
            currentPlant?.sort = uidetail.itemSort.text.toString()
            currentPlant?.color = uidetail.itemColor.text.toString()
            currentPlant?.supplier = uidetail.itemSupplier.text.toString()
            currentPlant?.category = Category.getCategoryByInt(uidetail.itemCategory.selectedItemId.toInt())
            currentPlant?.let { viewModel.saveChanges(currentPlant!!) }

            this.startActivity(Intent(this, ListActivity::class.java))
            this.finish()
        }
        currentPlant = intent.getParcelableExtra(EXTRA_PLANT)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (currentPlant != null) {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(currentPlant!!.lastChanged)
        } else {
            getString(R.string.new_plant_title)
        }
        var spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categoryArray)
        uidetail.itemCategory.adapter = spinnerAdapter
        if (currentPlant != null) {
            ui.toolbarLayout.title = "" + currentPlant?.nomer
            uidetail.itemRod.setText(currentPlant?.rod)
            uidetail.itemVid.setText(currentPlant?.vid)
            uidetail.itemSort.setText(currentPlant?.sort)
            currentPlant?.category?.ordinal?.let { uidetail.itemCategory.setSelection(it) }
            uidetail.itemSupplier.setText(currentPlant?.supplier)
            uidetail.itemColor.setText(currentPlant?.color)
        }
    }
}