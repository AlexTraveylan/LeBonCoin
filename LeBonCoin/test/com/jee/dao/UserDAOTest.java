package com.jee.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jee.beans.User;

class UserDAOTest {
	
	@Test
	public void testGetAllUsers() {
		List<User> l = UsersDAO.getAllUsers();
		
		assertDoesNotThrow(() -> UsersDAO.getAllUsers());
		assertEquals(1, l.get(0).getId());
		
	}

}
