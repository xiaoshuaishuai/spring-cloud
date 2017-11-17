package com.xs.webboottest.limiting;

import lombok.Data;

@Data
public class FlowStatus {
	/**预警编号*/
	private String riskCode;
	/**预警并发量*/
	private String riskPermit;
	/**最大并发量*/
	private String permit;
	/**有效期*/
	private String permitUnit;
	/**是否拒绝请求*/
	private boolean refuse;
	/**是否预警*/
	private boolean risk;
	/**当前请求量*/
	private Long currCount;
	/**接口**/
	private String interfaceId;
}
