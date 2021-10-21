package mn.stock.broker.com.danielprinz.broker;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import mn.stock.broker.com.danielprinz.broker.model.Watchlist;
import mn.stock.broker.com.danielprinz.broker.store.InMemoryAccountStore;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.logging.Logger;

@Controller("/account/watchlist-reactive")
public class WatchListControllerReactive {

    private org.slf4j.Logger LOG =LoggerFactory.getLogger(WatchListControllerReactive.class);
    public static final UUID ACCOUNT_ID = UUID.randomUUID();

    private final InMemoryAccountStore store;

    public WatchListControllerReactive(InMemoryAccountStore store) {
        this.store = store;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.IO)
    public Watchlist get() {
        LOG.debug("getWatchList - {}", Thread.currentThread().getName());
        return store.getWatchList(ACCOUNT_ID);
    }

    @Put(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ExecuteOn(TaskExecutors.IO)
    public Watchlist update(@Body Watchlist watchlist) {
        return store.updateWatchList(ACCOUNT_ID, watchlist);
    }

    @Delete(
            value = "/{accountId}",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ExecuteOn(TaskExecutors.IO)
    public void delete(@PathVariable UUID accountId) {
        store.deleteWatchlist(accountId);
    }
}
