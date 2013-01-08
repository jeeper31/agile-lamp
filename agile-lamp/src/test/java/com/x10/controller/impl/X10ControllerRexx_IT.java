
package com.x10.controller.impl;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.x10.exception.X10Exception;

/**
 * Test case for the x10 controller rexx implementation.
 *                   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/com/x10/context/applicationContext.xml"})
public class X10ControllerRexx_IT {

  @Resource(name="buildController")
	private X10DeviceControllerRexx buildController;
	
  @Resource(name="weblogicController")
	private X10DeviceControllerRexx weblogicController;
	
  @Test
  public void testSuperBinanaryDay() {
    RunShitBuild build = new RunShitBuild();
    RunShitWeb web = new RunShitWeb();
      for(int i = 0; i < 1500; i++){
        build.run();
        web.run();
      }
  }
  
  private class RunShitBuild implements Runnable{
    @Override
    public void run() {
      try {
        buildController.pass();
        buildController.fail();
      } catch (X10Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  private class RunShitWeb implements Runnable{
    @Override
    public void run() {
      try {
        weblogicController.pass();
        weblogicController.fail();
      } catch (X10Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  @Test
	public void testBuildPass() {
		try {
			buildController.pass();
		} catch (X10Exception e) {
			fail(e.getMessage());
		}
	}

  @Test
	public void testBuildFail() {
		try {
			buildController.fail();
		} catch (X10Exception e) {
			fail(e.getMessage());
		}
	}
	
  @Test
	public void testWeblogicPass() {
	    try {

	      weblogicController.pass();
	    } catch (X10Exception e) {
	      fail(e.getMessage());
	    }

	  }

  @Test
	public void testWeblogicFail() {
	    try {
	      weblogicController.fail();
	    } catch (X10Exception e) {
	      fail(e.getMessage());
	    }
	  }

}
