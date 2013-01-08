package com.x10.pinger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="serverPinger")
public class ServerPinger {
  private static final Logger log = Logger.getLogger(ServerPinger.class);
  
  @Value("#{appProperties.devServerUrl}")
  private String serverUrl;

  public boolean remoteHostIsAlive() throws IOException {
    URL url = new URL(serverUrl);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    
    if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
      log.debug("The server: " + serverUrl + " is up...");
      return true;
    }else{
      log.warn("The Server: " + serverUrl + " seems to be down...");
      return false;
    }
  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

}
