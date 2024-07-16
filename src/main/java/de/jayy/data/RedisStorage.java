package de.jayy.data;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

public class RedisStorage implements DataStorage {

    private final Jedis jedis;

    public RedisStorage() {
        JedisClientConfig config = new JedisClientConfig() {
            @Override
            public String getPassword() {
                return System.getenv("REDIS_PASSWORD");
            }
        };

        jedis = new Jedis(System.getenv("REDIS_HOST"), System.getenv("REDIS_PORT") == null ? 6379 : Integer.parseInt(System.getenv("REDIS_PORT")), config);
    }

    @Override
    public String getClearFromMD5(String md5) {
        byte[] bytes = jedis.get(md5.getBytes());
        return bytes == null ? null : new String(bytes);
    }

    @Override
    public void storeMD5(String clear, String md5) {
        jedis.set(md5.getBytes(), clear.getBytes());
    }

    @Override
    public int getMD5Count() {
        return Math.toIntExact(jedis.dbSize());
    }
}
