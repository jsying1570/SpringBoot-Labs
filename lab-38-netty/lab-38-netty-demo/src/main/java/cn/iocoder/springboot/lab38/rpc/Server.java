package cn.iocoder.springboot.lab38.rpc;

import java.io.IOException;

public interface Server {
    void stop();

    void start() throws IOException, IOException;

    void register(Class<?> serviceInterface, Class<?> impl);

    boolean isRunning();

    int getPort();
}