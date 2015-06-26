package com.hxy.client;

/**
 * user's const
 * @author hxy
 *
 */
public class UserConst {
	//Response-HisConfig使用的模版
	public static final String MODEL_READ = "<ReadElement name=\"%s\" ETF_name=\"%s\" necessary=\"%s\" />";
	public static final String GROUP_MODEL_READ = "<ReadGroupElement name=\"%s\" ETF_name=\"%s\" repeat_name=\"%s\" necessary=\"%s\">";
	public static final String GROUP_MODEL_TAIL_READ = "</ReadGroupElement>";
	
	//Request-HisConfig使用的模版
	public static final String MODEL_WRITE = "<WriteElement name=\"%s\" ETF_name=\"%s\" necessary=\"%s\" />";
	public static final String GROUP_MODEL_WRITE = "<WriteGroupElement name=\"%s\" ETF_name=\"%s\" repeat_name=\"%s\" necessary=\"%s\">";
	public static final String GROUP_MODEL_TAIL_WRITE = "</WriteGroupElement>";
}
