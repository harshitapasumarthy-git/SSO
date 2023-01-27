package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ControllerClass {

	@Autowired
	private UserRepository userRepository;
   
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";

    @GetMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView login( String name){
    	 ModelAndView model1 = new ModelAndView();
         model1.setViewName("login");
  
         return model1;
    }

    @PostMapping("/reg") 
	  private ResponseEntity<?> registerUser(@RequestBody
	  AuthenticationRequest authenticationRequest){
	  
	  String username = authenticationRequest.getUserName(); 
	  String password = authenticationRequest.getPassword(); 
	  User usermodel = new User();
//	  usermodel.setId(1);
	  usermodel.setUserName(username); 
	  usermodel.setPassword(password);
	  
	  try { 
		  userRepository.save(usermodel); 
	
	} 
	  catch(Exception e) { return
	  ResponseEntity.ok( new
	  AuthenticationResponse("Error cannot be registered"+username));
	  
	  }
	  System.out.println(usermodel.toString());
	  return ResponseEntity.ok(new
	  AuthenticationResponse("Registration Successful" + username));
	  }


 

    @PostMapping(value ="/login")
    
   
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpServletResponse response, String redirect,Model model, @ModelAttribute LoginForm loginForm) {
    	
        // check if the userName and password exist in the user repository
        User users = userRepository.findByUserNameAndPassword(userName,password);
        ModelAndView model1 = new ModelAndView();
        if (users == null) {
        	model.addAttribute("error", "Invalid username or password!");
        	
             model1.setViewName("error");
        	 return "login";
        }
        
       
          String token = Jwt.generateToken(signingKey, userName);

          CookieClass.create(response, jwtTokenCookieName, token, false, -1, "localhost");

            model1.setViewName("login");
            return "redirect:"+ "https:localhost:8280/protected-resource" ;
       
    }
}



