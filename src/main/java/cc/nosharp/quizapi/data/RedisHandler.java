package cc.nosharp.quizapi.data;


import cc.nosharp.quizapi.datamodels.QuestionList;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisHandler {

    private static RedisHandler INSTANCE;


    private JedisPool jedisPool;

    private RedisHandler(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(16); // Max amount of workers accessing the redis database.
        config.setMaxIdle(8); // Maximum amount of idle workers.
        config.setMinIdle(1); // Minimum amount of idle workers.
        config.setMaxWaitMillis(3000); // Waiting for a free connection.

        this.jedisPool = new JedisPool(config, "127.0.0.1");
    }

    /**
     * Get's an instance of the redis data source.
     * @return The instance of the RedisDataSource
     */
    public static RedisHandler getDataSource(){
        if(INSTANCE == null){
            INSTANCE = new RedisHandler();
        }
        return INSTANCE;
    }

    private String getGameKeyFromUUID(String uuid){
        return "GameKey." + uuid;
    }

    /**
     * Checks if a game by a UUID exists.
     * @param uuid Checks if the gameID exists.
     * @return Whether or not a game exists with the given UUID.
     */
    public boolean doesUUIDExist(String uuid){
        return this.jedisPool.getResource().exists(this.getGameKeyFromUUID(uuid));
    }


    /**
     *
     * @param uuid The UUID of the quiz game.
     * @return The questions from the game.
     */
    public QuestionList getQuestionFromUUID(String uuid){
        return null;
    }

}
