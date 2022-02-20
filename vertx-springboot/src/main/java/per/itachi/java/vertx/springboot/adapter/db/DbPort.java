package per.itachi.java.vertx.springboot.adapter.db;

import java.util.Map;

public interface DbPort {

    Map<String, Object> queryExampleBy(String key);
}
