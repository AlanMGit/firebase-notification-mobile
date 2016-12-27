package com.firebasenotificationmobile.activity.globais;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebasenotificationmobile.R;
import com.firebasenotificationmobile.adapter.ListaMensagensAdapter;
import com.firebasenotificationmobile.common.utils.Utils;
import com.firebasenotificationmobile.model.Mensagem;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private ListView mListaMensagens;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capturarComponentes();

        carregarMensagensSalvas();
    }

    private void carregarMensagensSalvas() {

        ListaMensagensAdapter adapter = new ListaMensagensAdapter(this, Mensagem.listAll
                (Mensagem.class), new AoRemoverMensagem() {
            @Override
            public void removerMensagem() {
                carregarMensagensSalvas();
            }
        });

        mListaMensagens.setAdapter(adapter);
    }

    private void capturarComponentes() {
        mListaMensagens = (ListView) findViewById(R.id.lista_ultimas_mensagens);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        mSwipeRefresh.setOnRefreshListener(aoAtualizarPagina);
    }

    private SwipeRefreshLayout.OnRefreshListener aoAtualizarPagina = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            carregarMensagensSalvas();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }).start();
        }
    };
}
