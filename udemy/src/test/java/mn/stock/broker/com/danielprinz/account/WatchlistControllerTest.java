package mn.stock.broker.com.danielprinz.account;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import mn.stock.broker.com.danielprinz.broker.WatchListController;
import mn.stock.broker.com.danielprinz.broker.model.Symbol;
import mn.stock.broker.com.danielprinz.broker.model.Watchlist;
import mn.stock.broker.com.danielprinz.broker.store.InMemoryAccountStore;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class WatchlistControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(WatchlistControllerTest.class);
    private static final UUID TEST_ACCOUNT_ID = WatchListController.ACCOUNT_ID;

    @Inject
    EmbeddedApplication application;

    @Inject
    InMemoryAccountStore store;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void unauthorizedAccessIsForbidden() {
        try {
            client.toBlocking().retrieve("/account/watchlist");
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.UNAUTHORIZED, e.getStatus());
        }
    }

    @Test
    void returnsEmptyWatchlistForAccount() {
        final BearerAccessRefreshToken token = givenMyUserIsLoggedIn();

        var request = HttpRequest.GET("/account/watchlist")
                .accept(MediaType.APPLICATION_JSON)
                .bearerAuth(token.getAccessToken());
        final Watchlist result = client.toBlocking().retrieve(request, Watchlist.class);
        assertTrue(result.getSymbols().isEmpty());
        assertTrue(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
    }

    @Test
    void returnsWatchListForAccount() {
        final BearerAccessRefreshToken token = givenMyUserIsLoggedIn();

        final List<Symbol> symbols = Stream.of("APPL", "AMZN", "NFLX")
                .map(Symbol::new)
                .collect(Collectors.toList());
        Watchlist watchlist = new Watchlist(symbols);
        store.updateWatchList(TEST_ACCOUNT_ID, watchlist);

        var request = HttpRequest.GET("/account/watchlist")
                .accept(MediaType.APPLICATION_JSON)
                .bearerAuth(token.getAccessToken());

        final Watchlist result = client.toBlocking().retrieve(request, Watchlist.class);
        assertEquals(3, result.getSymbols().size());
        assertEquals(3, store.getWatchList(TEST_ACCOUNT_ID).getSymbols().size());
    }

    @Test
    void canUpdateWatchlistForAccount() {
        final BearerAccessRefreshToken token = givenMyUserIsLoggedIn();

        final List<Symbol> symbols = Stream.of("APPL", "AMZN", "NFLX")
                .map(Symbol::new)
                .collect(Collectors.toList());
        Watchlist watchlist = new Watchlist(symbols);

        var request = HttpRequest.PUT("/account/watchlist", watchlist)
                .accept(MediaType.APPLICATION_JSON)
                .bearerAuth(token.getAccessToken());

        final HttpResponse<Object> added = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.OK, added.getStatus());
    }

    @Test
    void canDeleteWatchlistForAccount() {
        final BearerAccessRefreshToken token = givenMyUserIsLoggedIn();

        final List<Symbol> symbols = Stream.of("APPL", "AMZN", "NFLX")
                .map(Symbol::new)
                .collect(Collectors.toList());
        Watchlist watchlist = new Watchlist(symbols);
        store.updateWatchList(TEST_ACCOUNT_ID, watchlist);

        var request = HttpRequest.DELETE("/account/watchlist/" + TEST_ACCOUNT_ID, watchlist)
                .accept(MediaType.APPLICATION_JSON)
                .bearerAuth(token.getAccessToken());

        final HttpResponse<Object> deleted = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.OK, deleted.getStatus());
        assertTrue(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
    }

    private BearerAccessRefreshToken givenMyUserIsLoggedIn() {
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("my-user", "secret");
        var login = HttpRequest.POST("/login", credentials);
        var response = client.toBlocking().exchange(login, BearerAccessRefreshToken.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        final BearerAccessRefreshToken token = response.body();
        assertNotNull(token);
        assertEquals("my-user", token.getUsername());
        LOG.debug("Login Bearer Token: {} expires in {}", token.getAccessToken(), token.getExpiresIn());
        return token;
    }
}