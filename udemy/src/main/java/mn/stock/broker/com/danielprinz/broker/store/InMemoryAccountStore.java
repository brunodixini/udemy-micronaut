package mn.stock.broker.com.danielprinz.broker.store;

import jakarta.inject.Singleton;
import mn.stock.broker.com.danielprinz.broker.model.Watchlist;

import java.util.HashMap;
import java.util.UUID;

@Singleton
public class InMemoryAccountStore {

    private final HashMap<UUID, Watchlist> watchListPerAccount = new HashMap<>();

    public Watchlist getWatchList(UUID accountId) {
        return watchListPerAccount.getOrDefault(accountId, new Watchlist());
    }

    public Watchlist updateWatchList(UUID accountId, Watchlist watchlist) {
        watchListPerAccount.put(accountId, watchlist);
        return getWatchList(accountId);
    }

    public void deleteWatchlist(final UUID accountId) {
        watchListPerAccount.remove(accountId);
    }
}
