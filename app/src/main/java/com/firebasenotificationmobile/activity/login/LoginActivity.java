package com.firebasenotificationmobile.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebasenotificationmobile.R;


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

        mBtnLogar.setOnClickListener( aoClicarLogar );
    }

    private View.OnClickListener aoClicarLogar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
