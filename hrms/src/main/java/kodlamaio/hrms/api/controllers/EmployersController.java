package kodlamaio.hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
	private final EmployerService employerService;

	@Autowired
	public EmployersController(final EmployerService employerService) {
		this.employerService = employerService;
	}

	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<Employer>>> getAll() {
		final DataResult<List<Employer>> result = employerService.getAll();

		return ResponseEntity.ok(result);
	}

	@PostMapping("/register")
	public ResponseEntity<Result> register(@Valid @RequestBody final EmployerForRegisterDto employerForRegisterDto) {
		final Result result = employerService.register(employerForRegisterDto);

		if (!result.isSuccess())
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(result);
	}

}