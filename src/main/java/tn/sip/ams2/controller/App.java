package tn.sip.ams2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tn.sip.ams2.entities.Provider;
import tn.sip.ams2.repository.ArticleRepository;
import tn.sip.ams2.repository.ProviderRepository;
@Controller
public class App {
	private final ArticleRepository articleRepository;
	private final ProviderRepository providerRepository;
	@Autowired
	public App(ArticleRepository articleRepository, ProviderRepository providerRepository) {
	
		this.articleRepository = articleRepository;
		this.providerRepository = providerRepository;
	}

	//@GetMapping("home")
    //public String get(Model model) {
		//List <Provider> listp=providerRepository.findAll();
			//Long countarticle =articleRepository.count();
			//Long countprovider =providerRepository.count();
			//model.addAttribute("countarticle", countarticle);
			//model.addAttribute("countprovider", countprovider);
			//model.addAttribute("listp", listp);

    	
    	//return "/index";
    	
    	
   // }

    

}
