package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.business.BaseService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CompanyStaffVerification;

public interface CompanyStaffVerificationService extends BaseService<CompanyStaffVerification> {
 Result verify (int id);
}
