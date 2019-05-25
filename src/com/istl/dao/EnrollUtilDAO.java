/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao;

import com.istl.enroll_kit.model.EnrollUtils;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public interface EnrollUtilDAO {
    public boolean save(EnrollUtils util,int a);
    public ObservableList<EnrollUtils> getUtilsList(String name);
    public boolean removeItem(EnrollUtils utils);
    public boolean check(String a);
}
