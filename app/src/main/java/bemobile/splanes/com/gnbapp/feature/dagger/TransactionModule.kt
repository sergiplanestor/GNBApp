package bemobile.splanes.com.gnbapp.feature.dagger

import androidx.lifecycle.ViewModel
import bemobile.splanes.com.gnbapp.commons.rest.RESTManager
import bemobile.splanes.com.gnbapp.commons.ui.viewmodelfactory.ViewModelKey
import bemobile.splanes.com.gnbapp.feature.datasource.GNBDataSource
import bemobile.splanes.com.gnbapp.feature.service.GNBService
import bemobile.splanes.com.gnbapp.feature.viewmodel.TransactionViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class TransactionModule {

    @Provides
    @Singleton
    fun providesGNBDataSource() : GNBDataSource {
        return RESTManager.createService(GNBDataSource::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    fun providesTransactionViewModel(service: GNBService): ViewModel {
        return TransactionViewModel(service)
    }
}