package kodlamaio.hrms.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import kodlamaio.hrms.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailActivationForVerifyDto implements Dto {
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String authToken;
}