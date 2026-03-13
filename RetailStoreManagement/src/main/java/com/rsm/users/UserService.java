package com.rsm.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    public User save(User user) {
	        // encode password before saving
	        String encoded = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encoded);
	        return userRepository.save(user);
	    }

	    public List<User> getAll() {
	        return userRepository.findAll();
	    }

	    public User getById(Long id) {
	        return userRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }

	    public User update(Long id, User updatedUser) {
	        User existing = getById(id);

	        existing.setUsername(updatedUser.getUsername());

	        // only re-encode if a new password is provided (e.g. not blank)
	        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
	            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	        }

	        existing.setRole(updatedUser.getRole());
	        existing.setStatus(updatedUser.getStatus());

	        return userRepository.save(existing);
	    }

	    public void delete(Long id) {
	        userRepository.deleteById(id);
	    }

}
