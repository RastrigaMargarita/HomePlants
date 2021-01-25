package margretcraft.homeplants.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivityItemDetailBinding
import margretcraft.homeplants.model.Plant

class DetailActivity : AppCompatActivity() {

    lateinit var ui: ActivityItemDetailBinding

    private var m: Plant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = ActivityItemDetailBinding.inflate(layoutInflater)

        setContentView(ui.root)

        setSupportActionBar(ui.detailToolbar)

        ui.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val intent = intent

        m = intent.getParcelableExtra(DetailFragment.ARG_ITEM)

        if (m != null) {
            ui.toolbarLayout.title = "" + m?.nomer;
            ui.itemDetailContainer.findViewById<TextView?>(R.id.item_rod).text = m?.rod
            ui.itemDetailContainer.findViewById<TextView?>(R.id.item_vid).text = m?.vid
            ui.itemDetailContainer.findViewById<TextView?>(R.id.item_sort).text = m?.sort
        }
    }
}