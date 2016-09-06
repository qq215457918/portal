package com.portal.dao.extra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.portal.bean.result.ReceptionInfoForm;

@Repository
public interface ReceptionInfoExtraDao {
	
	/**
	 * 通过客户ID查询客户的拜访记录
	 * by meng.yue
	 * @param customerId
	 * @return
	 */
	List<ReceptionInfoForm> queryRecordListbyId(String customerId);
	
	/**
	 * 查询为完成的拜访记录
	 * by meng.yue
	 * @return
	 */
	ReceptionInfoForm queryRecordBeginbyId(String customerId);

}
