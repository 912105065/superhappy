package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.MainDataStatistic;

public interface MainDataStatisticService {

	
	 //保存
	public void save(MainDataStatistic mds);
	
    //更新
	public void update(MainDataStatistic mds);
	
    //删除
	public void delete(int id);
	
    //查询，返回单个实体
	public MainDataStatistic get(int id);
	
    //查询，返回List
	public List<MainDataStatistic> query();
	
	
	//自定义，批量初始化和更新
	public void bathInitAndUpdate();
	
}
