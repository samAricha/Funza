package teka.mobile.funzav1.viewModelTier

import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import teka.mobile.funzav1.modelTier.data.remote.api.RetroServiceInterface
import teka.mobile.funzav1.modelTier.data.remote.api.RetrofitInstance

class NotesActivityViewModel: ViewModel() {

    init {

    }

    fun getNotes(){
        val retroServiceInterface = RetrofitInstance.getRetrofitInstance()
            .create(RetroServiceInterface::class.java)

        retroServiceInterface.getNotes().enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

}