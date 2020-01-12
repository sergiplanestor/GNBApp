package bemobile.splanes.com.gnbapp.commons.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object that provides method to create new services
 */
object RESTManager {

    /**
     * Method to create new service from given service class.
     * @param clazz service class.
     */
    fun <T> createService(clazz: Class<T>): T {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://quiet-stone-2094.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(clazz)
    }
}