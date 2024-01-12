
package com.crio.qeats.configs;

import java.time.Duration;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;


@Configuration
public class RedisConfiguration {

  // TODO: CRIO_TASK_MODULE_REDIS
  // The Jedis client for Redis goes through some initialization steps before you can
  // start using it as a cache.
  // Objective:
  // Some methods are empty or partially filled. Make it into a working implementation.
  public static final String redisHost = "localhost";

  // Amount of time after which the redis entries should expire.
  public static final int REDIS_ENTRY_EXPIRY_IN_SECONDS = 3600;

  // TIP(MODULE_RABBITMQ): RabbitMQ related configs.
  public static final String EXCHANGE_NAME = "rabbitmq-exchange";
  public static final String QUEUE_NAME = "rabbitmq-queue";
  public static final String ROUTING_KEY = "qeats.postorder";


  private int redisPort;
  private JedisPool jedisPool;


  @Value("${spring.redis.port}")
  public void setRedisPort(int port) {
    System.out.println("setting up redis port to " + port);
    redisPort = port;
  }

  public JedisPool getJedisPool() {
    return jedisPool;
  }

  /**
   * Initializes the cache to be used in the code.
   * TIP: Look in the direction of `JedisPool`.
   */
  @PostConstruct
  public void initCache() {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMinEvictableIdleTimeMillis(REDIS_ENTRY_EXPIRY_IN_SECONDS*1000);
    jedisPool = new JedisPool(poolConfig, redisHost, redisPort);
  }

  public void insertData(String key, String value) {
    try (Jedis jedis = getJedisPool().getResource()) {
      jedis.append(key, value);
    } catch (JedisException e) {}
  }

  public String getData(String key) {
    try (Jedis jedis = getJedisPool().getResource()) {
      return jedis.get(key);
    } catch (JedisException e) {
      return null;
    }
  }
  
  /**
   * Checks is cache is intiailized and available.
   * TIP: This would generally mean checking via {@link JedisPool}
   * @return true / false if cache is available or not.
   */
  public boolean isCacheAvailable() {
    try (Jedis jedis = getJedisPool().getResource()) {
      // Try to get a connection from the pool
      // If successful, the pool is initialized and operational
      return true;
    } catch (JedisException e) {
        // An exception occurred, indicating a problem with cache initialization
        return false;
    }
  }

  /**
   * Destroy the cache.
   * TIP: This is useful if cache is stale or while performing tests.
   */
  @PreDestroy
  public void destroyCache() {
    try (Jedis jedis = getJedisPool().getResource()) {
      // Use the FLUSHDB command to clear the current database
      jedis.flushDB();
    } catch (JedisException e) {
        // An exception occurred, indicating a problem with cache clearing
        e.printStackTrace();
    }
  }

}

