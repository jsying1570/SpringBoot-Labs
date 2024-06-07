package cn.iocoder.springboot.lab03.kafkademo.message;

import lombok.Getter;
import lombok.Setter;

/**
 * 示例 01 的 Message 消息
 */
@Setter
@Getter
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";
    /**
     * 编号
     */
    private Integer id;

    @Override
    public String toString() {
        return "Demo01Message{" +
                "id=" + id +
                '}';
    }

}
