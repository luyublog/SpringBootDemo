package com.east.demo.pojo.dto;

import com.east.demo.persist.entity.LyUserOrgan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author east
 * @date 2023/8/19 13:48.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LyUserOrganInfo extends LyUserOrgan {
    private List<LyUserOrganInfo> children = new ArrayList<>();
}

