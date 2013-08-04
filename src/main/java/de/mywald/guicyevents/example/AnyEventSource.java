package de.mywald.guicyevents.example;

import com.google.common.eventbus.*;
import com.google.inject.*;

/**
 * Example for any Business class that fires a certain de.mywald.guicyevents.example.AnEvent
 */
public class AnyEventSource {

    @Inject
    private EventBus eventbus;

    public void businessMethod(String value) {
        eventbus.post(new AnEvent(value));
    }
}
