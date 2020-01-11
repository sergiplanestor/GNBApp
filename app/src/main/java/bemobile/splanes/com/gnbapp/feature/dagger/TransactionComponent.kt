package bemobile.splanes.com.gnbapp.feature.dagger

import bemobile.splanes.com.gnbapp.feature.ui.MainActivity
import bemobile.splanes.com.gnbapp.feature.ui.TransactionActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [TransactionModule::class])
interface TransactionComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: TransactionActivity)
}