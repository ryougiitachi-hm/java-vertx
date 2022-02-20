package per.itachi.java.vertx.springboot.adapter.redis;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import per.itachi.java.vertx.springboot.util.VertxUtils;

@Slf4j
@Component
public class RedisMockAdapter implements RedisPort{

    private Map<String, Object> example;

    private Timer timer;

    @PostConstruct
    public void init() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new CacheClearTask(this), 0, 25 * 1000l);
    }

    @Override
    public Map<String, Object> getExample() {
        // view context
        VertxUtils.showVertxContext(getClass());
        return this.example;
    }

    @Override
    public void putExample(Map<String, Object> example) {
        // view context
        VertxUtils.showVertxContext(getClass());
        this.example = example;
    }
}

@Slf4j
class CacheClearTask extends TimerTask {

    private RedisPort redisPort;

    public CacheClearTask(RedisPort redisPort) {
        this.redisPort = redisPort;
    }

    @Override
    public void run() {
        redisPort.putExample(null);
        log.info("Cache has been clear already. ");
    }

}
