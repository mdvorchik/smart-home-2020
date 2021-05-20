package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.config.ApplicationConfig;

public class Application {

    public static void main(String... args) {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SensorEventsManager sensorEventsManager = applicationContext.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
