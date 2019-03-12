package in.hocg.message.netty.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * Created by hocgin on 2019/3/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(prefix = "netty")
public class NettyProperties implements Serializable {
    private int port = 18765;
}
