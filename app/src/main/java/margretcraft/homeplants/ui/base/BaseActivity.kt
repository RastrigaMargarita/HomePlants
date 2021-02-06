package margretcraft.homeplants.ui.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import margretcraft.homeplants.ui.list.ListActivity
import margretcraft.homeplants.viewModel.BaseViewModel

abstract class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int
    abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)
        viewModel.getViewState().observe(this, Observer<VS> { t ->
            t?.apply {
                data?.let { renderData(it) }
                error?.let { renderError(it) }
            }
        })
    }

    protected open fun renderError(error: Throwable) {
        error.message?.let { showError(error.message!!) }
    }

    protected fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    abstract fun renderData(data: T)


}