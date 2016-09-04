package com.portal.dao.extra;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoExtraDao {

    /**
     * 根据电话号码查询用户信息
     * by meng.yue
     */
    CustomerInfo selectByPhone(String phone);

}