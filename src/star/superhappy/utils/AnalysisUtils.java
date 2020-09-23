package star.superhappy.utils;

import java.util.HashSet;
import java.util.Set;

import star.superhappy.pojo.MainData;

/**
 * 数据分析辅助类
 * 
 * @author Gang
 * 
 */
public class AnalysisUtils {

	// 一期对象转换成数组
	public static int[] ObjectConvertToArray(MainData data) {
		int[] array = new int[7];
		array[0] = data.getFirst();
		array[1] = data.getSecond();
		array[2] = data.getThird();
		array[3] = data.getFourth();
		array[4] = data.getFifth();
		array[5] = data.getSixth();
		array[6] = data.getSeventh();
		return array;
	}

	// 检测一组号码，在历期开奖中的中奖情况，前区中几个号，后区中几个号
	public static String checkMacth(int[] temp, int[] mainData) {
		int qCount = 0;// 前区匹配数
		int hCount = 0;// 后区匹配数
		// 前区匹配
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (temp[i] == mainData[j]) {
					qCount++;
					System.out.println(qCount);
					// continue;
				}
			}
		}
		for (int i = 5; i < 7; i++) {
			for (int j = 5; j < 7; j++) {
				if (temp[i] == mainData[j]) {
					hCount++;
					System.out.println(hCount);
					// continue;
				}
			}
		}
		return qCount + "+" + hCount;
		// System.out.println(qCount+"+"+hCount);
		// return matchResult(qCount+"+"+hCount);
	}

	// 开奖等级匹配次数统计，一等奖：n次，二等奖：n次，三等奖：n次
	public static String matchResult(String str) {

		// String result="";
		StringBuffer result = new StringBuffer();
		int yiDen = 0;
		int erDen = 0;
		int sanDen = 0;
		int siDen = 0;
		int wuDen = 0;
		int liuDen = 0;

		String checkResult = str.substring(0, str.lastIndexOf(";"));
		System.out.println(checkResult);
		String[] checkArray = checkResult.split(";");

		for (int i = 1; i < checkArray.length; i++) {
			if (checkArray[i].equals("3+0") || checkArray[i].equals("1+2")
					|| checkArray[i].equals("2+1")
					|| checkArray[i].equals("0+2")) {
				// result= "六等奖";
				liuDen++;
			} else if (checkArray[i].equals("4+0")
					|| checkArray[i].equals("3+1")
					|| checkArray[i].equals("2+2")) {
				// result="五等奖";
				wuDen++;
			} else if (checkArray[i].equals("4+1")
					|| checkArray[i].equals("3+2")) {
				// result= "四等奖";
				siDen++;
			} else if (checkArray[i].equals("5+0")
					|| checkArray[i].equals("4+2")) {
				// result= "三等奖";
				sanDen++;
			} else if (checkArray[i].equals("5+1")) {
				// result= "二等奖";
				erDen++;
			} else if (checkArray[i].equals("5+2")) {
				// result= "一等奖";
				yiDen++;
			}
		}

		result.append("一等奖：" + yiDen + "次\n");
		result.append("二等奖：" + erDen + "次\n");
		result.append("三等奖：" + sanDen + "次\n");
		result.append("四等奖：" + siDen + "次\n");
		result.append("五等奖：" + wuDen + "次\n");
		result.append("六等奖：" + liuDen + "次");
		return result.toString();
	}

	// 根据号码，判断奇偶
	public static String numberToJO(int number) {
		if (number % 2 == 0) {
			return "偶";
		} else {
			return "奇";
		}

	}

	// 根据号码，判断小大,前区
	public static String numberToXDByQian(int number) {
		if (number <= 18) {
			return "小";
		} else {
			return "大";
		}
	}

	// 根据号码，判断小大,后区
	public static String numberToXDByHou(int number) {
		if (number <= 6) {
			return "小";
		} else {
			return "大";
		}
	}

	public static String numberToXDByAC(int number) {
		// max值为6
		if (number <= 3) {
			return "小";
		} else {
			return "大";
		}
	}

	// 根据号码，判断质合一
	public static String numberToZHY(int number) {
		if (number == 1) {
			return "一";
		}
		if (number == 2 || number == 3) {
			return "质";
		} else {
			int temp = (int) Math.sqrt(number);
			for (int i = 2; i <= temp; i++) {
				if (number % i == 0) {
					return "合";
				}
			}
			return "质";
		}
	}

	// 根据号码，判断012路
	public static String numberTo012(int number) {
		if (number % 3 == 0) {
			return "0";
		} else if (number % 3 == 1) {
			return "1";
		} else {
			return "2";
		}
	}

	// AC值计算
	public static int ACResult(int first, int second, int third, int fourth,
			int fifith) {
		int[] arrayA = { first, second, third, fourth, fifith };
		int[] rs = new int[10];
		rs[0] = arrayA[1] - arrayA[0];
		rs[1] = arrayA[2] - arrayA[0];
		rs[2] = arrayA[3] - arrayA[0];
		rs[3] = arrayA[4] - arrayA[0];
		rs[4] = arrayA[2] - arrayA[1];
		rs[5] = arrayA[3] - arrayA[1];
		rs[6] = arrayA[4] - arrayA[1];
		rs[7] = arrayA[3] - arrayA[2];
		rs[8] = arrayA[4] - arrayA[2];
		rs[9] = arrayA[4] - arrayA[3];

		Set<Integer> rsSet = new HashSet<Integer>();
		for (int i : rs) {
			// System.out.println(i);
			rsSet.add(i);
		}

		// System.out.println(rsSet.size());
		int acValue = rsSet.size() - (5 - 1);

		return acValue;
	}

}
