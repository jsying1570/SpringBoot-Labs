package cn.iocoder.springboot.lab38.netty.protocoltcp;


import lombok.Getter;
import lombok.Setter;

//协议包
@Setter
@Getter
public class MessageProtocol {
    private int len; //关键
    private byte[] content;
}
