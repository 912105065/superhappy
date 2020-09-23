package star.superhappy.action;

import java.util.HashMap;
import java.util.List;

import star.superhappy.pojo.MainData;

public class MainDataAction extends BaseAction<MainData>{

	
	
	public String queryAll(){
		System.out.println("---mainData---");
		pageMap=new HashMap<String,Object>();
		List<MainData> mdLi=mainDataService.queryByPage(page,rows);
		pageMap.put("rows", mdLi);
		
		Long total=mainDataService.getCountByMainData();
		pageMap.put("total", total);
		return "jsonMap";
	}
}
