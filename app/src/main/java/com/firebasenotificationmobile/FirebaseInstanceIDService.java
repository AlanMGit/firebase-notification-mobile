package com.firebasenotificationmobile;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Alan on 12/12/2016.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService  {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

    }
}
