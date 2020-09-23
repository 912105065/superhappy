package star.superhappy.pojo;

/**
 * Yilouzhistatistics entity. @author MyEclipse Persistence Tools
 */

public class YiLouZhiStatistics implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer firstZ;
	private Integer secondZ;
	private Integer thridZ;
	private Integer fourthZ;
	private Integer fifthZ;
	private Integer sixthZ;
	private Integer seventhZ;
	
	private MainData mainData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstZ() {
		return firstZ;
	}

	public void setFirstZ(Integer firstZ) {
		this.firstZ = firstZ;
	}

	public Integer getSecondZ() {
		return secondZ;
	}

	public void setSecondZ(Integer secondZ) {
		this.secondZ = secondZ;
	}

	public Integer getThridZ() {
		return thridZ;
	}

	public void setThridZ(Integer thridZ) {
		this.thridZ = thridZ;
	}

	public Integer getFourthZ() {
		return fourthZ;
	}

	public void setFourthZ(Integer fourthZ) {
		this.fourthZ = fourthZ;
	}

	public Integer getFifthZ() {
		return fifthZ;
	}

	public void setFifthZ(Integer fifthZ) {
		this.fifthZ = fifthZ;
	}

	public Integer getSixthZ() {
		return sixthZ;
	}

	public void setSixthZ(Integer sixthZ) {
		this.sixthZ = sixthZ;
	}

	public Integer getSeventhZ() {
		return seventhZ;
	}

	public void setSeventhZ(Integer seventhZ) {
		this.seventhZ = seventhZ;
	}

	public MainData getMainData() {
		return mainData;
	}

	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}

	public YiLouZhiStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public YiLouZhiStatistics(Integer firstZ, Integer secondZ, Integer thridZ,
			Integer fourthZ, Integer fifthZ, Integer sixthZ, Integer seventhZ,
			MainData mainData) {
		super();
		this.firstZ = firstZ;
		this.secondZ = secondZ;
		this.thridZ = thridZ;
		this.fourthZ = fourthZ;
		this.fifthZ = fifthZ;
		this.sixthZ = sixthZ;
		this.seventhZ = seventhZ;
		this.mainData = mainData;
	}

	public YiLouZhiStatistics(Integer id, Integer firstZ, Integer secondZ,
			Integer thridZ, Integer fourthZ, Integer fifthZ, Integer sixthZ,
			Integer seventhZ, MainData mainData) {
		super();
		this.id = id;
		this.firstZ = firstZ;
		this.secondZ = secondZ;
		this.thridZ = thridZ;
		this.fourthZ = fourthZ;
		this.fifthZ = fifthZ;
		this.sixthZ = sixthZ;
		this.seventhZ = seventhZ;
		this.mainData = mainData;
	}

	@Override
	public String toString() {
		return "YiLouZhiStatistics [id=" + id + ", firstZ=" + firstZ
				+ ", secondZ=" + secondZ + ", thridZ=" + thridZ + ", fourthZ="
				+ fourthZ + ", fifthZ=" + fifthZ + ", sixthZ=" + sixthZ
				+ ", seventhZ=" + seventhZ + ", mainData=" + mainData + "]";
	}

	

	
	
	

}