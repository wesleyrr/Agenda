package com.rocha.agenda.fragment;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.*;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rocha.agenda.Localizador;
import com.rocha.agenda.R;
import com.rocha.agenda.activity.DetalhesAlunoActivity;
import com.rocha.agenda.activity.MapaActivity;
import com.rocha.agenda.adapter.JanelaInfoAdapter;
import com.rocha.agenda.dao.AlunoDAO;
import com.rocha.agenda.modelo.Aluno;

import java.io.IOException;
import java.util.List;

/**
 * Created by wesley on 09/02/17.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback
        , OnInfoWindowClickListener
        , OnCameraMoveStartedListener {

    private MarkerOptions marcador;
    private int estiloMapa = 1;

    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && !args.isEmpty()) {
            estiloMapa = args.getInt("tipo");
        }

        getMapAsync(this); //método assíncrono que retorna um mapa para o método onMapReady quando terminar
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        this.googleMap.setOnCameraMoveStartedListener(this);

        //definindo um estilo de mapa diferente - dia ou noite
        if (estiloMapa == 1) {
            this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_json));
        } else {
            this.googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_json_dark));
        }

        LatLng posicaoInicial = pegaCoordenadaDoEndereco("Avenida Bartolomeu Mitre, 310 Leblon RJ");
        if (posicaoInicial != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoInicial, 13);
            this.googleMap.moveCamera(update);
        }

        AlunoDAO alunoDAO = new AlunoDAO(getContext());
        for (Aluno aluno : alunoDAO.buscaAlunos()) {
            LatLng coordenada = pegaCoordenadaDoEndereco(aluno.getEndereco());
            if (coordenada != null) {
                marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(aluno.getNome());
                marcador.snippet(String.valueOf(aluno.getNota()));
                if (aluno.getNota() < 3) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (aluno.getNota() < 5) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                } else if (aluno.getNota() < 8) {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                } else {
                    marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                }
                //marcador.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_smile));
                this.googleMap.addMarker(marcador).setTag(aluno);

            }
        }

        //InfoWindow
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        this.googleMap.setInfoWindowAdapter(new JanelaInfoAdapter(inflater));

        this.googleMap.setOnInfoWindowClickListener(this);

        alunoDAO.close();

        new Localizador(getContext(), this.googleMap, (MapaActivity) getActivity());
    }


    private LatLng pegaCoordenadaDoEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco, 1);
            if (!resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Aluno aluno = (Aluno) marker.getTag();

        Intent vaiParaDetalhesAluno = new Intent(getContext(), DetalhesAlunoActivity.class);
        vaiParaDetalhesAluno.putExtra("aluno", aluno);
        startActivity(vaiParaDetalhesAluno);

    }

    @Override
    public void onCameraMoveStarted(int reason) {

        if (reason == OnCameraMoveStartedListener.REASON_GESTURE) {
            Toast.makeText(getContext(), "O usuário usou gesto no mapa.", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(getContext(),"zoom: " + googleMap.getCameraPosition().zoom, Toast.LENGTH_SHORT).show();

        Toast.makeText(getContext(),"latlng: " + googleMap.getProjection().getVisibleRegion(), Toast.LENGTH_LONG).show();

    }

    private LatLngBounds getLatLngBounds(){

        googleMap.getProjection().getVisibleRegion();

        return null;
    }

}
