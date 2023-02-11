package br.com.nullbank.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@ToString
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private UUID id;
	
	@Column(nullable = false, length = 130, name = "nome")
	private String name;
	
	@Column(nullable = false, unique = true, length = 11, name = "cpf")
	private String cpf;
}
