package com.x10;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
  private static final Logger log = Logger.getLogger(MainApplication.class);
  /**
   * Use this to kick off the app. This main method will never exit.
   * 
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    log.info("Starting up the processor...");
    new ClassPathXmlApplicationContext("context/applicationContext.xml", MainApplication.class);
    log.info("Exiting the processor...");
  }

}
