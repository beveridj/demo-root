package org.example.demo;

import com.example.demo.api.CatalogDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import lombok.Data;

@Data
public class DemoClient {

    public static final String DEFAULT_BASE_URI = "http://localhost";

    private final Client client;
    private String baseUri = DEFAULT_BASE_URI;

    public DemoClient() {
        client = ClientBuilder.newClient();
    }

    public CatalogDto get(Long aCatalogId) {
        return catalogTarget(aCatalogId)
                .request()
                .get(CatalogDto.class);
    }

    public void setBaseUri(String aBaseUri, int aPort) {
        baseUri = aBaseUri + ":" + aPort;
    }

    private WebTarget baseTarget() {
        return client.target(baseUri);
    }

    private WebTarget catalogTarget(Long aCatalogId) {
        return catalogTarget().path(aCatalogId.toString());
    }

    private WebTarget catalogTarget() {
        return baseTarget().path("api")
                .path("v1")
                .path("catalogs");
    }

}
