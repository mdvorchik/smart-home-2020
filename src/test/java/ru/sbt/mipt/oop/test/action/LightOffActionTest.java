package ru.sbt.mipt.oop.test.action;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.action.LightOffAction;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import java.lang.reflect.Field;
import java.util.Arrays;


public class LightOffActionTest {

    @Test
    public void testExecuteMethodOfLightTurnOffLightWhenReceivedLightOffAction() throws NoSuchFieldException, IllegalAccessException {
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
        smartHome.execute(new LightOffAction("1", smartHome, logger));
        smartHome.execute(new LightOffAction("2", smartHome, logger));
        //verify
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOn));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOff));
    }

    @Test
    public void testExecuteMethodOfLightTurnOffNotAffectOnAnotherObjectsWhenReceivedLightOffAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        SmartHome smartHome = new SmartHome();
        Light lightIsOn = new Light("1", true);
        Light lightIsNotOff = new Light("2", true);
        smartHome.addRoom(new Room(Arrays.asList(lightIsOn, lightIsNotOff),
                Arrays.asList(new Door(false, "1")), "testRoom"));
        Field lightIsOnField = lightIsOn.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        //when
        smartHome.execute(new LightOffAction("1", smartHome, logger));
        //verify
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOn));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsNotOff));
    }
}