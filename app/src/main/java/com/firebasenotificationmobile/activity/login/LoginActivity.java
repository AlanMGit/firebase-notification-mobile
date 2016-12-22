package com.firebasenotificationmobile.activity.login;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.firebasenotificationmobile.common.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private EditText mEdtLogin;
    private EditText mEdtSenha;
    private Button mBtnLogar;

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
        params.put("login", mEdtLogin.getText().toString());
        params.put("senha", mEdtSenha.getText().toString());

        return params;
    }

    private View.OnClickListener aoClicarLogar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this,
                    "Aguarde...", "Verificando login");

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            String url = "https://notificationfirebase.herokuapp.com/api/login";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    dialog.dismiss();
                    Utils.setToast(LoginActivity.this, "Foi");
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
}
