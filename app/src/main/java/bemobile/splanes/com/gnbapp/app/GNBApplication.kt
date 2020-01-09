package bemobile.splanes.com.gnbapp.app

import android.app.Application
import bemobile.splanes.com.gnbapp.app.dagger.AppComponent
import bemobile.splanes.com.gnbapp.app.dagger.AppModule
import bemobile.splanes.com.gnbapp.app.dagger.DaggerAppComponent

class GNBApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(
            AppModule(
                this
            )
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}