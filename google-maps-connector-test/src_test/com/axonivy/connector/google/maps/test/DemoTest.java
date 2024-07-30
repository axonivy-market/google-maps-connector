package com.axonivy.connector.google.maps.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.scripting.objects.CompositeObject;

@IvyProcessTest(enableWebServer = true)
class DemoTest {
  
  private static final BpmProcess testee = BpmProcess.path("Demo/searchLocation");

  @Test
  void callProcess(BpmClient bpmClient){
    BpmElement startable = testee.elementName("start.ivp");
    var mapDialogMock = BpmElement.pid("1790C4E5BB25B8AA-f3");
    bpmClient.mock().uiOf(mapDialogMock).withNoAction();
    ExecutionResult result = bpmClient.start().process(startable).execute();
    CompositeObject data = result.data().last();
    assertThat(data).isNotNull();
  }
}
