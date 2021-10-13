package mn.stock.broker.com.danielprinz.broker;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import mn.stock.broker.com.danielprinz.broker.model.Symbol;
import mn.stock.broker.com.danielprinz.broker.store.InMemoryStore;

import java.util.List;

@Controller("/markets")
public class MarketsController {

    private final InMemoryStore store;

    public MarketsController(final InMemoryStore store) {
        this.store = store;
    }

    @Get("/")
    public List<Symbol> all() {
        return store.getAllSymbols();
    }
}
