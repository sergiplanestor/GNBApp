package bemobile.splanes.com.gnbapp.feature.transaction.dagger

import androidx.lifecycle.ViewModel
import bemobile.splanes.com.gnbapp.commons.rest.RESTManager
import bemobile.splanes.com.gnbapp.commons.ui.viewmodelfactory.ViewModelKey
import bemobile.splanes.com.gnbapp.feature.transaction.datasource.TransactionDataSource
import bemobile.splanes.com.gnbapp.feature.transaction.service.TransactionService
import bemobile.splanes.com.gnbapp.feature.transaction.ui.viewmodel.TransactionViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class TransactionModule {

    @Provides
    @Singleton
    fun providesTransactionDataSource() : TransactionDataSource {
        return RESTManager.createService(TransactionDataSource::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    fun providesTransactionViewModel(service: TransactionService): ViewModel {
        return TransactionViewModel(service)
    }
}