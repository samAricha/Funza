package teka.mobile.funzav1.viewTier.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import teka.mobile.funzav1.R;
import teka.mobile.funzav1.viewModelTier.DocsActivityViewModel;

/*
* changing the NAME ON THE ACTION BAR WHEN OPENING THE FRAGMENT CONTAINING THE FRAGMENT.
* */

public class PdfReaderFragment extends Fragment {

    PDFView pdfView;
    Context ctx;

    public PdfReaderFragment() {
        // Required empty public constructor
    }

    public PdfReaderFragment(Context ctx) {
        // Required empty public constructor
        this.ctx = ctx;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_pdf_reader, container, false);
        pdfView = rootView.findViewById(R.id.pdf_viewer);
        openPDF(ctx);
        return rootView;
    }

    private void openPDF(Context context){
        String dirPath = context.getFilesDir()+"/cert/pdffiles";
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
        //File[] files = directory.listFiles();
        //Log.d("Files", "Size: "+ files.length);
        pdfView.fromUri(
            FileProvider.getUriForFile(ctx ,
                    "teka.mobile.funzav1.fileprovider",
                   pdfFile))
            .load();


        /*for (int i = 0; i < files.length; i++)
        {
            Toast.makeText(ctx, files[i].getName(), Toast.LENGTH_LONG).show();
            Log.d("Files", "FileName:" + files[i].getName());
        }*/
    }
}