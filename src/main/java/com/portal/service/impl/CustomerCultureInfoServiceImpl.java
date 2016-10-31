package com.portal.service.impl;

import com.portal.bean.Criteria;
import com.portal.bean.CustomerCultureInfo;
import com.portal.common.util.DateUtil;
import com.portal.dao.CustomerCultureInfoDao;
import com.portal.service.CustomerCultureInfoService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCultureInfoServiceImpl implements CustomerCultureInfoService {
    @Autowired
    private CustomerCultureInfoDao customerCultureInfoDao;

    private static final Logger logger = LoggerFactory.getLogger(CustomerCultureInfoServiceImpl.class);

    /**
     * 修改文交所信息
     * @param request
     * @return
     */
    public int updateCulture(HttpServletRequest request) {
        CustomerCultureInfo cInfo = new CustomerCultureInfo();
        Optional.ofNullable(request.getParameter("cid")).ifPresent(value -> cInfo.setId(value));
        Optional.ofNullable(request.getParameter("cultureName"))
                .ifPresent(value -> cInfo.setCultureName(value));
        Optional.ofNullable(request.getParameter("accountDate"))
                .ifPresent(
                        value -> cInfo.setAccountDate(DateUtil.parseDate(value, DateUtil.DATE_FMT_YYYY_MM_DD)));
        Optional.ofNullable(request.getParameter("bankFlag")).ifPresent(value -> cInfo.setBankFlag(value));
        Optional.ofNullable(request.getParameter("customerName"))
                .ifPresent(value -> cInfo.setCultureName(value));
        Optional.ofNullable(request.getParameter("code")).ifPresent(value -> cInfo.setCode(value));
        Optional.ofNullable(request.getParameter("phone")).ifPresent(value -> cInfo.setPhone(value));
        return updateByPrimaryKeySelective(cInfo);
    }

    public int countByExample(Criteria example) {
        int count = this.customerCultureInfoDao.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CustomerCultureInfo selectByPrimaryKey(String id) {
        return this.customerCultureInfoDao.selectByPrimaryKey(id);
    }

    public List<CustomerCultureInfo> selectByExample(Criteria example) {
        return this.customerCultureInfoDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return this.customerCultureInfoDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.customerCultureInfoDao.deleteByExample(example);
    }

    public int updateByExampleSelective(CustomerCultureInfo record, Criteria example) {
        return this.customerCultureInfoDao.updateByExampleSelective(record, example);
    }

    public int updateByExample(CustomerCultureInfo record, Criteria example) {
        return this.customerCultureInfoDao.updateByExample(record, example);
    }

    public int insert(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.insert(record);
    }

    public int insertSelective(CustomerCultureInfo record) {
        return this.customerCultureInfoDao.insertSelective(record);
    }
}
