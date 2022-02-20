package per.itachi.java.vertx.springboot.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

@Configuration
public class VertxInitializerConfiguration {
	
	@Autowired
	private List<AbstractVerticle> verticals;
	
	@Bean
	public Vertx vertx() {
		VertxOptions options = new VertxOptions();
		options.setWorkerPoolSize(Runtime.getRuntime().availableProcessors() << 1);
		Vertx vertx = Vertx.vertx();
		for (AbstractVerticle verticle : verticals) {
			DeploymentOptions deploymentOptions = new DeploymentOptions()
					.setInstances(1);
			vertx.deployVerticle(verticle, deploymentOptions);
		}
		return vertx;
	}
}
