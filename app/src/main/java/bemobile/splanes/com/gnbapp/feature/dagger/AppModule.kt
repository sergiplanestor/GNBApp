package bemobile.splanes.com.gnbapp.feature.dagger

import bemobile.splanes.com.gnbapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}