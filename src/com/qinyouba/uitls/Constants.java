package com.qinyouba.uitls;

public class Constants {
	/**
	 * 启动页延迟时间，3秒
	 */
	public static final int SPLASH_DISPLAY_LENGHT = 3000;
	/**
	 * sharepreference中保存是否首次打开应用状态的key
	 */
	public static final String FIRST_START="firststart";
	/**
	 * 服务器IP地址
	 */
	public static final String SERVER_IP="http://www.baidu.com";
	public static final String QIANYOUBA_SERVER_IP="http://192.168.0.150:8080/qianyouba_app_server/";
	/**
	 * 登录--请求码
	 */
	public static final int LOGIN_REQUEST = 0x1;
	/**
	 * 注册--请求码
	 */
	public static final int REGISTER_REQUEST = 0x2;
	/**
	 * 获取验证码--请求码
	 */
	public static final int GETVERIFYCODE_REQUEST = 0x3;
}
