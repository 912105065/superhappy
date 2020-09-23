package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.AcvalueStatistics;

public interface AcvalueStatisticsService {

	// 通用保存
	public void save(AcvalueStatistics acvalueStatistics);

	// 通用更新
	public void update(AcvalueStatistics acvalueStatistics);

	// 通用删除
	public void delete(int id);

	// 通用查询，返回单个实体
	public AcvalueStatistics get(int id);

	// 通用查询，返回List
	public List<AcvalueStatistics> query();
	
	
	//自定义，自动更新
	public void autoUpdate();

}
