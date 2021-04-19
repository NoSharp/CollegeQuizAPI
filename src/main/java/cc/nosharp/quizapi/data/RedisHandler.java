package cc.nosharp.quizapi.data;


import cc.nosharp.quizapi.datamodels.QuestionList;
import cc.nosharp.quizapi.datamodels.QuestionListProtos;
import com.google.protobuf.TextFormat;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

public class RedisHandler {

    private static RedisHandler INSTANCE;

    private JedisPool jedisPool;

    private RedisHandler(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(16); // Max amount of workers accessing the redis database.
        config.setMaxIdle(8); // Maximum amount of idle workers.
        config.setMinIdle(1); // Minimum amount of idle workers.
        config.setMaxWaitMillis(3000); // Waiting for a free connection.

        this.jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * Used for testing, and testing only.
     */
    private RedisHandler(JedisPool pool){
        this.jedisPool = pool;
    }

    /**
     * Get's an instance of the redis data source.
     * @return The instance of the RedisDataSource
     */
    public static RedisHandler getRedisHandler(){
        if(INSTANCE == null){
            INSTANCE = new RedisHandler();
        }
        return INSTANCE;
    }


    public static RedisHandler setupTestingHandler(JedisPool pool){
        if(INSTANCE == null){
            INSTANCE = new RedisHandler(pool);
        }
        return INSTANCE;
    }

    public static RedisHandler getTestingInstance(){
        return INSTANCE;
    }


    public String getGameKeyFromUUID(String uuid){
        return "GameKey." + uuid;
    }

    /**
     * Checks if a game by a UUID exists.
     * @param uuid Checks if the gameID exists.
     * @return Whether or not a game exists with the given UUID.
     */
    public boolean doesUUIDExist(String uuid){
        Jedis jedis = this.jedisPool.getResource();
        boolean exists = jedis.exists(uuid);
        jedis.close();
        return exists;
    }

    /**
     * Generates a new UUID that currently doesn't exist in the redis db.
     * @return The free UUID
     */
    public String getNewUUID(){
        String uuid = UUID.randomUUID().toString();
        while(this.doesUUIDExist(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void deleteGameUUID(String uuid){
        Jedis jedis = this.jedisPool.getResource();
        jedis.del(uuid);
        jedis.close();
    }

    /**
     * Get's a QuestionList from a Game UUID.
     * @param uuid The UUID of the quiz game.
     * @return The questions from the game.
     */
    public QuestionList getQuestionFromUUID(String uuid){

        Jedis jedis = this.jedisPool.getResource();
        String value = jedis.get(uuid);
        jedis.close();
        QuestionListProtos.QuestionListProto proto = null;
        try {
            //https://stackoverflow.com/questions/28890646/protocol-buffer-parsefromstring-in-java-for-parsing-text-format
            QuestionListProtos.QuestionListProto.Builder builder = QuestionListProtos.QuestionListProto.newBuilder();
            builder.clear();
            TextFormat.merge(value, builder);
            proto = builder.build();
        } catch (TextFormat.ParseException e) {
            e.printStackTrace();
        }

        assert proto != null;

        return QuestionList.fromProtoBuffer(this.getGameKeyFromUUID(uuid), proto);
    }

    /**
     * Inserts the Protocol Buffer encoded List into the redis database.
     * @param list THe Question List
     */
    public void insertNewQuestionList(QuestionList list){

        Jedis jedis = this.jedisPool.getResource();
        jedis.set(list.getQuestionListUUID(), list.toProtocolBuffer());
        jedis.close();
    }

}
