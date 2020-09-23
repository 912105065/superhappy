package star.superhappy.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import star.superhappy.service.MainDataService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//控制层
public class BaseAction<T> extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware, ModelDriven<T> {

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	

	protected T model;

	protected InputStream inputStream;//Ajax中使用

	protected Map<String, Object> pageMap = null;//Ajax中使用

	protected List<T> jsonList = null;//Ajax中使用

	
	protected Integer page;//EaysUI分页中使用，JSON格式
	
	

	protected Integer rows;//EaysUI分页中使用，JSON格式

	
	
	
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	

	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}

	
	public List<T> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<T> jsonList) {
		this.jsonList = jsonList;
	}
	

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	
	
	
	
	public T getModel() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return model;
	}

	protected MainDataService mainDataService;




	public MainDataService getMainDataService() {
		return mainDataService;
	}

	public void setMainDataService(MainDataService mainDataService) {
		this.mainDataService = mainDataService;
	}
	
	
	

	

	
	
}
