package star.superhappy.service.impl;

import java.util.ArrayList;
import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.PoolsADao;
import star.superhappy.pojo.PoolsA;
import star.superhappy.pojo.PoolsB;
import star.superhappy.service.PoolsAService;
import star.superhappy.utils.SHUtils;
import star.superhappy.vo.PoolsMacthResult;

public class PoolsAServiceImpl implements PoolsAService {

	private PoolsADao poolsADao;

	private MainDataDao mainDataDao;

	public PoolsADao getPoolsADao() {
		return poolsADao;
	}

	public void setPoolsADao(PoolsADao poolsADao) {
		this.poolsADao = poolsADao;
	}

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	// 保存
	public void save(PoolsA poolsA) {
		poolsADao.save(poolsA);
	}

	// 更新
	public void update(PoolsA poolsA) {
		poolsADao.update(poolsA);
	}

	// 删除
	public void delete(int id) {
		poolsADao.delete(id);
	}

	// 查询，返回单个实体
	public PoolsA get(int id) {
		return poolsADao.get(id);
	}

	// 查询，返回List
	public List<PoolsA> query() {
		return poolsADao.query();
	}

	// 自定义，初始化，批量更新
	public void batchUpdate(int n) {

		int tempN = n;// 以几期参数为分析一句，默认为3
		// 1.根据tempN，当前期号mainDataId确定要统计的期数，数据
		// 1.1获取mainData当前期号，maxMainDataId
		int maxMainDataId = mainDataDao.getMaxMainDataId();
		int nowPoolsAid = 0;

		if (poolsADao.query().size() == 0) {// PoolsA为空，初始化，并批量更新
			System.out.println("---PoolsA为空，初始化，并批量更新---");
			// poolsA的起始id
			nowPoolsAid = 2007000 + 1 + tempN;
		} else {// PoolsA不为空，并批量更新
			// 判断poolsAid
			// 期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
			int maxPoolsAId=poolsADao.getMaxPoolsAId();//获取poolsA最大期号2018154
			String tempId1 = String.valueOf(maxPoolsAId).substring(0, 4);//截取poolsA最大期号的年份2018154-->2018
			//System.out.println("tempId1:"+tempId1);
			if (maxPoolsAId == mainDataDao.getMaxMainDataIdByYear(Integer
					.parseInt(tempId1))) {//如果poolsA的最大期号等于mainData该年的最大期号，下一期poolsA的id跨年
				nowPoolsAid = (Integer.parseInt(tempId1) + 1) * 1000 + 1;
				System.out.println("修正为下一年poolsAId：" + nowPoolsAid);
			} else {
				nowPoolsAid=maxPoolsAId+1;
				System.out.println("修正为当年poolsAId：" + nowPoolsAid);
			}
			
			
			
		}

		while (nowPoolsAid < maxMainDataId + 1) {
			//System.out.println("nowPoolsAid-1:" + nowPoolsAid);
			int id = (nowPoolsAid / 1000) * 1000 + 1;// PoolsId对应每年的起始id
			if (nowPoolsAid - id >= n) {// 2007003-->003，不跨年
				// 获取前tempN期数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储前tempN
				for (int i = 0; i < tempN; i++) {
					int mainDataId = nowPoolsAid - i - 1;
					
					liAppear.add(mainDataDao.get(mainDataId).getFirst());
					liAppear.add(mainDataDao.get(mainDataId).getSecond());
					liAppear.add(mainDataDao.get(mainDataId).getThird());
					liAppear.add(mainDataDao.get(mainDataId).getFourth());
					liAppear.add(mainDataDao.get(mainDataId).getFifth());
				}

				
				List<Integer> poolsALi = new ArrayList<Integer>();// 记录地址池poolsA中的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsALi.add(i);
				}

				// 修正poolsALi，去除已开出的号码
				poolsALi.removeAll(liAppear);

				List<Integer> poolsAOutLi = new ArrayList<Integer>();// 记录池poolsA中要排除的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsAOutLi.add(i);
				}
				// 修正poolsAOutLi，去除poolsBLi的号码
				poolsAOutLi.removeAll(poolsALi);

				// int count=35-outCount;//poolsA池中的个数
				//System.out.println("poolsA:" + poolsALi);
				//System.out.println("poolsAOut:" + poolsAOutLi);
				// System.out.println("PoolsACount:"+count+",PoolsAOutCount:"+outCount);

				// poolsA池中击中信息
				PoolsMacthResult pmr = match(poolsALi,
						mainDataDao.get(nowPoolsAid).getFirst(), mainDataDao
								.get(nowPoolsAid).getSecond(),
						mainDataDao.get(nowPoolsAid).getThird(), mainDataDao
								.get(nowPoolsAid).getFourth(),
						mainDataDao.get(nowPoolsAid).getFifth());
				// poolsAOut池中击中信息
				PoolsMacthResult pmrOut = match(poolsAOutLi,
						mainDataDao.get(nowPoolsAid).getFirst(), mainDataDao
								.get(nowPoolsAid).getSecond(),
						mainDataDao.get(nowPoolsAid).getThird(), mainDataDao
								.get(nowPoolsAid).getFourth(),
						mainDataDao.get(nowPoolsAid).getFifth());

				PoolsA poolsA = new PoolsA();
				poolsA.setId(nowPoolsAid);
				poolsA.setMainData(mainDataDao.get(nowPoolsAid));

				poolsA.setPoolsA(pmr.getPools());// poolsA池
				poolsA.setCountPoolsAall(poolsALi.size());// poolsA池中的个数
				poolsA.setCountPoolsA(pmr.getCount());// poolsA中的击中个数
				poolsA.setNumberPoolsA(pmr.getResult());// poolsA池中的击中号码

				poolsA.setPoolsAout(pmrOut.getPools());// poolsA池排除
				poolsA.setCountAout(poolsAOutLi.size());// poolsA池排除的个数countBout
				poolsA.setCountPoolsAout(pmrOut.getCount());// poolsA池排除的击中个数countPoolsBout
				poolsA.setNumberPoolsAout(pmrOut.getResult());// poolsA池排除的击中号码
				System.out.println(poolsA);
				poolsADao.save(poolsA);
				// 判断poolsAid
				// 期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
				String tempId1 = String.valueOf(
						mainDataDao.get(nowPoolsAid).getId()).substring(0, 4);// 截取2007

				if (nowPoolsAid == mainDataDao.getMaxMainDataIdByYear(Integer
						.parseInt(tempId1))) {
					nowPoolsAid = (Integer.parseInt(tempId1) + 1) * 1000 + 1;
					System.out.println("修正为下一年poolsAId：" + nowPoolsAid);
				} else {
					nowPoolsAid++;
					System.out.println("修正为当年poolsAId：" + nowPoolsAid);
				}
			} else if (nowPoolsAid - id < n) {// 跨年
                 System.out.println("---进入跨年数据更新---");
				// 获得前几期数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储前tempN
				// 1.2获取前tempN期数据,当年数据
				for (int i = 0; i < (nowPoolsAid - id); i++) {
					int mainDataId = nowPoolsAid - i - 1;
					// System.out.println("mainId="+mainDataId);
					// System.out.println("后区号码1："+mainDataDao.get(mainDataId).getSixth()+"后区号码2："+mainDataDao.get(mainDataId).getSeventh());
					liAppear.add(mainDataDao.get(mainDataId).getFirst());
					liAppear.add(mainDataDao.get(mainDataId).getSecond());
					liAppear.add(mainDataDao.get(mainDataId).getThird());
					liAppear.add(mainDataDao.get(mainDataId).getFourth());
					liAppear.add(mainDataDao.get(mainDataId).getFifth());
				}
				int addCount = (nowPoolsAid - id) * 2;// 记录tempA中已经加入的个数

				for (int i = 0; i < n - (nowPoolsAid - id); i++) {
					System.out.println("nowPoolsAid:"+nowPoolsAid);
					int mainDataId = mainDataDao
							.getMaxMainDataIdByYear(nowPoolsAid / 1000 - 1) - i;
					liAppear.add(mainDataDao.get(mainDataId).getFirst());
					liAppear.add(mainDataDao.get(mainDataId).getSecond());
					liAppear.add(mainDataDao.get(mainDataId).getThird());
					liAppear.add(mainDataDao.get(mainDataId).getFourth());
					liAppear.add(mainDataDao.get(mainDataId).getFifth());
				}

				

				int outCount = 0;// 记录地址池B中的排除个数
				List<Integer> poolsALi = new ArrayList<Integer>();
				for (int i = 1; i < 36; i++) {
					poolsALi.add(i);
				}

			
				// 修正poolsALi，去除已开出的号码
				poolsALi.removeAll(liAppear);

				List<Integer> poolsAOutLi = new ArrayList<Integer>();// 记录地址池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsAOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsAOutLi.removeAll(poolsALi);

				int count = 12 - outCount;// poolsB地址池中的个数
				System.out.println("poolsB:" + poolsALi);
				System.out.println("poolsBOut:" + poolsAOutLi);
				// System.out.println("PoolsBCount:"+count+",PoolsBOutCount:"+outCount);

				// poolsA地址池中击中信息
				PoolsMacthResult pmr = match(poolsALi,
						mainDataDao.get(nowPoolsAid).getFirst(), mainDataDao
								.get(nowPoolsAid).getSecond(),
						mainDataDao.get(nowPoolsAid).getThird(), mainDataDao
								.get(nowPoolsAid).getFourth(),
						mainDataDao.get(nowPoolsAid).getFifth());
				// poolsA地址Out池中击中信息
				PoolsMacthResult pmrOut = match(poolsAOutLi,
						mainDataDao.get(nowPoolsAid).getFirst(), mainDataDao
								.get(nowPoolsAid).getSecond(),
						mainDataDao.get(nowPoolsAid).getThird(), mainDataDao
								.get(nowPoolsAid).getFourth(),
						mainDataDao.get(nowPoolsAid).getFifth());

				PoolsA poolsA = new PoolsA();
				poolsA.setId(nowPoolsAid);
				poolsA.setMainData(mainDataDao.get(nowPoolsAid));

				poolsA.setPoolsA(pmr.getPools());// poolsA池
				poolsA.setCountPoolsAall(poolsALi.size());// poolsA池中的个数
				poolsA.setCountPoolsA(pmr.getCount());// poolsA池中的击中个数
				poolsA.setNumberPoolsA(pmr.getResult());// poolsA池中的击中号码

				poolsA.setPoolsAout(pmrOut.getPools());// poolsA池排除
				poolsA.setCountAout(poolsAOutLi.size());// poolsA池排除的个数countBout
				poolsA.setCountPoolsAout(pmrOut.getCount());// poolsA池排除的击中个数countPoolsBout
				poolsA.setNumberPoolsAout(pmrOut.getResult());// poolsA池排除的击中号码
				System.out.println(poolsA);
				poolsADao.save(poolsA);
				// nowPoolsBid++;
				// System.out.println("nowPoolsBid-2:"+nowPoolsBid);
				// 判断poolsBid
				// 期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
				String tempId1 = String.valueOf(
						mainDataDao.get(nowPoolsAid).getId()).substring(0, 4);// 截取2007

				if (nowPoolsAid == mainDataDao.getMaxMainDataIdByYear(Integer
						.parseInt(tempId1))) {
					nowPoolsAid = (Integer.parseInt(tempId1) + 1) * 1000 + 1;
					System.out.println("修正为下一年yiLouId：" + nowPoolsAid);
				} else {
					nowPoolsAid++;
					System.out.println("修正为当年yiLouId：" + nowPoolsAid);
				}

			}

		}

	}

	// 自定义，计算出下一期的poolsA的数据
	public PoolsA queryNextPoolsAData(int n) {
		int tempN = n;// 以几期参数为分析一句，默认为3
		// 1.根据tempN，当前期号mainDataId确定要统计的期数，数据
		// 1.1获取mainData当前期号，maxMainDataId
		int maxMainDataId = mainDataDao.getMaxMainDataId();

		int nowPoolsAid = maxMainDataId + 1;// 将要开奖的这期期号

		if(maxMainDataId>=(2007*1000+1)){//可以计算poolsA
			int id = (nowPoolsAid / 1000) * 1000 + 1;// PoolsId对应每年的起始id
			if (nowPoolsAid - id >= n) {// 2007003-->003，不跨年
				// 获得前几期数据
				// 1.2获取前tempN期数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储钱tempN
				for (int i = 0; i < tempN; i++) {
					int mainDataId = nowPoolsAid - i - 1;
					liAppear.add(mainDataDao.get(mainDataId).getFirst());
					liAppear.add(mainDataDao.get(mainDataId).getSecond());
					liAppear.add(mainDataDao.get(mainDataId).getThird());
					liAppear.add(mainDataDao.get(mainDataId).getFourth());
					liAppear.add(mainDataDao.get(mainDataId).getFifth());
				}

				// 输出tempB
				/*
				 * for(int i=0;i<tempB.length;i++){ System.out.println(tempB[i]); }
				 */

				int outCount = 0;// 记录Pools池中的排除个数
				List<Integer> poolsALi = new ArrayList<Integer>();// 记录poolsB池中的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsALi.add(i);
				}

				List<Integer> tempPoolsAOutLi = new ArrayList<Integer>();// 记录记录地址池poolsB中要排除的号码，有重复值
				/*
				 * for (int i = 0; i < tempB.length; i++) { if
				 * (poolsBLi.contains(tempB[i])) { tempPoolsBOutLi.add(tempB[i]);
				 * outCount++; } }
				 */
				// 修正poolsBLi，去除已开出的号码
				poolsALi.removeAll(liAppear);

				List<Integer> poolsAOutLi = new ArrayList<Integer>();// 记录池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsAOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsAOutLi.removeAll(poolsALi);

				

				PoolsA poolsA = new PoolsA();
				poolsA .setId(nowPoolsAid);
				poolsA .setMainData(mainDataDao.get(nowPoolsAid));

				poolsA.setPoolsA(SHUtils.poolsDataToString(poolsALi));// poolsB地址池
				poolsA.setCountPoolsAall(poolsALi.size());// poolsB池中的个数
				poolsA.setCountPoolsA(null);// poolsB池中的击中个数
				poolsA.setNumberPoolsA(null);// poolsB池中的击中号码

				poolsA.setPoolsAout(SHUtils.poolsDataToString(poolsAOutLi));// poolsB池排除
				poolsA.setCountAout(poolsAOutLi.size());// poolsB池排除的个数countBout
				poolsA.setCountPoolsAout(null);// poolsB池排除的击中个数countPoolsBout
				poolsA.setNumberPoolsAout(null);// poolsB池排除的击中号码
				// System.out.println(poolsB);
				return poolsA;

			} else if (nowPoolsAid - id < n) {// 跨年

				// 获得前几期数据
				// int tempB[] = new int[tempN * 2];// 存储获取到的前tempN期的后区数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储钱tempN
				// 1.2获取前tempN期数据,当年数据
				for (int i = 0; i < (nowPoolsAid - id); i++) {
					int mainDataId = nowPoolsAid - i - 1;
					liAppear.add(mainDataDao.get(mainDataId).getFirst());
					liAppear.add(mainDataDao.get(mainDataId).getSecond());
					liAppear.add(mainDataDao.get(mainDataId).getThird());
					liAppear.add(mainDataDao.get(mainDataId).getFourth());
					liAppear.add(mainDataDao.get(mainDataId).getFifth());
				}
			

				for (int i = 0; i < n - (nowPoolsAid - id); i++) {
					int mainDataId = mainDataDao
							.getMaxMainDataIdByYear(nowPoolsAid / 1000 - 1) - i;
					liAppear.add(mainDataDao.get(mainDataId).getFirst());
					liAppear.add(mainDataDao.get(mainDataId).getSecond());
					liAppear.add(mainDataDao.get(mainDataId).getThird());
					liAppear.add(mainDataDao.get(mainDataId).getFourth());
					liAppear.add(mainDataDao.get(mainDataId).getFifth());
				}

			
				List<Integer> poolsALi = new ArrayList<Integer>();// 记录地址池poolsB中的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsALi.add(i);
				}

				List<Integer> tempPoolsAOutLi = new ArrayList<Integer>();// 记录记录池poolsB中要排除的号码，有重复值
				
				// 修正poolsBLi，去除已开出的号码
				poolsALi.removeAll(liAppear);

				List<Integer> poolsAOutLi = new ArrayList<Integer>();// 记录池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 36; i++) {
					poolsAOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsAOutLi.removeAll(poolsALi);

				// int count = 12 - outCount;// poolsB池中的个数
				System.out.println("poolsB:" + poolsALi);
				System.out.println("poolsBOut:" + poolsAOutLi);
				// System.out.println("PoolsBCount:"+count+",PoolsBOutCount:"+outCount);

				PoolsB poolsB = new PoolsB();
				PoolsA poolsA = new PoolsA();
				poolsA .setId(nowPoolsAid);
				poolsA .setMainData(mainDataDao.get(nowPoolsAid));

				poolsA.setPoolsA(SHUtils.poolsDataToString(poolsALi));// poolsB地址池
				poolsA.setCountPoolsAall(poolsALi.size());// poolsB池中的个数
				poolsA.setCountPoolsA(null);// poolsB池中的击中个数
				poolsA.setNumberPoolsA(null);// poolsB池中的击中号码

				poolsA.setPoolsAout(SHUtils.poolsDataToString(poolsAOutLi));// poolsB池排除
				poolsA.setCountAout(poolsAOutLi.size());// poolsB池排除的个数countBout
				poolsA.setCountPoolsAout(null);// poolsB池排除的击中个数countPoolsBout
				poolsA.setNumberPoolsAout(null);// poolsB池排除的击中号码
				// System.out.println(poolsB);
				return poolsA;

			}
		}else{
			return null;
		}

		
		return null;

	}

	public PoolsMacthResult match(List<Integer> pools, int first, int second,
			int third, int fourth, int fifth) {
		int count = 0;
		int arrayA[] = { first, second, third, fourth, fifth };
		// pools信息，号码
		StringBuffer strPools = new StringBuffer();
		for (int i = 0; i < pools.size(); i++) {
			strPools.append(pools.get(i));
			strPools.append(",");
		}
		String newStrPools = null;
		if (strPools != null && !strPools.equals("")) {
			int index1 = strPools.lastIndexOf(",");
			newStrPools = strPools.substring(0, index1);
		}

		// 击中信息，结果
		StringBuffer strResult = new StringBuffer();
		for (int i = 0; i < arrayA.length; i++) {
			if (pools.contains(arrayA[i])) {
				count++;
				strResult.append(arrayA[i]);
				strResult.append(",");
			}
		}
		// System.out.println("count:"+count);
		// System.out.println("str:"+str.toString());
		// 去掉最后一个","
		String newStrResult = null;
		if (count > 0) {
			int index2 = strResult.lastIndexOf(",");
			// System.out.println(index);
			newStrResult = strResult.substring(0, index2);
			System.out.println(newStrResult);
		}

		return new PoolsMacthResult(newStrPools, count, newStrResult);
	}

}
