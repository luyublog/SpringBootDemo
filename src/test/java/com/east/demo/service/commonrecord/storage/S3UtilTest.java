package com.east.demo.service.commonrecord.storage;

import com.east.demo.service.util.common.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;


@SpringBootTest
class S3UtilTest {

//    @BeforeAll
//    static void beforeAll() {
//        S3Config s3Config = new S3Config();
//        AmazonS3 amazonS3 = s3Config.getAmazonS3();
//        // 工具类s3只能通过注入初始化，这里没法强行处理，除非反射
//        try {
//            Class<S3Util> s3UtilClass = S3Util.class;
//            Field s3Client = s3UtilClass.getDeclaredField("s3Client");
//            s3Client.setAccessible(true);
//            s3Client.set(s3Client,amazonS3);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    void downloadFile() {
        try {
            InputStream inputStream = S3Util.downloadFile("test", "屏幕截图 2024-01-11 195557.png");
            CommonUtil.saveFile(inputStream, "temp/1.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void list() {
        S3Util.listFile().forEach(System.out::println);
    }
}