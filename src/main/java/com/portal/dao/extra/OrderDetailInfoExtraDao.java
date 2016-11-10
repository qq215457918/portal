package com.portal.dao.extra;

import com.portal.bean.Criteria;
import com.portal.bean.result.ReceptionInfoForm;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailInfoExtraDao {

    /**
     * @Title: getOrderDetailCounts 
     * @Description: 根据条件获取登门出单详细数据总条数
     * @param criteria
     * @return int
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午11:57:02 
     * @version V1.0
     */
    int getOrderDetailCounts(Criteria criteria);

    /**
     * @Title: getOrderDetailsByCondition 
     * @Description: 根据条件获取登门出单详细数据
     * @param criteria
     * @return List<ReceptionInfoForm>
     * @author Xia ZhengWei
     * @date 2016年10月17日 下午11:57:29 
     * @version V1.0
     */
    List<ReceptionInfoForm> getOrderDetailsByCondition(Criteria criteria);

    /**
     * 为了定金查询订单的总金额
     * @param criteria
     * @return
     */
    Long countPrice4Deposit(Criteria criteria);

}
