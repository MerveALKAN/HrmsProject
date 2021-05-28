package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailActivationService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.helpers.EmailService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmailActivationDao;
import kodlamaio.hrms.entities.concretes.EmailActivation;
import kodlamaio.hrms.entities.dtos.EmailActivationForVerifyDto;

@Service
public class EmailActivationManager implements EmailActivationService {
	private final EmailActivationDao emailActivationDao;
	private final EmailService emailService;

	@Autowired
	public EmailActivationManager(final EmailActivationDao emailActivationDao, final EmailService emailService) {
		this.emailActivationDao = emailActivationDao;
		this.emailService = emailService;
	}

	@Override
	public Result add(final EmailActivation emailActivation) {
		emailActivationDao.save(emailActivation);
		return new SuccessResult(Messages.emailActivationAdded);
	}

	@Override
	public Result createAndSendByMail(final int userId, final String email) {
		final EmailActivation emailActivation = new EmailActivation(0, userId, "123456", email, false);
		// TODO create authToken
		emailActivationDao.save(emailActivation);

		emailService.send(email,
				Messages.emailActivationVerifyEmailTitle,
				Messages.emailActivationVerifyEmailBody + "www.localhost:8080/api/emailactivations/verify?authToken="
						+ emailActivation.getAuthToken() + "&email=" + email);

		return new SuccessResult(Messages.emailActivationCreatedAndSent);
	}

	@Override
	public Result delete(final EmailActivation emailActivation) {
		emailActivationDao.delete(emailActivation);

		return new SuccessResult(Messages.emailActivationDeleted);
	}

	@Override
	public DataResult<List<EmailActivation>> getAll() {
		final List<EmailActivation> emailActivations = emailActivationDao.findAll();

		return new SuccessDataResult<List<EmailActivation>>(emailActivations);
	}

	@Override
	public DataResult<EmailActivation> getById(final int id) {
		final Optional<EmailActivation> emailActivation = emailActivationDao.findById(id);

		if (emailActivation.isEmpty())
			return new ErrorDataResult<EmailActivation>(Messages.emailActivationNotFound);

		return new SuccessDataResult<EmailActivation>(emailActivation.get());
	}

	@Override
	public Result update(final EmailActivation emailActivation) {
		emailActivationDao.save(emailActivation);

		return new SuccessResult(Messages.emailActivationUpdated);
	}

	@Override
	public Result verify(final EmailActivationForVerifyDto emailActivationForVerifyDto) {
		final Optional<EmailActivation> emailActivation = emailActivationDao.findByEmailAndAuthToken(
				emailActivationForVerifyDto.getEmail(),
				emailActivationForVerifyDto.getAuthToken());

		if (emailActivation.isEmpty())
			return new ErrorResult(Messages.emailNotVerified);

		emailActivation.get().setApproved(true);
		emailActivationDao.save(emailActivation.get());

		return new SuccessResult(Messages.emailVerified);
	}

}