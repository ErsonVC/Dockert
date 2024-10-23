package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import config.JwtUtil;
import dto.LoginDto;
import dto.RegisterDto;
import model.User;
import service.UserService;

@RestController
public class AuthController {
	
    @Autowired
    private UserService userService; 

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto userLoginDto) {
        User user = userService.findByUsername(userLoginDto.getUsername());
        if (user != null && new BCryptPasswordEncoder().matches(userLoginDto.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto userRegisterDto) {
        userService.registerUser(userRegisterDto);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}