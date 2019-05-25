package com.istl.dao;



import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollDivision;
import javafx.collections.ObservableList;


public interface DistrictDAO {
    public ObservableList<EnrollDistrict> findAllDistrict();
    public ObservableList<EnrollDistrict> getAllDistrictByID(EnrollDivision value);
}
