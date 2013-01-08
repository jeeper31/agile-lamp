package com.x10.processor.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sun.syndication.io.FeedException;
import com.x10.controller.IX10DeviceController;
import com.x10.exception.X10Exception;
import com.x10.pinger.ServerPinger;
import com.x10.processor.Processor;
import com.x10.rss.TeamCityRssReader;

@Service(value="scheduledProcessor")
public class ScheduledProcessor implements Processor {
  private static final Logger log = Logger.getLogger(ScheduledProcessor.class);

  @Resource(name="buildController")
  private IX10DeviceController buildController;
  
  @Resource(name="weblogicController")
  private IX10DeviceController weblogicController;
  
  @Resource(name="serverPinger")
  private ServerPinger pinger;

  @Autowired
  private TeamCityRssReader rssReader;

  @Override
  @Scheduled(fixedDelay = 60000)
  public void process() {
    if(log.isDebugEnabled()){
      log.debug("Processing...");
    }

    try {

      processBuildStatus();
      processDevServertatus();

    } catch (Exception e) {
      log.error(e);
    }
  }

  private void processBuildStatus() throws MalformedURLException, FeedException, IOException, X10Exception {
    if (rssReader.getBuildStatus()) {
      buildController.pass();
    } else {
      buildController.fail();
    }
  }
  
  private void processDevServertatus() throws MalformedURLException, FeedException, IOException, X10Exception {
    if (pinger.remoteHostIsAlive()) {
      weblogicController.pass();
    } else {
      weblogicController.fail();
    }
  }

}
