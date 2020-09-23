package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.TypeAStatistics;

public interface TypeAStatisticsService {

	// 保存
	public void save(TypeAStatistics typeAStatistics);

	// 更新
	public void update(TypeAStatistics typeAStatistics);

	// 根据id，删除
	public void delete(int id);

	// 根据id查询，返回单个实体
	public TypeAStatistics get(int id);

	// 查询，返回List
	public List<TypeAStatistics> query();
	
	
	
	//自定义，自动更新
	public void autoBathUpdate();

}
