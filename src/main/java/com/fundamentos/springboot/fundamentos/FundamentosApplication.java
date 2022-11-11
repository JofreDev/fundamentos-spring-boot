package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	private PrintAreas printAreas;

	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo;



	// Constructor que hace posible la inyección de dependencias
	// @qualifier me ayuda a definir que implementación de la interface usar
	//Por lo tanto puedo tener n implementaciones a partir de una dependencia
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  PrintAreas printAreas,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo){
		this.componentDependency = componentDependency;
		this.myBean= myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.printAreas = printAreas;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	/* Método que ejecuta , en la aplicación,todo lo que se le indique */

	@Override
	public void run(String... args) throws Exception {

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
