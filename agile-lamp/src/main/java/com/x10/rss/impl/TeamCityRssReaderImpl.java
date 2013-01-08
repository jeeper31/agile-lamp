package com.x10.rss.impl;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.x10.rss.TeamCityRssReader;

@Component
public class TeamCityRssReaderImpl implements TeamCityRssReader {
  private static final String FAILED_BUILD_STATUS_MSG = "Failed Build";
  private static final Logger log = Logger.getLogger(TeamCityRssReaderImpl.class);

  @Value("#{appProperties.teamcityBuildUrl}")
  private String serverUrl;

  @Override
  public Boolean getBuildStatus() throws IllegalArgumentException, FeedException, IOException {
    URL feedSource = null;
    feedSource = new URL(serverUrl);
    SyndFeedInput input = new SyndFeedInput();

    XmlReader xmlReader = new XmlReader(feedSource);
    
    SyndFeed feed = input.build(xmlReader);
    SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
    SyndPerson status = (SyndPerson) entry.getAuthors().get(0);
    
    if (status.getName().equalsIgnoreCase(FAILED_BUILD_STATUS_MSG)) {
      log.info("The Build Failed!");
      return false;
    } else {
      log.info("The Build Worked!");
      return true;
    }

  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

}
