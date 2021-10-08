package mn.stock.broker.com.danielprinz;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloWorldController {

    private HelloWorldService service;

    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }

    @Get("/")
    public String index() {
        return service.sayHi();
    }
}
