//package mn.stock.broker.com.danielprinz.account;
//
//import io.micronaut.http.HttpRequest;
//import io.micronaut.http.HttpResponse;
//import io.micronaut.http.HttpStatus;
//import io.micronaut.http.client.HttpClient;
//import io.micronaut.http.client.annotation.Client;
//import io.micronaut.runtime.EmbeddedApplication;
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import jakarta.inject.Inject;
//import mn.stock.broker.com.danielprinz.broker.WatchListController;
//import mn.stock.broker.com.danielprinz.broker.WatchListControllerReactive;
//import mn.stock.broker.com.danielprinz.broker.model.Symbol;
//import mn.stock.broker.com.danielprinz.broker.model.Watchlist;
//import mn.stock.broker.com.danielprinz.broker.store.InMemoryAccountStore;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@MicronautTest
//class WatchlistControllerReactiveTest {
//
//    private static final Logger LOG = LoggerFactory.getLogger(WatchlistControllerReactiveTest.class);
//    private static final UUID TEST_ACCOUNT_ID = WatchListControllerReactive.ACCOUNT_ID;
//
//    @Inject
//    EmbeddedApplication application;
//
//    @Inject
//    InMemoryAccountStore store;
//
//    @Inject
//    @Client("/account/watchlis")
//    HttpClient client;
//
//    @Test
//    void returnsEmptyWatchlistForAccount() {
//        final Watchlist result = client.toBlocking().retrieve(HttpRequest.GET("/"), Watchlist.class);
//        assertTrue(result.getSymbols().isEmpty());
//        assertTrue(store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
//    }
//
//    @Test
//    void returnsWatchListForAccount() {
//        final List<Symbol> symbols = Stream.of("APPL", "AMZN", "NFLX")
//                .map(Symbol::new)
//                .collect(Collectors.toList());
//        Watchlist watchlist = new Watchlist(symbols);
//        store.updateWatchList(TEST_ACCOUNT_ID, watchlist);
//
//        final Watchlist result = client.toBlocking().retrieve("/", Watchlist.class);
//        assertEquals(3, result.getSymbols().size());
//        assertEquals(3, store.getWatchList(TEST_ACCOUNT_ID).getSymbols().size());
//    }
//
//    @Test
//    void canUpdateWatchlistForAccount() {
//        final List<Symbol> symbols = Stream.of("APPL", "AMZN", "NFLX")
//                .map(Symbol::new)
//                .collect(Collectors.toList());
//        Watchlist watchlist = new Watchlist(symbols);
//
//        final HttpResponse<Object> added = client.toBlocking().exchange(HttpRequest.PUT("/", watchlist));
//        assertEquals(HttpStatus.OK, added.getStatus());
//    }
//
//    @Test
//    void canDeleteWatchlistForAccount() {
//
//        final List<Symbol> symbols = Stream.of("APPL", "AMZN", "NFLX")
//                .map(Symbol::new)
//                .collect(Collectors.toList());
//        Watchlist watchlist = new Watchlist(symbols);
//        store.updateWatchList(TEST_ACCOUNT_ID, watchlist);
//
//        final HttpResponse<Object> deleted = client.toBlocking().exchange(HttpRequest.DELETE("/" + TEST_ACCOUNT_ID));
//        assertEquals(HttpStatus.OK, deleted.getStatus());
//        assertEquals(store, store.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
//    }
//}