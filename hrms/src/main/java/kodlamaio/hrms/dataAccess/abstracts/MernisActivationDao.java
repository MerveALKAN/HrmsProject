package kodlamaio.hrms.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.MernisActivation;

public interface MernisActivationDao extends JpaRepository<MernisActivation, Integer> {

	
	Optional<MernisActivation> findByUserId (int userId);
}
