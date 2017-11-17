package com.xs.webboottest.limiting;

import com.xs.webboottest.vo.RequestVo;

public interface IFlowLimit {
	FlowStatus limit(RequestVo requestVo);
}
