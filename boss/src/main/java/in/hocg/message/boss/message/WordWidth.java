package in.hocg.message.boss.message;

/**
 * Created by hocgin on 2019/3/5.
 * email: hocgin@gmail.com
 * 字宽
 *
 * 魔数(4) | 版本号(1) | 序列化算法(1) | 指令(1) | 数据长度(4) | 数据(n)
 *
 * @author hocgin
 */
public interface WordWidth {
    int MAGIC_NUMBER = 4;
    int VERSION = 1;
    int SERIALIZER_ALGORITHM = 1;
    int COMMEND = 1;
    int DATA_LENGTH = 1;
}
