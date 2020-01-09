package bemobile.splanes.com.gnbapp.app.dagger

import bemobile.splanes.com.gnbapp.app.GNBApplication
import bemobile.splanes.com.gnbapp.feature.dagger.TransactionModule
import bemobile.splanes.com.gnbapp.feature.dagger.TransactionComponent
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(gnbApplication: GNBApplication)

    fun plus(transactionModule: TransactionModule): TransactionComponent
}