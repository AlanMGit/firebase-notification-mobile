package com.firebasenotificationmobile.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alan on 24/12/2016.
 */

public class UsuarioLogado {

    private String token;
    private int usu_id;

    public int getUsu_id() {
        return usu_id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
