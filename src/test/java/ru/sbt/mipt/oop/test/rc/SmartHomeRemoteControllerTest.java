package ru.sbt.mipt.oop.test.rc;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.api.rc.SmartHomeRemoteController;
import ru.sbt.mipt.oop.api.rc.commands.ActivateAlarmCommand;
import ru.sbt.mipt.oop.api.rc.commands.RcCommand;
import ru.sbt.mipt.oop.config.ApplicationConfig;

public class SmartHomeRemoteControllerTest {

    private AbstractApplicationContext testContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    @Test
    public void testCommandWasExecutedWhenCorrectButtonPressed() {
        //given
        RcCommand rcCommand = Mockito.mock(ActivateAlarmCommand.class);
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        remoteControl.programButton("3", rcCommand);
        //when
        remoteControl.onButtonPressed("test", "3");
        //verify
        Mockito.verify(rcCommand, Mockito.times(1)).execute();
    }

    @Test
    public void testAnotherCommandWasNotExecutedWhenCorrectButtonPressed() {
        //given
        RcCommand rcCommand = Mockito.mock(ActivateAlarmCommand.class);
        RcCommand anotherRcCommand = Mockito.mock(ActivateAlarmCommand.class);
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        remoteControl.programButton("3", rcCommand);
        remoteControl.programButton("4", rcCommand);
        //when
        remoteControl.onButtonPressed("test", "3");
        //verify
        Mockito.verify(anotherRcCommand, Mockito.times(0)).execute();
    }

    @Test
    public void testNewCommandAddingWhenProgramButton() {
        //given
        RcCommand rcCommand = Mockito.mock(ActivateAlarmCommand.class);
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        //when
        remoteControl.programButton("3", rcCommand);
        remoteControl.onButtonPressed("test", "3");
        //verify
        Mockito.verify(rcCommand, Mockito.times(1)).execute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenIncorrectButtonPressed() {
        //given
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        //when
        remoteControl.onButtonPressed("test", "wrong_code");
        //verify Illegal Argument Exception
    }

    @Test
    public void testProgramDoesNotCrashWhenNullButtonPressed() {
        //given
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        //when
        remoteControl.programButton("3", null);
        remoteControl.onButtonPressed("test", "3");
        //verify Program does not crash at this moment
        Assert.assertTrue(true);
    }
}