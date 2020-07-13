package restapi.jwtauthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restapi.jwtauthentication.config.JwtTokenUtil;
import restapi.jwtauthentication.entity.User;
import restapi.jwtauthentication.model.AuthRequest;
import restapi.jwtauthentication.model.AuthResponse;
import restapi.jwtauthentication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception
	{
		authenticate(authRequest.getUsername(), authRequest.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody AuthRequest authRequest) throws Exception
	{
		User newUser = new User(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()));
		userService.save(newUser);
		return ResponseEntity.ok("Successfully created new User");
	}
	
	private void authenticate(String username, String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} 
		catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
