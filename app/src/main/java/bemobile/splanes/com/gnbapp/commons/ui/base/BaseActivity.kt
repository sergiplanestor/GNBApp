package bemobile.splanes.com.gnbapp.commons.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bemobile.splanes.com.gnbapp.commons.rest.RestCallback
import bemobile.splanes.com.gnbapp.commons.ui.component.AppLoader
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
    private var firstLoadData = true

// =================================================================================================
// Lifecycle
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDaggerComponent()
        initViewModel()
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        if (firstLoadData) {
            loadData()
            firstLoadData = false;
        } else {
            reloadData()
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

// =================================================================================================
// ViewModel and Binding views
// =================================================================================================

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[getViewModel()]
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

    open fun reloadData() {
        showLoader()

        // Nothing to do here. Override it on child in order to launch second & more times services
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

    abstract fun getViewModel() : Class<VM>

    //abstract fun getViewModelFactory() : ViewModelProvider.Factory

    abstract fun injectDaggerComponent()
}