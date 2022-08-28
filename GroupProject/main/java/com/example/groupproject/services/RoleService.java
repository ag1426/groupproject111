package com.example.groupproject.services;

import com.example.groupproject.dao.RoleDao;
import com.example.groupproject.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole( Role role){
       return roleDao.save(role);


    }
}
