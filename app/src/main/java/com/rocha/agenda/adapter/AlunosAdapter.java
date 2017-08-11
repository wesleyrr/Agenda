package com.rocha.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rocha.agenda.R;
import com.rocha.agenda.modelo.Aluno;

import java.util.List;

/**
 * Created by wesley on 07/02/17.
 */

public class AlunosAdapter extends BaseAdapter {
    private final Context context;
    private final List<Aluno> alunos;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        //caso o dispositivo esteja em modo paisagem busca o layout land
        TextView campoEndereco = (TextView) view.findViewById(R.id.item_endereco);
        if(campoEndereco != null){
            campoEndereco.setText(aluno.getEndereco());
        }

        TextView campoSite = (TextView) view.findViewById(R.id.item_site);
        if(campoSite != null){
            campoSite.setText(aluno.getSite());
        }

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap,100,100,true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);//ajusta a foto no imageview
        }


        return view;
    }
}
