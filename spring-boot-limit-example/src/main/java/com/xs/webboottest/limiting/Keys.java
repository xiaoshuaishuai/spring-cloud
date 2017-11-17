package com.xs.webboottest.limiting;

import java.text.MessageFormat;

public class Keys {
	/**
	 * {0} 具体接口
	 */
	private static final String limits = "dev:limits:{0}";
	/**
	 * {0} 具体接口
	 */
	private static final String risks = "dev:risk:{0}";

	public static void main(String[] args) {

		System.out.println(MessageFormat.format(Keys.limits, "testjob"));
		System.out.println(MessageFormat.format(Keys.risks, "testjob"));
	}
}
