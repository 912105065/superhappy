package star.superhappy.service;

public interface YiLouZhiStatisticsService {

	
	//自定义，初始化
	public void initYiLouZhiStatisics();
	
	
	//自定义，获取最大Id号
    public int getYiLouZhiStatisticsMaxId();
	
	
	//自定义，批量插入
	public void batchYiLouZhiStatisics();
}
