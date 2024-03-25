package com.guipalm4.oauthsociallogin;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;



@Controller
@RequestMapping
public class SocialLoginController {
    
    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model){
        
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();
        
        System.out.println("Atibutos do token de acesso:");
        for(Map.Entry<String, Object> entry : attributes.entrySet()) {
            
            System.out.println("Chave " + entry.getKey() + ", Valor " + entry.getValue());
        }
        
        final var name = attributes.get("name").toString();
        final var email = attributes.get("email").toString();
        final var picture = attributes.get("picture").toString();
        
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("photo", picture);
        
        return "index";
    }
    
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    
}
