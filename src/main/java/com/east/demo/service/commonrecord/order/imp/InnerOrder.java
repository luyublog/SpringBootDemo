package com.east.demo.service.commonrecord.order.imp;

import com.east.demo.service.commonrecord.order.imp.after.InnerAfterOrderAction;
import com.east.demo.service.commonrecord.order.imp.check.InnerCheckOrderImp;
import com.east.demo.service.commonrecord.order.imp.generate.InnerGenerateOrder;
import com.east.demo.service.commonrecord.order.imp.model.bo.OrderInfo;
import com.east.demo.service.commonrecord.order.imp.model.req.InnerOrderRequest;
import com.east.demo.service.commonrecord.order.imp.save.InnerSaveOrder;
import com.east.demo.service.commonrecord.order.interfac.AbstractOrder;

import java.util.function.Supplier;

/**
 * 内部下单实现类
 *
 * @author: east
 * @date: 2023/11/24
 */
public class InnerOrder extends AbstractOrder<InnerOrderRequest> {

    public InnerOrder(InnerCheckOrderImp check,
                      InnerGenerateOrder generate,
                      InnerSaveOrder save,
                      InnerAfterOrderAction afterOrder) {
        super(check, generate, save, afterOrder);
    }

    @Override
    public void order(InnerOrderRequest innerOrderRequest) {
        try {
            check.commonCheck(innerOrderRequest);
            check.specialCheck(innerOrderRequest);

            Supplier<OrderInfo> orderInfoCopier = innerOrderRequest::generateOrderInfo;
            generate.generateSequence(orderInfoCopier);
            generate.generateRvcNo(orderInfoCopier);

            save.save(orderInfoCopier);

            afterOrder.after(orderInfoCopier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

