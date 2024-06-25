package mvcand3layer.model;

public class BuildingDTO {
	private String name;
	private Integer number;
	private String ward;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	@Override
	public String toString() {
		return "BuildingDTO [name=" + name + ", number=" + number + ", ward=" + ward + "]";
	}
}
