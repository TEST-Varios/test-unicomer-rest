package cl.testapi.unicomer.model.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "clients")
public class ClientsDataInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	@Column(name = "UUID", columnDefinition = "VARCHAR(255)")
	private UUID cliId;

	private String firstName;

	private String lastName;

	private String birthday;

	private String gender;

	private String cellPhone;

	private String homePhone;

	private String adressHome;

	private String profession;

	private String incomes;

}
