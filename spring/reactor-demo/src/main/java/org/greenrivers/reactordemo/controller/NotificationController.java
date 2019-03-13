package org.greenrivers.reactordemo.controller;

import org.greenrivers.reactordemo.model.NotificationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.bus.Event;
import reactor.bus.EventBus;

@RestController
public class NotificationController {
    private EventBus eventBus;

    @Autowired
    public NotificationController(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @GetMapping(value = "/startNotification/{param}", produces = {"application/json"})
    public String startNotification(@PathVariable Integer param) {
        for(int i = 0; i < param; i++) {
            NotificationData notificationData = new NotificationData();
            notificationData.setId(i);

            eventBus.notify("notificationConsumer", Event.wrap(notificationData));

            System.out.println("Notification " + i + ": notification task submitted successfully");

        }
        return "{\"message\": \"Task submitted.\"}";
    }

}
