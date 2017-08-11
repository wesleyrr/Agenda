package com.rocha.agenda.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.rocha.agenda.R;


public class PlanetFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Integer id = 0;

        View view = inflater.inflate(R.layout.fragment_planet, container,false);

        Bundle args = getArguments();
        if(args != null){
             id = args.getInt("numero");
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(getIdPlanet(id));

        return view;
    }

    private Integer getIdPlanet(Integer i){
        Integer num = 2;

        switch (i){
            case 0:
                num = R.drawable.mercury;
                break;
            case 1:
                num = R.drawable.venus;
                break;
            case 2:
                num = R.drawable.earth;
                break;
            case 3:
                num = R.drawable.mars;
                break;
            case 4:
                num = R.drawable.jupiter;
                break;
            case 5:
                num = R.drawable.saturn;
                break;
            case 6:
                num = R.drawable.uranus;
                break;
            case 7:
                num = R.drawable.neptune;
                break;
        }
        return num;
    }
}
