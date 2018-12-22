package com.group2.dataStore.redis;

import com.group2.util.AuraConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by xicheng.dong on 10/23/17.
 */
public class JavaRedisClient {
    private static int MAX_IDLE = 200;
    private static int TIMEOUT = 10000;
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool pool = null;

    public static JedisPoolConfig config() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setTestOnBorrow(TEST_ON_BORROW);
        return config;
    }

    public static JedisPool get() {
        if(pool == null) {
            pool=new JedisPool(config(),
                    AuraConfig.getRoot().getString("redis.redis.server"),
//                    AuraConfig.getRedis().getString("redis.server"),
                    Integer.parseInt(AuraConfig.getRedis().getString("redis.port")),
                    TIMEOUT);

        }
        return pool;
    }
}
