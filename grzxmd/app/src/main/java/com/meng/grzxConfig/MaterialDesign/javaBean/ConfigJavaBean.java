package com.meng.grzxConfig.MaterialDesign.javaBean;

import java.util.ArrayList;
import java.util.HashSet;

public class ConfigJavaBean {
	public HashSet<GroupConfig> groupConfigs = new HashSet<>();
	public HashSet<Long> QQNotReply = new HashSet<>();
	public HashSet<Long> blackListQQ = new HashSet<>();
	public HashSet<Long> blackListGroup = new HashSet<>();
	public ArrayList<String> wordNotReply = new ArrayList<>();
	public HashSet<PersonInfo> personInfo = new HashSet<>();
	public HashSet<Long> masterList = new HashSet<>();
	public HashSet<Long> adminList = new HashSet<>();
	public HashSet<Long> groupAutoAllowList = new HashSet<>();
}
