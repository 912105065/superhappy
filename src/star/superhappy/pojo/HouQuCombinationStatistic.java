package star.superhappy.pojo;

/**
 * Houqucombinationstatistic entity. @author MyEclipse Persistence Tools
 */

public class HouQuCombinationStatistic implements java.io.Serializable {

	// Fields

	private Integer id;//后区号码组合Id
	private String hqCombination;//后区号码组合类型
	private int hqComCount;//组合出现次数
	private int hqSum;//和值
	private String hq012;//012路
	private int hqinterval;//跨度
	private int year;//年份
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHqCombination() {
		return hqCombination;
	}
	public void setHqCombination(String hqCombination) {
		this.hqCombination = hqCombination;
	}
	public int getHqComCount() {
		return hqComCount;
	}
	public void setHqComCount(int hqComCount) {
		this.hqComCount = hqComCount;
	}
	public int getHqSum() {
		return hqSum;
	}
	public void setHqSum(int hqSum) {
		this.hqSum = hqSum;
	}
	public String getHq012() {
		return hq012;
	}
	public void setHq012(String hq012) {
		this.hq012 = hq012;
	}
	public int getHqinterval() {
		return hqinterval;
	}
	public void setHqinterval(int hqinterval) {
		this.hqinterval = hqinterval;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	
	public HouQuCombinationStatistic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public HouQuCombinationStatistic(String hqCombination, int hqComCount,
			int hqSum, String hq012, int hqinterval, int year) {
		super();
		this.hqCombination = hqCombination;
		this.hqComCount = hqComCount;
		this.hqSum = hqSum;
		this.hq012 = hq012;
		this.hqinterval = hqinterval;
		this.year = year;
	}
	
	
	
	public HouQuCombinationStatistic(Integer id, String hqCombination,
			int hqComCount, int hqSum, String hq012, int hqinterval, int year) {
		super();
		this.id = id;
		this.hqCombination = hqCombination;
		this.hqComCount = hqComCount;
		this.hqSum = hqSum;
		this.hq012 = hq012;
		this.hqinterval = hqinterval;
		this.year = year;
	}
	
	
	
	
	@Override
	public String toString() {
		return "HouQuCombinationStatistic [id=" + id + ", hqCombination="
				+ hqCombination + ", hqComCount=" + hqComCount + ", hqSum="
				+ hqSum + ", hq012=" + hq012 + ", hqinterval=" + hqinterval
				+ ", year=" + year + "]";
	}
	
	
	
	
	

	
	
}