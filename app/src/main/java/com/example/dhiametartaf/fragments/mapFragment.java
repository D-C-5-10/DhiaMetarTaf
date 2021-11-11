package com.example.dhiametartaf.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.dhiametartaf.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class mapFragment extends Fragment {

    private double latitude;
    private double longitude;
    private MapView map;
    View mapFragmentView;


    public mapFragment(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public mapFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Configuration.getInstance().load(getActivity()
                , PreferenceManager.getDefaultSharedPreferences(getActivity()));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("MAP");
        mapFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        map = mapFragmentView.findViewById(R.id.map);

        map.setTileSource(TileSourceFactory.MAPNIK);//render
        map.setMultiTouchControls(true);
        GeoPoint startPoint = new GeoPoint(this.latitude, this.longitude);
        IMapController mapController = map.getController();
        mapController.setZoom(3.0);
        mapController.setCenter(startPoint);

        ArrayList<OverlayItem> items = new ArrayList<>();

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getActivity()
                , items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(this.latitude +" - "+ this.longitude);
        Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.placeholder, null);
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        Drawable dr = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int) (10.0f * getResources().getDisplayMetrics().density), (int) (10.0f * getResources().getDisplayMetrics().density), true));
        marker.setIcon(dr);
        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
        map.getOverlays().add(marker);
        return mapFragmentView;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
