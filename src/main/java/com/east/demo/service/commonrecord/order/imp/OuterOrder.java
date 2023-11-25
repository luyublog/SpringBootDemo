package com.east.demo.service.commonrecord.order.imp;

import com.east.demo.service.commonrecord.order.imp.after.OuterAfterOrderAction;
import com.east.demo.service.commonrecord.order.imp.check.OuterCheckOrderImp;
import com.east.demo.service.commonrecord.order.imp.generate.OuterGenerateOrder;
import com.east.demo.service.commonrecord.order.imp.model.bo.OuterNeededSavedInfo;
import com.east.demo.service.commonrecord.order.imp.model.req.OuterOrderRequest;
import com.east.demo.service.commonrecord.order.imp.save.OuterSaveOrder;
import com.east.demo.service.commonrecord.order.interfac.AbstractOrder;
import org.springframework.stereotype.Service;

/**
 * 内部下单实现类
 *
 * @author: east
 * @date: 2023/11/24
 */
@Service
public class OuterOrder extends AbstractOrder<OuterOrderRequest, OuterNeededSavedInfo> {

    public OuterOrder(OuterCheckOrderImp check,
                      OuterGenerateOrder generate,
                      OuterSaveOrder save,
                      OuterAfterOrderAction afterOrder) {
        super(check, generate, save, afterOrder);
    }

    @Override
    public void order(OuterOrderRequest outerOrderRequest) {
        check.commonCheck(outerOrderRequest);
        check.specialCheck(outerOrderRequest);

        OuterNeededSavedInfo outerNeededSavedInfo = generate.generate(outerOrderRequest);

        save.save(outerNeededSavedInfo);

        afterOrder.after(() -> outerNeededSavedInfo);
    }

}

