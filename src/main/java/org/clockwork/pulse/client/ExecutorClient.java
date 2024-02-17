package org.clockwork.pulse.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExecutorClient {

  private final RestClient client;

  @Autowired
  public ExecutorClient(RestClient client) {
    this.client = client;
  }


  public String executePostMethod(String url, String data, String authToken) {

    var response = client.post()
        .uri(url)
        .contentType(APPLICATION_JSON)
        .body(data)
        .retrieve()
        .body(String.class);

    return response;

  }

  public String executeGetMethod(String url, String authToken) {

    var response = client.get()
        .uri(url)
        .retrieve()
        .body(String.class);

    return response;
  }

  public String executePostMethod(String url, String data) {

    var response = client.post()
        .uri(url)
        .contentType(APPLICATION_JSON)
        .body(data)
        .retrieve()
        .body(String.class);

    return response;
  }

  public String executeGetMethod(String url) {

    return client.get()
        .uri(url)
        .retrieve()
        .body(String.class);
  }

}
