package per.itachi.java.vertx.springboot.strategy.promise;

import io.vertx.core.Promise;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.itachi.java.vertx.springboot.adapter.db.DbPort;
import per.itachi.java.vertx.springboot.adapter.redis.RedisPort;
import per.itachi.java.vertx.springboot.constant.WebServerUrl;
import per.itachi.java.vertx.springboot.util.MutexUtils;
import per.itachi.java.vertx.springboot.util.VertxUtils;

/**
 * should be executed into worker thread.
 * */
@Slf4j
@Component
public class CacheResultPromise implements RoutePromiseHandler {

    @Autowired
    private RedisPort redisPort;

    @Autowired
    private DbPort dbPort;

    @Override
    public void handle(Promise<CommonDto> promise) {
        // view context
//        VertxUtils.showVertxContext(getClass());
        // mock logic
        Map<String, Object> example = redisPort.getExample();
        while (example == null) {
            Lock lock = MutexUtils.COMMON_LOCK;
            if (lock.tryLock()) {
                log.info("The Thread [{}] acquired lock successfully, and started loading. ", Thread.currentThread().getName());
                try { // not so gracefully
                    example = dbPort.queryExampleBy(UUID.randomUUID().toString());
                    redisPort.putExample(example);
                    log.info("The Thread [{}] loaded and cached data successfully. ", Thread.currentThread().getName());
                }
                finally {
                    // not necessary ?
//                    Condition condition = MutexUtils.COMMON_CONDITION;
//                    condition.signalAll();
                    lock.unlock();
                }
            }
            else {
                log.info("The Thread [{}] failed to acquire lock, and started awaiting. ", Thread.currentThread().getName());
                try {
                    Condition condition = MutexUtils.COMMON_CONDITION;
                    condition.await();
                }
                catch (InterruptedException e) {
                    log.error("The thread [{}] was interrupted. ", Thread.currentThread().getName(), e);
                }
                example = redisPort.getExample();
            }
        }

        CommonDto commonDto = new CommonDto();
        commonDto.setResult(example);
        promise.complete(commonDto);
    }

    @Override
    public String getName() {
        return WebServerUrl.V1_CACHES_KEY;
    }

}
