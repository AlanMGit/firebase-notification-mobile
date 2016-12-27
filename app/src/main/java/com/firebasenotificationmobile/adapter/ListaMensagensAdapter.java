package com.firebasenotificationmobile.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebasenotificationmobile.R;
import com.firebasenotificationmobile.activity.globais.AoRemoverMensagem;
import com.firebasenotificationmobile.model.Mensagem;

import java.util.List;

/**
 * Created by Alan on 27/12/2016.
 */

public class ListaMensagensAdapter extends BaseAdapter {

    private final AoRemoverMensagem mAoRemoverMensagem;
    private Activity mContext;
    private List<Mensagem> mListaMensagem;

    public ListaMensagensAdapter(Activity mContext, List<Mensagem> mListaMensagem, AoRemoverMensagem aoRemoverMensagem) {
        this.mContext = mContext;
        this.mListaMensagem = mListaMensagem;
        this.mAoRemoverMensagem = aoRemoverMensagem;
    }

    @Override
    public int getCount() {
        return mListaMensagem.size();
    }

    @Override
    public Object getItem(int i) {
        return mListaMensagem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mListaMensagem.get(i).getId();
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View view = convertView;

        if (view == null)
            view = inflater.inflate(R.layout.item_mensagem, viewGroup, false);

        TextView mensagem = (TextView) view.findViewById(R.id.mensagem);
//        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        ImageView removerMensagem = (ImageView) view.findViewById(R.id.remover_mensagem);

        mensagem.setText( mListaMensagem.get(i).getMensagem() );

        removerMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mensagem mensagemClicada = mListaMensagem.get(i);
                mensagemClicada.delete();
                mAoRemoverMensagem.removerMensagem();
            }
        });

        return view;
    }
}
