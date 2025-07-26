package poly.ptpm.demojwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.ptpm.demojwt.dto.AuthRequest;
import poly.ptpm.demojwt.dto.VerifyResult;
import poly.ptpm.demojwt.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verify(@RequestBody String token) {
        String username = jwtUtil.extractUsername(token);
        boolean isValid = jwtUtil.validateToken(token, username);
        VerifyResult result = new VerifyResult();
        result.setUsername(username);
        result.setValid(isValid);
        return ResponseEntity.ok(result);
    }
}
