package com.portal.service;

import com.portal.bean.Criteria;
import com.portal.bean.EmployeeInfo;
import com.portal.bean.GoodsInfo;
import com.portal.bean.result.GoodsInfoForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

public interface GoodsInfoService {

    //获取赠品信息
    public List<GoodsInfo> selectPresentInfo(HttpServletRequest request, String goodsType);

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

    /**
     * @Title: selectByConditions 
     * @Description: 根据条件查询数据
     * @param criteria
     * @return List<GoodsInfoForm>
     * @author Xia ZhengWei
     * @date 2017年1月4日 下午10:43:10 
     * @version V1.0
     */
    List<GoodsInfoForm> selectByConditions(Criteria criteria);

    /**
     * @Title: ajaxGoodsData 
     * @Description: 异步获取商品信息
     * @param criteria
     * @param sEcho
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月26日 下午10:21:32 
     * @version V1.0
     */
    JSONObject ajaxGoodsData(Criteria criteria, String sEcho);

    /**
     * @Title: saveGoodsInfo 
     * @Description: 保存商品信息
     * @param goodsInfoForm 商品信息
     * @param employee  员工信息
     * @param result    返回结果集
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月28日 下午10:25:34 
     * @version V1.0
     */
    JSONObject saveGoodsInfo(GoodsInfoForm goodsInfoForm, EmployeeInfo employee, JSONObject result);

    /**
     * @Title: deleteGoodsInfo 
     * @Description: 删除商品信息
     * @param id    商品ID
     * @param result
     * @return JSONObject
     * @author Xia ZhengWei
     * @date 2016年12月28日 下午10:04:27 
     * @version V1.0
     */
    JSONObject deleteGoodsInfo(String id, JSONObject result);
}
