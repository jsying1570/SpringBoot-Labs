package cn.iocoder.springboot.lab03.kafkademo.message;


import lombok.Getter;
import lombok.Setter;

/**
 * 示例 01 的 Message 消息
 */
@Setter
@Getter
public class Demo01Message {

    public static final String TOPIC = "demo01";
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
