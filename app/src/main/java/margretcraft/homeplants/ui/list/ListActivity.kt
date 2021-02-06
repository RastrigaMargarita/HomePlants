package margretcraft.homeplants.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivityItemListBinding
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.ui.base.BaseActivity
import margretcraft.homeplants.ui.detail.DetailActivity
import margretcraft.homeplants.ui.detail.DetailFragment
import margretcraft.homeplants.viewModel.ListViewModel

class ListActivity : BaseActivity<List<Plant>?, ListViewState>() {

    private var mTwoPane = false
    override val ui: ActivityItemListBinding by lazy { ActivityItemListBinding.inflate(layoutInflater) }
    override val layoutRes: Int = R.layout.activity_item_list

    override val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java) }
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(ui.toolbar)
        ui.toolbar.title = title

        if (ui.frameLayout.findViewById<View?>(R.id.item_detail_container) != null) {
            mTwoPane = true
        }
        adapter = ListAdapter(object : CustomItemClickListener {
            override fun onItemClick(view: View?, position: Int, plant: Plant) {
                setDetail(plant)
            }
        })
        ui.frameLayout.findViewById<RecyclerView>(R.id.recycler_item_list).adapter = adapter;
        ui.frameLayout.findViewById<FloatingActionButton>(R.id.fabadd)
                .setOnClickListener(View.OnClickListener {

            setDetail(Plant())
        })

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
            startActivity(DetailActivity.getStartIntent(this, plant.id))
            this.finish()
        }
    }

    override fun renderData(data: List<Plant>?) {
        if (data == null) return
        adapter.plants = data
    }


}
