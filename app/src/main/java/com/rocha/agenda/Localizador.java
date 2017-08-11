package com.rocha.agenda;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.rocha.agenda.activity.MapaActivity;

/**
 * Created by wesley on 10/02/17.
 */
public class Localizador implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private final GoogleApiClient client;
    private final GoogleMap mapa;
    private final Context context;
    private final MapaActivity activity;
    private Location location;
    private CameraUpdate cameraUpdate;


    public Localizador(Context context, GoogleMap mapa, MapaActivity activity) {
        client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();

        client.connect();

        this.mapa = mapa;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest request = new LocationRequest();
        request.setSmallestDisplacement(20); //atualizar a cada 20 metros
        request.setInterval(5000); // a cada 5 segundos
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        LatLng coordenada = new LatLng(location.getLatitude(), location.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLng(coordenada);

        //marcador.position(coordenada);
        //marcador.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_smile));
        //mapa.addMarker(marcador);

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 888);
        }

        this.mapa.setMyLocationEnabled(true);
        this.mapa.moveCamera(cameraUpdate);
    }


    /*@Override
    public void onCameraChange(CameraPosition cameraPosition) {
        Toast.makeText(context, "zoom " + cameraPosition.zoom, Toast.LENGTH_SHORT).show();
    }*/

}