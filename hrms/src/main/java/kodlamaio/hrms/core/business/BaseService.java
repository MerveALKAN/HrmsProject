package kodlamaio.hrms.core.business;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface BaseService<T>{

	
	Result add(T entity);
	Result delete(T entity);
	DataResult<List<T>> getAll();
	DataResult<T> getById(int id);
	Result update (T entity);
	
}
