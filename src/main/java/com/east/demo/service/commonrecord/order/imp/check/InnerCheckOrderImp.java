package com.east.demo.service.commonrecord.order.imp.check;

import com.east.demo.service.commonrecord.order.imp.model.req.InnerOrderRequest;
import com.east.demo.service.commonrecord.order.interfac.Check;
import org.springframework.stereotype.Component;

/**
 * 账单核对接口
 *
 * @author: east
 * @date: 2023/11/23
 */
@Component
public class InnerCheckOrderImp implements Check<InnerOrderRequest> {
    @Override
    public void commonCheck(InnerOrderRequest orderRequest) {
        // todo 公用和常识性问题处理
    }

    @Override
    public void specialCheck(InnerOrderRequest orderRequest) {
        // todo 渠道特殊校验
    }
}
