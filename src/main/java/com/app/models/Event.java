package com.app.models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private float id;
    private float time;
    List<Messaging> messaging = new ArrayList<Messaging>();

    public float getId() {
        return id;
    }

    public float getTime() {
        return time;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public List<Messaging> getMessaging() {
        return messaging;
    }

    public void setMessaging(List<Messaging> messaging) {
        this.messaging = messaging;
    }

}
