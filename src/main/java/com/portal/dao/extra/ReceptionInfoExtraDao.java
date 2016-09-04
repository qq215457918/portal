package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.result.ReceptionInfoFrom;

@Repository
public interface ReceptionInfoExtraDao {
	
	/**
	 * 通过客户ID查询客户的拜访记录
	 * by meng.yue
	 * @param customerId
	 * @return
	 */
	List<ReceptionInfoFrom> queryRecordListbyId(String customerId);

}
