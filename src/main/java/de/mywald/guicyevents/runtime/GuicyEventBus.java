package de.mywald.guicyevents.runtime;

import com.google.common.eventbus.*;
import com.google.inject.*;
import org.reflections.*;
import org.reflections.scanners.*;
import org.reflections.util.*;

import java.lang.reflect.*;
import java.util.*;

public class GuicyEventBus extends EventBus {
    private static final String BASE_PACKAGE = "de.mywald.guicyevents";
    private static Set<Class<?>> subscriberClasses = searchForSubscriberClasses();

    private static Set<Class<?>> searchForSubscriberClasses() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(BASE_PACKAGE)))
                .setUrls(ClasspathHelper.forPackage(BASE_PACKAGE))
                .setScanners(new SubTypesScanner(), new MethodAnnotationsScanner()));

        Set<Method> subscriber = reflections.getMethodsAnnotatedWith(Subscribe.class);

        Set<Class<?>> ret = new HashSet<Class<?>>();

        for (Method m : subscriber) {
            ret.add(m.getDeclaringClass());
        }

        return ret;
    }


    @Inject
    private Injector injector;

    private boolean subscribersAreInjected;

    @Override
    public void post(Object event) {
        if (!subscribersAreInjected) {
            injectAndRegisterAllSubscriberClasses();
        }

        super.post(event);
    }

    synchronized
    private void injectAndRegisterAllSubscriberClasses() {
        if (!subscribersAreInjected) {

            for (Class<?> clazz : subscriberClasses) {
                Object o = injector.getInstance(clazz);
                register(o);
            }

            subscribersAreInjected = true;
        }
    }
}
