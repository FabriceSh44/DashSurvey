package com.fan.dashsurvey.dashsurvey.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.fan.dashsurvey.dashsurvey.DashSurveyNotification;
import com.fan.dashsurvey.dashsurvey.MainDashSurvey;
import com.fan.dashsurvey.dashsurvey.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by fabriceedon on 9/30/17.
 */

public class DashSurveyMessagingService extends FirebaseMessagingService {
    private static final String TAG =  "DSMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = "";
        if (remoteMessage.getNotification().getTitle() != null){
            title = remoteMessage.getNotification().getTitle();
        }
        String message = "";
        if (remoteMessage.getNotification().getBody() != null){
            message = remoteMessage.getNotification().getBody();
        }
        DashSurveyNotification.SendNotification(this,title, message);
    }

    private void sendNotification(String title, String message) {

    }


}
