package com.sandi.authorizationsvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.cloud.config.enabled=false")
@ActiveProfiles("test")
public class AuthorizationSvcApplicationTests {

	@Test
	public void contextLoads() {
	}

}
