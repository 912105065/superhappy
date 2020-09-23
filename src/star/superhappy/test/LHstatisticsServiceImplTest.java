package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.service.LHstatisticsService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class LHstatisticsServiceImplTest {

	@Resource
	private LHstatisticsService lhstatisticsService;
	
	//测试根据id查询
	@Test
	public void testGet() {
		System.out.println(lhstatisticsService.get(1).getId());
	}
	
	//测试批量初始化和更新
	@Test
	public void bathInitAndUpdate(){
		lhstatisticsService.bathInitAndUpdate();
	}

}
