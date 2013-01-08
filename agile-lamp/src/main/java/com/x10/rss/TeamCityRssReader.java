package com.x10.rss;

import java.io.IOException;
import java.net.MalformedURLException;

import com.sun.syndication.io.FeedException;

public interface TeamCityRssReader {
  Boolean getBuildStatus() throws MalformedURLException, IllegalArgumentException, FeedException, IOException;
}
