package com.x10.processor.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.x10.controller.IX10DeviceController;
import com.x10.pinger.ServerPinger;
import com.x10.rss.TeamCityRssReader;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledProcessor_UT {

  @Mock private IX10DeviceController buildController;
  @Mock private IX10DeviceController weblogicController;
  @Mock private TeamCityRssReader rssReader;
  @Mock private ServerPinger pinger;
  @InjectMocks private ScheduledProcessor processor = new ScheduledProcessor();
  
  @Test
  public void successfulBuildShouldCallPassOnController() throws Exception{
    when(rssReader.getBuildStatus()).thenReturn(true);
    
    processor.process();
    
    verify(buildController).pass();
  }
  
  @Test
  public void failedBuildShouldCallFailOnController() throws Exception{
    when(rssReader.getBuildStatus()).thenReturn(false);
    
    processor.process();
    
    verify(buildController).fail();
  }
  
  @Test
  public void successfulServerPingShouldCallPassOnWeblogicController() throws Exception{
    when(pinger.remoteHostIsAlive()).thenReturn(true);
    
    processor.process();
    
    verify(weblogicController).pass();
  }
  
  @Test
  public void failedServerPingShouldCallFailOnWeblogicController() throws Exception{
    when(pinger.remoteHostIsAlive()).thenReturn(false);
    
    processor.process();
    
    verify(weblogicController).fail();
  }
}
