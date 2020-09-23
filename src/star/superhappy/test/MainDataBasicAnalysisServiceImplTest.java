package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.service.MainDataBasicAnalysisService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class MainDataBasicAnalysisServiceImplTest {

	@Resource
	private MainDataBasicAnalysisService mainDataBasicAnalysisService;
	
	
	//测试，初始化，批量更新
	@Test
	public void batchUpdate(){
		mainDataBasicAnalysisService.batchUpdate();
	}

}
