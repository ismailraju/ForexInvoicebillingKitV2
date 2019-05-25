///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.istl.accesscontrol.model;
//
//
//import java.io.Serializable;
//import java.util.Date;
//
//
//public class Person implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private Integer id;
//
//    private Integer createdBy;
//
//    private Date creationDate;
//
//    private Date dateOfBirth;
//
//    private String email;
//
//    private String fatherName;
//
//    private String firstNameEn;
//
//    private String firstNameLocal;
//
//    private String lastNameEn;
//
//    private String lastNameLocal;
//
//    private Date lastUpdateDate;
//
//    private Integer lastUpdatedBy;
//
//    private Short maritalStatus;
//
//    private String middleNameEn;
//
//    private String middleNameLocal;
//
//    private String mobileNumber;
//
//    private String motherName;
//
//    private String nationalId;
//
//    private Short occupation;
//
//    private String presentAddress;
//
//    private String permanentAddress;
//
//    private Short permanentPostCode;
//
//    private String referenceNo;
//
//    private String spouseName;
//
//    private Short status;
//
//    private Integer version;
//
//    private EnrollDivision permanentDivisionId;
//
//    private EnrollDistrict permanentDistrictId;
//
//    private EnrollDistrict placeOfBirth;
//
//    private EnrollLookup afisMatchStatus;
//
//    private EnrollLookup textMatchStatus;
//
//    private EnrollLookup applicationStatus;
//
//    private EnrollLookup gender;
//
//    private EnrollNationalityLookup nationalityId;
//
//    private EnrollStation enrollStationId;
//    private EnrollUnion permanentUnionId;
//
//    private EnrollUpazila permanentUpazilaId;
//
//    private PersonBiometric enrollPersonBiometricCollection;
//
//    private com.istl.accesscontrol.model.EnrollUtils categoriesId;
//
//    private com.istl.accesscontrol.model.EnrollUtils organizationId;
//
//    private com.istl.accesscontrol.model.EnrollUtils designationId;
//
//    private String remarks;
//
//    private String bloodGroup;
//
//    public Person() {
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(Integer createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFatherName() {
//        return fatherName;
//    }
//
//    public void setFatherName(String fatherName) {
//        this.fatherName = fatherName;
//    }
//
//    public String getFirstNameEn() {
//        return firstNameEn;
//    }
//
//    public void setFirstNameEn(String firstNameEn) {
//        this.firstNameEn = firstNameEn;
//    }
//
//    public String getFirstNameLocal() {
//        return firstNameLocal;
//    }
//
//    public void setFirstNameLocal(String firstNameLocal) {
//        this.firstNameLocal = firstNameLocal;
//    }
//
//    public String getLastNameEn() {
//        return lastNameEn;
//    }
//
//    public void setLastNameEn(String lastNameEn) {
//        this.lastNameEn = lastNameEn;
//    }
//
//    public String getLastNameLocal() {
//        return lastNameLocal;
//    }
//
//    public void setLastNameLocal(String lastNameLocal) {
//        this.lastNameLocal = lastNameLocal;
//    }
//
//    public Date getLastUpdateDate() {
//        return lastUpdateDate;
//    }
//
//    public void setLastUpdateDate(Date lastUpdateDate) {
//        this.lastUpdateDate = lastUpdateDate;
//    }
//
//    public Integer getLastUpdatedBy() {
//        return lastUpdatedBy;
//    }
//
//    public void setLastUpdatedBy(Integer lastUpdatedBy) {
//        this.lastUpdatedBy = lastUpdatedBy;
//    }
//
//    public Short getMaritalStatus() {
//        return maritalStatus;
//    }
//
//    public void setMaritalStatus(Short maritalStatus) {
//        this.maritalStatus = maritalStatus;
//    }
//
//    public String getMiddleNameEn() {
//        return middleNameEn;
//    }
//
//    public void setMiddleNameEn(String middleNameEn) {
//        this.middleNameEn = middleNameEn;
//    }
//
//    public String getMiddleNameLocal() {
//        return middleNameLocal;
//    }
//
//    public void setMiddleNameLocal(String middleNameLocal) {
//        this.middleNameLocal = middleNameLocal;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public String getMotherName() {
//        return motherName;
//    }
//
//    public void setMotherName(String motherName) {
//        this.motherName = motherName;
//    }
//
//    public String getNationalId() {
//        return nationalId;
//    }
//
//    public void setNationalId(String nationalId) {
//        this.nationalId = nationalId;
//    }
//
//    public Short getOccupation() {
//        return occupation;
//    }
//
//    public void setOccupation(Short occupation) {
//        this.occupation = occupation;
//    }
//
//    public String getPresentAddress() {
//        return presentAddress;
//    }
//
//    public void setPresentAddress(String presentAddress) {
//        this.presentAddress = presentAddress;
//    }
//
//    public String getPermanentAddress() {
//        return permanentAddress;
//    }
//
//    public void setPermanentAddress(String permanentAddress) {
//        this.permanentAddress = permanentAddress;
//    }
//
//    public Short getPermanentPostCode() {
//        return permanentPostCode;
//    }
//
//    public void setPermanentPostCode(Short permanentPostCode) {
//        this.permanentPostCode = permanentPostCode;
//    }
//
//    public String getReferenceNo() {
//        return referenceNo;
//    }
//
//    public void setReferenceNo(String referenceNo) {
//        this.referenceNo = referenceNo;
//    }
//
//    public String getSpouseName() {
//        return spouseName;
//    }
//
//    public void setSpouseName(String spouseName) {
//        this.spouseName = spouseName;
//    }
//
//    public Short getStatus() {
//        return status;
//    }
//
//    public void setStatus(Short status) {
//        this.status = status;
//    }
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }
//
//    public EnrollDivision getPermanentDivisionId() {
//        return permanentDivisionId;
//    }
//
//    public void setPermanentDivisionId(EnrollDivision permanentDivisionId) {
//        this.permanentDivisionId = permanentDivisionId;
//    }
//
//    public EnrollDistrict getPermanentDistrictId() {
//        return permanentDistrictId;
//    }
//
//    public void setPermanentDistrictId(EnrollDistrict permanentDistrictId) {
//        this.permanentDistrictId = permanentDistrictId;
//    }
//
//    public EnrollDistrict getPlaceOfBirth() {
//        return placeOfBirth;
//    }
//
//    public void setPlaceOfBirth(EnrollDistrict placeOfBirth) {
//        this.placeOfBirth = placeOfBirth;
//    }
//
//    public EnrollLookup getAfisMatchStatus() {
//        return afisMatchStatus;
//    }
//
//    public void setAfisMatchStatus(EnrollLookup afisMatchStatus) {
//        this.afisMatchStatus = afisMatchStatus;
//    }
//
//    public EnrollLookup getTextMatchStatus() {
//        return textMatchStatus;
//    }
//
//    public void setTextMatchStatus(EnrollLookup textMatchStatus) {
//        this.textMatchStatus = textMatchStatus;
//    }
//
//    public EnrollLookup getApplicationStatus() {
//        return applicationStatus;
//    }
//
//    public void setApplicationStatus(EnrollLookup applicationStatus) {
//        this.applicationStatus = applicationStatus;
//    }
//
//    public EnrollLookup getGender() {
//        return gender;
//    }
//
//    public void setGender(EnrollLookup gender) {
//        this.gender = gender;
//    }
//
//    public EnrollNationalityLookup getNationalityId() {
//        return nationalityId;
//    }
//
//    public void setNationalityId(EnrollNationalityLookup nationalityId) {
//        this.nationalityId = nationalityId;
//    }
//
////    public EnrollStation getEnrollStationId() {
////        return enrollStationId;
////    }
////
////    public void setEnrollStationId(EnrollStation enrollStationId) {
////        this.enrollStationId = enrollStationId;
////    }
//    public EnrollUnion getPermanentUnionId() {
//        return permanentUnionId;
//    }
//
//    public void setPermanentUnionId(EnrollUnion permanentUnionId) {
//        this.permanentUnionId = permanentUnionId;
//    }
//
//    public EnrollUpazila getPermanentUpazilaId() {
//        return permanentUpazilaId;
//    }
//
//    public void setPermanentUpazilaId(EnrollUpazila permanentUpazilaId) {
//        this.permanentUpazilaId = permanentUpazilaId;
//    }
//
//    public PersonBiometric getEnrollPersonBiometricCollection() {
//        return enrollPersonBiometricCollection;
//    }
//
//    public void setEnrollPersonBiometricCollection(PersonBiometric enrollPersonBiometricCollection) {
//        this.enrollPersonBiometricCollection = enrollPersonBiometricCollection;
//    }
//
//    public EnrollStation getEnrollStationId() {
//        return enrollStationId;
//    }
//
//    public void setEnrollStationId(EnrollStation enrollStationId) {
//        this.enrollStationId = enrollStationId;
//    }
//
//    public com.istl.accesscontrol.model.EnrollUtils getCategoriesId() {
//        return categoriesId;
//    }
//
//    public void setCategoriesId(com.istl.accesscontrol.model.EnrollUtils categoriesId) {
//        this.categoriesId = categoriesId;
//    }
//
//    public com.istl.accesscontrol.model.EnrollUtils getOrganizationId() {
//        return organizationId;
//    }
//
//    public void setOrganizationId(com.istl.accesscontrol.model.EnrollUtils organizationId) {
//        this.organizationId = organizationId;
//    }
//
//    public com.istl.accesscontrol.model.EnrollUtils getDesignationId() {
//        return designationId;
//    }
//
//    public void setDesignationId(com.istl.accesscontrol.model.EnrollUtils designationId) {
//        this.designationId = designationId;
//    }
//
//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
//
//    public String getBloodGroup() {
//        return bloodGroup;
//    }
//
//    public void setBloodGroup(String bloodGroup) {
//        this.bloodGroup = bloodGroup;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" + "id=" + id + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", fatherName=" + fatherName + ", firstNameEn=" + firstNameEn + ", firstNameLocal=" + firstNameLocal + ", lastNameEn=" + lastNameEn + ", lastNameLocal=" + lastNameLocal + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdatedBy=" + lastUpdatedBy + ", maritalStatus=" + maritalStatus + ", middleNameEn=" + middleNameEn + ", middleNameLocal=" + middleNameLocal + ", mobileNumber=" + mobileNumber + ", motherName=" + motherName + ", nationalId=" + nationalId + ", occupation=" + occupation + ", presentAddress=" + presentAddress + ", permanentAddress=" + permanentAddress + ", permanentPostCode=" + permanentPostCode + ", referenceNo=" + referenceNo + ", spouseName=" + spouseName + ", status=" + status + ", version=" + version + ", permanentDivisionId=" + permanentDivisionId + ", permanentDistrictId=" + permanentDistrictId + ", placeOfBirth=" + placeOfBirth + ", afisMatchStatus=" + afisMatchStatus + ", textMatchStatus=" + textMatchStatus + ", applicationStatus=" + applicationStatus + ", gender=" + gender + ", nationalityId=" + nationalityId + ", enrollStationId=" + enrollStationId + ", permanentUnionId=" + permanentUnionId + ", permanentUpazilaId=" + permanentUpazilaId + ", enrollPersonBiometricCollection=" + enrollPersonBiometricCollection + ", categoriesId=" + categoriesId + ", organizationId=" + organizationId + ", designationId=" + designationId + ", remarks=" + remarks + ", bloodGroup=" + bloodGroup + '}';
//    }
//
//}
