package com.x10.pinger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class ServerPinger_UT {

  private ServerPinger pinger = new ServerPinger();
  
  @Test
  public void pingingSiteWhichIsUpShouldReturnTrue() throws IOException{
    pinger.setServerUrl("http://www.google.com");
    
    boolean result = pinger.remoteHostIsAlive();
    
    assertTrue(result);
  }
  
  @Test
  public void pingingSiteWhichIsDownShouldReturnFalse() throws IOException{
    pinger.setServerUrl("http://www.blehbleh124234blehface.com");
    
    boolean result = pinger.remoteHostIsAlive();
    
    assertFalse(result);
  }
}
