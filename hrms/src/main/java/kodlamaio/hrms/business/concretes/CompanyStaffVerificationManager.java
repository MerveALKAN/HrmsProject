package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CompanyStaffVerificationService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CompanyStaffVerificationDao;
import kodlamaio.hrms.entities.concretes.CompanyStaffVerification;

@Service
public class CompanyStaffVerificationManager implements CompanyStaffVerificationService {
	private final CompanyStaffVerificationDao companyStaffVerificationDao;

	@Autowired
	public CompanyStaffVerificationManager(final CompanyStaffVerificationDao companyStaffVerificationDao) {
		this.companyStaffVerificationDao = companyStaffVerificationDao;
	}

	@Override
	public Result add(final CompanyStaffVerification companyStaffVerification) {
		companyStaffVerificationDao.save(companyStaffVerification);

		return new SuccessResult(Messages.companyStaffVerificationAdded);
	}

	@Override
	public Result delete(final CompanyStaffVerification companyStaffVerification) {
		companyStaffVerificationDao.delete(companyStaffVerification);

		return new SuccessResult(Messages.companyStaffVerificationDeleted);
	}

	@Override
	public DataResult<List<CompanyStaffVerification>> getAll() {
		final List<CompanyStaffVerification> companyStaffVerifications = companyStaffVerificationDao.findAll();

		return new SuccessDataResult<List<CompanyStaffVerification>>(companyStaffVerifications);
	}

	@Override
	public DataResult<CompanyStaffVerification> getById(final int id) {
		final Optional<CompanyStaffVerification> companyStaffVerification = companyStaffVerificationDao.findById(id);

		if (companyStaffVerification.isEmpty())
			return new ErrorDataResult<CompanyStaffVerification>(Messages.companyStaffVerificationNotFound);

		return new SuccessDataResult<CompanyStaffVerification>(companyStaffVerification.get());
	}

	@Override
	public Result update(final CompanyStaffVerification companyStaffVerification) {
		companyStaffVerificationDao.save(companyStaffVerification);

		return new SuccessResult(Messages.companyStaffVerificationUpdated);
	}

	@Override
	public Result verify(final int id) {
		final Optional<CompanyStaffVerification> companyStaffVerification = companyStaffVerificationDao.findById(id);

		if (companyStaffVerification.isEmpty())
			return new ErrorResult(Messages.companyStaffVerificationNotFound);

		companyStaffVerification.get().setApproved(true);
		companyStaffVerificationDao.save(companyStaffVerification.get());

		return new SuccessResult(Messages.companyStaffVerificationVerified);
	}

}