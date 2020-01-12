package bemobile.splanes.com.gnbapp.app.dagger

import bemobile.splanes.com.gnbapp.app.GNBApplication
import bemobile.splanes.com.gnbapp.commons.ui.viewmodelfactory.ViewModelFactoryModule
import bemobile.splanes.com.gnbapp.feature.transaction.dagger.TransactionModule
import bemobile.splanes.com.gnbapp.feature.transaction.dagger.TransactionComponent
import dagger.Component

@Component(modules = [AppModule::class, ViewModelFactoryModule::class])
interface AppComponent {

    fun inject(gnbApplication: GNBApplication)

    fun plus(transactionModule: TransactionModule): TransactionComponent
}