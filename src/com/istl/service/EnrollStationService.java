/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.service;

import com.istl.dao.imp.EnrollStationDAOImp;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollStation;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class EnrollStationService {
    EnrollStationDAOImp daoimp = new EnrollStationDAOImp();
    public ObservableList<EnrollStation> getStationById(EnrollDistrict district)
    {
        return daoimp.getStationById(district);
    }
    
}
