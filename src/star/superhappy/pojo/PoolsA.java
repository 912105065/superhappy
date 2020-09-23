package star.superhappy.pojo;

/**
 * 池A
 * Poolsa entity. @author MyEclipse Persistence Tools
 */

public class PoolsA implements java.io.Serializable {

	// Fields

	private Integer id;

	private String poolsA;//poolsA池
	private Integer countPoolsAall;//poolsA池中的个数
	private Integer countPoolsA;//poolsA池中的击中个数
	private String numberPoolsA;//poolsA池中的击中号码
	private String poolsAout;//poolsA池排除
	private Integer countAout;//poolsA池排除的个数
	private Integer countPoolsAout;//poolsA池排除的击中个数
	private String numberPoolsAout;//poolsA池排除的击中号码
	
	
	
    private MainData mainData;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getPoolsA() {
		return poolsA;
	}



	public void setPoolsA(String poolsA) {
		this.poolsA = poolsA;
	}



	public Integer getCountPoolsAall() {
		return countPoolsAall;
	}



	public void setCountPoolsAall(Integer countPoolsAall) {
		this.countPoolsAall = countPoolsAall;
	}



	public Integer getCountPoolsA() {
		return countPoolsA;
	}



	public void setCountPoolsA(Integer countPoolsA) {
		this.countPoolsA = countPoolsA;
	}



	public String getNumberPoolsA() {
		return numberPoolsA;
	}



	public void setNumberPoolsA(String numberPoolsA) {
		this.numberPoolsA = numberPoolsA;
	}



	public String getPoolsAout() {
		return poolsAout;
	}



	public void setPoolsAout(String poolsAout) {
		this.poolsAout = poolsAout;
	}



	public Integer getCountAout() {
		return countAout;
	}



	public void setCountAout(Integer countAout) {
		this.countAout = countAout;
	}



	public Integer getCountPoolsAout() {
		return countPoolsAout;
	}



	public void setCountPoolsAout(Integer countPoolsAout) {
		this.countPoolsAout = countPoolsAout;
	}



	public String getNumberPoolsAout() {
		return numberPoolsAout;
	}



	public void setNumberPoolsAout(String numberPoolsAout) {
		this.numberPoolsAout = numberPoolsAout;
	}



	public MainData getMainData() {
		return mainData;
	}



	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}



	public PoolsA() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PoolsA(Integer id, String poolsA, Integer countPoolsAall,
			Integer countPoolsA, String numberPoolsA, String poolsAout,
			Integer countAout, Integer countPoolsAout, String numberPoolsAout,
			MainData mainData) {
		super();
		this.id = id;
		this.poolsA = poolsA;
		this.countPoolsAall = countPoolsAall;
		this.countPoolsA = countPoolsA;
		this.numberPoolsA = numberPoolsA;
		this.poolsAout = poolsAout;
		this.countAout = countAout;
		this.countPoolsAout = countPoolsAout;
		this.numberPoolsAout = numberPoolsAout;
		this.mainData = mainData;
	}



	@Override
	public String toString() {
		return "PoolsA [id=" + id + ", poolsA=" + poolsA + ", countPoolsAall="
				+ countPoolsAall + ", countPoolsA=" + countPoolsA
				+ ", numberPoolsA=" + numberPoolsA + ", poolsAout=" + poolsAout
				+ ", countAout=" + countAout + ", countPoolsAout="
				+ countPoolsAout + ", numberPoolsAout=" + numberPoolsAout
				+ ", mainData=" + mainData + "]";
	}

	
    
    
    
    

}