package cn.org.faster.framework.test.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * @author zhangbowen
 * @since 2019/3/8
 */
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) throws FileNotFoundException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("test","rqw");
        try {
            randomAccessFile.getChannel().read(ByteBuffer.allocate(1000));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpringApplication.run(TestApplication.class, args);
    }
}
