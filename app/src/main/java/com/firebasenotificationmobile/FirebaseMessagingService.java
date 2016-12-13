package com.firebasenotificationmobile;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Alan on 12/12/2016.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String message = remoteMessage.getData().get("message");
    }
}
