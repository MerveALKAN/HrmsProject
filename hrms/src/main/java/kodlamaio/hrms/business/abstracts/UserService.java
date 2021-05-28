package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.business.BaseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;

public interface UserService extends BaseService<User> {
DataResult<User> getByEmail (String email);

Result register (User user);
}
