package com.portal.dao.extra;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import com.portal.bean.result.CustomerSimpleInfoForm;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoExtraDao {

    /**
     * 更新商品信息
     * @param criteria
     * @return
     */
    int updateProduct(Criteria criteria);

    /**
     * 更新赠品信息
     * @param criteria
     * @return
     */
    int updateGift(Criteria criteria);

    /**
     * 开始接待更新客户信息表
     * @param criteria
     * @return
     */
    int updateReceiverStaff(Criteria criteria);

    /**
     * 根据电话号码查询用户信息
     * by meng.yue
     */
    CustomerInfo selectByPhone(String phone);

    /**
     * @Title: getFiltrateCustomers 
     * @Description: 根据条件获取筛选客户数据
     * @param criteria
     * @return List<CustomerSimpleInfoForm>
     * @author Xia ZhengWei
     * @date 2016年10月20日 下午11:11:18 
     * @version V1.0
     */
    List<CustomerSimpleInfoForm> getFiltrateCustomers(Criteria criteria);

    /**
     * @Title: getCustomerCounts 
     * @Description: 根据起始日期条件查询接待客户数量
     * @param example
     * @return Map<String,Integer>
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午8:21:49 
     * @version V1.0
     */
    Map<String, Integer> getCustomerCounts(Criteria example);

    /**
     * @Title: getAllCategoryCustomer 
     * @Description: 获取所有各种分类对应的客户数量
     * @param example
     * @return Map<String,Object>
     * @author Xia ZhengWei
     * @date 2016年10月29日 下午8:21:59 
     * @version V1.0
     */
    Map<String, Object> getAllCategoryCustomer(Criteria example);

    /**
     * @Title: countByConditions 
     * @Description: 后台管理根据条件查询客户总数量
     * @param example
     * @return int
     * @author Xia ZhengWei
     * @date 2017年1月3日 下午3:01:43 
     * @version V1.0
     */
    int countByConditions(Criteria example);

    /**
     * @Title: selectByConditions 
     * @Description: 后台管理根据条件查询客户数据
     * @param example
     * @return List<CustomerSimpleInfoForm>
     * @author Xia ZhengWei
     * @date 2017年1月3日 下午3:02:39 
     * @version V1.0
     */
    List<CustomerSimpleInfoForm> selectByConditions(Criteria example);
    
    /**
     * @Title: getAllCustomer 
     * @Description: 获取所有客户信息(主键、电话、拨打时间)
     * @return List<CustomerInfo>
     * @author Xia ZhengWei
     * @date 2017年2月21日 下午9:14:32 
     * @version V1.0
     */
    public List<CustomerInfo> getAllCustomer();
    
    /**
     * @Title: insertEmptyCustomer 
     * @Description: 插入空白客户信息
     * @param info
     * @return int
     * @author Xia ZhengWei
     * @date 2017年2月21日 下午10:11:45 
     * @version V1.0
     */
    public int insertEmptyCustomer(CustomerInfo info);

}
