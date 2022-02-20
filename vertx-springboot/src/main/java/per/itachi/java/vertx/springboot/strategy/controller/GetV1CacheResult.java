package per.itachi.java.vertx.springboot.strategy.controller;

import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.itachi.java.vertx.springboot.constant.WebServerUrl;
import per.itachi.java.vertx.springboot.strategy.promise.RoutePromiseContext;

@Component
public class GetV1CacheResult implements ControllerHandler {

    @Autowired
    private RoutePromiseContext routePromiseContext;

    @Override
    public String getUri() {
        return WebServerUrl.V1_CACHES_KEY;
    }

    @Override
    public void handle(RoutingContext context) {
        String strKey = context.request().getParam("key");
        context.vertx().executeBlocking(routePromiseContext.getHandler(getUri()));
        context.response().end("cache");
    }
}
