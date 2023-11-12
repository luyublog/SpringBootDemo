package com.east.demo.service.commonrecord.download;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.east.demo.model.dto.base.resp.BaseResp;
import com.east.demo.persist.entity.LyUserOrgan;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 混合响应，既包含excel也包含json
     *
     * @return response
     */
    public ResponseEntity<byte[]> getJsonDataWithExcel() {
        // 生成JSON数据
        JSONObject jsonData = new JSONObject();
        jsonData.set("name", "John Doe");
        jsonData.set("age", 30);
        jsonData.set("email", "johndoe@example.com");

        // 生成Excel数据
        log.info("生成Excel数据");
        Workbook workbook;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            ClassPathResource classPathResource = new ClassPathResource("template/excel/模板.xlsx");
            workbook = WorkbookFactory.create(classPathResource.getInputStream());
            // do something
            // 将excel转为字节数组后续导出
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
                    .body(combineExcelAndJson(byteArrayOutputStream.toByteArray(), jsonData.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    /**
     * 将excel和json进行组合  todo boundary规则有问题，需要参考表单请求中的boundary
     *
     * @param excelData excel
     * @param jsonData  json
     * @return result
     * @throws IOException exception
     */
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

    /**
     * 根据参数判断返回的是json还是文件
     */
    public void downloadFileOrGetJson(String fileType, HttpServletResponse response) {
        try {
            if ("json".equalsIgnoreCase(fileType)) {
                // 如果format参数为"json"，则返回JSON对象
                BaseResp jsonResponse = new BaseResp("Hello, JSON!", "1");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSONUtil.toJsonStr(jsonResponse));
            } else {
                // 否则返回字节流（示例中为PDF文件）
                byte[] pdfBytes = generateSamplePDF();
                response.setContentType(MediaType.APPLICATION_PDF_VALUE);
                response.setContentLength(pdfBytes.length);
                response.setHeader("Content-Disposition", "attachment; filename=example.pdf");
                response.getOutputStream().write(pdfBytes);
                response.flushBuffer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 生成一个示例的PDF文件字节数据的方法（假设这里是生成PDF的逻辑）
    private byte[] generateSamplePDF() {
        // 这里省略具体的生成PDF的逻辑，直接返回一个示例的字节数组
        return "Sample PDF Data".getBytes();
    }
}
