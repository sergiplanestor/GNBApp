package bemobile.splanes.com.gnbapp.feature.dagger

import bemobile.splanes.com.gnbapp.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [TransactionModule::class])
interface TransactionComponent {
    fun inject(activity: MainActivity)
}