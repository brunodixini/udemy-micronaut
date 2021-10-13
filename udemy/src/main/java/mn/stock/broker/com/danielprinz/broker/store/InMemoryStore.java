package mn.stock.broker.com.danielprinz.broker.store;

import jakarta.inject.Singleton;
import mn.stock.broker.com.danielprinz.broker.model.Symbol;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class InMemoryStore {

    private final List<Symbol> symbols;

    public InMemoryStore() {
        symbols = Stream.of("AAPL", "AMZN", "FB", "GOOG", "MSFT", "NFLX", "TSLA")
                .map(str -> new Symbol(str))
                .collect(Collectors.toList());
    }

    public List<Symbol> getAllSymbols() {
        return symbols;
    }
}
