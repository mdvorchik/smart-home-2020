package ru.sbt.mipt.oop.test.action;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.action.AlarmActivateAction;
import ru.sbt.mipt.oop.action.AlarmDeactivateAction;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.house.alarm.Alarm;
import ru.sbt.mipt.oop.house.alarm.AlarmActivated;
import ru.sbt.mipt.oop.house.alarm.AlarmDeactivated;
import ru.sbt.mipt.oop.house.alarm.AlarmTriggered;

import java.lang.reflect.Field;

public class AlarmActionsTest {

    @Test
    public void testAlarmIsActivatedWhenReceivedAlarmActivateAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmActivated);
    }

    @Test
    public void testAlarmIsDeactivatedWhenReceivedAlarmDeactivateActionWithRightCode() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmDeactivated);
    }

    @Test
    public void testAlarmIsNotReactivatedWithNewCodeWhenAlarmWasActivateAndReceivedAlarmActivateActionWithNewCode() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        smartHome.execute(new AlarmActivateAction("1", smartHome, "new_code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "code")); //we can deactivate alarm with first code
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmDeactivated);
    }

    @Test
    public void testAlarmIsTriggeredWhenReceivedAlarmDeactivateActionWithWrongCode() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "wrong_code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmTriggered);
    }

    @Test
    public void testAlarmStealIsDeactivatedWhenReceivedAlarmDeactivateActionWithSomeCodeAndAlarmIsDeactivated() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "some_code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmDeactivated);
    }

    @Test
    public void testAlarmCanNotBeActivatedWhenReceivedAlarmActivateActionWithSomeCodeAndAlarmIsTriggered() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "wrong_code"));
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmTriggered);
    }

    @Test
    public void testAlarmCanNotBeDeactivatedWhenReceivedAlarmDeactivateActionWithWrongCodeAndAlarmIsTriggered() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "wrong_code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "wrong_code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmTriggered);
    }
    @Test
    public void testAlarmCanBeDeactivatedWhenReceivedAlarmDeactivateActionWithRightCodeAndAlarmIsTriggered() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Field alarmField = smartHome.getClass().getDeclaredField("alarm");
        alarmField.setAccessible(true);
        //when
        smartHome.execute(new AlarmActivateAction("1", smartHome, "code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "wrong_code"));
        smartHome.execute(new AlarmDeactivateAction("1", smartHome, "code"));
        //verify
        Assert.assertTrue(((Alarm) alarmField.get(smartHome)).getAlarmState() instanceof AlarmDeactivated);
    }
}