package com.east.demo.service.commonrecord.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

/**
 * s3工具类
 *
 * @author: east
 * @date: 2024/3/23 11:01
 */

@Service
public class S3Util {
    @Autowired
    AmazonS3 amazonS3;

    private static AmazonS3 s3Client;

    @PostConstruct
    public void init() {
        s3Client = amazonS3;
    }

    /**
     * 下载
     *
     * @param bucketName 桶名
     * @param fileKey    key
     */
    public static InputStream downloadFile(String bucketName, String fileKey) {
        GetObjectRequest request = new GetObjectRequest(bucketName, fileKey);
        S3Object response = s3Client.getObject(request);
        return response.getObjectContent();
    }

    /**
     * Bucket列表
     */
    public static List<Bucket> listFile() {
        ListBucketsRequest request = new ListBucketsRequest();
        assert s3Client != null;
        return s3Client.listBuckets(request);
    }

}
