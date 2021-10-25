package net.reciclaje;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class AppController {
 
    @Autowired
	private UserRepository repo;
	
    @RequestMapping({"/login", "/",})
    public String viewHomePage() {
        return "login";
    }
    
    @RequestMapping({"/index"})
    public String viewIndexPage() {
        return "index";
    }
    
    @RequestMapping({"/register"})
    public String showSingUpForm(Model model) {
    	model.addAttribute("user", new User());
    	
    	return "login";
    }
    
    @PostMapping("/process_register")
    public String processRegistration(User user) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String encodedPassword = encoder.encode(user.getPassword());
    	user.setPassword(encodedPassword);
    	repo.save(user);
    	return "registro_completado";
    }
    
    @RequestMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
         
        return "users";
    }
    
    
}
