package star.superhappy.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import star.superhappy.dao.BaseDao;




@SuppressWarnings("unchecked")
//@Repository("baseDao")
//@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz;//clazz中存储了当前操作的类型
	
	public BaseDaoImpl(){
		System.out.println("this代表当前调用构造方法的对象："+this);
		System.out.println("获得当前this对象的父类信息："+this.getClass().getSuperclass());
		System.out.println("获得当前this对象的父类信息（包括泛型信息）："+this.getClass().getGenericSuperclass());
		//获取泛型参数
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		//getActualTypeArguments,返回表示此类型实际类型参数的 Type 对象的数组
		clazz=(Class) type.getActualTypeArguments()[0];
	}
	
	//@Resource  //没有指定指定名称默认是属性的名称与id捆绑
	private SessionFactory sessionFactory;

	protected Session getSession() {
		// 从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	//通用保存
	public void save(T t) {
		// TODO Auto-generated method stub
		getSession().save(t);
	}

	//通用更新
	public void update(T t) {
		// TODO Auto-generated method stub
		getSession().update(t);
	}

	//通用删除
	public void delete(int id) {
		// getSimpleName(),  返回源代码中给出的底层类的简称
		String hql="DELETE "+clazz.getSimpleName()+" WHERE id=:id";
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();
	}

	//通用查询，返回单个实体
	public T get(int id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(clazz, id);
	}


	//通用查询，返回List
	public List<T> query() {
		// TODO Auto-generated method stub
		String hql="FROM "+clazz.getSimpleName();//getSimpleName(),返回源代码中给出的底层类的简称
		return getSession().createQuery(hql).list();
	}

}

