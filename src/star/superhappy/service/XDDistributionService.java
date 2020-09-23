package star.superhappy.service;

import java.util.List;

import star.superhappy.pojo.XDDistribution;


public interface XDDistributionService {

	// 保存
	public void save(XDDistribution xxd);

	// 更新
	public void update(XDDistribution xxd);

	// 删除
	public void delete(int id);

	// 查询，返回单个实体
	public XDDistribution get(int id);

	// 查询，返回List
	public List<XDDistribution> query();

	// 自定义，初始化，批量更新
	public void batchUpdate();



}
