package per.itachi.java.vertx.springboot.strategy.controller;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public interface ControllerHandler extends Handler<RoutingContext> {
	
	String getUri();

}
