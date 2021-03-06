package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="job_positions")


public class JobPosition {
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Id

@Column(name = "id")
	private int id;
@Column(name = "title")	
	private String title;


	
}
