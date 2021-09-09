package com.CIS355.MaintenanceTrak;

import com.CIS355.MaintenanceTrak.UI.MainMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class MaintenanceTrakApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = createApplicationContext(args);
		displayMainFrame(context);
	}

	private static ConfigurableApplicationContext createApplicationContext (String[] args){
		return new SpringApplicationBuilder(MaintenanceTrakApplication.class)
				.headless(false)
				.run(args);
	}

	private static void displayMainFrame(ConfigurableApplicationContext context){
		SwingUtilities.invokeLater(() -> {
			MainMenu mainMenu = new MainMenu();
		});
	}

}
