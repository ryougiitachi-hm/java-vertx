package per.itachi.java.vertx.springboot.strategy.controller;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import org.springframework.stereotype.Component;

import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.vertx.springboot.constant.WebServerUrl;

@Slf4j
@Component
public class GetV1PersonsHandler implements ControllerHandler {

	@Override
	public void handle(RoutingContext context) {
		// view context
		Context vertxContext = Vertx.currentContext();
		log.info("The current context,  context.isEventLoopContext={}, context.isWorkerContext={}.",
				vertxContext.isEventLoopContext(), vertxContext.isWorkerContext());
		log.info("The current context,  Context.isOnVertxThread={}, context.isOnEventLoopThread={}, context.isOnWorkerThread={}.",
				Context.isOnVertxThread(), Context.isOnEventLoopThread(), Context.isOnWorkerThread());
		// controller logic
		context.vertx().executeBlocking(promise -> {
			log.info("The current context,  context.isEventLoopContext={}, context.isWorkerContext={}.",
					Vertx.currentContext().isEventLoopContext(), Vertx.currentContext().isWorkerContext());
			log.info("The current context,  Context.isOnVertxThread={}, context.isOnEventLoopThread={}, context.isOnWorkerThread={}.",
					Context.isOnVertxThread(), Context.isOnEventLoopThread(), Context.isOnWorkerThread());
			promise.complete();
		});
		log.info("The invokee {} has personId={}. ", WebServerUrl.V1_PERSONS_PERSON_ID,
				context.request().getParam("personId"));
		context.response().end("OK");
	}

	@Override
	public String getUri() {
		return WebServerUrl.V1_PERSONS_PERSON_ID;
	}
}
