package com.majianwei.redis.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * pool：
 * get和set
 */
public class RedisUtil {

    static JedisPool jedisPool = null;

    static {
        GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
        //参数设置:
        poolConfig.setMaxTotal(30);//最大连接数
        poolConfig.setMaxIdle(10);//最大空闲数
        poolConfig.setMaxWaitMillis(3*1000);//超时时间
        poolConfig.setTestOnBorrow(true);//借的时候进行测试

        //你在做的时候,应该丢到properties的配置文件:直接用这个工具类
        String host = "127.0.0.1";
        int port = 6379;//redis默认端口
        int timeout = 5 * 1000;
        String password = "redispassword";
        jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
    }

    //get和set
    public  static void set(String key,String value){
        Jedis jedis=null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public  static String get(String key){
        Jedis jedis=null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
