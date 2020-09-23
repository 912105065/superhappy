package star.superhappy.pojo;

/**
 * Lhstatistics entity. @author MyEclipse Persistence Tools
 */

public class LHstatistics implements java.io.Serializable {

	// Fields

	private Integer id;//连号Id
	private String qqlh1;//前区连号1
	private int qqlh1count;//前区连号1个数
	private String qqlh2;//前区连号2
	private int qqlh2count;//前区连号2个数
	private String hqlh;//后区连号
	
	
	
	private MainData mainData;//关联mainData



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getQqlh1() {
		return qqlh1;
	}



	public void setQqlh1(String qqlh1) {
		this.qqlh1 = qqlh1;
	}



	public int getQqlh1count() {
		return qqlh1count;
	}



	public void setQqlh1count(int qqlh1count) {
		this.qqlh1count = qqlh1count;
	}



	public String getQqlh2() {
		return qqlh2;
	}



	public void setQqlh2(String qqlh2) {
		this.qqlh2 = qqlh2;
	}



	public int getQqlh2count() {
		return qqlh2count;
	}



	public void setQqlh2count(int qqlh2count) {
		this.qqlh2count = qqlh2count;
	}



	public String getHqlh() {
		return hqlh;
	}



	public void setHqlh(String hqlh) {
		this.hqlh = hqlh;
	}



	public MainData getMainData() {
		return mainData;
	}



	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}



	public LHstatistics() {
		super();
		// TODO Auto-generated constructor stub
	}



	public LHstatistics(Integer id, String qqlh1, int qqlh1count, String qqlh2,
			int qqlh2count, String hqlh, MainData mainData) {
		super();
		this.id = id;
		this.qqlh1 = qqlh1;
		this.qqlh1count = qqlh1count;
		this.qqlh2 = qqlh2;
		this.qqlh2count = qqlh2count;
		this.hqlh = hqlh;
		this.mainData = mainData;
	}



	@Override
	public String toString() {
		return "LHstatistics [id=" + id + ", qqlh1=" + qqlh1 + ", qqlh1count="
				+ qqlh1count + ", qqlh2=" + qqlh2 + ", qqlh2count="
				+ qqlh2count + ", hqlh=" + hqlh + ", mainData=" + mainData
				+ "]";
	}



	
	
	
}