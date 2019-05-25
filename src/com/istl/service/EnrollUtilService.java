/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.service;

import com.istl.accesscontrol.model.EnrollUtilsRespons;
import com.istl.dao.imp.EnrollUtilDAOImp;
import com.istl.enroll_kit.model.EnrollUtils;
import com.istl.enroll_kit.service.AccessControlServiceManager;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class EnrollUtilService {
    EnrollUtilDAOImp dao = new EnrollUtilDAOImp();
    AccessControlServiceManager manager = new AccessControlServiceManager();
    
    public boolean save(EnrollUtils util,int a)
    {
        return dao.save(util,a);
    }
    
    public ObservableList<EnrollUtils> getUtilsList(String name)
    {
       return dao.getUtilsList(name);
    }
    public boolean removeItem(EnrollUtils utils){
        return dao.removeItem(utils);
    }
    public EnrollUtilsRespons getList()
    {
        return manager.getUtilsList();
    }
    
     public boolean isValidEnrollUtils(String a)
    {
        return dao.check(a);
    }
}
