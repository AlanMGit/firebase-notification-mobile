package com.firebasenotificationmobile.model;

import com.orm.SugarRecord;

/**
 * Created by Alan on 27/12/2016.
 */

public class Mensagem extends SugarRecord {

    private String mensagem;

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static void salvarMensagem(String mensagem){
        Mensagem m = new Mensagem();
        m.setMensagem( mensagem );
        m.save();
    }
}
