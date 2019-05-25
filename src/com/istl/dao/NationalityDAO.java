package com.istl.dao;


import com.istl.enroll_kit.model.EnrollNationalityLookup;
import javafx.collections.ObservableList;


public interface NationalityDAO {
    public ObservableList<EnrollNationalityLookup> findAllNationality();
}
