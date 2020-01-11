package bemobile.splanes.com.gnbapp.commons.rest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RestCallbackAdapter<T>(private val delegate: RestCallback) : Callback<T> {


    override fun onFailure(call: Call<T>, t: Throwable) {

    }


    override fun onResponse(call: Call<T>, response: Response<T>) {

    }
}