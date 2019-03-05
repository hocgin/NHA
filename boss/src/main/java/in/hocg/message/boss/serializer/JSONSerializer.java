package in.hocg.message.boss.serializer;


import com.alibaba.fastjson.JSON;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * JSON 序列化算法实现
 *
 * @author hocgin
 */
@SuppressWarnings("all")
public class JSONSerializer implements Serializer {
    
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON.algorithm();
    }
    
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }
    
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}