package kodlamaio.hrms.business.adapters;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
	public class PersonForValidateFromMernisService {

	@NotNull
	private long TcKimlikNo;
	
	@NotBlank
	String ad;
	
	@NotBlank
	String soyad;
	
	@Past
	int dogumYili;
}
