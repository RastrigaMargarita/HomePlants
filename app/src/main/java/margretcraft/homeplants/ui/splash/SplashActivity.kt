package margretcraft.homeplants.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import margretcraft.homeplants.R
import margretcraft.homeplants.databinding.ActivitySplashBinding
import margretcraft.homeplants.model.NoAuthException
import margretcraft.homeplants.ui.base.BaseActivity
import margretcraft.homeplants.ui.list.ListActivity
import margretcraft.homeplants.viewModel.SplashViewModel

private const val RC_IN = 42
private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    override val ui: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override val layoutRes: Int = R.layout.activity_splash
    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startListActivity()
        }
    }

    override fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLoginAcrivity()
            else -> error.message?.let { showError(it) }
        }
    }

    private fun startLoginAcrivity() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setLogo(R.drawable.violeticon)
                        .setTheme(R.style.Theme_HomePlants_SplashTheme)
                        .setAvailableProviders(listOf(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.GoogleBuilder().build()
                        ))
                        .build(),
                RC_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    private fun startListActivity() {
        startActivity(Intent(this, ListActivity::class.java))
    }
}