package bemobile.splanes.com.gnbapp.commons.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {

    @Provides
    fun providesViewModelFactory(map: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }
}