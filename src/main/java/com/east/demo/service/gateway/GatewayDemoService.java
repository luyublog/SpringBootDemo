package com.east.demo.service.gateway;

import com.east.demo.dto.download.DownloadHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 配合gateway上相关逻辑的接口
 * @author: east
 * @date: 2023/9/15
 */

@Slf4j
@Service
public class GatewayDemoService {
    public String formData(String json, MultipartFile multipartFile) {
        log.info("json数据为:{}", json);


//        List<LyUserOrgan> excelInfo = parseExcel(multipartFile);
        parseExcel(multipartFile);
//        log.info("excel数据为:{}", excelInfo.get(0).toString());

        return json;
    }

    /**
     * easyExcel解析的数据对应的对象是Header类，靠
     *
     * @param multipartFile file
     */
    private void parseExcel(MultipartFile multipartFile) {
//        try {
//            EasyExcel.read(multipartFile.getInputStream(), DownloadHeader.class, new DemoDataListener())
//                    .sheet().doRead();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void save(List<DownloadHeader> cachedDataList) {
        // save msg to db
    }
}
