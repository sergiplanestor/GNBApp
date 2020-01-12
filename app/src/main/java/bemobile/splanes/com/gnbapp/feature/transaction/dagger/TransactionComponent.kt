package bemobile.splanes.com.gnbapp.feature.transaction.dagger

import bemobile.splanes.com.gnbapp.feature.transaction.ui.view.MainActivity
import bemobile.splanes.com.gnbapp.feature.transaction.ui.view.TransactionActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [TransactionModule::class])
interface TransactionComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: TransactionActivity)
}