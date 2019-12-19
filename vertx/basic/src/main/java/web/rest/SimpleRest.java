package web.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
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
        log.info("handleGetProduct called");
        String productId = routingContext.request().getParam("productId");
        HttpServerResponse response = routingContext.response();
        if(productId == null) {
            sendError(400, "product id missing", response);
        } else {
            JsonObject product = products.get(productId);
            if(product == null) {
                sendError(404, "product not found", response);
            } else {
                response.putHeader("content-type", "application/json").end(product.encodePrettily());
            }
        }
    }

    private void handleAddProduct(RoutingContext routingContext) {
        log.info("handleAddProduct called");
        String productId = routingContext.request().getParam("productId");
        HttpServerResponse response = routingContext.response();
        if(productId == null) {
            sendError(400, "product id missing", response);
        } else {
            JsonObject product = routingContext.getBodyAsJson();
            if(product == null) {
                sendError(400, "body not found", response);
            } else {
                products.put(productId, product);
                response.end();
            }
        }
    }

    public void handleListProducts(RoutingContext routingContext) {
        log.info("handleListProducts called");
        HttpServerResponse response = routingContext.response();
        JsonArray arr = new JsonArray();
        products.forEach((k, v) -> arr.add(v));
        response.putHeader("content-type", "application/json").end(arr.encodePrettily());
    }

    private void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode);
    }

    private void sendError(int statusCode, String message, HttpServerResponse response) {
        response.setStatusCode(statusCode).putHeader("content-type", "application/json").end(new JsonObject().put("message", message).encodePrettily());
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
