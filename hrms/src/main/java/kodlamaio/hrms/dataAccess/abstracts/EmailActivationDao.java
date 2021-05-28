package kodlamaio.hrms.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EmailActivation;

public interface EmailActivationDao extends JpaRepository<EmailActivation, Integer>{

	
	Optional<EmailActivation> findByEmailAndAuthToken(String email, String authToken);
}
