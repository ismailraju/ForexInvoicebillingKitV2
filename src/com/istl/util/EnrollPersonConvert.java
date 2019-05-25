/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import com.istl.accesscontrol.model.EnrollDistrict;
import com.istl.accesscontrol.model.EnrollDivision;
import com.istl.accesscontrol.model.EnrollLookup;
import com.istl.accesscontrol.model.EnrollNationalityLookup;
import com.istl.accesscontrol.model.EnrollStation;
import com.istl.accesscontrol.model.EnrollUnion;
import com.istl.accesscontrol.model.EnrollUpazila;
import com.istl.accesscontrol.model.Person;
import com.istl.accesscontrol.model.PersonBiometric;
import com.istl.enroll_kit.model.EnrollPerson;
import com.istl.enroll_kit.model.EnrollUtils;

/**
 *
 * @author Arun
 */
public class EnrollPersonConvert {

    public static Person EnrollPersonToPerson(EnrollPerson ePerson, PersonBiometric biometric) {
        Person person = new Person();

        EnrollDivision permanentDivision = new EnrollDivision();
        permanentDivision.setId(ePerson.getPermanentDivisionId().getId());
        person.setPermanentDivisionId(permanentDivision);

        EnrollDistrict permanentDistrict = new EnrollDistrict();
        permanentDistrict.setId(ePerson.getPermanentDistrictId().getId());
        person.setPermanentDistrictId(permanentDistrict);

        com.istl.accesscontrol.model.EnrollStation permanentSation = new EnrollStation();
        permanentSation.setId(ePerson.getPermanentStationId().getId());
        person.setPermanentStationId(permanentSation);

        person.setPermanentPostCode(ePerson.getPermanentPostCode());

        person.setPermanentAddress(ePerson.getPermanentAddress());

        EnrollDivision presentDivision = new EnrollDivision();
        presentDivision.setId(ePerson.getPresentDivisionId().getId());
        person.setPresentDivisionId(presentDivision);

        EnrollDistrict presentDistrict = new EnrollDistrict();
        presentDistrict.setId(ePerson.getPresentDistrictId().getId());
        person.setPresentDistrictId(presentDistrict);

        com.istl.accesscontrol.model.EnrollStation presentSation = new EnrollStation();
        presentSation.setId(ePerson.getPresentStationId().getId());
        person.setPresentStationId(presentSation);

        person.setPresentAddress(ePerson.getPresentAddress());

        person.setPresentPostCode(ePerson.getPresentPostCode());

        EnrollDistrict placeOfBirth = new EnrollDistrict();
        placeOfBirth.setId(ePerson.getPlaceOfBirth().getId());

        EnrollLookup afisMatchStatus = new EnrollLookup();
        afisMatchStatus.setId(ePerson.getAfisMatchStatus().getId());
        afisMatchStatus.setCode(ePerson.getGender().getCode());
        afisMatchStatus.setLookupType(ePerson.getGender().getLookupType());
        afisMatchStatus.setNameEn(ePerson.getGender().getNameEn());
        afisMatchStatus.setNameLocal(ePerson.getGender().getNameLocal());
        afisMatchStatus.setStatus(ePerson.getGender().getStatus());

        EnrollLookup textMatchStatus = new EnrollLookup();
        textMatchStatus.setId(ePerson.getTextMatchStatus().getId());
        textMatchStatus.setCode(ePerson.getGender().getCode());
        textMatchStatus.setLookupType(ePerson.getGender().getLookupType());
        textMatchStatus.setNameEn(ePerson.getGender().getNameEn());
        textMatchStatus.setNameLocal(ePerson.getGender().getNameLocal());
        textMatchStatus.setStatus(ePerson.getGender().getStatus());

        EnrollLookup applicationStatus = new EnrollLookup();

        applicationStatus.setId(ePerson.getApplicationStatus().getId());
        applicationStatus.setCode(ePerson.getGender().getCode());
        applicationStatus.setLookupType(ePerson.getGender().getLookupType());
        applicationStatus.setNameEn(ePerson.getGender().getNameEn());
        applicationStatus.setNameLocal(ePerson.getGender().getNameLocal());
        applicationStatus.setStatus(ePerson.getGender().getStatus());

        EnrollLookup gender = new EnrollLookup();
        gender.setId(ePerson.getGender().getId());
        gender.setCode(ePerson.getGender().getCode());
        gender.setLookupType(ePerson.getGender().getLookupType());
        gender.setNameEn(ePerson.getGender().getNameEn());
        gender.setNameLocal(ePerson.getGender().getNameLocal());
        gender.setStatus(ePerson.getGender().getStatus());

        EnrollNationalityLookup nationalityId = new EnrollNationalityLookup();
        nationalityId.setId(ePerson.getNationalityId().getId());
        nationalityId.setCountryIcaoCode(ePerson.getNationalityId().getCountryIcaoCode());
        nationalityId.setCountryNameEn(ePerson.getNationalityId().getCountryNameEn());
        nationalityId.setCountryNameLocal(ePerson.getNationalityId().getCountryNameLocal());
        nationalityId.setCountryNationality(ePerson.getNationalityId().getCountryNationality());
        nationalityId.setStatus(ePerson.getNationalityId().getStatus());

//        EnrollStation enrollStationId = new EnrollStation();
//        enrollStationId.setId(ePerson.getEnrollStationId().getId());
//        EnrollUnion presentUnionId = new EnrollUnion();
//        presentUnionId.setId(ePerson.getPermanentUnionId().getId());
//        EnrollUpazila presentUpazilaId= new EnrollUpazila();
//        presentUpazilaId.setId(ePerson.getPermanentUpazilaId().getId());
        person.setAfisMatchStatus(afisMatchStatus);
        person.setApplicationStatus(applicationStatus);
        person.setGender(gender);
        person.setCreatedBy(ePerson.getCreatedBy());
        person.setCreationDate(ePerson.getCreationDate());
        person.setDateOfBirth(ePerson.getDateOfBirth());
        person.setEmail(ePerson.getEmail());
        person.setFatherName(ePerson.getFatherName());
        person.setFirstNameEn(ePerson.getFirstNameEn());
        person.setFirstNameLocal(ePerson.getFirstNameLocal());
        person.setId(ePerson.getId());
        person.setLastNameEn(ePerson.getLastNameEn());
        person.setLastNameLocal(ePerson.getLastNameLocal());
        person.setLastUpdateDate(ePerson.getLastUpdateDate());
        person.setLastUpdatedBy(ePerson.getLastUpdatedBy());
        person.setMaritalStatus(new EnrollLookup(ePerson.getMaritalStatus()));
        person.setMiddleNameEn(ePerson.getMiddleNameEn());
        person.setMiddleNameLocal(ePerson.getMiddleNameLocal());
        person.setMobileNumber(ePerson.getMobileNumber());
        person.setMotherName(ePerson.getMotherName());
        person.setNationalId(ePerson.getNationalId());
        person.setNationalityId(nationalityId);
        person.setOccupation(ePerson.getOccupation());
        person.setPlaceOfBirth(placeOfBirth);

//        person.setPermanentUpazilaId(presentUpazilaId);
//        person.setPermanentUnionId(presentUnionId);
        person.setReferenceNo(ePerson.getReferenceNo());
        person.setSpouseName(ePerson.getSpouseName());
        //added by iqbal

        person.setBloodGroup(ePerson.getBloodGroup());

        System.out.println("==========category=============" + ePerson.getCategoriesId().toString());

        com.istl.accesscontrol.model.EnrollUtils category = new com.istl.accesscontrol.model.EnrollUtils();
        category.setId(ePerson.getCategoriesId().getId());
        category.setName(ePerson.getCategoriesId().getName());
        category.setType(ePerson.getCategoriesId().getType());
        if (ePerson.getCategoriesId().getColorCode() != null && ePerson.getCategoriesId().getColorCode().length() != 0) {
            String s = "#" + ePerson.getCategoriesId().getColorCode().substring(2, 8);
            System.err.println("sss > " + s);
            category.setColorCode(s);
        }
        else
        {
            category.setColorCode("#FFFFFF");
        }

        category.setPriority(ePerson.getCategoriesId().getPriority());
        category.setRemarks(ePerson.getCategoriesId().getRemarks());
        person.setCategoriesId(category);

        com.istl.accesscontrol.model.EnrollUtils OrganizationId = new com.istl.accesscontrol.model.EnrollUtils();
        OrganizationId.setId(ePerson.getOrganizationId().getId());
        OrganizationId.setName(ePerson.getOrganizationId().getName());
        OrganizationId.setType(ePerson.getOrganizationId().getType());
        OrganizationId.setPriority(ePerson.getCategoriesId().getPriority());
        OrganizationId.setRemarks(ePerson.getCategoriesId().getRemarks());
        person.setOrganizationId(OrganizationId);

//        com.istl.accesscontrol.model.EnrollUtils DesignationId=new com.istl.accesscontrol.model.EnrollUtils();
//        DesignationId.setId(ePerson.getDesignationId().getId());
//        DesignationId.setName(ePerson.getDesignationId().getName());
//        DesignationId.setType(ePerson.getDesignationId().getType());
        person.setDesignation(ePerson.getDesignation());

//        person.setCategoriesId(ePerson.getCategoriesId());
//        person.setOrganizationId(ePerson.getOrganizationId());
//        person.setDesignationId(ePerson.getDesignationId());
        person.setRemarks(ePerson.getRemarks());
        //
        // person.setStatus(ePerson.getStatus());
        person.setTextMatchStatus(textMatchStatus);
        person.setVersion(ePerson.getVersion());
        person.setEnrollPersonBiometricCollection(biometric);

        return person;
    }

}
