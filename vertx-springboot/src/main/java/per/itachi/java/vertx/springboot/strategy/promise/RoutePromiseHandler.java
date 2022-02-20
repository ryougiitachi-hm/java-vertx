package per.itachi.java.vertx.springboot.strategy.promise;

import io.vertx.core.Handler;
import io.vertx.core.Promise;

public interface RoutePromiseHandler extends Handler<Promise<CommonDto>> {

    String getName();
}
