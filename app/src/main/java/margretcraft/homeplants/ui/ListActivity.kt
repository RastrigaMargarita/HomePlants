package margretcraft.homeplants.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivityItemListBinding
import margretcraft.homeplants.model.Plant

import margretcraft.homeplants.viewModel.ListViewModel

class ListActivity : AppCompatActivity() {

    private var mTwoPane = false
    lateinit var ui: ActivityItemListBinding

    lateinit var viewModel: ListViewModel
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityItemListBinding.inflate(layoutInflater)

        setContentView(ui.root)

        setSupportActionBar(ui.toolbar)
        ui.toolbar.title = title

        ui.fabadd.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        ui.fabadd.setImageResource(R.drawable.violetplusbutton)
        if (ui.frameLayout.findViewById<View?>(R.id.item_detail_container) != null) {
            mTwoPane = true
        }

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        adapter = ListAdapter(object : ListAdapter.CustomItemClickListener {
            override fun onItemClick(view: View?, position: Int, plant: Plant) {
                setDetail(plant)

            }

        })

        ui.frameLayout.findViewById<RecyclerView>(R.id.recycler_item_list).adapter = adapter;

        viewModel.viewState().observe(this, Observer<ListViewState> { t -> t?.let { adapter.plants = t.plants } })

    }

    fun setDetail(plant: Plant) {

        if (mTwoPane) {
            val arguments = Bundle()
            arguments.putParcelable(DetailFragment.ARG_ITEM, plant)
            val fragment = DetailFragment()
            fragment.arguments = arguments
            this.supportFragmentManager.beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
        } else {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra(DetailFragment.ARG_ITEM, plant)

            this.startActivity(intent)
        }
    }
}
