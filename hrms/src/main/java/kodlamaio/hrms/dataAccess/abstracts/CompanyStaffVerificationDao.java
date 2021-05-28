package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.CompanyStaffVerification;

public interface CompanyStaffVerificationDao extends JpaRepository<CompanyStaffVerification, Integer> {

	
	
}
