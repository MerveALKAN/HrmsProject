package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.MernisActivationService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.adapters.PersonForValidateFromMernisService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.MernisActivation;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;

@Service
public class JobSeekerManager implements JobSeekerService {
	private final JobSeekerDao jobSeekerDao;
	private final UserService userService;
	private final MernisActivationService mernisActivationService;

	@Autowired
	public JobSeekerManager(final JobSeekerDao jobSeekerDao, final UserService userService,
			final MernisActivationService mernisActivationService) {
		this.jobSeekerDao = jobSeekerDao;
		this.userService = userService;
		this.mernisActivationService = mernisActivationService;
	}

	@Override
	public Result add(final JobSeeker jobSeeker) {
		jobSeekerDao.save(jobSeeker);

		return new SuccessResult(Messages.jobSeekerAdded);
	}

	private Result arePasswordMatch(final String password, final String confirmPassword) {
		return password.equals(confirmPassword) ? new SuccessResult() : new ErrorResult(Messages.passwordsNotMatch);
	}

	@Override
	public Result delete(final JobSeeker jobSeeker) {
		jobSeekerDao.delete(jobSeeker);

		return new SuccessResult(Messages.jobSeekerDeleted);
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		final List<JobSeeker> jobSeekers = jobSeekerDao.findAll();

		return new SuccessDataResult<List<JobSeeker>>(jobSeekers);
	}

	@Override
	public DataResult<JobSeeker> getById(final int id) {
		final Optional<JobSeeker> jobSeeker = jobSeekerDao.findById(id);

		if (jobSeeker.isEmpty())
			return new ErrorDataResult<JobSeeker>(Messages.jobSeekerNotFound);

		return new SuccessDataResult<JobSeeker>(jobSeeker.get());
	}

	@Override
	public DataResult<JobSeeker> getByIdentityNumber(final String identityNumber) {
		final Optional<JobSeeker> jobSeeker = jobSeekerDao.findByIdentityNumber(identityNumber);

		if (jobSeeker.isEmpty())
			return new ErrorDataResult<JobSeeker>(Messages.jobSeekerNotFound);

		return new SuccessDataResult<JobSeeker>(jobSeeker.get());
	}

	private Result isNotNationalIdentityExist(final String identityNumber) {
		return !getByIdentityNumber(identityNumber).isSuccess() ? new SuccessResult()
				: new ErrorResult(Messages.jobSeekerWithIdentityNumberAlreadyExits);
	}

	@Override
	public Result register(final JobSeekerForRegisterDto jobSeekerForRegisterDto) {
		final Result businessRulesResult = BusinessRules.run(
				isNotNationalIdentityExist(jobSeekerForRegisterDto.getIdentityNumber()),
				arePasswordMatch(jobSeekerForRegisterDto.getPassword(), jobSeekerForRegisterDto.getConfirmPassword()),
				mernisActivationService.check(new PersonForValidateFromMernisService(
						Long.parseLong(jobSeekerForRegisterDto.getIdentityNumber()),
						jobSeekerForRegisterDto.getFirstName(),
						jobSeekerForRegisterDto.getLastName(),
						jobSeekerForRegisterDto.getBirthDate().getYear())));
		if (!businessRulesResult.isSuccess())
			return businessRulesResult;

		final User user = new User(0, jobSeekerForRegisterDto.getEmail(), jobSeekerForRegisterDto.getPassword());
		final Result userRegisterResult = userService.register(user);
		if (!userRegisterResult.isSuccess())
			return userRegisterResult;

		final JobSeeker jobSeeker = new JobSeeker(user.getId(),
				jobSeekerForRegisterDto.getFirstName(),
				jobSeekerForRegisterDto.getLastName(),
				jobSeekerForRegisterDto.getIdentityNumber(),
				jobSeekerForRegisterDto.getBirthDate());
		add(jobSeeker);

		final MernisActivation mernisActivation = new MernisActivation(0, user.getId(), true);
		mernisActivationService.add(mernisActivation);

		return new SuccessResult(Messages.jobSeekerAdded);
	}

	@Override
	public Result update(final JobSeeker jobSeeker) {
		jobSeekerDao.save(jobSeeker);

		return new SuccessResult(Messages.jobSeekerUpdated);
	}
	

	

}