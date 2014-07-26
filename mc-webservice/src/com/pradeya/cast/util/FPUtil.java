package com.pradeya.cast.util;

public class FPUtil {

	public static final String ID = "_id";

	public static final String ORGANIZATION = "organization";
	public static final String FINANCIAL_PLANNER = "financialPlanner";
	public static final String ORGANIZATION_DETAIL = "detail";

	public static final String CLIENT = "client";
	public static final String CLIENT_DETAIL = "clientDetail";
	public static final String REFERENCE = "reference";
	public static final String APPOINTMENT = "appointment";
	public static final String MEMBER = "member";
	public static final String GOAL = "goal";
	public static final String PROFILE = "profile";


	public static boolean isUsernameVaid(String username) {
		if (username.split("\\@").length == 2) {
			return username.split("\\@")[1].split("\\.").length >= 3;
		}
		return false;
	}

	public static String getWhichUser(String username) {

		return username.split("\\@")[1].split("\\.")[0];
	}

	public static String getUserDomain(String username) {
		return username.split("\\@")[1].split("\\.",
				username.split("\\@")[1].indexOf("."))[1];
	}
	
	public static String getUser(String username) {
		return username.split("\\@")[0];
	}

}
