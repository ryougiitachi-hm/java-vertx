package per.itachi.java.vertx.springboot.adapter.redis;

import java.util.Map;

public interface RedisPort {

    Map<String, Object> getExample();

    void putExample(Map<String, Object> example);
}
