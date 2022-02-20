package per.itachi.java.vertx.springboot.strategy.promise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutePromiseContext {

    @Autowired
    private List<RoutePromiseHandler> handlers;

    private Map<String, RoutePromiseHandler> mapHandler;

    @PostConstruct
    private void init() {
        Map<String, RoutePromiseHandler> mapHandler = new HashMap<>();
        mapHandler = new HashMap<>();
        for (RoutePromiseHandler handler : handlers) {
            mapHandler.put(handler.getName(), handler);
        }
        this.mapHandler = mapHandler;
    }

    public RoutePromiseHandler getHandler(String name) {
        return mapHandler.get(name);
    }
}
