package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmailActivationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmailActivationForVerifyDto;

@RestController
@RequestMapping("/api/emailactivations")
public class EmailActivationsController {
	private final EmailActivationService emailActivationService;

	@Autowired
	public EmailActivationsController(final EmailActivationService emailActivationService) {
		this.emailActivationService = emailActivationService;
	}

	@GetMapping("/verify")
	public ResponseEntity<Result> verify(@Valid final EmailActivationForVerifyDto emailActivationForVerifyDto) {
		final Result result = emailActivationService.verify(emailActivationForVerifyDto);

		if (!result.isSuccess())
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(result);
	}
}