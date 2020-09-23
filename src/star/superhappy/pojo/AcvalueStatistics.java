package star.superhappy.pojo;

/**
 * Acvaluestatistics entity. @author MyEclipse Persistence Tools
 */

public class AcvalueStatistics implements java.io.Serializable {

	

	private Integer id;//AC值ID
	private Integer acvalue;//AC值
	private String acjo;//AC值奇偶
	private String acxd;//AC值小大
	private String aczhy;//AC值质合一
	private Integer ac012;//AC值012路
	private Integer aczf;//AC值振幅

	
	private MainData md;//关联MainData


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAcvalue() {
		return acvalue;
	}


	public void setAcvalue(Integer acvalue) {
		this.acvalue = acvalue;
	}


	public String getAcjo() {
		return acjo;
	}


	public void setAcjo(String acjo) {
		this.acjo = acjo;
	}


	public String getAcxd() {
		return acxd;
	}


	public void setAcxd(String acxd) {
		this.acxd = acxd;
	}


	public String getAczhy() {
		return aczhy;
	}


	public void setAczhy(String aczhy) {
		this.aczhy = aczhy;
	}


	public Integer getAc012() {
		return ac012;
	}


	public void setAc012(Integer ac012) {
		this.ac012 = ac012;
	}


	public Integer getAczf() {
		return aczf;
	}


	public void setAczf(Integer aczf) {
		this.aczf = aczf;
	}


	public MainData getMd() {
		return md;
	}


	public void setMd(MainData md) {
		this.md = md;
	}


	
	
	public AcvalueStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AcvalueStatistics(Integer id, Integer acvalue, String acjo,
			String acxd, String aczhy, Integer ac012, Integer aczf, MainData md) {
		super();
		this.id = id;
		this.acvalue = acvalue;
		this.acjo = acjo;
		this.acxd = acxd;
		this.aczhy = aczhy;
		this.ac012 = ac012;
		this.aczf = aczf;
		this.md = md;
	}


	@Override
	public String toString() {
		return "AcvalueStatistics [id=" + id + ", acvalue=" + acvalue
				+ ", acjo=" + acjo + ", acxd=" + acxd + ", aczhy=" + aczhy
				+ ", ac012=" + ac012 + ", aczf=" + aczf + ", md=" + md + "]";
	}
	
	
	
	
	
	
}