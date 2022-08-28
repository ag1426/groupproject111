package com.example.groupproject.services;

import com.example.groupproject.dao.ProductDao;
import com.example.groupproject.dao.RoleDao;
import com.example.groupproject.dao.UserDao;
import com.example.groupproject.entity.Product;
import com.example.groupproject.entity.Role;
import com.example.groupproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerNewUser(User user) {


        return userDao.save(user);
    }

       public void initRolesAndUser(){
           Role adminRole = new Role();
           adminRole.setRoleName("Admin");
           adminRole.setRoleDescription("Admin Role");
           roleDao.save(adminRole);

           Role userRole = new Role();
           userRole.setRoleName("User");
           userRole.setRoleDescription("Default role for newly created record");
           roleDao.save(userRole);

           Role merchantRole = new Role();
           merchantRole.setRoleName("Merchant");
           merchantRole.setRoleDescription("Default role for newly created seller record");
           roleDao.save(merchantRole);

           User adminUser = new User();
           adminUser.setUserName("admin");
           adminUser.setUserFullName("Anjal Giri");
           adminUser.setEmail("anjal@gmail.com");
           adminUser.setUserPassword(getEncodedPassword("admin"));
           Set<Role> adminRoles = new HashSet<>();
           adminRoles.add(adminRole);
           adminUser.setRole(adminRoles);
           userDao.save(adminUser);


           User user = new User();
           user.setUserName("Zodiac_God");
           user.setUserFullName("Suyog Dhakal");
           user.setEmail("suyog@gmail.com");
           user.setUserPassword(getEncodedPassword("Suyog@123"));
           Set<Role> userRoles = new HashSet<>();
           userRoles.add(userRole);
           user.setRole(userRoles);
           userDao.save(user);

           User merchant = new User();
           merchant.setUserName("Rikrish");
           merchant.setUserFullName("Rikrish Shrestha");
           merchant.setEmail("rikrish@gmail.com");
           merchant.setUserPassword(getEncodedPassword("Rikrish123"));
           Set<Role> merchantRoles = new HashSet<>();
           merchantRoles.add(merchantRole);
           merchant.setRole(merchantRoles);
           userDao.save(merchant);

           Product product = new Product();
           product.setProductId(1);
           product.setProductName("Hoodie");
           product.setCategory("Clothing");

           product.setStocks(33);
           product.setPrice(200);
           product.setMerchant(merchant);
           productDao.save(product);

           Product product2 = new Product();
           product2.setProductId(2);
           product2.setProductName("Burger");
           product2.setCategory("Food");

           product2.setStocks(9);
           product2.setPrice(66);
           product2.setMerchant(merchant);
           productDao.save(product2);
       }

        public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
        }

    }

