package margretcraft.homeplants

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ItemListActivity : AppCompatActivity() {

    private var mTwoPane = false
    private var mockArray = ArrayList<ItemPlant>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = title
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        if (findViewById<View?>(R.id.item_detail_container) != null) {
            mTwoPane = true
        }
        val recyclerView = findViewById<View>(R.id.item_list)!!
        setupRecyclerView(recyclerView as RecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        mockArray.add(ItemPlant(22, 1, "Пеларгония", "Зональная", "Peppermint twist", "", "Leroy Merlin", "красный"))
        mockArray.add(ItemPlant(25, 2, "Пеларгония", "Остролистная", "Diabolo", "", "Leroy Merlin", "темно-синий"))
        mockArray.add(ItemPlant(30, 3, "Сенполия", "Midi махровый", "Ramona", "", "ЕК", "розовый"))

        recyclerView.adapter = ItemAdapter(mockArray, this);
    }

    fun setDetail(itemPlant: ItemPlant) {

        if (mTwoPane) {
            val arguments = Bundle()
            arguments.putParcelable(ItemDetailFragment.ARG_ITEM, itemPlant)
            val fragment = ItemDetailFragment()
            fragment.arguments = arguments
            this.supportFragmentManager.beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
        } else {

            val intent = Intent(this, ItemDetailActivity::class.java)

            intent.putExtra(ItemDetailFragment.ARG_ITEM, itemPlant)

            this.startActivity(intent)
        }
    }
}
