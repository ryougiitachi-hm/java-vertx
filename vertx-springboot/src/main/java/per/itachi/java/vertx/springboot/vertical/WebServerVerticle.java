package per.itachi.java.vertx.springboot.vertical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;
import per.itachi.java.vertx.springboot.constant.WebServerUrl;
import per.itachi.java.vertx.springboot.strategy.controller.ControllerContext;
import per.itachi.java.vertx.springboot.util.VertxUtils;


@Slf4j
@Component
public class WebServerVerticle extends AbstractVerticle {

	@Autowired
	private ControllerContext controllerContext;

	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
		log.info("Initialised EligibilityVertical, Vertx={}, Context={}. ", vertx, context);
	}

	@Override
	public JsonObject config() {
		return super.config();
	}

	@Override
	public void start() throws Exception {
		// super.start(); // empty method.
		Router router = Router.router(this.vertx);
		// returns html with 404 resource not found if url not found
		router.get(WebServerUrl.V1_ELIGIBILITY)
				.handler(controllerContext.getHandler(WebServerUrl.V1_ELIGIBILITY));
		router.get(WebServerUrl.V1_PERSONS_PERSON_ID)
				.handler(controllerContext.getHandler(WebServerUrl.V1_PERSONS_PERSON_ID));
		router.get(WebServerUrl.V1_CACHES_KEY)
				.handler(controllerContext.getHandler(WebServerUrl.V1_CACHES_KEY));
		vertx.createHttpServer()
				.requestHandler(router)
				.listen(8080);
		VertxUtils.showVertxContext(getClass());
//		vertx.eventBus().send()
	}
}
