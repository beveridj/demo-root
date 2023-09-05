package com.example.demo;

import com.example.demo.api.CatalogDto;
import org.example.demo.DemoClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoClientTest {

    @LocalServerPort
    private int port;
    private DemoClient client;

    @BeforeAll
    void setUp(){
        client = new DemoClient();
        client.setBaseUri(DemoClient.DEFAULT_BASE_URI, port);
    }

    @Test
    void testDemoClient(){
        CatalogDto request = CatalogDto.newInstance().setName("The Catalog").setDescription("The catalog description.");
        CatalogDto response = client.get(1L);
        assertThat(response.getId(), is(1L));

    }
}
