package com.guoke.architecture.services.imps;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.guoke.architecture.models.Requirement;
import com.guoke.service.BaseService;


@Service
public class RequirementService extends BaseService<Requirement> {
	@Override
	public Map<String, Object> getPageList(int page, int rows,Map<String, String> request) {		
		return getPageList(page,rows,q->{
			if(request.containsKey("projectId")) {
				q.Where("projectId=%s", request.get("projectId"));
			}
		});
	}

}
