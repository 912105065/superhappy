package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.service.YiLouDataService;
import star.superhappy.service.impl.YiLouDataServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-*.xml")
public class YiLouServiceImplTest {

	@Resource
	private YiLouDataService yiLouDataService;
	
	
	
	
	//测试保存1期遗漏数据
	@Test
	public void testSaveDataByOne(){
		int i=2017001;
		String date=String.valueOf(i);
		//System.out.println(date);
	    String temp1=date.substring(0,4);//截取2017
	    //System.out.println(temp1);
	    String temp2=date.substring(4,7);//截取001
	    //System.out.println(temp2);
	    if(temp1.equals("2017")){
	    	System.out.println("ok");
	    }
	    yiLouDataService.saveDataByOne();
	}
	
	
	//测试遗漏参数初始化，2007001
	@Test
	public void testYiLouDataInit() {
		yiLouDataService.yiLouDataInit();
	}
	
	//测试批量更新遗漏数据，注：第一次录入数据，需要先初始化
	@Test
	public void testBatchSaveData(){
		yiLouDataService.batchSaveData();
	}

}
