package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailActivationService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.helpers.EmailService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;


@Service
public class UserManager implements UserService {
	private final UserDao userDao;

	@Autowired
	public UserManager(final UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Result add(final User user) {
		userDao.save(user);

		return new SuccessResult(Messages.userAdded);
	}

	@Override
	public Result delete(final User user) {
		userDao.delete(user);

		return new SuccessResult(Messages.userDeleted);
	}

	@Override
	public DataResult<List<User>> getAll() {
		final List<User> users = userDao.findAll();

		return new SuccessDataResult<List<User>>(users);
	}

	@Override
	public DataResult<User> getByEmail(final String email) {
		final Optional<User> user = userDao.findByEmail(email);

		if (user.isEmpty())
			return new ErrorDataResult<User>(Messages.userNotFound);

		return new SuccessDataResult<User>(user.get());
	}

	@Override
	public DataResult<User> getById(final int id) {
		final Optional<User> user = userDao.findById(id);

		if (user.isEmpty())
			new ErrorDataResult<User>(Messages.userNotFound);

		return new SuccessDataResult<User>(user.get());
	}

	@Override
	public Result isNotEmailExist(final String email) {
		return userDao.findByEmail(email).isEmpty() ? new SuccessResult()
				: new ErrorResult(Messages.userWithMailAlreadyExits);
	}

	@Override
	public Result update(final User user) {
		userDao.save(user);

		return new SuccessResult(Messages.userUpdated);
	}

}