package bemobile.splanes.com.gnbapp.commons.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bemobile.splanes.com.gnbapp.R
import bemobile.splanes.com.gnbapp.commons.rest.RestCallback
import bemobile.splanes.com.gnbapp.commons.ui.component.AppLoader
import bemobile.splanes.com.gnbapp.commons.ui.dialog.PopUp
import bemobile.splanes.com.gnbapp.commons.ui.dialog.PopUpModel
import kotlinx.android.synthetic.main.include_loader.*
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), RestCallback {

// =================================================================================================
// Views
// =================================================================================================

    private var mLoader : AppLoader? = null

// =================================================================================================
// Attributes
// =================================================================================================

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var mViewModel : VM

    private var isFirstOnResume = true

// =================================================================================================
// Lifecycle
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDaggerComponent()
        setContentView(getLayoutResource())
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstOnResume) {
            loadData()
            isFirstOnResume = false
        } else {
            updateViews()
        }
    }

// =================================================================================================
// ViewModel and Binding views
// =================================================================================================

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[getViewModelClass()]
    }

    open fun initViews() {
        mLoader = appLoader
    }

// =================================================================================================
// Load data
// =================================================================================================

    open fun loadData() {
        showLoader()
        // Nothing to do here. Override it on child in order to launch first time services
    }

    open fun showErrorPopUp() {
        PopUp(this, PopUpModel(
            title = getString(R.string.popup_error_title),
            body = getString(R.string.popup_error_body),
            positiveButton = getString(R.string.accept)
        )).show()
    }

// =================================================================================================
// Update views
// =================================================================================================

    open fun updateViews() {
        // Nothing to do here
    }

// =================================================================================================
// Launch activity
// =================================================================================================

    fun <T> launchActivity(clazz: Class<T>, bundle: Bundle? = null) {
        val intent = Intent(this, clazz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        overridePendingTransition(0, 0) // TODO !!!
        startActivity(intent)
    }

// =================================================================================================
// Loader
// =================================================================================================

    fun showLoader() {
        mLoader?.showLoader()
    }

    fun hideLoader() {
        mLoader?.hideLoader()
    }

// =================================================================================================
// RestCallback Implementation
// =================================================================================================

    override fun onSuccess() {

    }

    override fun onFailure() {

    }

// =================================================================================================
// Abstract methods
// =================================================================================================

    abstract fun getLayoutResource() : Int

    abstract fun getViewModelClass() : Class<VM>

    abstract fun injectDaggerComponent()
}