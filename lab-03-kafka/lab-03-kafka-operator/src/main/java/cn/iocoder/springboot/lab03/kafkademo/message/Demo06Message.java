package cn.iocoder.springboot.lab03.kafkademo.message;

import lombok.Getter;
import lombok.Setter;

/**
 * 示例 06 的 Message 消息
 */
@Setter
@Getter
public class Demo06Message {

    public static final String TOPIC = "DEMO_06";
    /**
     * 编号
     */
    private Integer id;

    @Override
    public String toString() {
        return "Demo06Message{" +
                "id=" + id +
                '}';
    }

}
