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

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekersController {
	private final JobSeekerService jobSeekerService;

	@Autowired
	public JobSeekersController(final JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}

	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<JobSeeker>>> getAll() {
		final DataResult<List<JobSeeker>> result = jobSeekerService.getAll();

		return ResponseEntity.ok(result);
	}

	@PostMapping("/register")
	public ResponseEntity<Result> register(@Valid @RequestBody final JobSeekerForRegisterDto jobSeekerForRegisterDto) {
		final Result result = jobSeekerService.register(jobSeekerForRegisterDto);

		if (!result.isSuccess())
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(result);
	}
}
