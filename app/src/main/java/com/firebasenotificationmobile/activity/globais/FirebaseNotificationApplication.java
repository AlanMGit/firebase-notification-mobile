package com.firebasenotificationmobile.activity.globais;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Alan on 27/12/2016.
 */

public class FirebaseNotificationApplication extends Application {

    private static FirebaseNotificationApplication mFirebaseNotificationApplication;

    public static synchronized FirebaseNotificationApplication getInstance() {
        return mFirebaseNotificationApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init( this );
        mFirebaseNotificationApplication = this;
    }
}
