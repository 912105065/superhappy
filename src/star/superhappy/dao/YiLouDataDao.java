package star.superhappy.dao;

import java.util.List;

import star.superhappy.pojo.YiLouData;

public interface YiLouDataDao extends BaseDao<YiLouData> {

	
	//自定义方法
	//读取当前yiLouData的最大期号id
	public int getMaxYiLouDataId();
	
	//获取号码当年的最大遗漏值
	public int getMaxNonYiLouZhiByYear(String number,String flag,int startId,int endId);
	
	
	//获取号码的最大遗漏值
	public int getMaxNonYiLouZhi(String number,String flag);
	
	
	//获取号码当期的遗漏值
	public int getYiLouZhiById(String number,int id);
	
	
	//获取近n期号码击中的遗漏值
	public List<Number> queryJZYiLouZhiByN(String number,String flag,int n);
}
