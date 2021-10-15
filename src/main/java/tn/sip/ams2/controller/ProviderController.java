package tn.sip.ams2.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import tn.sip.ams2.entities.Article;
import tn.sip.ams2.entities.Provider;
import tn.sip.ams2.repository.ProviderRepository;


@Controller
@RequestMapping("/provider/")

public class ProviderController {
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads2";

	@Autowired
	private final ProviderRepository providerRepository;

	// injection de dépencence + IOC(Inversion Of Control)
	public ProviderController(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	@GetMapping("list")
	public String listProviders(Model model) {
		List<Provider> lp = (List<Provider>) providerRepository.findAll();
		if (lp.size() == 0)
			lp = null;
		// System.out.println(lp);
		// System.out.println(lp.size());
		model.addAttribute("providers", lp);
		return "provider/listProviders";
	}

	@GetMapping("add")
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();// object dont la valeur des attributs par defaut
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("add")
    //@ResponseBody
    public String addArticle(@Valid Provider article, BindingResult result, @RequestParam(name = "providerId", required = false) Long p,
    		@RequestParam("files") MultipartFile[] files
) {
    	
    	
    	/// start upload
    	
    	StringBuilder fileName = new StringBuilder();
    	LocalDateTime ldt = LocalDateTime.now();
    	MultipartFile file = files[0];
    	String finalName = getSaltString().concat(file.getOriginalFilename()); 
    	Path fileNameAndPath = Paths.get(uploadDirectory, finalName);
    	
    	fileName.append(finalName);
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		/// end upload
		 article.setLogo(fileName.toString());

    	 providerRepository.save(article);
    	 return "redirect:list";
    	
    	//return article.getLabel() + " " +article.getPrice() + " " + p.toString();
    }
	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id, Model
	model) {
	//long id2 = 100L;
	Provider provider = providerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
	System.out.println("suite du programme...");
	providerRepository.delete(provider);
	/*model.addAttribute("providers",
	providerRepository.findAll());
	return "provider/listProviders";*/
	return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") long
			id, Model model) {
			Provider provider = providerRepository.findById(id)
			.orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
			model.addAttribute("provider", provider);
			return "provider/updateProvider";
			}

	@PostMapping("update")
	public String updateProvider(@Valid Provider provider, BindingResult result, Model model) {
		providerRepository.save(provider);
		return "redirect:list";
	}
	
	@GetMapping("show/{id}")
	public String showProvider(@PathVariable("id") long id, Model model) {
		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		List<Article> articles = providerRepository.findArticlesByProvider(id);
		for (Article a : articles)
			System.out.println("Article = " + a.getLabel());
		
		model.addAttribute("articles", articles);
		model.addAttribute("provider", provider);
		return "provider/showProvider";
	}
	
	@PostMapping("search")
	//@ResponseBody
	public String findProviderByName(@RequestParam("nom")String name,Model model) {
		//List<Provider> lp = providerRepository.ListProviderName(name);
		List<Provider> lp = providerRepository.findProviderByNameLike(name);
		
		if (lp.size() == 0)
			lp = null;
		 System.out.println(lp);
		// System.out.println(lp.size());
		model.addAttribute("providers", lp);
		return "provider/listProviders";
		//return "Vous avez taper : "+ name;
	}
	protected static String getSaltString() {
 		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
 		StringBuilder salt = new StringBuilder();  
 		Random rnd = new Random();
 		while (salt.length() < 18) { // length of the random string.
 			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
 			salt.append(SALTCHARS.charAt(index));
 		}
 		String saltStr = salt.toString();
 		return saltStr;

 	}

}