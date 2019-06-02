package com.example.launcher.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SearchingCarpets extends Fragment {
    public static final String TITLE = "جستجو";
    private ImageView input_carpet, output1, output2, output3;
    private Button submit;
    private int GET_IMAGE_CODE_CARPET1 = 101;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searching_carpets, container, false);
        submit = view.findViewById(R.id.choosebtn);
        input_carpet = view.findViewById(R.id.incarpet1imageV);
        output1 = view.findViewById(R.id.outcarpet3imageV);
        output2 = view.findViewById(R.id.outcarpet4imageV);
        output3 = view.findViewById(R.id.outcarpet2imageV);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
                getSimilar();
            }
        });
        input_carpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
                getSimilar();
            }
        });
        return view;
    }

    private void getSimilar() {
        int arr[] = new int[bitmap.getWidth() + bitmap.getHeight()];
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                arr[i * bitmap.getWidth() + j] = bitmap.getPixel(i,j);
            }
        }
        getSimilarImages(arr);
    }

    private void getSimilarImages(int arr[]) {
        // TODO: In this method we're gonna find three most similar images to the input and showing them

    }

    private void getImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GET_IMAGE_CODE_CARPET1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_IMAGE_CODE_CARPET1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    input_carpet.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
