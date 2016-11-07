package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.GoodsInfo;
import com.portal.bean.result.GoodsInfoForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface GoodsInfoService {

    //获取赠品信息
    public List<GoodsInfo> selectPresentInfo(HttpServletRequest request);

    public List<String> checkGoodsInfo(HttpServletRequest request);

    //获取商品信息
    List<GoodsInfoForm> getGoodInfoList(HttpServletRequest request);

    public List<GoodsInfoForm> selectGoodsInfo(HttpServletRequest request, int type);

    public int getGoodsCount(HttpServletRequest request, int type);

    public int getGoodInfoCount(HttpServletRequest request);

    int countByExample(Criteria example);

    GoodsInfo selectByPrimaryKey(String id);

    List<GoodsInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(GoodsInfo record, Criteria example);

    int updateByExample(GoodsInfo record, Criteria example);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);
}
