package com.rocha.agenda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.rocha.agenda.R;
import com.rocha.agenda.modelo.Aluno;

/**
 * Created by wesley on 15/02/17.
 */

public class JanelaInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater layoutInflater;

    public JanelaInfoAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        Aluno aluno = (Aluno) marker.getTag();

        View popup = layoutInflater.inflate(R.layout.popup, null);

        TextView tv = (TextView) popup.findViewById(R.id.popup_titulo);
        tv.setText(marker.getTitle());

        tv = (TextView) popup.findViewById(R.id.popup_snippet);
        tv.setText(marker.getSnippet());


        tv = (TextView) popup.findViewById(R.id.popup_endereco);
        tv.setText(aluno.getEndereco());

        String nota = marker.getSnippet().replace(".0","");

        android.widget.RatingBar ratingBar = (RatingBar) popup.findViewById(R.id.popup_nota);
        ratingBar.setProgress(Integer.parseInt(nota));

        return popup;
    }
}
