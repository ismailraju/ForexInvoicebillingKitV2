package com.istl.dao;

import com.istl.enroll_kit.model.EnrollDivision;
import javafx.collections.ObservableList;


public interface DivisionDAO {
     public ObservableList<EnrollDivision> findAllDivision();
}
