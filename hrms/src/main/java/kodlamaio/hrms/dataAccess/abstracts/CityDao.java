package kodlamaio.hrms.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.City;

import java.util.List;

public interface CityDao extends JpaRepository<City, Short> {
	Optional<City> findByName(String name);

	Optional<List<City>> findByNameContains(String name);
}