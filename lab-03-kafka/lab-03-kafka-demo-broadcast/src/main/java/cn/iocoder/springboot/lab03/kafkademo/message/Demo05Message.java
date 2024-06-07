package cn.iocoder.springboot.lab03.kafkademo.message;

import lombok.Getter;
import lombok.Setter;

/**
 * 示例 05 的 Message 消息
 */
@Setter
@Getter
public class Demo05Message {

    public static final String TOPIC = "DEMO_05";
    /**
     * 编号
     */
    private Integer id;

    @Override
    public String toString() {
        return "Demo05Message{" +
                "id=" + id +
                '}';
    }

}
