package cn.iocoder.springboot.lab03.kafkademo.message;

import lombok.Getter;
import lombok.Setter;

/**
 * 示例 04 的 Message 消息
 */
@Setter
@Getter
public class Demo04Message {

    public static final String TOPIC = "DEMO_04";
    /**
     * 编号
     */
    private Integer id;

    @Override
    public String toString() {
        return "Demo04Message{" +
                "id=" + id +
                '}';
    }

}
