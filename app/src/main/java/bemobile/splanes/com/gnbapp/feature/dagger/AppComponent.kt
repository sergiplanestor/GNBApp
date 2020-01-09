package bemobile.splanes.com.gnbapp.feature.dagger

import bemobile.splanes.com.gnbapp.app.GNBApplication
import dagger.Component

@Component(modules = [
    AppModule::class
])
interface AppComponent {
    fun inject(gnbApplication: GNBApplication)
}