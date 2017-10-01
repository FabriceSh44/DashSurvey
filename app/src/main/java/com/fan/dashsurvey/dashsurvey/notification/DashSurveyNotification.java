package com.fan.dashsurvey.dashsurvey.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import com.fan.dashsurvey.dashsurvey.MainDashSurvey;
import com.fan.dashsurvey.dashsurvey.R;

import java.util.Random;


/**
 * Created by fabriceedon on 10/1/17.
 */

public class DashSurveyNotification {
    private static final String CHANNEL_ID = "channel_01";

    public static void SendNotification(Context context, CharSequence eventMessage) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = GetChannel(context);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        RemoteViews notificationView = new RemoteViews(context.getPackageName(), R.layout.notification_dash_survey);
        notificationView.setTextViewText(R.id.eventName, eventMessage);

        Intent intent = new Intent(context, MainDashSurvey.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        PendingIntent yesIntent = GetIntent(context, yesButtonListener.class);
        PendingIntent noIntent = GetIntent(context, noButtonListener.class);
        notificationView.setOnClickPendingIntent(R.id.yesbutton, yesIntent);
        notificationView.setOnClickPendingIntent(R.id.nobutton, noIntent);

        Notification notification;
        if (Build.VERSION.SDK_INT >= 26) {
            notification = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setCustomContentView(notificationView)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        } else {
            notification = new Notification.Builder(context)
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContent(notificationView)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .getNotification();
        }

        notificationManager.notify(getRequestCode(), notification);

    }

    private static PendingIntent GetIntent(Context context, Class<?> classListener) {
        Intent intent = new Intent(context, classListener);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel GetChannel(Context context) {
        // The id of the channel.
        String id = CHANNEL_ID;
        // The user-visible name of the channel.
        CharSequence name = context.getString(R.string.channel_name);
        // The user-visible description of the channel.
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        // Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        return mChannel;

    }

    private static int getRequestCode() {
        Random rnd = new Random();
        return 100 + rnd.nextInt(900000);
    }
}

