package com.firebasenotificationmobile.activity.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebasenotificationmobile.R;
import com.firebasenotificationmobile.activity.globais.MainActivity;
import com.firebasenotificationmobile.common.utils.Utils;
import com.firebasenotificationmobile.model.UsuarioLogado;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class LoginActivity extends AppCompatActivity {

    private EditText mEdtLogin;
    private EditText mEdtSenha;
    private Button mBtnLogar;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        capturarComponentes();

    }

    private void capturarComponentes() {

        mEdtLogin = (EditText) findViewById(R.id.edt_login);
        mEdtSenha = (EditText) findViewById(R.id.edt_senha);
        mBtnLogar = (Button) findViewById(R.id.btn_logar);

        mBtnLogar.setOnClickListener(aoClicarLogar);
    }

    private HashMap<String, String> obterParametros() {
        HashMap<String, String> params = new HashMap<>();
        params.put("usu_login", mEdtLogin.getText().toString());
        params.put("usu_senha", mEdtSenha.getText().toString());
        return params;
    }

    private View.OnClickListener aoClicarLogar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Aguarde...");

            dialog.show();
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            String url = "http://notificationfirebase.herokuapp.com/api/login";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Gson gson = new Gson();
                    UsuarioLogado usuarioLogado = gson.fromJson(s, UsuarioLogado.class);

                    SharedPreferences mSharedPref = LoginActivity.this.getSharedPreferences
                            (LoginActivity.this.getClass().getName(), Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = mSharedPref.edit();
                    editor.putString("token", usuarioLogado.getToken());
                    editor.commit();

                    alterarTokenNotificacaoUsuario(usuarioLogado);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    dialog.dismiss();
                    Utils.setToast(LoginActivity.this, "Erro ao realizar login, verifique seu email e senha");
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return obterParametros();
                }
            };

            requestQueue.add(request);

        }
    };

    private void alterarTokenNotificacaoUsuario(final UsuarioLogado usuarioLogado) {

        String url = "http://notificationfirebase.herokuapp.com/api/usuario/alterartoken";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                dialog.dismiss();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer {"
                        .concat(usuarioLogado.getToken())
                        .concat("}"));
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("token", FirebaseInstanceId.getInstance().getToken());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(request);

    }
}
