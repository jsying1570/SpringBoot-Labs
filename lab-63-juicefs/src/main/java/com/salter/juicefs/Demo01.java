package com.salter.juicefs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @author salter
 */
public class Demo01 {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.jfs.impl", "io.juicefs.JuiceFileSystem");
        // JuiceFS 元数据引擎地址
        conf.set("juicefs.meta", "redis://:123456.CKG@172.16.0.35:6379/3");
        conf.set("juicefs.superuser","salter");
//        conf.set("juicefs.users","file:///Users/salter/juicefs_name");
//        conf.set("juicefs.groups","file:///Users/salter/juicefs_group");
        // 请替换 {JFS_NAME} 为正确的值
        Path p = new Path("jfs://juicefs-test3/");
        FileSystem jfs = p.getFileSystem(conf);
        //上传文件
//        jfs.copyFromLocalFile(new Path("/Users/salter/test.txt"),new Path("jfs://juicefs-test3/test.txt"));
        String scheme = jfs.getScheme();
        System.err.println(scheme);


        FileStatus[] fileStatuses = jfs.listStatus(p);
        // 遍历 JuiceFS 文件系统并打印文件路径
        for (FileStatus status : fileStatuses) {
            System.err.println(status.getPath());
        }
    }
}
