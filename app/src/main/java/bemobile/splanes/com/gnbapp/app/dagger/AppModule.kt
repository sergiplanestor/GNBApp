package bemobile.splanes.com.gnbapp.app.dagger

import bemobile.splanes.com.gnbapp.app.GNBApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: GNBApplication) {

    @Provides
    @Singleton
    fun providesApplication() = mApplication
}