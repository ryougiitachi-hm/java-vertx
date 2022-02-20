package per.itachi.java.vertx.springboot.strategy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerContext {
	
	@Autowired
	private List<ControllerHandler> handlers;
	
	private Map<String, ControllerHandler> mapHandler;
	
	@PostConstruct
	private void init() {
		mapHandler = new HashMap<>();
		for (ControllerHandler handler : handlers) {
			mapHandler.put(handler.getUri(), handler);
		}
	}
	
	public ControllerHandler getHandler(String uri) {
		return mapHandler.get(uri);
	}

}
