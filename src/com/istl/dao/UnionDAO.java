/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao;

import com.istl.enroll_kit.model.EnrollUnion;
import com.istl.enroll_kit.model.EnrollUpazila;
import javafx.collections.ObservableList;

/**
 *
 * @author arun
 */
public interface UnionDAO {
    public ObservableList<EnrollUnion> getAllUnionByID(EnrollUpazila value);
    
}
