package mn.stock.broker.com.danielprinz.broker;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class MarketsControllerTest {

    @Inject
    EmbeddedApplication application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void returnListOfMarkets() {
        final List result = client.toBlocking().retrieve("/markets", List.class);
        assertEquals(7, result.size());
    }
}