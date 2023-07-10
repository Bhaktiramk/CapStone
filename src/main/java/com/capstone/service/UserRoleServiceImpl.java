package com.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.entity.Role;
import com.capstone.repo.RoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findRoleByName(String roleName) {

		return roleRepository.findByName(roleName);
	}

}
