package com.east.demo.service.commonrecord.order.imp;

import com.east.demo.service.commonrecord.order.imp.after.OuterAfterOrderAction;
import com.east.demo.service.commonrecord.order.imp.check.OuterCheckOrderImp;
import com.east.demo.service.commonrecord.order.imp.generate.OuterGenerateOrder;
import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.imp.model.req.OuterOrderRequest;
import com.east.demo.service.commonrecord.order.imp.save.InnerSaveOrder;
import com.east.demo.service.commonrecord.order.interfac.AbstractOrder;

import java.util.function.Supplier;

/**
 * 内部下单实现类
 *
 * @author: east
 * @date: 2023/11/24
 */
public class OuterOrder extends AbstractOrder<OuterOrderRequest> {

    public OuterOrder(OuterCheckOrderImp check,
                      OuterGenerateOrder generate,
                      InnerSaveOrder save,
                      OuterAfterOrderAction afterOrder) {
        super(check, generate, save, afterOrder);
    }

    @Override
    public void order(OuterOrderRequest outerOrderRequest) {
        check.commonCheck(outerOrderRequest);
        check.specialCheck(outerOrderRequest);

        Supplier<OrderInfo> orderInfoCopier = outerOrderRequest::generateOrderInfo;
        generate.generateSequence(orderInfoCopier);
        generate.generateRvcNo(orderInfoCopier);

        save.save(orderInfoCopier);

        afterOrder.after(orderInfoCopier);
    }

}

