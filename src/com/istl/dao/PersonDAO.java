/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.dao;

import com.istl.enroll_kit.model.EnrollPerson;
import javafx.collections.ObservableList;

/**
 *
 * @author arun
 */
public interface PersonDAO {
    public ObservableList<EnrollPerson> findTopPerson(Integer value);
    public ObservableList<EnrollPerson> findAllPerson();
    public int personDelete(EnrollPerson person);
    public ObservableList<EnrollPerson> personSearchByName(String name) ;
    public ObservableList<EnrollPerson> personAdvancedSearch(EnrollPerson person);
    public ObservableList<EnrollPerson> getPersonByStatus(Integer id);
    public boolean nidMatching(String str,EnrollPerson person);
    public boolean mobileMatching(String str,EnrollPerson person);
}
