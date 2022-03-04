package ru.javabegin.example.roles.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	public Role(String name) {
		this.name = name;
	}
	public Role(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return this.name;
	}
	
}
