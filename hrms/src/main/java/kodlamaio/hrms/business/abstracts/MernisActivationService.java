package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.business.adapters.PersonForValidateFromMernisService;
import kodlamaio.hrms.core.business.BaseService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.MernisActivation;

public interface MernisActivationService extends BaseService<MernisActivation>{

	Result check(PersonForValidateFromMernisService personForValidateFromMernisService);
}
