package com.example.master;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.message.MathCalculate;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

public class MasterCalculateTest {

    public static void main(String[] args) {
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider");
        propMap.put("akka.remote.netty.tcp.hostname", "127.0.0.1");
        propMap.put("akka.remote.netty.tcp.port", 8989);

        Config config = ConfigFactory.parseMap(propMap);
        ActorSystem system = ActorSystem.create("master-server", config);
        //实例化一个actor
        ActorRef masterActor = system.actorOf(Props.create(MasterResultActor.class), "master-actor");
        //从actorSystem中获取一个actor或者远程的actor，这里获取worker的actor
        /*
         * 1、akka.tcp ： 这个格式是固定的协议头部。
         * 2、math-worker ： 这个是不确定的，它其实就是ActorSystem创建时指定的名称，如果名称不同在构建url时也要进行相应的修改。
         * 3、127.0.0.1:8899 ： 这个是服务的IP和端口号，在构建ActorSystem时指定。
         * 4、/user/ ： 固定格式。
         * 5、worker-actor ： 注册ActorRef时指定的actor名称，actor就是根据这个名称查找。
         */
        ActorSelection actorSelection = system.actorSelection("akka.tcp://math-worker@127.0.0.1:8899/user/worker-actor");
        for(int i =0; i < 5; i++) {
            final int num = i;
            new Thread(() -> actorSelection.tell(new MathCalculate(num, num, MathCalculate.OperateEnum.ADD), masterActor)).start();
        }
    }
}
