package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import star.superhappy.service.MainDataService;
import star.superhappy.service.XDDistributionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class XDDistributionServiceImplTest {

	@Resource
	private XDDistributionService xddistributionService;
	
	@Resource
	private MainDataService mainDataServcie;

	// 查询，返回单个实体
	@Test
	public void get() {

		System.out.println(xddistributionService.get(2006001).getId());
	}

	@Test
	public void batchUpdateTest() {
		xddistributionService.batchUpdate();
	}

}
