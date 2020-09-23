package star.superhappy.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.pojo.PoolsA;
import star.superhappy.service.PoolsAService;
import star.superhappy.service.PoolsBService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class PoolsAServiceImplTest {

	@Resource
	private PoolsAService poolsAService;


    //初始化，批量更新
	@Test
	public void testBatchUpdate() {
		poolsAService.batchUpdate(3);
	}
	
	// 自定义，计算出下一期的poolsA的数据
	@Test
	public void queryNextPoolsAData(){
		System.out.println(poolsAService.queryNextPoolsAData(3));
	}

}
