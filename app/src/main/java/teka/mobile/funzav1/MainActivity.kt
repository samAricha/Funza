package teka.mobile.funzav1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.barteksc.pdfviewer.PDFView
import teka.mobile.funzav1.viewModelTier.DocsActivityViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var shareButton: Button
    lateinit var progressBar: ProgressBar
    lateinit var pdfView: PDFView
    lateinit var docsViewModel: DocsActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shareButton = findViewById(R.id.shareButton)
        progressBar = findViewById(R.id.progressBar)
        pdfView = findViewById(R.id.pdfView)

        initViewModel()
        shareButton.setOnClickListener {

        }
    }

    private fun initViewModel() {
        docsViewModel = ViewModelProvider(this, object :ViewModelProvider.NewInstanceFactory(){
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DocsActivityViewModel(fileDir = filesDir) as T
             }
        }).get(DocsActivityViewModel::class.java)

        docsViewModel.isFileReadyObserver.observe(this, Observer<Boolean>{
            progressBar.visibility = View.GONE

            if (!it){
                Toast.makeText(this@MainActivity, "Failed to downlaod PDF", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity, "PDF downloaded successfully", Toast.LENGTH_LONG).show()

                try {
                    pdfView.fromUri(FileProvider.getUriForFile(applicationContext,
                        "teka.mobile.funzav1.fileprovider",
                        docsViewModel.getPdfFileUri()))
                        .load()
                }catch (e: Exception){
                    Toast.makeText(this@MainActivity, "Failed to downlaod PDF", Toast.LENGTH_LONG).show()
                }
            }
        })
        docsViewModel.downloadPdfFile("https://firebasestorage.googleapis.com/v0/b/fashionatyourstep-a8df7.appspot.com/o/Pie_Charts.pdf?alt=media&token=4b50a8ad-80c7-48d6-a681-3ee66d0e8018")
    }
}