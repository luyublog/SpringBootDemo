package com.east.demo.service;

import com.east.demo.dto.BaseResp;
import com.east.demo.dto.LyUserOrganInfo;
import com.east.demo.persist.mapper.LyUserOrganMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Created by east on 2023/8/6 18:38.
 */
@Service
public class DemoService {

    @Autowired
    LyUserOrganMapper lyUserOrganMapper;

    public void generateResp(HttpServletResponse response) {
        try {
            String format = "json";
            if ("json".equalsIgnoreCase(format)) {
                // 如果format参数为"json"，则返回JSON对象
                BaseResp jsonResponse = new BaseResp("Hello, JSON!", "1");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponse.toString());
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

    public List<LyUserOrganInfo> demo() {
        Map<Integer, LyUserOrganInfo> idMapLyUserOrgan = lyUserOrganMapper.selectAll();

        List<LyUserOrganInfo> result = new ArrayList<>(idMapLyUserOrgan.size());

        Set<Map.Entry<Integer, LyUserOrganInfo>> entries = idMapLyUserOrgan.entrySet();
        idMapLyUserOrgan.values().forEach(x -> {
            if (x.getFatherId().equals(0)) {
                result.add(x);
            } else {
                LyUserOrganInfo fatherInfo = idMapLyUserOrgan.get(x.getFatherId());
                fatherInfo.getChildren().add(x);
            }

        });

        return result;
    }

//    public static void main(String[] args) {
//        String url="jdbc:oracle:thin:@154.40.46.38:14420:XE";
//        String username="system";
//        String password="Oracle98761234";
//        String driver="oracle.jdbc.OracleDriver";
//        try {
//            Class.forName(driver);
//            Connection con= DriverManager.getConnection(url, username, password);
//            Statement state=con.createStatement();   //容器
//            String sql="select * from user_test";   //SQL语句
//            ResultSet resultSet= state.executeQuery(sql);         //将sql语句上传至数据库执行
//            while (resultSet.next()){
//                System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));
//            }
//            con.close();//关闭通道
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void generateTreeInfo(List<LyUserOrganInfo> lyUserOrganList,
                                  List<LyUserOrganInfo> result,
                                  LyUserOrganInfo rootNode,
                                  int index) {

        if (index >= lyUserOrganList.size()) {
            return;
        }
        LyUserOrganInfo lyUserOrganInfo = lyUserOrganList.get(index);

        LyUserOrganInfo newRootNode = new LyUserOrganInfo();
        // 获取跟节点
        // 如果fatherId为0则说明自身是根节点
        // 如果不为0，且与前一个对象同级，则沿用之前的根节点
        // 如果不为0, 且与前一个对象不同级,则用前一个对象作为根节点
        if (lyUserOrganInfo.getFatherId().equals(0)) {
            result.add(lyUserOrganInfo);
        } else if (lyUserOrganInfo.getFatherId().equals(lyUserOrganList.get(index - 1).getFatherId())) {
            rootNode.getChildren().add(lyUserOrganInfo);
            newRootNode = rootNode;
        } else {
            newRootNode = lyUserOrganList.get(index - 1);
            newRootNode.getChildren().add(lyUserOrganInfo);
        }

        // 下一个
        int newIndex = index + 1;
        generateTreeInfo(lyUserOrganList, result, newRootNode, newIndex);

    }
}

