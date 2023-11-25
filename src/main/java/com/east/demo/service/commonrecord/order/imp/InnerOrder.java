package com.east.demo.service.commonrecord.order.imp;

import com.east.demo.service.commonrecord.order.imp.after.InnerAfterOrderAction;
import com.east.demo.service.commonrecord.order.imp.check.InnerCheckOrderImp;
import com.east.demo.service.commonrecord.order.imp.generate.InnerGenerateOrder;
import com.east.demo.service.commonrecord.order.imp.model.bo.InnerNeededSavedInfo;
import com.east.demo.service.commonrecord.order.imp.model.req.InnerOrderRequest;
import com.east.demo.service.commonrecord.order.imp.save.InnerSaveOrder;
import com.east.demo.service.commonrecord.order.interfac.AbstractOrder;
import org.springframework.stereotype.Service;

/**
 * 内部下单实现类
 *
 * @author: east
 * @date: 2023/11/24
 */
@Service
public class InnerOrder extends AbstractOrder<InnerOrderRequest, InnerNeededSavedInfo> {

    // 对于通配符的对象，需要有明确的类型指定，通常两种（1.这里的直接指定 2.模板中指定） 这里采用模板指定
    // [参考](https://github.com/luyublog/JavaDemo/blob/main/src/usage/advanced/oop/wildcards/imp/save/InnerSaveOrder.java)
//    protected final Generate<InnerOrderRequest, InnerNeededSavedInfo> generate;

    public InnerOrder(InnerCheckOrderImp check,
                      InnerGenerateOrder generate,
                      InnerSaveOrder save,
                      InnerAfterOrderAction afterOrder) {
        super(check, generate, save, afterOrder);
        this.generate = generate;
    }

    @Override
    public void order(InnerOrderRequest innerOrderRequest) {
        try {
            check.commonCheck(innerOrderRequest);
            check.specialCheck(innerOrderRequest);

            InnerNeededSavedInfo innerNeededSavedInfo = generate.generate(innerOrderRequest);

            save.save(innerNeededSavedInfo);

            afterOrder.after(() -> innerNeededSavedInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

