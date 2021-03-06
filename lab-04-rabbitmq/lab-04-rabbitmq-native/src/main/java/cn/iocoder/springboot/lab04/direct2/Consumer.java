package cn.iocoder.springboot.lab04.direct2;

import cn.iocoder.springboot.lab04.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception{
        final Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicConsume("queue1",new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 打印日志
                System.out.println(String.format("[线程：%s][路由键：%s][消息内容：%s]",
                        Thread.currentThread(), envelope.getRoutingKey(), new String(body)));
                // ack 消息已经消费
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
