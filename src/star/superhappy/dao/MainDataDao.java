package star.superhappy.dao;

import java.util.List;

import star.superhappy.pojo.MainData;

public interface MainDataDao extends BaseDao<MainData> {

	
	//自定义方法
	//自定义，读取当前mainData的最大期号id
	public int getMaxMainDataId();
	
	
	//自定义，读取当前mainData的最小期号id
    public int getMinMainDataId();
	
	
	//根据年判断该年的最大期号，2017
	public int getMaxMainDataIdByYear(int year);

	
	
	
	 //自定义，根据期号段，查询maindata
	public List<MainData> queryMainDataByMIds(int startId,int endId);
	
	//自定义，根据日期区间，后区号码1，后区号码2，查询出现次数
	public Long getCountByDateAndhqNumber1AndhqNumber2(int a,int b,String beginDate,String endDate);
	
	//自定义，根据日期区间，后区号码1，后区号码2，查询List
    public List<MainData> queryByDateAndhqNumber1AndhqNumber2(int a,int b,String beginDate,String endDate);
    
    
    //自定义，获取mainData的总期数
    public long getCountByMainData();
    
    
    //自定义，查询MainData,EasyUI分页显示maindata
    public List<MainData> queryByPage(int pageNo,int pageSize);
    
    
    //自定义，查询MainData的数量，EasyUI分页显示maindata
    //public Long getCountByPage(int pageNo,int pageSize);
    
    
    //自定义，按期号段，统计mainData后区号码出现次数-first位
    public long statisticMDFirstCountByMDId(int startMDId,int endMDId,int number);
    
    //自定义，按期号段，统计mainData后区号码出现次数-second位
    public long statisticMDSecondCountByMDId(int startMDId,int endMDId,int number);
    
    //自定义，按期号段，统计mainData后区号码出现次数-third位
    public long statisticMDThirdCountByMDId(int startMDId,int endMDId,int number);
    
    //自定义，按期号段，统计mainData后区号码出现次数-fourth位
    public long statisticMDFourthCountByMDId(int startMDId,int endMDId,int number);
    
    //自定义，按期号段，统计mainData后区号码出现次数-fifth位
    public long statisticMDFifthCountByMDId(int startMDId,int endMDId,int number);
    
    //自定义，按期号段，统计mainData后区号码出现次数-sixth位
    public long statisticMDSixthCountByMDId(int startMDId,int endMDId,int number);
    
    //自定义，按期号段，统计mainData后区号码出现次数-seventh位
    public long statisticMDSeventhCountByMDId(int startMDId,int endMDId,int number);
    
     
    
		
	
}
