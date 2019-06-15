package com.example.launcher.myapplication;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;

public class Navigation extends Fragment implements OnMapReadyCallback {

    public static CharSequence TITLE = "مسیریابی";
    private GoogleMap mMap;
    private MapView mapView;
    int graph_size = 93, matrix[][];
    MapPoint[] mapPoints = new MapPoint[graph_size];
    LinkedList<Integer> adjListArray[];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mapView.onResume();
        return view;
    }

    private void preProcess() {
        // TODO: Complete this method in order to allocate markets and make the graph to using algorithms
        matrix = new int[graph_size][graph_size];
        adjListArray = new LinkedList[graph_size];
        for (int i = 0; i < graph_size; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        readFile();
    }

    private void readFile() {
        File file = new File("assets/coordinates.txt");
        int i = 0;
        try {
            InputStream inputStream = getContext().getAssets().open(file.getName());
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            while ((ch = inputStream.read()) != -1) {
                stringBuilder.append((char) ch);
                if ((char) ch == '\n') {
                    Log.e("TAG", i + ", " + stringBuilder.toString());
                    String line[] = stringBuilder.toString().trim().split(",");
                    boolean isBranch = line[0].trim().length() > 2;
                    String name = line[1].trim();
                    Double latitude = Double.valueOf(line[2].trim());
                    Double longitude = Double.valueOf(line[3].trim());
                    MapPoint mapPoint = new MapPoint(i, new LatLng(latitude, longitude), isBranch);
                    if (name.length() > 2) {
                        mapPoint.name = name;
                    }
                    mapPoints[i] = mapPoint;
                    String arr = stringBuilder.toString().trim().replaceAll(".*\\[", "");
                    arr = arr.replaceAll("].*", "");
                    String[] neighbours = arr.split(",");
                    for (int j = 0; j < neighbours.length; j++) {
                        matrix[i][Integer.parseInt(neighbours[j])] = 1;
                        matrix[Integer.parseInt(neighbours[j])][i] = 1;
                        adjListArray[i].add(Integer.valueOf(neighbours[j]));
                        adjListArray[Integer.parseInt(neighbours[j])].add(i);
                    }
                    Log.e("O_O", arr);
                    stringBuilder.setLength(0);
                    i++;
                }
            }
            inputStream.close();
        } catch (Exception e) {
            Log.e("TAG", String.valueOf(i));
            e.printStackTrace();
        }

        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[0].length; k++) {
                if (matrix[j][k] == 1) {
                    mMap.addPolyline(new PolylineOptions().add(new LatLng
                                    (mapPoints[j].coordinates.latitude, mapPoints[j].coordinates.longitude),
                            new LatLng(mapPoints[k].coordinates.latitude, mapPoints[k].coordinates.longitude))
                            .width(5)
                            .color(Color.BLUE));
                    if (mapPoints[k].name != null) {
                        if (mapPoints[j].name != null) {
                            Log.e("NAME", "j:" + j + " " + mapPoints[j].name);
                        }
                        Log.e("NAME", "j:" + j + " " + ",k:" + k + " " + mapPoints[k].name);
                    }
                }
            }
        }
    }

    private void getBestWay(double latitude, double longitude) {
        //TODO:Add the buyer point to the graph and find the nearest node to it.
        //TODO:Edges must be weighted,according to first-last distances

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        // Add a marker in Isfahan and move the camera
        LatLng isfahan = new LatLng(32.65246, 51.67462);
        mMap.addMarker(new MarkerOptions().position(isfahan).title("Marker in Isfahan"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(isfahan, 15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("مبدا");
                markerOptions.draggable(true);
                mMap.clear();
                mMap.addMarker(markerOptions);
                getBestWay(markerOptions.getPosition().latitude, markerOptions.getPosition().longitude);
            }
        });
        if (this.mMap == null){
            Log.e("TAG","Null hastam");
        }
        preProcess();

    }

    class MapPoint {
        int index;
        LatLng coordinates;
        boolean isBranch = false;
        String name;

        MapPoint(int index, LatLng coordinates, boolean isBranch) {
            this.index = index;
            this.coordinates = coordinates;
            this.isBranch = isBranch;
        }
    }
}
