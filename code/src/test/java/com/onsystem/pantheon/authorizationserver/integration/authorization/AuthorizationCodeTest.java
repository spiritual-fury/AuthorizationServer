package com.onsystem.pantheon.authorizationserver.integration.authorization;

import com.onsystem.pantheon.authorizationserver.AuthorizationServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AuthorizationServerApplication.class)
@ActiveProfiles(profiles = {"postgres"})
public class AuthorizationCodeTest {

    @Test
    public void exampleTest() {
        System.out.println("asdlnalsdn");
    }
}
