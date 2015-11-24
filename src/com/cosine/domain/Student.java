package com.cosine.domain;
/**
 * 学生信息实体类，包含属性：
 * 学号 
 * 姓名 
 * 班级  
 * 性别  
 * 联系方式  
 * 家庭地址  
 * 是否党员
 */
public class Student {
	private String id;
	private String uuid;
	private String name;
	private String sclass;
	private String sex;
	private String phone;
	private String address;
	private boolean party;
	public Student(){
		
	}
	
	public Student(String id, String name, String sclass, String sex,
			String phone, String address, boolean party) {
		this.id = id;
		this.name = name;
		this.sclass = sclass;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.setParty(party);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isParty() {
		return party;
	}

	public void setParty(boolean party) {
		this.party = party;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	
}
