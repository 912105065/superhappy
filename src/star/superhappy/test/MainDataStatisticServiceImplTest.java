package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.MainDataStatisticDao;
import star.superhappy.service.MainDataStatisticService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class MainDataStatisticServiceImplTest {

	@Resource
	private MainDataStatisticService mainDataStatisticService;
	
	@Resource
	private MainDataStatisticDao mainDataStatisticDao;
	
	@Test
	public void testBathInitAndUpdate() {
		mainDataStatisticService.bathInitAndUpdate();
	}

}
