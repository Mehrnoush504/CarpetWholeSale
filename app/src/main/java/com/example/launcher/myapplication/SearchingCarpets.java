package com.example.launcher.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.launcher.myapplication.Database.CarpetDBManager;
import com.example.launcher.myapplication.Models.Carpet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SearchingCarpets extends Fragment {
    public static final String TITLE = "جستجو";
    private ImageView input_carpet, output1, output2, output3;
    private Button submit;
    private int GET_IMAGE_CODE_CARPET1 = 101;
    private CarpetDBManager carpetDBManager;
    private int result = 0;
    private int [] Arr;
    private int[][] bitmap_arr;
    private Bitmap bitmap;
    private int[] bitmap_array;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searching_carpets, container, false);
        submit = view.findViewById(R.id.choosebtn);
        input_carpet = view.findViewById(R.id.incarpet1imageV);
        carpetDBManager = new CarpetDBManager(getContext());
        output1 = view.findViewById(R.id.outcarpet3imageV);
        output2 = view.findViewById(R.id.outcarpet4imageV);
        output3 = view.findViewById(R.id.outcarpet2imageV);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        input_carpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        return view;
    }

    private void getSimilar() {
        int arr[] = new int[bitmap.getWidth() * bitmap.getHeight()];
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                arr[i * bitmap.getHeight() + j] = bitmap.getPixel(i, j);
            }
        }
        getSimilarImages(arr);
    }

    private void getSimilarImages(int arr[]) {
        carpetDBManager.open();
        ArrayList<Carpet> carpets = carpetDBManager.getALLCarpets();
        Bitmap bitmap1 = null, bitmap2 = null, bitmap3 = null;
        long most_likeness = 99999999;
        for (int i = 0; i < carpets.size(); i++) {
            Carpet carpet = carpets.get(i);
            Uri uri = Uri.fromFile(new File(carpet.getPath()));
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                bitmap_arr = convert_to_arr(bitmap);
                long likeness = compareImages(arr, bitmap_arr);
                if (likeness < most_likeness) {
                    bitmap3 = bitmap2;
                    bitmap2 = bitmap1;
                    bitmap1 = bitmap;
                    most_likeness = likeness;
                }
                Log.i("TAG", String.valueOf(likeness));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        output1.setImageBitmap(bitmap1);
        output2.setImageBitmap(bitmap2);
        output3.setImageBitmap(bitmap3);
        carpetDBManager.close();
    }

    private int compareImages(int[] arr, int[][] bitmap_2darr) {
        int bitmap_arr[] = new int[bitmap_2darr.length * bitmap_2darr.length];
        for (int i = 0; i < bitmap_2darr.length; i++) {
            for (int j = 0; j < bitmap_2darr.length; j++) {
                bitmap_arr[i * bitmap_2darr.length + j] = bitmap_2darr[i][j];
            }
        }
        Log.i("MYTAG",arr.length + ",bitma_arr length: " + bitmap_arr.length);
        Arr = arr;
        bitmap_array = bitmap_arr;
        new Calculator_Helper().execute();
        return result;
    }

    class Calculator_Helper extends AsyncTask<Void,Void,Void>{
        private int Opt(int[] arr, int[] bitmap_arr, int i, int j) {
            if (i == arr.length) {
                return 2 * (bitmap_arr.length - j);
            } else if (j == bitmap_arr.length) {
                return 2 * (arr.length - i);
            } else {
                int pen = 3;
                if (arr[i] == bitmap_arr[j]) {
                    pen = 0;
                }
                Log.i("MYTAG", "):");
                return Math.min(Opt(arr, bitmap_arr, i + 1, j) + 2, Math.min(Opt(arr, bitmap_arr,
                        i, j + 1) + 2, Opt(arr, bitmap_arr, i + 1, j + 1) + pen));
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            result = Opt(Arr,bitmap_array,0,0);
            return null;
        }
    }

    private void getImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GET_IMAGE_CODE_CARPET1);
    }


    private int[][] convert_to_arr(Bitmap carpet_bitmap) {
        int arr[][] = new int[carpet_bitmap.getHeight()][carpet_bitmap.getWidth()];
        for (int i = 0; i < carpet_bitmap.getHeight(); i++) {
            for (int j = 0; j < carpet_bitmap.getWidth(); j++) {
                arr[i][j] = carpet_bitmap.getPixel(j, i);
            }
        }
        return arr;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_IMAGE_CODE_CARPET1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    input_carpet.setImageBitmap(bitmap);
                    if (bitmap != null)
                        getSimilar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
