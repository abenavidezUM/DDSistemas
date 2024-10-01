package com.example.joinTable;

import com.example.joinTable.entidades.Alumno;
import com.example.joinTable.entidades.Profesor;
import com.example.joinTable.enuneraciones.Especialidades;
import com.example.joinTable.enuneraciones.Titulos;
import com.example.joinTable.repositorios.AlumnoRepository;
import com.example.joinTable.repositorios.PersonaRepository;

import com.example.joinTable.repositorios.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class JoinTableApplication {

	private static final Logger logger = LoggerFactory.getLogger(JoinTableApplication.class);

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;
	public static void main(String[] args) {
		SpringApplication.run(JoinTableApplication.class, args);

		System.out.println("funcionando");
	}
	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository,
						   AlumnoRepository alumnoRepository,
						   ProfesorRepository profesorRepository) {
		return args -> {
			// Creo dos objetos Profesor
			Profesor prof1 = Profesor.builder()
					.nombre("Peter")
					.apellido("Gomez")
					.fechaIngreso(LocalDate.of(2020, 1, 1))
					.cantHijos(1)
					.titulo(Titulos.MASTER)
					.sueldo(new BigDecimal("123.45"))
					.build();

			Profesor prof2 = Profesor.builder()
					.nombre("Marta")
					.apellido("Gutierrez")
					.fechaIngreso(LocalDate.of(2021, 3, 15))
					.cantHijos(2)
					.titulo(Titulos.INGENIERO)
					.sueldo(new BigDecimal("2000.00"))
					.build();

			// Grabo los profesores a través del repositorio
			profesorRepository.save(prof1);
			profesorRepository.save(prof2);

			// Creo tres objetos Alumno
			Alumno alu1 = Alumno.builder()
					.nombre("Juan")
					.apellido("Waloski")
					.legajo(101)
					.especialidad(Especialidades.BACHILLER)
					.build();

			Alumno alu2 = Alumno.builder()
					.nombre("Ana")
					.apellido("Lia")
					.legajo(102)
					.especialidad(Especialidades.PERITO_MERCANTIL)
					.build();

			Alumno alu3 = Alumno.builder()
					.nombre("Luis")
					.apellido("Mira")
					.legajo(103)
					.especialidad(Especialidades.BACHILLER)
					.build();

			// Grabo los alumnos a través del repositorio
			alumnoRepository.save(alu1);
			alumnoRepository.save(alu2);
			alumnoRepository.save(alu3);

		};

	};
}
