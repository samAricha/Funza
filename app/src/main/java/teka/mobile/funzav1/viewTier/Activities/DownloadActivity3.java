package teka.mobile.funzav1.viewTier.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import teka.mobile.funzav1.R;
import teka.mobile.funzav1.viewTier.Fragments.PdfListFragment;
import teka.mobile.funzav1.viewTier.Fragments.PdfReaderFragment;

public class DownloadActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download3);

        if (ActivityCompat.checkSelfPermission(
                DownloadActivity3.this,
                Manifest.permission
                        .READ_EXTERNAL_STORAGE)
                != PackageManager
                .PERMISSION_GRANTED) {
            // When permission is not granted Request permission
            ActivityCompat.requestPermissions(
                    DownloadActivity3.this,
                    new String[] {
                            Manifest.permission
                                    .READ_EXTERNAL_STORAGE },
                    1);
        }else {
            // When permission is granted
            // call the fragments

            PdfReaderFragment pdfReaderFragment = new PdfReaderFragment(getApplicationContext());
            FragmentManager fm = getSupportFragmentManager();
            //fm.beginTransaction().replace(R.id.download_activity_frame, new PdfReaderFragment()).commit();
            fm.beginTransaction().replace(R.id.download_activity_frame, pdfReaderFragment).commit();
            //openPDF();
            //startListFragment();
        }

    }

    private void startListFragment(){
        String dirPath = getFilesDir()+"/cert/pdffiles";
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }

        File[] pdfList = dirFile.listFiles();//this is an array of pdf file in the dirPath
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.download_activity_frame, new PdfListFragment(pdfList)).commit();
    }



    private void openPDF(){
        String dirPath = getFilesDir()+"/cert/pdffiles";
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        String fileName = "DemoGraphs.pdf";
        String filePath = dirPath+"/"+fileName;


        File pdfFile = new File(filePath);

        String path = dirPath;
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Toast.makeText(getApplicationContext(), files[i].getName(), Toast.LENGTH_LONG).show();
            Log.d("Files", "FileName:" + files[i].getName());
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);

        // check condition
        if (requestCode == 1 && grantResults.length > 0
                && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {

            // When permission is granted
            // call the fragments
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.download_activity_frame, new PdfReaderFragment()).commit();
            //openPDF();
        }
        else {
            // When permission is denied
            // Display toast
            Toast.makeText(getApplicationContext(),
                            "Permission Denied",
                            Toast.LENGTH_SHORT).show();
        }
    }
}