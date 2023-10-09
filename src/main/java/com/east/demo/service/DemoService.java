package com.east.demo.service;

import com.east.demo.dto.LyUserOrganInfo;
import com.east.demo.persist.mapper.LyUserOrganMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

