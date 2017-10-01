package com.fan.dashsurvey.dashsurvey.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

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
        sendNotification(title, message);
    }

    private void sendNotification(String title, String message) {
        Intent intent = new Intent(this, MainDashSurvey.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        RemoteViews notificationView = new RemoteViews(getPackageName(),
                R.layout.notification_dash_survey);
//TODO : get notification channel id
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.dashsurvey_icon)
                .setContent(notificationView)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(getRequestCode(), notificationBuilder.build());
    }

    private static int getRequestCode() {
        Random rnd = new Random();
        return 100 + rnd.nextInt(900000);
    }
}
