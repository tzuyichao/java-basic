package web.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleRest extends AbstractVerticle {
    private static final Logger log = LoggerFactory.getLogger(SimpleRest.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(new VertxOptions());
        vertx.deployVerticle(new SimpleRest());
    }

    private Map<String, JsonObject> products = new ConcurrentHashMap<>();

    @Override
    public void start() {
        // other form "hello, world"
//        vertx.createHttpServer().requestHandler(req -> req.response().end("hello, world!")).listen(8080);
//        log.info("Server Started.");
        setupInitialData();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/products/:productID").handler(this::handleGetProduct);
        router.put("/products/:productID").handler(this::handleAddProduct);
        router.get("/products").handler(this::handleListProducts);

        vertx.createHttpServer().requestHandler(router).listen(8080);
        log.info("Server is Running.");
    }

    private void handleGetProduct(RoutingContext routingContext) {
        log.info("called");
        HttpServerResponse response = routingContext.response();
        response.end("Hello, World!");
    }

    private void handleAddProduct(RoutingContext routingContext) {
        log.info("called");
        HttpServerResponse response = routingContext.response();
        response.end("Hello, World!");
    }

    public void handleListProducts(RoutingContext routingContext) {
        log.info("called");
        HttpServerResponse response = routingContext.response();
        response.end("Hello, World!");
    }

    private void setupInitialData() {
        addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
        addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
        addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
    }

    private void addProduct(JsonObject product) {
        products.put(product.getString("id"), product);
    }
}
