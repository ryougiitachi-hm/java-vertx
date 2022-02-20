package per.itachi.java.vertx.springboot.util;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

@Slf4j
public class VertxUtils {

    public static void showVertxContext() {
        Context vertxContext = Vertx.currentContext();
        if (vertxContext == null) {
            // context will be null if the current context is not in vertx.
            return;
        }
        log.info("The current context,  context.isEventLoopContext={}, context.isWorkerContext={}.",
                vertxContext.isEventLoopContext(), vertxContext.isWorkerContext());
        log.info("The current context,  Context.isOnVertxThread={}, Context.isOnEventLoopThread={}, Context.isOnWorkerThread={}.",
                Context.isOnVertxThread(), Context.isOnEventLoopThread(), Context.isOnWorkerThread());
    }

    public static void showVertxContext(Class<?> clazz) {
        Context vertxContext = Vertx.currentContext();
        if (vertxContext == null) {
            // context will be null if the current context is not in vertx.
            return;
        }
        log.info("The current context for {},  context.isEventLoopContext={}, context.isWorkerContext={}.",
                clazz.getSimpleName(), vertxContext.isEventLoopContext(), vertxContext.isWorkerContext());
        log.info("The current context for {},  Context.isOnVertxThread={}, Context.isOnEventLoopThread={}, Context.isOnWorkerThread={}.",
                clazz.getSimpleName(), Context.isOnVertxThread(), Context.isOnEventLoopThread(), Context.isOnWorkerThread());
    }

    public static void showVertxContext(Marker marker) {
        Context vertxContext = Vertx.currentContext();
        if (vertxContext == null) {
            // context will be null if the current context is not in vertx.
            return;
        }
        log.info(marker, "The current context,  context.isEventLoopContext={}, context.isWorkerContext={}.",
                vertxContext.isEventLoopContext(), vertxContext.isWorkerContext());
        log.info(marker, "The current context,  Context.isOnVertxThread={}, Context.isOnEventLoopThread={}, Context.isOnWorkerThread={}.",
                Context.isOnVertxThread(), Context.isOnEventLoopThread(), Context.isOnWorkerThread());
    }
}
