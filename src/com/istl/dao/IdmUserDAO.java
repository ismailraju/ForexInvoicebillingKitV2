/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao;

import com.istl.enroll_kit.model.IdmUser;

/**
 *
 * @author Arun
 */
public interface IdmUserDAO{
    public boolean addUser(IdmUser user);
    public IdmUser getDBPassword(String username);
    public boolean updateUser(IdmUser user);
}
