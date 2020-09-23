package star.superhappy.dao;

import java.util.List;

public interface BaseDao<T> {

	
	    //通用保存
		public void save(T t);
		
	    //通用更新
		public void update(T t);
		
	    //通用删除
		public void delete(int id);
		
	    //通用查询，返回单个实体
		public T get(int id);
		
	    //通用查询，返回List
		public List<T> query();
		
		
}
