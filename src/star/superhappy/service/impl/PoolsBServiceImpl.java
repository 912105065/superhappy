package star.superhappy.service.impl;

import java.util.ArrayList;
import java.util.List;

import star.superhappy.dao.MainDataDao;
import star.superhappy.dao.PoolsBDao;
import star.superhappy.pojo.MainData;
import star.superhappy.pojo.PoolsB;
import star.superhappy.service.PoolsBService;
import star.superhappy.utils.SHUtils;
import star.superhappy.vo.PoolsMacthResult;

public class PoolsBServiceImpl implements PoolsBService {

	private MainDataDao mainDataDao;
	private PoolsBDao poolsBDao;

	public MainDataDao getMainDataDao() {
		return mainDataDao;
	}

	public void setMainDataDao(MainDataDao mainDataDao) {
		this.mainDataDao = mainDataDao;
	}

	public PoolsBDao getPoolsBDao() {
		return poolsBDao;
	}

	public void setPoolsBDao(PoolsBDao poolsBDao) {
		this.poolsBDao = poolsBDao;
	}

	// 通用保存
	public void save(PoolsB poolsB) {
		poolsBDao.save(poolsB);
	}

	// 通用更新
	public void update(PoolsB poolsB) {
		poolsBDao.update(poolsB);
	}

	// 通用删除
	public void delete(int id) {
		poolsBDao.delete(id);
	}

	// 通用查询，返回单个实体
	public PoolsB get(int id) {
		return poolsBDao.get(id);
	}

	// 通用查询，返回List
	public List<PoolsB> query() {
		return poolsBDao.query();
	}

	// 自定义，初始化，批量更新
	public void batchUpdate(int n) {

		int tempN = n;// 以几期参数为分析一句，默认为3
		// 1.根据tempN，当前期号mainDataId确定要统计的期数，数据
		// 1.1获取mainData当前期号，maxMainDataId
		int maxMainDataId = mainDataDao.getMaxMainDataId();
		int nowPoolsBid = 0;

		if (poolsBDao.query().size() == 0) {// PoolsB为空，初始化，并批量更新
			System.out.println("---PoolsB为空，初始化，并批量更新---");
			// poolsB的起始id
			nowPoolsBid = 2007000 + 1 + tempN;
		} else {// PoolsB不为空，并批量更新
			// 判断poolsBid
			// 期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
			int maxPoolsBId = poolsBDao.getMaxPoolsBId();// 获取poolsB最大期号2018154
			String tempId1 = String.valueOf(maxPoolsBId).substring(0, 4);// 截取poolsA最大期号的年份2018154-->2018
			// System.out.println("tempId1:"+tempId1);
			if (maxPoolsBId == mainDataDao.getMaxMainDataIdByYear(Integer
					.parseInt(tempId1))) {// 如果poolsB的最大期号等于mainData该年的最大期号，下一期poolsA的id跨年
				nowPoolsBid = (Integer.parseInt(tempId1) + 1) * 1000 + 1;
				System.out.println("修正为下一年poolsBId：" +nowPoolsBid);
			} else {
				nowPoolsBid = maxPoolsBId + 1;
				System.out.println("修正为当年poolsBId：" + nowPoolsBid);
			}
		}

		while (nowPoolsBid < maxMainDataId + 1) {
			System.out.println("nowPoolsBid-1:" + nowPoolsBid);
			int id = (nowPoolsBid / 1000) * 1000 + 1;// PoolsBId对应每年的起始id
			if (nowPoolsBid - id >= n) {// 2007003-->003，不跨年
				// 获得前几期数据
				
				List<Integer> liAppear = new ArrayList<Integer>();// 存储前tempN期数据
				for (int i = 0; i < tempN; i++) {
					int mainDataId = nowPoolsBid - i - 1;
					liAppear.add(mainDataDao.get(mainDataId).getSixth());
					liAppear.add(mainDataDao.get(mainDataId).getSeventh());
				}

				

				int outCount = 0;// 记录Pools池中的排除个数
				List<Integer> poolsBLi = new ArrayList<Integer>();// 记录poolsB池中的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBLi.add(i);
				}

				List<Integer> tempPoolsBOutLi = new ArrayList<Integer>();// 记录记录地址池poolsB中要排除的号码，有重复值
				
				// 修正poolsBLi，去除已开出的号码
				poolsBLi.removeAll(liAppear);

				List<Integer> poolsBOutLi = new ArrayList<Integer>();// 记录池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsBOutLi.removeAll(poolsBLi);

				int count = 12 - outCount;// poolsB池中的个数
				System.out.println("poolsB:" + poolsBLi);
				System.out.println("poolsBOut:" + poolsBOutLi);
				// System.out.println("PoolsBCount:"+count+",PoolsBOutCount:"+outCount);

				// poolsB池中击中信息
				PoolsMacthResult pmr = match(poolsBLi,
						mainDataDao.get(nowPoolsBid).getSixth(), mainDataDao
								.get(nowPoolsBid).getSeventh());
				// poolsBOut池中击中信息
				PoolsMacthResult pmrOut = match(poolsBOutLi,
						mainDataDao.get(nowPoolsBid).getSixth(), mainDataDao
								.get(nowPoolsBid).getSeventh());

				PoolsB poolsB = new PoolsB();
				poolsB.setId(nowPoolsBid);
				poolsB.setMainData(mainDataDao.get(nowPoolsBid));

				poolsB.setPoolsB(pmr.getPools());// poolsB地址池
				poolsB.setCountPoolsBall(poolsBLi.size());// poolsB池中的个数
				poolsB.setCountPoolsB(pmr.getCount());// poolsB池中的击中个数
				poolsB.setNumberPoolsB(pmr.getResult());// poolsB池中的击中号码

				poolsB.setPoolsBout(pmrOut.getPools());// poolsB池排除
				poolsB.setCountBout(poolsBOutLi.size());// poolsB池排除的个数countBout
				poolsB.setCountPoolsBout(pmrOut.getCount());// poolsB池排除的击中个数countPoolsBout
				poolsB.setNumberPoolsBout(pmrOut.getResult());// poolsB池排除的击中号码
				System.out.println(poolsB);
				poolsBDao.save(poolsB);
				// 判断poolsBid
				// 期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
				String tempId = String.valueOf(nowPoolsBid);
				String tempId1 = String.valueOf(
						mainDataDao.get(nowPoolsBid).getId()).substring(0, 4);// 截取2007

				if (nowPoolsBid == mainDataDao.getMaxMainDataIdByYear(Integer
						.parseInt(tempId1))) {
					nowPoolsBid = (Integer.parseInt(tempId1) + 1) * 1000 + 1;
					System.out.println("修正为下一年poolsBId：" + nowPoolsBid);
				} else {
					nowPoolsBid++;
					System.out.println("修正为当年poolsBId：" + nowPoolsBid);
				}
			} else if (nowPoolsBid - id < n) {// 跨年

				// 获得前几期数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储前tempN期数据
				// 1.2获取前tempN期数据,当年数据
				for (int i = 0; i < (nowPoolsBid - id); i++) {
					int mainDataId = nowPoolsBid - i - 1;
					liAppear.add(mainDataDao.get(mainDataId).getSixth());
					liAppear.add(mainDataDao.get(mainDataId).getSeventh());
				}
				int addCount = (nowPoolsBid - id) * 2;// 记录tempB中已经加入的个数

				for (int i = 0; i < n - (nowPoolsBid - id); i++) {
					int mainDataId = mainDataDao
							.getMaxMainDataIdByYear(nowPoolsBid / 1000 - 1) - i;
					liAppear.add(mainDataDao.get(mainDataId).getSixth());
					liAppear.add(mainDataDao.get(mainDataId).getSeventh());
				}

			

				int outCount = 0;// 记录地址池B中的排除个数
				List<Integer> poolsBLi = new ArrayList<Integer>();// 记录地址池poolsB中的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBLi.add(i);
				}

				List<Integer> tempPoolsBOutLi = new ArrayList<Integer>();// 记录记录池poolsB中要排除的号码，有重复值
				
				// 修正poolsBLi，去除已开出的号码
				poolsBLi.removeAll(liAppear);

				List<Integer> poolsBOutLi = new ArrayList<Integer>();// 记录池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsBOutLi.removeAll(poolsBLi);

				int count = 12 - outCount;// poolsB池中的个数
				System.out.println("poolsB:" + poolsBLi);
				System.out.println("poolsBOut:" + poolsBOutLi);
				// System.out.println("PoolsBCount:"+count+",PoolsBOutCount:"+outCount);

				// poolsB地址池中击中信息
				PoolsMacthResult pmr = match(poolsBLi,
						mainDataDao.get(nowPoolsBid).getSixth(), mainDataDao
								.get(nowPoolsBid).getSeventh());
				// poolsB地址Out池中击中信息
				PoolsMacthResult pmrOut = match(poolsBOutLi,
						mainDataDao.get(nowPoolsBid).getSixth(), mainDataDao
								.get(nowPoolsBid).getSeventh());

				PoolsB poolsB = new PoolsB();
				poolsB.setId(nowPoolsBid);
				poolsB.setMainData(mainDataDao.get(nowPoolsBid));

				poolsB.setPoolsB(pmr.getPools());// poolsB池
				poolsB.setCountPoolsBall(poolsBLi.size());// poolsB池中的个数
				poolsB.setCountPoolsB(pmr.getCount());// poolsB池中的击中个数
				poolsB.setNumberPoolsB(pmr.getResult());// poolsB池中的击中号码

				poolsB.setPoolsBout(pmrOut.getPools());// poolsB池排除
				poolsB.setCountBout(poolsBOutLi.size());// poolsB池排除的个数countBout
				poolsB.setCountPoolsBout(pmrOut.getCount());// poolsB池排除的击中个数countPoolsBout
				poolsB.setNumberPoolsBout(pmrOut.getResult());// poolsB池排除的击中号码
				System.out.println(poolsB);
				poolsBDao.save(poolsB);
				// nowPoolsBid++;
				// System.out.println("nowPoolsBid-2:"+nowPoolsBid);
				// 判断poolsBid
				// 期号判断,假设2008154最后一期，下一期不是2008155，而是2009001
				String tempId = String.valueOf(nowPoolsBid);
				String tempId1 = String.valueOf(
						mainDataDao.get(nowPoolsBid).getId()).substring(0, 4);// 截取2007

				if (nowPoolsBid == mainDataDao.getMaxMainDataIdByYear(Integer
						.parseInt(tempId1))) {
					nowPoolsBid = (Integer.parseInt(tempId1) + 1) * 1000 + 1;
					System.out.println("修正为下一年poolsBId：" + nowPoolsBid);
				} else {
					nowPoolsBid++;
					System.out.println("修正为当年poolsBId：" + nowPoolsBid);
				}

			}

		}

	}

	// 自定义，计算出下一期的poolsB的数据
	public PoolsB queryNextPoolsBData(int n) {

		int tempN = n;// 以几期参数为分析一句，默认为3
		// 1.根据tempN，当前期号mainDataId确定要统计的期数，数据
		// 1.1获取mainData当前期号，maxMainDataId
		int maxMainDataId = mainDataDao.getMaxMainDataId();

		int nowPoolsBid = maxMainDataId + 1;// 将要开奖的这期期号

		int id = (nowPoolsBid / 1000) * 1000 + 1;// PoolsId对应每年的起始id

		if (maxMainDataId >= (2007 * 1000 + 1)) {// 可以计算poolsB

			if (nowPoolsBid - id >= n) {// 2007003-->003，不跨年
				// 获得前几期数据
				// int tempB[] = new int[tempN * 2];// 存储获取到的前tempN期的后区数据
				// 1.2获取前tempN期数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储钱tempN
				for (int i = 0; i < tempN; i++) {
					int mainDataId = nowPoolsBid - i - 1;
					// System.out.println("mainId="+mainDataId);
					// System.out.println("后区号码1："+mainDataDao.get(mainDataId).getSixth()+"后区号码2："+mainDataDao.get(mainDataId).getSeventh());
					// tempB[2 * i] = mainDataDao.get(mainDataId).getSixth();
					// tempB[2 * i + 1] =
					// mainDataDao.get(mainDataId).getSeventh();
					liAppear.add(mainDataDao.get(mainDataId).getSixth());
					liAppear.add(mainDataDao.get(mainDataId).getSeventh());
				}

				// 输出tempB
				/*
				 * for(int i=0;i<tempB.length;i++){
				 * System.out.println(tempB[i]); }
				 */

				int outCount = 0;// 记录Pools池中的排除个数
				List<Integer> poolsBLi = new ArrayList<Integer>();// 记录poolsB池中的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBLi.add(i);
				}

				List<Integer> tempPoolsBOutLi = new ArrayList<Integer>();// 记录记录地址池poolsB中要排除的号码，有重复值
				/*
				 * for (int i = 0; i < tempB.length; i++) { if
				 * (poolsBLi.contains(tempB[i])) {
				 * tempPoolsBOutLi.add(tempB[i]); outCount++; } }
				 */
				// 修正poolsBLi，去除已开出的号码
				poolsBLi.removeAll(liAppear);

				List<Integer> poolsBOutLi = new ArrayList<Integer>();// 记录池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsBOutLi.removeAll(poolsBLi);

				int count = 12 - outCount;// poolsB池中的个数
				// System.out.println("poolsB:"+poolsBLi);
				// System.out.println("poolsBOut:"+poolsBOutLi);
				// System.out.println("PoolsBCount:"+count+",PoolsBOutCount:"+outCount);

				// poolsB池中击中信息
				// PoolsMacthResult
				// pmr=match(poolsBLi,mainDataDao.get(nowPoolsBid).getSixth(),mainDataDao.get(nowPoolsBid).getSeventh());
				// poolsBOut池中击中信息
				// PoolsMacthResult
				// pmrOut=match(poolsBOutLi,mainDataDao.get(nowPoolsBid).getSixth(),mainDataDao.get(nowPoolsBid).getSeventh());

				PoolsB poolsB = new PoolsB();
				poolsB.setId(nowPoolsBid);
				poolsB.setMainData(mainDataDao.get(nowPoolsBid));

				poolsB.setPoolsB(SHUtils.poolsDataToString(poolsBLi));// poolsB地址池
				poolsB.setCountPoolsBall(poolsBLi.size());// poolsB池中的个数
				poolsB.setCountPoolsB(null);// poolsB池中的击中个数
				poolsB.setNumberPoolsB(null);// poolsB池中的击中号码

				poolsB.setPoolsBout(SHUtils.poolsDataToString(poolsBOutLi));// poolsB池排除
				poolsB.setCountBout(poolsBOutLi.size());// poolsB池排除的个数countBout
				poolsB.setCountPoolsBout(null);// poolsB池排除的击中个数countPoolsBout
				poolsB.setNumberPoolsBout(null);// poolsB池排除的击中号码
				// System.out.println(poolsB);
				return poolsB;

			} else if (nowPoolsBid - id < n) {// 跨年

				// 获得前几期数据
				// int tempB[] = new int[tempN * 2];// 存储获取到的前tempN期的后区数据
				List<Integer> liAppear = new ArrayList<Integer>();// 存储钱tempN
				// 1.2获取前tempN期数据,当年数据
				for (int i = 0; i < (nowPoolsBid - id); i++) {
					int mainDataId = nowPoolsBid - i - 1;
					// System.out.println("mainId="+mainDataId);
					// System.out.println("后区号码1："+mainDataDao.get(mainDataId).getSixth()+"后区号码2："+mainDataDao.get(mainDataId).getSeventh());
					liAppear.add(mainDataDao.get(mainDataId).getSixth());
					liAppear.add(mainDataDao.get(mainDataId).getSeventh());
				}
				int addCount = (nowPoolsBid - id) * 2;// 记录tempB中已经加入的个数

				for (int i = 0; i < n - (nowPoolsBid - id); i++) {
					int mainDataId = mainDataDao
							.getMaxMainDataIdByYear(nowPoolsBid / 1000 - 1) - i;
					liAppear.add(mainDataDao.get(mainDataId).getSixth());
					liAppear.add(mainDataDao.get(mainDataId).getSeventh());
				}

				// 输出tempB
				/*
				 * for(int i=0;i<tempB.length;i++){
				 * System.out.println(tempB[i]); }
				 */

				int outCount = 0;// 记录地址池B中的排除个数
				List<Integer> poolsBLi = new ArrayList<Integer>();// 记录地址池poolsB中的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBLi.add(i);
				}

				List<Integer> tempPoolsBOutLi = new ArrayList<Integer>();// 记录记录池poolsB中要排除的号码，有重复值
				/*
				 * for (int i = 0; i < tempB.length; i++) { if
				 * (poolsBLi.contains(tempB[i])) {
				 * tempPoolsBOutLi.add(tempB[i]); outCount++; } }
				 */
				// 修正poolsBLi，去除已开出的号码
				poolsBLi.removeAll(liAppear);

				List<Integer> poolsBOutLi = new ArrayList<Integer>();// 记录池poolsB中要排除的号码，初始化为1~12
				for (int i = 1; i < 13; i++) {
					poolsBOutLi.add(i);
				}
				// 修正poolsBOutLi，去除poolsBLi的号码
				poolsBOutLi.removeAll(poolsBLi);

				// int count = 12 - outCount;// poolsB池中的个数
				System.out.println("poolsB:" + poolsBLi);
				System.out.println("poolsBOut:" + poolsBOutLi);
				// System.out.println("PoolsBCount:"+count+",PoolsBOutCount:"+outCount);

				PoolsB poolsB = new PoolsB();
				poolsB.setId(nowPoolsBid);
				poolsB.setMainData(mainDataDao.get(nowPoolsBid));

				poolsB.setPoolsB(SHUtils.poolsDataToString(poolsBLi));// poolsB池
				poolsB.setCountPoolsBall(poolsBLi.size());// poolsB池中的个数
				poolsB.setCountPoolsB(null);// poolsB池中的击中个数
				poolsB.setNumberPoolsB(null);// poolsB池中的击中号码

				poolsB.setPoolsBout(SHUtils.poolsDataToString(poolsBOutLi));// poolsB池排除
				poolsB.setCountBout(poolsBOutLi.size());// poolsB池排除的个数countBout
				poolsB.setCountPoolsBout(null);// poolsB池排除的击中个数countPoolsBout
				poolsB.setNumberPoolsBout(null);// poolsB池排除的击中号码
				// System.out.println(poolsB);
				return poolsB;

			}
		} else {
			return null;
		}

		return null;

	}

	public PoolsMacthResult match(List<Integer> pools, int sixth, int seventh) {
		int count = 0;
		int arrayB[] = { sixth, seventh };
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
		for (int i = 0; i < arrayB.length; i++) {
			if (pools.contains(arrayB[i])) {
				count++;
				strResult.append(arrayB[i]);
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
