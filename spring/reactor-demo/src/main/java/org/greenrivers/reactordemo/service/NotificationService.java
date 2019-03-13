package org.greenrivers.reactordemo.service;

import org.greenrivers.reactordemo.model.NotificationData;

public interface NotificationService {
    void initiateNotification(NotificationData notificationData) throws InterruptedException;
}
