package star.superhappy.dao.impl;

import java.util.List;

import org.hibernate.Query;

import star.superhappy.dao.MainDataDao;
import star.superhappy.pojo.MainData;
import star.superhappy.utils.Util;

public class MainDataDaoImpl extends BaseDaoImpl<MainData> implements MainDataDao {

	
	//读取当前mainData的最大期号id
	@Override
	public int getMaxMainDataId() {
		String sql="select MAX(mainId) from maindata";
		Query query=getSession().createSQLQuery(sql);
		return (int) query.uniqueResult();
	}

	
	//根据年判断该年的最大期号，2017
	@Override
	public int getMaxMainDataIdByYear(int year) {
		String year1= String.valueOf(year)+"-01-01";
		String year2= String.valueOf(year)+"-12-31";
		String sql="select MAX(mainId) from maindata where date >=:dateBegin and date<=:dateEnd";
		Query query=getSession().createSQLQuery(sql);
		query.setDate("dateBegin", Util.doConvertToDate(year1))
		     .setDate("dateEnd", Util.doConvertToDate(year2));
		      
		return (int) query.uniqueResult();
	}


	//读取当前mainData的最小期号id
	@Override
	public int getMinMainDataId() {
		String hql="select MIN(id) from MainData";
		Query query=getSession().createQuery(hql);
		return (int) query.uniqueResult();
	}
	
	//根据日期区间，后区号码1，后区号码2，查询出现次数
	@Override
	public Long getCountByDateAndhqNumber1AndhqNumber2(int a,int b,String beginDate,String endDate){
		String hql="select COUNT(md) from MainData md where md.sixth=:a and md.seventh=:b and md.date >:beginDate and md.date<:endDate";
		Query query=getSession().createQuery(hql);
		query.setInteger("a",a)
		     .setInteger("b",b)
		     .setDate("beginDate", Util.doConvertToDate(beginDate))
		     .setDate("endDate", Util.doConvertToDate(endDate));
		
		return  (Long) query.uniqueResult();
	}

	//根据日期区间，后区号码1，后区号码2，查询List
	@Override
	public List<MainData> queryByDateAndhqNumber1AndhqNumber2(int a,
			int b, String beginDate, String endDate) {
		String hql="from MainData md where md.sixth=:a and md.seventh=:b and md.date >:beginDate and md.date<:endDate";
		Query query=getSession().createQuery(hql);
		query.setInteger("a",a)
		     .setInteger("b",b)
		     .setDate("beginDate", Util.doConvertToDate(beginDate))
		     .setDate("endDate", Util.doConvertToDate(endDate));
		
		return  query.list();
	}

	//获取mainData的总期数
	@Override
	public long getCountByMainData() {
		String hql="SELECT COUNT(md.id) FROM MainData md";
		Query query=getSession().createQuery(hql);
		return (long) query.uniqueResult();
	}

	
	
	//自定义，查询MainData,EasyUI分页显示maindata
	@Override
	public List<MainData> queryByPage(int pageNo, int pageSize) {
		String hql="FROM MainData md ORDER BY md.date DESC";
		Query query=getSession().createQuery(hql)
				    .setFirstResult((pageNo-1)*pageSize)
				    .setMaxResults(pageSize);
		return query.list();
	}

	//自定义，按期号段，统计mainData前区号码出现次数-first位
	@Override
	public long statisticMDFirstCountByMDId(int startMDId, int endMDId,
			int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.first=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}

    //自定义，按期号段，统计mainData前区号码出现次数-second位
	@Override
	public long statisticMDSecondCountByMDId(int startMDId, int endMDId,
			int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.second=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}


	//自定义，按期号段，统计mainData前区号码出现次数-third位
	@Override
	public long statisticMDThirdCountByMDId(int startMDId, int endMDId,
			int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.third=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}

	//自定义，按期号段，统计mainData前区号码出现次数-fourth位
	@Override
	public long statisticMDFourthCountByMDId(int startMDId, int endMDId,
			int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.fourth=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}

	//自定义，按期号段，统计mainData前区号码出现次数-fifth位
	@Override
	public long statisticMDFifthCountByMDId(int startMDId, int endMDId,
			int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.fifth=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}
	
	
	//自定义，按期号段，统计mainData后区号码出现次数-sixth位
	@Override
	public long statisticMDSixthCountByMDId(int startMDId, int endMDId, int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.sixth=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}


	//自定义，按期号段，统计mainData后区号码出现次数-seventh位
	@Override
	public long statisticMDSeventhCountByMDId(int startMDId, int endMDId,
			int number) {
		String hql="SELECT COUNT(md.id) FROM MainData md where md.id>=:startMDId and md.id<=:endMDId and md.seventh=:number";
		Query query=getSession().createQuery(hql);
		query.setInteger("startMDId", startMDId)
		     .setInteger("endMDId", endMDId)
		     .setInteger("number", number);
		return  (long) query.uniqueResult();
	}


	 //自定义，根据期号段，查询maindata
	@Override
	public List<MainData> queryMainDataByMIds(int startId, int endId) {
		String hql="FROM Maindata md WHERE md.id>=:startId and md.id<=:endId";
		Query query=getSession().createQuery(hql);
		query.setInteger("startId", startId)
		     .setInteger("endId", endId);
		return query.list();
	}


	



	
	

}
