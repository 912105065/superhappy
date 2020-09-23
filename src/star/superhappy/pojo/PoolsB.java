package star.superhappy.pojo;

/**
 * PoosB地址池B
 * Poolsb entity. @author MyEclipse Persistence Tools
 */

public class PoolsB implements java.io.Serializable {

	// Fields

	private Integer id;
	
	private String poolsB;//poolsB池
	private Integer countPoolsBall;//poolsB池中的个数
	private Integer countPoolsB;//poolsB池中的击中个数
	private String numberPoolsB;//poolsB池中的击中号码
	private String poolsBout;//poolsB池排除
	private Integer countBout;//poolsB池排除的个数
	private Integer countPoolsBout;//poolsB池排除的击中个数
	private String numberPoolsBout;//poolsB池排除的击中号码
	
	
	private MainData mainData;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPoolsB() {
		return poolsB;
	}


	public void setPoolsB(String poolsB) {
		this.poolsB = poolsB;
	}


	public Integer getCountPoolsBall() {
		return countPoolsBall;
	}


	public void setCountPoolsBall(Integer countPoolsBall) {
		this.countPoolsBall = countPoolsBall;
	}


	public Integer getCountPoolsB() {
		return countPoolsB;
	}


	public void setCountPoolsB(Integer countPoolsB) {
		this.countPoolsB = countPoolsB;
	}


	public String getNumberPoolsB() {
		return numberPoolsB;
	}


	public void setNumberPoolsB(String numberPoolsB) {
		this.numberPoolsB = numberPoolsB;
	}


	public String getPoolsBout() {
		return poolsBout;
	}


	public void setPoolsBout(String poolsBout) {
		this.poolsBout = poolsBout;
	}


	public Integer getCountBout() {
		return countBout;
	}


	public void setCountBout(Integer countBout) {
		this.countBout = countBout;
	}


	public Integer getCountPoolsBout() {
		return countPoolsBout;
	}


	public void setCountPoolsBout(Integer countPoolsBout) {
		this.countPoolsBout = countPoolsBout;
	}


	public String getNumberPoolsBout() {
		return numberPoolsBout;
	}


	public void setNumberPoolsBout(String numberPoolsBout) {
		this.numberPoolsBout = numberPoolsBout;
	}


	public MainData getMainData() {
		return mainData;
	}


	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}


	public PoolsB() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PoolsB(Integer id, String poolsB, Integer countPoolsBall,
			Integer countPoolsB, String numberPoolsB, String poolsBout,
			Integer countBout, Integer countPoolsBout, String numberPoolsBout,
			MainData mainData) {
		super();
		this.id = id;
		this.poolsB = poolsB;
		this.countPoolsBall = countPoolsBall;
		this.countPoolsB = countPoolsB;
		this.numberPoolsB = numberPoolsB;
		this.poolsBout = poolsBout;
		this.countBout = countBout;
		this.countPoolsBout = countPoolsBout;
		this.numberPoolsBout = numberPoolsBout;
		this.mainData = mainData;
	}


	@Override
	public String toString() {
		return "PoolsB [id=" + id + ", poolsB=" + poolsB + ", countPoolsBall="
				+ countPoolsBall + ", countPoolsB=" + countPoolsB
				+ ", numberPoolsB=" + numberPoolsB + ", poolsBout=" + poolsBout
				+ ", countBout=" + countBout + ", countPoolsBout="
				+ countPoolsBout + ", numberPoolsBout=" + numberPoolsBout
				+ ", mainData=" + mainData + "]";
	}



}