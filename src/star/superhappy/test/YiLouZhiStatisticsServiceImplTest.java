package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.service.YiLouZhiStatisticsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class YiLouZhiStatisticsServiceImplTest {

	@Resource
	private YiLouZhiStatisticsService yiLouZhiStatisticsService;

	// 初始化2007001
	@Test
	public void testInitYiLouZhiStatisics() {
		yiLouZhiStatisticsService.initYiLouZhiStatisics();
	}

	// 测试，获取最大Id号
	@Test
	public void getYiLouZhiStatisticsMaxId() {
        System.out.println("最大id："+yiLouZhiStatisticsService.getYiLouZhiStatisticsMaxId());
	}
	
	//自定义，批量插入，注意，第一次，需要初始化
	@Test
	public void batchYiLouZhiStatisics(){
		yiLouZhiStatisticsService.batchYiLouZhiStatisics();
	}
}
