package de.mywald.guicyevents.example;

import com.google.common.eventbus.*;
import com.google.inject.*;
import de.mywald.guicyevents.runtime.*;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(GuicyEventBus.class);
    }
}
