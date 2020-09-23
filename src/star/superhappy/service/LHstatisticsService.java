package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.LHstatistics;

public interface LHstatisticsService {

	// 通用保存
	public void save(LHstatistics lhstatistics);

	// 通用更新
	public void update(LHstatistics lhstatistics);

	// 通用删除
	public void delete(int id);

	// 通用查询，返回单个实体
	public LHstatistics get(int id);

	// 通用查询，返回List
	public List<LHstatistics> query();
	
	
	
	//自定义，批量初始化和更新连号数据
	public void bathInitAndUpdate();

}
