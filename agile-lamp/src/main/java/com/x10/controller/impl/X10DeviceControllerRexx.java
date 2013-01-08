/*
 * (C) 2005 National Australia Bank [All rights reserved]. This product and related documentation
 * are protected by copyright restricting its use, copying, distribution, and decompilation. No part
 * of this product or related documentation may be reproduced in any form by any means without prior
 * written authorization of National Australia Bank. Unless otherwise arranged, third parties may
 * not have access to this product or related documents.
 */
package com.x10.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.x10.controller.IX10DeviceController;
import com.x10.exception.X10Exception;

/**
 * Controls two X10 devices: one used to indicate that something was successful
 * and the other that something failed. This implementation is by a Rexx
 * program.
 * 
 */
//@Component
public class X10DeviceControllerRexx implements IX10DeviceController {
  private static final Logger LOG = Logger.getLogger(X10DeviceControllerRexx.class);

  private static final String FAIL = "OFF";
  private static final String PASS = "ON";
  private static final String COMMAND = "rexx";

  //@Value("#{appProperties.rexxProgrammeFileName}")
  private String rexxProgrammeFileName;

  //@Value("#{appProperties.failDeviceCode}")
  private String failDeviceCode;

  //@Value("#{appProperties.passDeviceCode}")
  private String passDeviceCode;

  public X10DeviceControllerRexx() {
  }

  /**
   * Constructor.
   * 
   * @param passDeviceCode
   *          x10 device pass code
   * @param failDeviceCode
   *          x10 device fail code
   */
  public X10DeviceControllerRexx(String passDeviceCode, String failDeviceCode) {
    this.passDeviceCode = passDeviceCode;
    this.failDeviceCode = failDeviceCode;
  }

  /**
   * Executes a runtime command that invokes a rexx script.
   * 
   * @param deviceCode
   *          x10 device code
   * @param status
   *          status of the x10 device
   * @throws X10Exception
   */
  private void executeCommand(String deviceCode, String status) throws X10Exception {
    String s = null;
    Runtime rt = Runtime.getRuntime();
    Process p = null;
    BufferedReader stdInput = null;
    BufferedReader stdError = null;

    try {
      LOG.debug(COMMAND + " " + rexxProgrammeFileName + " " + deviceCode + " " + status);
      p = rt.exec(COMMAND + " " + rexxProgrammeFileName + " " + deviceCode + " " + status);

      Thread.sleep(2000);

      stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
      stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

      if (LOG.isDebugEnabled()) {
        LOG.debug("Standard output of the command:\n");
        while ((s = stdInput.readLine()) != null) {
          LOG.debug(s);
        }
      }

      LOG.debug("Standard error of the command (if any):\n");
      while ((s = stdError.readLine()) != null) {
        LOG.error(s);
      }

    } catch (IOException e) {
      throw new X10Exception("Exception occurred during pass event: " + e.getMessage());

    } catch (InterruptedException e) {
      LOG.error(e.getMessage());
    }
  }


  @Override
  public void pass() throws X10Exception {
    switchOff(failDeviceCode);
    switchOn(passDeviceCode);
  }

  @Override
  public void fail() throws X10Exception {
    switchOff(passDeviceCode);
    switchOn(failDeviceCode);
  }

  @Override
  public void switchOff(String deviceCode) throws X10Exception {
    executeCommand(deviceCode, FAIL);
  }

  @Override
  public void switchOn(String deviceCode) throws X10Exception {
    executeCommand(deviceCode, PASS);
  }

  public void setRexxProgrammeFileName(String rexxProgrammeFileName) {
    this.rexxProgrammeFileName = rexxProgrammeFileName;
  }

  public void setFailDeviceCode(String failDeviceCode) {
    this.failDeviceCode = failDeviceCode;
  }

  public void setPassDeviceCode(String passDeviceCode) {
    this.passDeviceCode = passDeviceCode;
  }
}
