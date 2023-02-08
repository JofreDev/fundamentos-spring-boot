package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class); // -> Manejo de Logs

	private ComponentDependency componentDependency;

	// Se inyecta nuevamente a la interfaz
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	private PrintAreas printAreas;

	private MyBeanWithProperties myBeanWithProperties;

	private UserRepository userRepository;

	private UserPojo userPojo;

	private UserService userService;



	// Constructor que hace posible la inyección de dependencias
	// @qualifier me ayuda a definir que implementación de la interface usar
	//Por lo tanto puedo tener n implementaciones a partir de una interfaz
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  PrintAreas printAreas,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository,
								  UserService userService){
		this.componentDependency = componentDependency;
		this.myBean= myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.printAreas = printAreas;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	/*Método que ejecuta , en la aplicación,todo lo que se le indique*/

	@Override
	public void run(String... args) throws Exception {

		//conceptos();
		saveUsersDB();
		//getInformationJPQLFromUser();
		saveWithErrorTransactional();

	}

	private void saveUsersDB(){
		User user1 = new User("John","john@domain.com", LocalDate.of(2021,03,20));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 11, 12));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021, 2, 27));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021, 4, 10));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,
										user10,user11,user12);
		list.forEach(userRepository::save);
	}

	private void getInformationJPQLFromUser(){

		try{
			LOGGER.info("Usuario con el método findUserByEmail : "	+
					userRepository.findUserByEmail("iuytfriuyf@domain.com")
							.orElseThrow(() -> new RuntimeException("No se encontró el user")));
		}catch(Exception e){
			LOGGER.error("Esto es un error  "+ e.getMessage());
		}

			userRepository.findAndSort("user", Sort.by("id").descending())
					.stream()
					.forEach(user -> LOGGER.info("USUARIO CON MÉTODO SORT : "+user));

		userRepository.findByName("Daniela")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method" + user));

		LOGGER.info("Usuario con Query Method :  " + userRepository.findByEmailAndName("ff@domain.com","Luis"));

		userRepository.findByNameLike("%user%")// representa un like de SQL
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike" + user));

		userRepository.findByNameOrEmail(null,"user10@domain.com" )// representa un like de SQL
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));

		userRepository.findByBirthDateIsBetween(LocalDate.of(2021,03,01),
												LocalDate.of(2021,04,02))// representa un like de SQL
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByBirthDateIsBetween" + user));


		userRepository.findByNameLikeOrderByIdDesc("%user%")// %% necesario para implementar like
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLikeOrderByIdDesc" + user));


		userRepository.findByNameContainingOrderByIdDesc("user")// %% necesario para implementar like
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameContainingOrderByIdDesc" + user));

		// Se muestra a nivel de clase DTO
		LOGGER.info("El Usuario a partir del named parameter es : "+userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 9, 8), "daniela@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontró el usario a partir del named parameter")));





	}

	public void conceptos(){

		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		printAreas.printAreasWithDependency();
		System.out.println(myBeanWithProperties.function());

		System.out.println(userPojo.getEmail() +" - " + userPojo.getPassword());
		System.out.println(userPojo.getAge());

		// Manejo de errores con logger
		try{
			//Error
			int value = 10/0;
			LOGGER.debug("Mi valor :"+value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero "+ e.getMessage());
		}

	}

	private void saveWithErrorTransactional(){

		///Se genera un error automático

		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1,test2,test3,test4);

		try{
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Se ha generado un error en 'Insertar'. Se ejecuta Rollback");
		};


		userService.getAllUsers().stream()
				.forEach(user ->
						LOGGER.info("Este es el usuario dentro del método transaccional " +
								user));
	}

}
