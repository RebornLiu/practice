package com.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class FastJsonSerializer<T> implements RedisSerializer<T> {

    private Class clz;
    public FastJsonSerializer(Class clz) {
       super();
       this.clz = clz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return new byte[0];
        }
        return JSON.toJSONString(t).getBytes(Charset.defaultCharset());
        //return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(Charset.defaultCharset());
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        //String str = new String(bytes, Charset.defaultCharset());
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String str = new String(bytes, Charset.defaultCharset());
        return (T)JSON.parseObject(str, clz);
    }
}
