package per.itachi.java.vertx.springboot.adapter.db;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.helpers.BasicMarker;
import org.springframework.stereotype.Component;
import per.itachi.java.vertx.springboot.util.VertxUtils;

@Slf4j
@Component
public class DbMockAdapter implements DbPort{

    private static final long DELAY_MS_QUERY_EXAMPLE_BY = 15 * 1000l;

    @Override
    public Map<String, Object> queryExampleBy(String key) {
        // view context
        VertxUtils.showVertxContext(getClass());
        // delay ms
        try {
            TimeUnit.MILLISECONDS.sleep(DELAY_MS_QUERY_EXAMPLE_BY);
        }
        catch (InterruptedException e) {
            log.error("The thread [{}] was interrupted. ", Thread.currentThread().getName(), e);
        }
        // mock logic
        Map<String, Object> example = new HashMap<>();
        example.put("orderNo", UUID.randomUUID().toString());
        example.put("price", ThreadLocalRandom.current().nextInt(50));
        example.put("count", ThreadLocalRandom.current().nextInt(50));
        return example;
    }
}
