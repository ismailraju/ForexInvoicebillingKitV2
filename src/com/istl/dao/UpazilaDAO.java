package com.istl.dao;

import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollUpazila;
import javafx.collections.ObservableList;

public interface UpazilaDAO {
    public ObservableList<EnrollUpazila> getAllUpazilaByID(EnrollDistrict value);
   
}
