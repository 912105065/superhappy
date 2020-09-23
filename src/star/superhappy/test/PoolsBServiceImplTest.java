package star.superhappy.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import star.superhappy.pojo.PoolsB;
import star.superhappy.service.PoolsBService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class PoolsBServiceImplTest {

	@Resource
	private PoolsBService poolsBService;

	//通用查询，返回List
	@Test
	public void query() {
		System.out.println(poolsBService.query().size());
	}

	@Test
	public void testBatchUpdate() {
		poolsBService.batchUpdate(3);
	}
	
	
   //自定义，计算出下一期的poolsB的数据
   @Test
   public void queryNextPoolsBData(){
		System.out.println(poolsBService.queryNextPoolsBData(3));
	}
   
   

}
