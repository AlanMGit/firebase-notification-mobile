package com.firebasenotificationmobile.services.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebasenotificationmobile.activity.globais.MainActivity;
import com.firebasenotificationmobile.R;
import com.firebasenotificationmobile.model.Mensagem;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Alan on 12/12/2016.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String message = remoteMessage.getData().get("message");

        salvarMensagemRecebida(message);
        criarNotificacao( message );
    }

    private void criarNotificacao(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Teste")
                .setContentText( message )
                .setContentIntent( pi )
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void salvarMensagemRecebida(String message) {
        Mensagem.salvarMensagem( message );
        Log.e("SUCESS", "MENSAGEM SALVA -> ".concat(message));
    }
}
