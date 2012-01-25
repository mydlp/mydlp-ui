package com.mydlp.ui.remoting.blazeds;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

//import com.mydlp.ui.thrift.MyDLPUIThriftService;

@Service("policyBRS")
@RemotingDestination
public class PolicyBRSImpl implements PolicyService
{

	@Override
	public void compilePolicy(Integer policyId) {
		// TODO Auto-generated method stub
		
	}
	
//	@Autowired
//	@Qualifier("thriftService")
	//protected MyDLPUIThriftService thriftService;

//	@Override
	//public void compilePolicy(Integer policyId) {
	//	thriftService.compileFilter(policyId);
	//}

}