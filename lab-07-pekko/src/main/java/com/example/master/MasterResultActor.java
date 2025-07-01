package com.example.master;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;
import com.example.message.MathCalculate;

/**
 * @author salter
 */
public class MasterResultActor extends UntypedActor {
    @Override
    public void preStart() throws Exception {
        System.out.println("MasterResultActor invoker");
    }
    @Override
    public void onReceive(Object message) {
        MathCalculate math = null;
        if(message instanceof String) {
            String json = (String) message;
            math = JSONObject.parseObject(json, MathCalculate.class);
        } else if(message instanceof MathCalculate) {
            math = (MathCalculate) message;
        }
        assert math != null;
        System.out.println("calculate result: " + math.getNum1() + " " + math.getOp() + " " + math.getNum2() + " = " + math.getResult());
    }
}
