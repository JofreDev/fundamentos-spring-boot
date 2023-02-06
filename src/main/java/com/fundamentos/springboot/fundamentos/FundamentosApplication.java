package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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



	// Constructor que hace posible la inyección de dependencias
	// @qualifier me ayuda a definir que implementación de la interface usar
	//Por lo tanto puedo tener n implementaciones a partir de una interfaz
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  PrintAreas printAreas,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean= myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.printAreas = printAreas;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	/*Método que ejecuta , en la aplicación,todo lo que se le indique*/

	@Override
	public void run(String... args) throws Exception {

		//conceptos();
		saveUsersDB();

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

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9);
		list.forEach(userRepository::save);
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
}
