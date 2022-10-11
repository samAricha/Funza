package teka.mobile.funzav1.viewModelTier

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import teka.mobile.funzav1.modelTier.data.remote.api.RetroServiceInterface
import teka.mobile.funzav1.modelTier.data.remote.api.RetrofitInstance
import java.io.*
import kotlin.concurrent.thread

class DocsActivityViewModel(val fileDir: File):ViewModel(){

    private var pdfFileName:File
    private var dirPath: String
    private var fileName: String
    var isFileReadyObserver = MutableLiveData<Boolean>()

    init {
        dirPath = "${fileDir}/cert/pdffiles"
        val dirFile = File(dirPath)
        if(!dirFile.exists()){
            dirFile.mkdirs()
        }
        fileName = "DemoGraphs.pdf"
        val file = "${dirPath}/${fileName}"
        pdfFileName = File(file)
        if(pdfFileName.exists()){
            pdfFileName.delete()
        }
    }

    fun getPdfFileUri():File = pdfFileName

    fun downloadPdfFile(pdfUrl: String){
        thread {
            val retroServiceInterface = RetrofitInstance.getRetrofitInstance().create(RetroServiceInterface::class.java)
            retroServiceInterface.downloadPdfFile(pdfUrl).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if(response.isSuccessful){
                        val result = response.body()?.byteStream()
                        //check if the result is null or not
                        result?.let {
                            isFileReadyObserver.postValue(true)
                            writeToFile(it)
                        }?:kotlin.run {
                            isFileReadyObserver.postValue(false)
                        }

                    }else
                        isFileReadyObserver.postValue(false)
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    isFileReadyObserver.postValue(false)
                }
            })
        }
    }

    //Function writes byte stream to file
    private fun writeToFile(inputStream: InputStream) {
        try {
            val fileReader = ByteArray(4096)
            var fileSizeDownloaded = 0
            val fos:OutputStream = FileOutputStream(pdfFileName)
            do {
                val read = inputStream.read(fileReader)
                if(read != -1){
                    fos.write(fileReader,0,read)
                    fileSizeDownloaded += read
                }
            }while (read != -1)
            fos.flush()
            fos.close()
            isFileReadyObserver.postValue(true)
        }catch (e: IOException){
            isFileReadyObserver.postValue(true)
        }

    }
}