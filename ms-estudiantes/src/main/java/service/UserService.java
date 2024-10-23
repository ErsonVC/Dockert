package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dto.RegisterDto;
import model.User;
import repository.UserRepository;

@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

	    public User registerUser(RegisterDto userRegisterDto) {
	        User user = new User();
	        user.setUsername(userRegisterDto.getUsername());
	        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
	        return userRepository.save(user);
	    }
    
   /*public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); 
        return userRepository.save(user); 
    }*/
}
