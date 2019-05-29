package com.example.launcher.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.launcher.myapplication.Basic.Strassen;

import java.io.IOException;
import java.nio.IntBuffer;

/**
 * @author Amir Muhammad
 */

public class ChangeCarpet extends Fragment {

    private static final int GET_IMAGE_CODE_CARPET1 = 200;
    private static final int GET_IMAGE_CODE_CARPET2 = 201;
    Button combine, choose_carpet, choose_filter;
    ImageView carpet, filter, product;
    Bitmap carpet_bitmap, filter_bitmap, product_bitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_carpet, container, false);
        combine = view.findViewById(R.id.combine);
        choose_carpet = view.findViewById(R.id.choosebtn);
        choose_filter = view.findViewById(R.id.choose2btn);
        carpet = view.findViewById(R.id.carpet1image);
        filter = view.findViewById(R.id.carpet2image);
        product = view.findViewById(R.id.carpet3image);
        choose_carpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage(1);
            }
        });
        choose_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage(2);
            }
        });
        combine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProduct();
            }
        });
        return view;
    }

    private void getImage(int i) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (i == 1) {
            startActivityForResult(intent, GET_IMAGE_CODE_CARPET1);
        } else if (i == 2) {
            startActivityForResult(intent, GET_IMAGE_CODE_CARPET2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_IMAGE_CODE_CARPET1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    carpet_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    carpet.setImageBitmap(carpet_bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == GET_IMAGE_CODE_CARPET2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    filter_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    filter.setImageBitmap(filter_bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setProduct() {
        int arr1[][] = convert_to_arr(carpet_bitmap);
        int arr2[][] = convert_to_arr(filter_bitmap);
        Strassen strassen = new Strassen();
        int product_arr[] = strassen.main(arr1, arr2);
        product_bitmap = convert_to_bitmap(product_arr);
        product.setImageBitmap(product_bitmap);
    }

    private Bitmap convert_to_bitmap(int[] product_arr) {
        Bitmap bitmap = Bitmap.createBitmap(product_arr.length, product_arr.length, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(IntBuffer.wrap(product_arr));
        return bitmap;
    }

    private int[][] convert_to_arr(Bitmap carpet_bitmap) {
        int arr[][] = new int[carpet_bitmap.getHeight()][carpet_bitmap.getWidth()];
        for (int i = 0; i < carpet_bitmap.getHeight(); i++) {
            for (int j = 0; j < carpet_bitmap.getWidth(); j++) {
                arr[i][j] = carpet_bitmap.getPixel(i, j);
            }
        }
        return arr;
    }
}
