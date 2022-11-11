package teka.mobile.funzav1.viewTier.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import teka.mobile.funzav1.R;
import teka.mobile.funzav1.modelTier.models.PdfModel;
import teka.mobile.funzav1.viewTier.Adapters.DownloadsAdapter;

/*
* FIRST THIS LIST OF PDF FILES IS GOING TO BE ADDED INTO A LIST OF PDF MODEL ONE BY ONE USING ARRAY LOOP.
* */

public class PdfListFragment extends Fragment {

    List<File> pdfList = new ArrayList<>();
    List<PdfModel> pdfModelList = new ArrayList<>();
    Context ctx;

    public PdfListFragment() {
        // Required empty public constructor
    }

    public PdfListFragment(File[] pdfArray){
        Collections.addAll(pdfList, pdfArray);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_pdf_list, container, false);

        displayList();
        return rootView;
    }

    private void displayList() {
        Toast.makeText(ctx, pdfList.get(0).getName(), Toast.LENGTH_LONG).show();


        /*
        * here is the complete implementation of adding the first list
        * to the second list that is a list of pdf Models. i.e pdfList<pdfModel>
        * */

        DownloadsAdapter downloadsAdapter = new DownloadsAdapter();
        //downloadsAdapter.setDownloadList(pdfList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;

    }
}