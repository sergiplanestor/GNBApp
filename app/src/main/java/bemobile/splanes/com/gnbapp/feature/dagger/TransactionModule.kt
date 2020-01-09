package bemobile.splanes.com.gnbapp.feature.dagger

import bemobile.splanes.com.gnbapp.commons.RESTManager
import bemobile.splanes.com.gnbapp.feature.datasource.GNBDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TransactionModule {

    @Provides
    @Singleton
    fun providesGNBDataSource() : GNBDataSource {
        return RESTManager.createService(GNBDataSource::class.java)
    }

    /*@Provides
    @Singleton
    fun providesGNBService(gnbDataSource: GNBDataSource) : GNBService {
        return GNBService(gnbDataSource)
    }
    */
}