package mn.stock.broker.com.danielprinz.broker;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import mn.stock.broker.com.danielprinz.broker.model.Watchlist;
import mn.stock.broker.com.danielprinz.broker.store.InMemoryAccountStore;

import java.util.UUID;

@Controller("/account/watchlist")
public class WatchListController {

    public static final UUID ACCOUNT_ID = UUID.randomUUID();

    private final InMemoryAccountStore store;

    public WatchListController(InMemoryAccountStore store) {
        this.store = store;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public Watchlist get() {
        return store.getWatchList(ACCOUNT_ID);
    }

    @Put(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public Watchlist update(@Body Watchlist watchlist) {
        return store.updateWatchList(ACCOUNT_ID, watchlist);
    }

    @Delete(
            value = "/{accountId}",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public void delete(@PathVariable UUID accountId) {
        store.deleteWatchlist(accountId);
    }
}
