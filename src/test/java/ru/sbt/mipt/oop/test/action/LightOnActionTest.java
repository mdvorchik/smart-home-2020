package ru.sbt.mipt.oop.test.action;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.action.LightOnAction;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import java.lang.reflect.Field;
import java.util.Arrays;

public class LightOnActionTest extends TestCase {

    @Test
    public void testExecuteMethodOfLightTurnOnLightWhenReceivedLightOnAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        SmartHome smartHome = new SmartHome();
        Light lightIsOn = new Light("1", true);
        Light lightIsOff = new Light("2", false);
        smartHome.addRoom(new Room(Arrays.asList(lightIsOn, lightIsOff),
                Arrays.asList(new Door(false, "1")), "testRoom"));
        Field lightIsOnField = lightIsOn.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        //when
        smartHome.execute(new LightOnAction("1", smartHome, logger));
        smartHome.execute(new LightOnAction("2", smartHome, logger));
        //verify
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOn));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOff));
    }

    @Test
    public void testExecuteMethodOfLightTurnOnNotAffectOnAnotherObjectsWhenReceivedLightOnAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        SmartHome smartHome = new SmartHome();
        Light lightIsNotOn = new Light("1", false);
        Light lightIsOff = new Light("2", false);
        smartHome.addRoom(new Room(Arrays.asList(lightIsNotOn, lightIsOff),
                Arrays.asList(new Door(false, "1")), "testRoom"));
        Field lightIsOnField = lightIsNotOn.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        //when
        smartHome.execute(new LightOnAction("2", smartHome, logger));
        //verify
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsNotOn));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOff));
    }
}