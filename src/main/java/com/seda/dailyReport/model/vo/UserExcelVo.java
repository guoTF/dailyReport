package com.seda.dailyReport.model.vo;

/**
 * 导出Excel表userVo类
 * @author gtf 20180625
 *
 */
public class UserExcelVo {

	//序号
	private int num;

	//姓名
    private String userName;

    //年龄
    private int age;

    //电话
    private String phone;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}
