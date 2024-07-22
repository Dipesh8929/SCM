package com.scm.controllers;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.MessageType;
import com.scm.helpers.message1;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Builder;


@Builder
@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @RequestMapping("/home")
    public String home(Model model)
    {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "substring Technologies");
        model.addAttribute("facebook", "dipesh punyani");
        model.addAttribute("Youtube", "https://www.youtube.com/");
        return "home";
    }
// aboute route
@RequestMapping("/about")
public String aboutPage(Model model)
{
    model.addAttribute("isLogin", true);
    System.out.println("About page loading");
    return "about";
}
// services
@RequestMapping("/services")
public String servicesPage()
{
System.out.println("services loading");
    return "services";
}

// contact page
@GetMapping("/contact")
public String contact() {
    return new String("contact");
}

// login page

@GetMapping("/login")
public String login() {
    return new String("login");
}

// sigunp page

@GetMapping("/register")
public String register(Model model) {
    UserForm userForm=new UserForm();
    // default data bhi daal skte hai 
    
    

    model.addAttribute("userForm", userForm);
    return new String("register");
}

// processing register
@RequestMapping(value = "/do-register", method=RequestMethod.POST)
public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session){
    System.out.println("processing registration");
    // fetch from data
        // UserForm
        System.out.println(userForm);
    // validate from data

if(rBindingResult.hasErrors())
{
    return"register";
}

    // save to database
    // userservie

    // userform ko use krke user ka data bnaya hai
    // User user=User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .password(userForm.getPassword())
    // .about(userForm.getAbout())
    // .phoneNumber(userForm.getPhoneNumber())
    // .profilePic("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?t=st=1720387374~exp=1720390974~hmac=432f38363a90ac982f4894e8af963c6076680d5197a8b10b19edc6861b68517e&w=740")
    // .build();

    User user=new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setProfilePic("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?t=st=1720387374~exp=1720390974~hmac=432f38363a90ac982f4894e8af963c6076680d5197a8b10b19edc6861b68517e&w=740");
    



        User savedUser=userService.saveUser(user);
        System.out.println("user Saved");
    // message="Registration Successful"

    // add the message
message1 message=message1.builder().content("Registration successful").type(MessageType.green).build();
    session.setAttribute("message",message);

    // redirect login page


    return "redirect:/register";

}



}
