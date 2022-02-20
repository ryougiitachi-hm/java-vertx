package per.itachi.java.vertx.springboot.util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexUtils {

    public static final Lock COMMON_LOCK = new ReentrantLock();

    public static final Condition COMMON_CONDITION = COMMON_LOCK.newCondition();
}
