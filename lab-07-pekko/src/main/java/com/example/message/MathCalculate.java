package com.example.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author salter
 */
@Setter
@Getter
public class MathCalculate implements Serializable {
    /**
     * 数字1
     */
    private int num1;
    /**
     * 数字2
     */
    private int num2;
    /**
     * 计算结果
     */
    private int result;
    /**
     * 操作类型
     */
    private OperateEnum type;
    /**
     * 计算结果对应的运算符
     */
    private String op;


    public MathCalculate(int result, OperateEnum type) {
        this.result = result;
        this.type = type;
    }

    public MathCalculate(int num1, int num2, OperateEnum type) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
    }

    public MathCalculate(int num1, int num2, int result, OperateEnum type) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.type = type;
    }

    @Getter
    public static enum OperateEnum {
        /**
         * 加法
         */
        ADD("+"),
        /**
         * 减法
         */
        SUBTRACT("-"),
        /**
         * 乘法
         */
        MULTIPLY("*"),
        /**
         * 除法
         */
        DIVIDE("/"),
        /**
         * 结果
         */
        RESULT("="),
        ;
        private final String val;
        OperateEnum(String val) {
            this.val = val;
        }
    }
}
