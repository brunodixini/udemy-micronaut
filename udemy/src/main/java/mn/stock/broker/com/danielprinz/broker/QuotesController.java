package mn.stock.broker.com.danielprinz.broker;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import mn.stock.broker.com.danielprinz.broker.model.Quote;
import mn.stock.broker.com.danielprinz.broker.store.InMemoryStore;

import java.util.Optional;

@Controller("/quotes")
public class QuotesController {

    private final InMemoryStore store;

    public QuotesController(InMemoryStore store) {
        this.store = store;
    }

    @Get("/{symbol}")
    public HttpResponse getQuote(@PathVariable String symbol) {
        var maybeQuote = store.fetchQuote(symbol);
        final CustomError notFound = CustomError.builder()
                .status(HttpStatus.NOT_FOUND.getCode())
                .error(HttpStatus.NOT_FOUND.name())
                .message("quote for symbol not available")
                .path("/quote/" + symbol)
                .build();
        if(maybeQuote.isEmpty()) {
            return HttpResponse.notFound(notFound);
        }
        return HttpResponse.ok(maybeQuote.get());
    }
}
