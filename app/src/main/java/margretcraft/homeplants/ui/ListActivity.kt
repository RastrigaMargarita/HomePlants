package margretcraft.homeplants.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivityItemListBinding
import margretcraft.homeplants.model.Category
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.viewModel.ListViewModel

class ListActivity : AppCompatActivity() {

    private var mTwoPane = false
    private lateinit var ui: ActivityItemListBinding
    lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setSupportActionBar(ui.toolbar)
        ui.toolbar.title = title
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        if (ui.frameLayout.findViewById<View?>(R.id.item_detail_container) != null) {
            mTwoPane = true
        }
        adapter = ListAdapter(object : CustomItemClickListener {
            override fun onItemClick(view: View?, position: Int, plant: Plant) {
                setDetail(plant)
            }
        })
        ui.frameLayout.findViewById<FloatingActionButton>(R.id.fabadd).setOnClickListener(View.OnClickListener {

            setDetail(Plant(0, 0, Category.BLOOMING, "", "", "", "", "", ""))
        })
        ui.frameLayout.findViewById<RecyclerView>(R.id.recycler_item_list).adapter = adapter;
        viewModel.viewState().observe(this, Observer<ListViewState> { t -> t?.let { adapter.plants = t.plants } })
        if (mTwoPane) {
            if (adapter.plants.size > 0) {
                setDetail(adapter.plants[0].copy())
            }
        }
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
            startActivity(DetailActivity.getStartIntent(this, plant))
            this.finish()
        }
    }


}
