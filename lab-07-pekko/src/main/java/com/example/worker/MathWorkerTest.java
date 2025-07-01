package com.example.worker;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author salter
 */
public class MathWorkerTest {

    public static void main(String[] args) {
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider");
        propMap.put("akka.remote.netty.tcp.hostname", "127.0.0.1");
        propMap.put("akka.remote.netty.tcp.port", 8899);
        Config config = ConfigFactory.parseMap(propMap);

        ActorSystem system = ActorSystem.create("math-worker", config);
        system.actorOf(Props.create(WorkerCalculateActor.class), "worker-actor");
    }
}
