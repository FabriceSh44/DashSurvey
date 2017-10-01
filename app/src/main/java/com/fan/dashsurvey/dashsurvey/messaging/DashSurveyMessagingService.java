package com.fan.dashsurvey.dashsurvey.messaging;

import com.fan.dashsurvey.dashsurvey.notification.DashSurveyNotification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

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
        DashSurveyNotification.SendNotification(this,message);
    }

}
