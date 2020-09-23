package star.superhappy.dao.impl;

import org.hibernate.Query;

import star.superhappy.dao.YiLouZhiStatisticV2Dao;
import star.superhappy.pojo.YiLouZhiStatisticV2;

public class YiLouZhiStatisticV2DaoImpl extends BaseDaoImpl<YiLouZhiStatisticV2> implements YiLouZhiStatisticV2Dao{

	
	//自定义
	//返回当前nowId的值
	@Override
	public int getNowId() {
		// TODO Auto-generated method stub
		String sql="select distinct nowId from yilouzhistatisticv2";
		Query query=getSession().createSQLQuery(sql);
		return (int) query.uniqueResult();
	}

}
