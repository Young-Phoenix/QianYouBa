package com.qianyouba.entity;

public class User {
	private int id;
	
	private String username;//用户名
	private String userpass;//密码
	private String usertel;//电话
	private String userCom;//公司
	private String userAddress;//地址
	private String userQQ;//QQ
	private String userAddtime;//注册时间
	private int userMoney;//财富余额
	private int userState;//状态
	private String userIP;//最后登录IP
	private String userEmail;//邮箱
	private String idCard;//身份证
	private String sex;//性别
	private int age;//年龄
	private String touxiang;//头像
	private Area area;//县区
	private City city;//地市
	private Province province;//省份
	private String realName;//真实姓名
	private String info;//简介
	
	public User(){}

	public User(int id, String username, String userpass, String usertel,
			String userCom, String userAddress, String userQQ,
			String userAddtime, int userMoney, int userState, String userIP,
			String userEmail, String idCard, String sex, int age,
			String touxiang, Area area, City city,
			Province province, String realName, String info) {
		super();
		this.id = id;
		this.username = username;
		this.userpass = userpass;
		this.usertel = usertel;
		this.userCom = userCom;
		this.userAddress = userAddress;
		this.userQQ = userQQ;
		this.userAddtime = userAddtime;
		this.userMoney = userMoney;
		this.userState = userState;
		this.userIP = userIP;
		this.userEmail = userEmail;
		this.idCard = idCard;
		this.sex = sex;
		this.age = age;
		this.touxiang = touxiang;
		this.area = area;
		this.city = city;
		this.province = province;
		this.realName = realName;
		this.info = info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUserCom() {
		return userCom;
	}

	public void setUserCom(String userCom) {
		this.userCom = userCom;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserQQ() {
		return userQQ;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}

	public String getUserAddtime() {
		return userAddtime;
	}

	public void setUserAddtime(String userAddtime) {
		this.userAddtime = userAddtime;
	}

	public int getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
