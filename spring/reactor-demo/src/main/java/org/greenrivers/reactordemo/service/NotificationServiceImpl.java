package org.greenrivers.reactordemo.service;

import org.greenrivers.reactordemo.model.NotificationData;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void initiateNotification(NotificationData notificationData) throws InterruptedException {
        System.out.println("Notification service started for Notification Id " + notificationData.getId());
        Thread.sleep(5000);
        System.out.println("Notification service end for Notification Id " + notificationData.getId());
    }
}
