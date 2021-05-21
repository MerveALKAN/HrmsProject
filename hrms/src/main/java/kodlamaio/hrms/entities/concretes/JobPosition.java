package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="job_positions")

public class JobPosition {
@GeneratedValue
@Id

@Column(name = "id")
	private int id;
@Column(name = "title")	
	private String title;

public JobPosition() {}

public JobPosition(int id, String title) {
	super();
	this.id = id;
	this.title = title;
}

	
}
