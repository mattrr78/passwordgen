package org.mattrr78.passwordgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class Application {

    public static final String VERSION = "1.0.1";

    private final PasswordGenerator generator;

    private final String ipAddress;

    public Application()  {
        generator = new PasswordGenerator();
        ipAddress = getIpAddress();
    }

    private String getIpAddress()  {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path = "/api/password")
    public ResponseEntity<GeneratePasswordResponse> generate(@RequestBody GeneratePasswordRequest request) {
        GeneratePasswordResponse response = new GeneratePasswordResponse();

        response.setPasswords(generator.generate(request));

        response.setFootnote(new Footnote(VERSION, ipAddress));

        return ResponseEntity.ok(response);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
