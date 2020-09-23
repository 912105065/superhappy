package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.PoolsB;

public interface PoolsBService {

	// 通用保存
	public void save(PoolsB poolsB);

	// 通用更新
	public void update(PoolsB poolsB);

	// 通用删除
	public void delete(int id);

	// 通用查询，返回单个实体
	public PoolsB get(int id);

	// 通用查询，返回List
	public List<PoolsB> query();


	// 自定义，批量更新
	public void batchUpdate(int n);
	
	//自定义，计算出下一期的poolsB的数据
	public PoolsB queryNextPoolsBData(int n);
}
