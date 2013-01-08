package com.x10.rss.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class TeamCityRssReaderImpl_UT {
  private TeamCityRssReaderImpl reader;
  
  @Before
  public void init(){
    reader = new TeamCityRssReaderImpl();
  }
  
  @Test
  public void successBuild_shouldReturnTrue() throws Exception{
    ClassPathResource resource = new ClassPathResource("testGoodRssFeed.xml");
    
    reader.setServerUrl("file://" + resource.getURL().getPath());
    assertTrue(reader.getBuildStatus());
  }
  
  @Test
  public void successBuild_shouldReturnFalse() throws Exception{
    ClassPathResource resource = new ClassPathResource("testFailureRssFeed.xml");
    
    reader.setServerUrl("file://" + resource.getURL().getPath());
    assertFalse(reader.getBuildStatus());
  }
}
