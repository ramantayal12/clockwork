package org.clockwork.pulse.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class JobsExecutorClient {

  private final RestClient restClient;

  @Autowired
  public JobsExecutorClient(RestClient restClient) {
    this.restClient = restClient;
  }


  public String executePostMethod(String url, String data, String authToken) {

    return restClient.post()
        .uri(url)
        .contentType(APPLICATION_JSON)
        .body(data)
        .retrieve()
        .body(String.class);

  }

  public String executeGetMethod(String url, String authToken) {

    return restClient.get()
        .uri(url)
        .retrieve()
        .body(String.class);
  }

  public String executePostMethod(String url, String data) {

    return restClient.post()
        .uri(url)
        .contentType(APPLICATION_JSON)
        .body(data)
        .retrieve()
        .body(String.class);
  }

  public String executeGetMethod(String url) {

    return restClient.get()
        .uri(url)
        .retrieve()
        .body(String.class);

  }

}
