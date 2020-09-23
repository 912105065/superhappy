package star.superhappy.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.dao.YiLouDataDao;
import star.superhappy.service.YiLouZhiStatisticV2Service;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class YiLouZhiStatisticV2ServiceImplTest {

	@Resource
	private YiLouZhiStatisticV2Service yiLouZhiStatisticV2Service;
	
	
	
	
	@Test
	public void batchYiLouZhiStatisicsTest(){
		yiLouZhiStatisticV2Service.batchUpdate();
		//System.out.println(yiLouZhiStatisticV2Service.query());
		//System.out.println(	(2019154/1000)*1000+1);
		
		
	}
	
	@Test
	public void getMaxNonYiLouZhiByYear(){
		yiLouZhiStatisticV2Service.getMaxNonYiLouZhiByYear();
	}
}
