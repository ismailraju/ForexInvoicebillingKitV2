/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import com.istl.enroll_kit.model.IdmUser;

/**
 *
 * @author Arun
 */
public class  User {
    static IdmUser user= new IdmUser();

    public  User() {
    }
    
    public static IdmUser getUser() {
        return user;
    }

    public static void setUser(IdmUser user) {
        User.user = user;
    }
    
    
    
}
