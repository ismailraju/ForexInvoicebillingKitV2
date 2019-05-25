package com.istl.service;

import com.istl.dao.imp.DistrictDAOImp;
import com.istl.dao.imp.DivisionDAOImp;
import com.istl.dao.imp.LookupDAOImp;
import com.istl.dao.imp.NationalityDAOImp;
import com.istl.dao.imp.ParsonDAOImp;
import com.istl.dao.imp.UnionDAOImp;
import com.istl.dao.imp.UpazilaDAOImp;
import com.istl.enroll_kit.model.EnrollDistrict;
import com.istl.enroll_kit.model.EnrollDivision;
import com.istl.enroll_kit.model.EnrollLookup;
import com.istl.enroll_kit.model.EnrollNationalityLookup;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollPersonBiometric;
import com.istl.enroll_kit.model.EnrollUnion;
import com.istl.enroll_kit.model.EnrollUpazila;
import java.text.ParseException;
import javafx.collections.ObservableList;

public class PersonService {

    ParsonDAOImp person = new ParsonDAOImp();

    public EnrollLookup getEnrollLookup(Short value) {
        EnrollLookup gender = new EnrollLookup();
        gender.setId(value);

        return gender;
    }

    public boolean addPersonData(EnrollPerson person, EnrollPersonBiometric biometric) {
        // System.out.println("com.istl.service.ParsonService.addPersonData()"+person);
        boolean result = false;
        ParsonDAOImp rs = new ParsonDAOImp();
        int data = rs.addPerson(person);
        if (data != 0) {
            person.setId(data);
            // System.out.println("com.istl.service.ParsonService.addPersonData()"+person.getId());
            biometric.setPersonId(person);
            result = rs.addBiometric(biometric);
        }
        return result;
    }

    public boolean personUpdate(EnrollPerson person, EnrollPersonBiometric biometric) {
        return this.person.personUpdate(person, biometric);
    }
    public boolean onlyPersonUpdate(EnrollPerson person) {
        return this.person.personUpdate(person);
    }

    public ObservableList<EnrollNationalityLookup> getAllNationality() {
        NationalityDAOImp nationality = new NationalityDAOImp();
        return nationality.findAllNationality();
    }

    public ObservableList<EnrollDivision> getAllDivision() {
        DivisionDAOImp division = new DivisionDAOImp();
        return division.findAllDivision();
    }

    public ObservableList<EnrollDistrict> getAllDistrict() {
        DistrictDAOImp district = new DistrictDAOImp();
        return district.findAllDistrict();
    }

    public ObservableList<EnrollDistrict> getDistrictByID(EnrollDivision id) {
        DistrictDAOImp district = new DistrictDAOImp();
        return district.getAllDistrictByID(id);
    }

    public ObservableList<EnrollUpazila> getUpazilaByID(EnrollDistrict id) {
        UpazilaDAOImp upazila = new UpazilaDAOImp();
        return upazila.getAllUpazilaByID(id);
    }

    public ObservableList<EnrollUnion> getUnionByID(EnrollUpazila id) {
        System.out.println(">>>>>");
        UnionDAOImp union = new UnionDAOImp();
        if (id != null) {
            return union.getAllUnionByID(id);
        }
        return null;
    }

    public ObservableList<EnrollPerson> findTopNPerson(Integer value) {
        return this.person.findTopPerson(value);
    }

    public ObservableList<EnrollPerson> searchByName(String value) {
        return this.person.personSearchByName(value);
    }

    public ObservableList<EnrollPerson> personAdvancedSearch(EnrollPerson parson) {
        return this.person.personAdvancedSearch(parson);
    }

    public ObservableList<EnrollPerson> getAllParson() {
        return person.findAllPerson();
    }

    public EnrollLookup getLookUP(Short id) {
        LookupDAOImp lookup = new LookupDAOImp();
        return lookup.getLookup(id);
    }

    public ObservableList<EnrollPerson> getPersonByStatus(Integer id) {
        return person.getPersonByStatus(id);
    }
    public EnrollPerson getPersonById(Integer id)
    {
        return person.getPersonById(id);
    }
    public boolean nidMatching(String str,EnrollPerson p){
        return person.nidMatching(str,p);
    }
    public boolean mobileMatching(String str,EnrollPerson p){
        return person.mobileMatching(str,p);
    }
}
