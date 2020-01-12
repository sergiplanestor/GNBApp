package bemobile.splanes.com.gnbapp.commons.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * ViewModel Factory to can inject objects using Dagger in ViewModels
 */
@Singleton
class ViewModelFactory
    @Inject
    constructor(private val viewModels: MutableMap<Class<out ViewModel>,
                Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}