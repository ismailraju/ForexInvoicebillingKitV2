/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.service;

import com.istl.dao.imp.IdmUserDAOImp;
import com.istl.enroll_kit.model.IdmUser;
import com.istl.util.PasswordUtils;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author Arun
 */
public class IdmUserService {
    
    IdmUserDAOImp dao= new IdmUserDAOImp();
    
    public boolean result(IdmUser user){
        return dao.addUser(user);
    }
    
    public boolean updateUser(IdmUser user){
        return dao.updateUser(user);
    }
    
    public String getSecurepassword(String password) throws InvalidKeySpecException{
        return PasswordUtils.getMD5EncryptedValue(password);
    }
    
    public IdmUser getVerification(String username,String password) throws InvalidKeySpecException{
        IdmUser user=dao.getDBPassword(username);
        if(user.getId() != null && user.getPassword().equals(PasswordUtils.getMD5EncryptedValue(password))){
            return user;
        }
        return null;
    }
}
