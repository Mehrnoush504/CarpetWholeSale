package com.example.launcher.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

public class Navigation extends Fragment implements OnMapReadyCallback {

    public static CharSequence TITLE = "مسیریابی";
    private GoogleMap mMap;
    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation,container,false);
//        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
//        getChildFragmentManager().beginTransaction().replace(R.id.buyer_viewPager, mapFragment).commit();
//        mapFragment.getMapAsync(this);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        mapView.onResume();
        preProcess();
        return view;
    }

    private void preProcess() {
        // TODO: Complete this method in order to allocate markets and make the graph to using algorithms

    }

    private void getBestWay(double latitude, double longitude) {
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
    }
}
