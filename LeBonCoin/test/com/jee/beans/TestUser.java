package com.jee.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUser {
	
	@Test
	public void testUserBuilder() {
		User user = new User
                .Builder(50, "login")
                .password("password")
                .localisation("nice")
                .img("img")
                .build();
		
		assertEquals(50, user.getId());
		assertEquals("login", user.getLogin());
		assertEquals("nice", user.getLocalisation());
		assertEquals("password", user.getPassword());
	}

}
