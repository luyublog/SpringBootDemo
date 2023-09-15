package com.east.demo.service.download;

import cn.hutool.json.JSONObject;
import com.alibaba.excel.EasyExcel;
import com.east.demo.dto.download.DownloadHeader;
import com.east.demo.persist.entity.LyUserOrgan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: east
 * @date: 2023/9/13
 */
@Service
@Slf4j
public class DownloadDemoService {

    public ResponseEntity<byte[]> getDataWithExcel() {
        // 生成JSON数据
        JSONObject jsonData = new JSONObject();
        jsonData.set("name", "John Doe");
        jsonData.set("age", 30);
        jsonData.set("email", "johndoe@example.com");

        // 生成Excel数据
        log.info("生成Excel数据");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, DownloadHeader.class)
                .sheet("Data")
//                .head(String.class)
                .doWrite(createExcelData());

        // 设置响应头，指定附件的文件名
        log.info("设置响应头，指定附件的文件名");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_MIXED);
        // 添加Content-Disposition，可以指定文件名
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.xlsx");


        // 创建一个包含Excel和JSON数据的响应实体
        ResponseEntity<byte[]> responseEntity = null;
        try {
            responseEntity = ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(combineExcelAndJson(outputStream.toByteArray(), jsonData.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseEntity;
    }

    private byte[] combineExcelAndJson(byte[] excelData, String jsonData) throws IOException {
        ByteArrayOutputStream combinedOutputStream = new ByteArrayOutputStream();

        // 将Excel数据写入输出流
        combinedOutputStream.write(excelData);

        // 添加分隔符
        combinedOutputStream.write(("--boundary\r\n").getBytes());
        combinedOutputStream.write(("Content-Type: application/json\r\n\r\n").getBytes());

        // 将JSON数据写入输出流
        combinedOutputStream.write(jsonData.getBytes());

        // 添加结束分隔符
        combinedOutputStream.write(("\r\n--boundary--\r\n").getBytes());

        return combinedOutputStream.toByteArray();
    }

    private List<String> createExcelHeader() {
//        ArrayList<String> arrayList=new ArrayList();
//        arrayList.add("ID");
//        arrayList.add("NAME");
//        arrayList.add("FATHER_ID");
//        return new ArrayList<>(){{
//            add(arrayList);
//        }};
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("ID");
        arrayList.add("NAME");
        arrayList.add("FATHER_ID");
        return arrayList;

    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("ID");
        List<String> head1 = new ArrayList<String>();
        head1.add("NAME");
        List<String> head2 = new ArrayList<String>();
        head2.add("FATHER_ID");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<LyUserOrgan> createExcelData() {
        ArrayList<LyUserOrgan> arrayList = new ArrayList();

        arrayList.add(new LyUserOrgan("1", "name1", "1"));
        arrayList.add(new LyUserOrgan("2", "name2", "2"));

        return arrayList;
    }
}
