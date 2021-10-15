package tn.sip.ams2;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.sip.ams2.controller.ArticleController;
import tn.sip.ams2.controller.ProviderController;


@SpringBootApplication
public class Ams2Application {

	public static void main(String[] args) {
		new File(ArticleController.uploadDirectory).mkdir();
		new File(ProviderController.uploadDirectory).mkdir();
		SpringApplication.run(Ams2Application.class, args);
	}

}
