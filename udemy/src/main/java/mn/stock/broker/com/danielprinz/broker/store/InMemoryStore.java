package mn.stock.broker.com.danielprinz.broker.store;

import jakarta.inject.Singleton;
import mn.stock.broker.com.danielprinz.broker.model.Quote;
import mn.stock.broker.com.danielprinz.broker.model.Symbol;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class InMemoryStore {

    private final List<Symbol> symbols;
    private final HashMap<String, Quote> cachedQuote = new HashMap<>();
    private final ThreadLocalRandom current = ThreadLocalRandom.current();

    public InMemoryStore() {
        symbols = Stream.of("AAPL", "AMZN", "FB", "GOOG", "MSFT", "NFLX", "TSLA")
                .map(str -> new Symbol(str))
                .collect(Collectors.toList());

        symbols.forEach(symbol -> 
            cachedQuote.put(symbol.getValue(), randomQuote(symbol))
        );
    }
    private Quote randomQuote(final Symbol symbol) {

        return Quote.builder()
                .symbol(symbol)
                .bid(randomValue())
                .ask(randomValue())
                .lastPrice(randomValue())
                .volume(randomValue())
                .build();
    }

    public List<Symbol> getAllSymbols() {
        return symbols;
    }

    public Optional<Quote> fetchQuote(final String symbol) {

        return Optional.ofNullable(cachedQuote.get(symbol));
    }

    private BigDecimal randomValue() {
        return BigDecimal.valueOf(current.nextDouble(1, 100));
    }

    public void update(final Quote quote) {
        cachedQuote.put(quote.getSymbol().getValue(), quote);
    }
}
