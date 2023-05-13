package my.learn.oidc.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import my.learn.oidc.model.Provider;
import my.learn.oidc.model.User;
import my.learn.oidc.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
 
    private final UserRepository repo;

    public void processOAuthPostLogin(String username, String email, String provider) {
        repo.findByUsername(username)
        .ifPresentOrElse(existingUser -> {
        	// any action need to do with existing user
        	// may be count logged in times...
        }, ()-> {
        	User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setProvider(Provider.valueOf(provider.toUpperCase()));    

            repo.save(newUser);
        });
    }
     
}
