package kodlamaio.hrms.business.abstracts;

import java.util.List;
import kodlamaio.hrms.core.business.BaseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertForListDto;

public interface JobAdvertService extends BaseService<JobAdvert> {
	Result disableById(int id);

	DataResult<List<JobAdvertForListDto>> getAllByIsActiveAndEmployer_CompanyNameForList(boolean isActive,
			String companyName);

	DataResult<List<JobAdvertForListDto>> getAllByIsActiveForList(boolean isActive);

	DataResult<List<JobAdvertForListDto>> getAllByIsActiveOrderByCreatedAtByForList(boolean isActive, String sort);
}