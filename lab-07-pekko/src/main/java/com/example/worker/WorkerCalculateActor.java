package com.example.worker;

import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;
import com.example.message.MathCalculate;

public class WorkerCalculateActor extends UntypedActor {

    @Override
    public void onReceive(Object message) {
        // System.out.println("sender info : " + getSender().path().toString());

        MathCalculate math = null;
        if(message instanceof String) {
            String json = (String) message;
            math = JSONObject.parseObject(json, MathCalculate.class);
        } else if(message instanceof MathCalculate) {
            math = (MathCalculate) message;
        }

        if(math != null) {
            int result = 0;
            switch (math.getType()) {
                case ADD:
                    result = math.getNum1() + math.getNum2();
                    break;
                case SUBTRACT:
                    result = math.getNum1() - math.getNum2();
                    break;
                case DIVIDE:
                    result = math.getNum1() / math.getNum2();
                    break;
                case MULTIPLY:
                    result = math.getNum1() * math.getNum2();
                    break;
                case RESULT:
                    result = math.getResult();
                    break;
            }
            if(math.getType() != MathCalculate.OperateEnum.RESULT) {
                MathCalculate rs = new MathCalculate(math.getNum1(), math.getNum2(), result, MathCalculate.OperateEnum.RESULT);
                rs.setOp(math.getType().getVal());
                //获取消息发送者的actor，this.getSelf()获取自身的actor
                getSender().tell(rs, this.getSelf());

                System.out.println("worker calculator: " + math.getNum1() + " " + math.getType().getVal() + " " + math.getNum2() + " = " + result);
            }
        }
    }
}
