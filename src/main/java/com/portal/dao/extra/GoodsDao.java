package com.portal.dao.extra;

import com.portal.bean.Criteria;
import com.portal.bean.result.GoodsInfoForm;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao {

    /**
     * 根据主键查询记录
     */
    GoodsInfoForm selectByPrimaryKey(String id);

    List<GoodsInfoForm> selectByExample(Criteria criteria);

}
