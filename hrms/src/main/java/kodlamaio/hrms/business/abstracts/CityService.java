package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.business.BaseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.City;

public interface CityService extends BaseService<City> {
	DataResult<City> getByName(String name);

	DataResult<List<City>> getByNameContains(String name);
}