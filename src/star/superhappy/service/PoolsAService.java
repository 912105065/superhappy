package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.PoolsA;
import star.superhappy.pojo.PoolsB;

public interface PoolsAService {

	// 保存
	public void save(PoolsA poolsA);

	// 更新
	public void update(PoolsA poolsA);

	// 删除
	public void delete(int id);

	// 查询，返回单个实体
	public PoolsA get(int id);

	// 查询，返回List
	public List<PoolsA> query();

	// 自定义，初始化，批量更新
	public  void batchUpdate(int n);
	
	
	//自定义，计算出下一期的poolsA的数据
    public PoolsA queryNextPoolsAData(int n);
	
}
