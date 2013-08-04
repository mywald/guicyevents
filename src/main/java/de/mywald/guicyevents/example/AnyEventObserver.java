package de.mywald.guicyevents.example;

import com.google.common.eventbus.*;

/**
 * Any Business class that observes a certain AnEvent
 */
public class AnyEventObserver {
    public static String HAS_BEEN_CALLED_WITH = "";

    @Subscribe
    public void observingEvent(AnEvent event) {
        HAS_BEEN_CALLED_WITH = event.getValue();
    }
}
