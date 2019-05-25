/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "enroll_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnrollPerson.findAll", query = "SELECT e FROM EnrollPerson e")
    , @NamedQuery(name = "EnrollPerson.findById", query = "SELECT e FROM EnrollPerson e WHERE e.id = :id")
    , @NamedQuery(name = "EnrollPerson.findByReferenceNo", query = "SELECT e FROM EnrollPerson e WHERE e.referenceNo = :referenceNo")
    , @NamedQuery(name = "EnrollPerson.findByFirstNameEn", query = "SELECT e FROM EnrollPerson e WHERE e.firstNameEn = :firstNameEn")
    , @NamedQuery(name = "EnrollPerson.findByMiddleNameEn", query = "SELECT e FROM EnrollPerson e WHERE e.middleNameEn = :middleNameEn")
    , @NamedQuery(name = "EnrollPerson.findByLastNameEn", query = "SELECT e FROM EnrollPerson e WHERE e.lastNameEn = :lastNameEn")
    , @NamedQuery(name = "EnrollPerson.findByFirstNameLocal", query = "SELECT e FROM EnrollPerson e WHERE e.firstNameLocal = :firstNameLocal")
    , @NamedQuery(name = "EnrollPerson.findByMiddleNameLocal", query = "SELECT e FROM EnrollPerson e WHERE e.middleNameLocal = :middleNameLocal")
    , @NamedQuery(name = "EnrollPerson.findByLastNameLocal", query = "SELECT e FROM EnrollPerson e WHERE e.lastNameLocal = :lastNameLocal")
    , @NamedQuery(name = "EnrollPerson.findByDateOfBirth", query = "SELECT e FROM EnrollPerson e WHERE e.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "EnrollPerson.findByNationalId", query = "SELECT e FROM EnrollPerson e WHERE e.nationalId = :nationalId")
    , @NamedQuery(name = "EnrollPerson.findByMotherName", query = "SELECT e FROM EnrollPerson e WHERE e.motherName = :motherName")
    , @NamedQuery(name = "EnrollPerson.findByFatherName", query = "SELECT e FROM EnrollPerson e WHERE e.fatherName = :fatherName")
    , @NamedQuery(name = "EnrollPerson.findBySpouseName", query = "SELECT e FROM EnrollPerson e WHERE e.spouseName = :spouseName")
    , @NamedQuery(name = "EnrollPerson.findByMaritalStatus", query = "SELECT e FROM EnrollPerson e WHERE e.maritalStatus = :maritalStatus")
    , @NamedQuery(name = "EnrollPerson.findByOccupation", query = "SELECT e FROM EnrollPerson e WHERE e.occupation = :occupation")
    , @NamedQuery(name = "EnrollPerson.findByEmail", query = "SELECT e FROM EnrollPerson e WHERE e.email = :email")
    , @NamedQuery(name = "EnrollPerson.findByMobileNumber", query = "SELECT e FROM EnrollPerson e WHERE e.mobileNumber = :mobileNumber")
    , @NamedQuery(name = "EnrollPerson.findByPermanentPostCode", query = "SELECT e FROM EnrollPerson e WHERE e.permanentPostCode = :permanentPostCode")
    , @NamedQuery(name = "EnrollPerson.findByVersion", query = "SELECT e FROM EnrollPerson e WHERE e.version = :version")
    , @NamedQuery(name = "EnrollPerson.findByStatus", query = "SELECT e FROM EnrollPerson e WHERE e.status = :status")
    , @NamedQuery(name = "EnrollPerson.findByCreatedBy", query = "SELECT e FROM EnrollPerson e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EnrollPerson.findByCreationDate", query = "SELECT e FROM EnrollPerson e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "EnrollPerson.findByLastUpdatedBy", query = "SELECT e FROM EnrollPerson e WHERE e.lastUpdatedBy = :lastUpdatedBy")
    , @NamedQuery(name = "EnrollPerson.findByLastUpdateDate", query = "SELECT e FROM EnrollPerson e WHERE e.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "EnrollPerson.findByBloodGroup", query = "SELECT e FROM EnrollPerson e WHERE e.bloodGroup = :bloodGroup")})
public class EnrollPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "reference_no")
    private String referenceNo;
    @Basic(optional = false)
    @Column(name = "first_name_en")
    private String firstNameEn;
    @Column(name = "middle_name_en")
    private String middleNameEn;
    @Basic(optional = false)
    @Column(name = "last_name_en")
    private String lastNameEn;
    @Column(name = "first_name_local")
    private String firstNameLocal;
    @Column(name = "middle_name_local")
    private String middleNameLocal;
    @Column(name = "last_name_local")
    private String lastNameLocal;
    @Basic(optional = false)
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "spouse_name")
    private String spouseName;
    @Column(name = "marital_status")
    private Short maritalStatus;
    @Column(name = "occupation")
    private Short occupation;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "permanent_post_code")
    private Short permanentPostCode;
    @Column(name = "present_post_code")
    private Short presentPostCode;
    @Basic(optional = false)
    @Lob
    @Column(name = "permanent_address")
    private String permanentAddress;
    @Lob
    @Column(name = "present_address")
    private String presentAddress;
    @Basic(optional = false)
    @Column(name = "version")
    private int version;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "created_by")
    private int createdBy;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "last_updated_by")
    private int lastUpdatedBy;
    @Basic(optional = false)
    @Column(name = "last_update_date")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @Column(name = "blood_group")
    private String bloodGroup;
    @Basic(optional = false)
    @Lob
    @Column(name = "remarks")
    private String remarks;
    @JoinColumn(name = "present_station_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollStation presentStationId;
    @JoinColumn(name = "permanent_station_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollStation permanentStationId;
    @JoinColumn(name = "place_of_birth", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollDistrict placeOfBirth;
    @JoinColumn(name = "permanent_division_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollDivision permanentDivisionId;
    @JoinColumn(name = "present_division_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollDivision presentDivisionId;
    @JoinColumn(name = "permanent_union_id", referencedColumnName = "id")
    @ManyToOne
    private EnrollUnion permanentUnionId;
    @JoinColumn(name = "categories_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollUtils categoriesId;
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollUtils organizationId;
    @JoinColumn(name = "permanent_upazila_id", referencedColumnName = "id")
    @ManyToOne
    private EnrollUpazila permanentUpazilaId;
    @Column(name = "designation")
    private String designation;
    @JoinColumn(name = "permanent_district_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollDistrict permanentDistrictId;
    @JoinColumn(name = "present_district_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollDistrict presentDistrictId;
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollNationalityLookup nationalityId;
    @JoinColumn(name = "gender", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollLookup gender;
    @JoinColumn(name = "application_status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollLookup applicationStatus;
    @JoinColumn(name = "afis_match_status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollLookup afisMatchStatus;
    @JoinColumn(name = "text_match_status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EnrollLookup textMatchStatus;
    @Column(name = "is_completed")
    private Integer isCompleted;

    public EnrollPerson() {
    }

    public EnrollPerson(Integer id) {
        this.id = id;
    }

    public EnrollPerson(Integer id, String referenceNo, String firstNameEn, String middleNameEn, String lastNameEn, String firstNameLocal, String middleNameLocal, String lastNameLocal, Date dateOfBirth, String nationalId, String motherName, String fatherName, String spouseName, Short maritalStatus, Short occupation, String email, String mobileNumber, Short permanentPostCode, Short presentPostCode, String permanentAddress, String presentAddress, int version, String status, int createdBy, Date creationDate, int lastUpdatedBy, Date lastUpdateDate, String bloodGroup, String remarks, EnrollStation presentStationId, EnrollStation permanentStationId, EnrollDistrict placeOfBirth, EnrollDivision permanentDivisionId, EnrollDivision presentDivisionId, EnrollUnion permanentUnionId, EnrollUtils categoriesId, EnrollUtils organizationId, EnrollUpazila permanentUpazilaId, String designation, EnrollDistrict permanentDistrictId, EnrollDistrict presentDistrictId, EnrollNationalityLookup nationalityId, EnrollLookup gender, EnrollLookup applicationStatus, EnrollLookup afisMatchStatus, EnrollLookup textMatchStatus, Integer isCompleted) {
        this.id = id;
        this.referenceNo = referenceNo;
        this.firstNameEn = firstNameEn;
        this.middleNameEn = middleNameEn;
        this.lastNameEn = lastNameEn;
        this.firstNameLocal = firstNameLocal;
        this.middleNameLocal = middleNameLocal;
        this.lastNameLocal = lastNameLocal;
        this.dateOfBirth = dateOfBirth;
        this.nationalId = nationalId;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.spouseName = spouseName;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.permanentPostCode = permanentPostCode;
        this.presentPostCode = presentPostCode;
        this.permanentAddress = permanentAddress;
        this.presentAddress = presentAddress;
        this.version = version;
        this.status = status;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdateDate = lastUpdateDate;
        this.bloodGroup = bloodGroup;
        this.remarks = remarks;
        this.presentStationId = presentStationId;
        this.permanentStationId = permanentStationId;
        this.placeOfBirth = placeOfBirth;
        this.permanentDivisionId = permanentDivisionId;
        this.presentDivisionId = presentDivisionId;
        this.permanentUnionId = permanentUnionId;
        this.categoriesId = categoriesId;
        this.organizationId = organizationId;
        this.permanentUpazilaId = permanentUpazilaId;
        this.designation = designation;
        this.permanentDistrictId = permanentDistrictId;
        this.presentDistrictId = presentDistrictId;
        this.nationalityId = nationalityId;
        this.gender = gender;
        this.applicationStatus = applicationStatus;
        this.afisMatchStatus = afisMatchStatus;
        this.textMatchStatus = textMatchStatus;
        this.isCompleted = isCompleted;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

  

    public Short getPresentPostCode() {
        return presentPostCode;
    }

    public void setPresentPostCode(Short presentPostCode) {
        this.presentPostCode = presentPostCode;
    }

    public EnrollStation getPresentStationId() {
        return presentStationId;
    }

    public void setPresentStationId(EnrollStation presentStationId) {
        this.presentStationId = presentStationId;
    }

    public EnrollStation getPermanentStationId() {
        return permanentStationId;
    }

    public void setPermanentStationId(EnrollStation permanentStationId) {
        this.permanentStationId = permanentStationId;
    }

    public EnrollDivision getPresentDivisionId() {
        return presentDivisionId;
    }

    public void setPresentDivisionId(EnrollDivision presentDivisionId) {
        this.presentDivisionId = presentDivisionId;
    }

    public EnrollDistrict getPresentDistrictId() {
        return presentDistrictId;
    }

    public void setPresentDistrictId(EnrollDistrict presentDistrictId) {
        this.presentDistrictId = presentDistrictId;
    }
    

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getMiddleNameEn() {
        return middleNameEn;
    }

    public void setMiddleNameEn(String middleNameEn) {
        this.middleNameEn = middleNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getFirstNameLocal() {
        return firstNameLocal;
    }

    public void setFirstNameLocal(String firstNameLocal) {
        this.firstNameLocal = firstNameLocal;
    }

    public String getMiddleNameLocal() {
        return middleNameLocal;
    }

    public void setMiddleNameLocal(String middleNameLocal) {
        this.middleNameLocal = middleNameLocal;
    }

    public String getLastNameLocal() {
        return lastNameLocal;
    }

    public void setLastNameLocal(String lastNameLocal) {
        this.lastNameLocal = lastNameLocal;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Short getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Short maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Short getOccupation() {
        return occupation;
    }

    public void setOccupation(Short occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Short getPermanentPostCode() {
        return permanentPostCode;
    }

    public void setPermanentPostCode(Short permanentPostCode) {
        this.permanentPostCode = permanentPostCode;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public EnrollStation getEnrollStationId() {
        return permanentStationId;
    }

    public void setEnrollStationId(EnrollStation enrollStationId) {
        this.permanentStationId = enrollStationId;
    }

    public EnrollDistrict getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(EnrollDistrict placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public EnrollDivision getPermanentDivisionId() {
        return permanentDivisionId;
    }

    public void setPermanentDivisionId(EnrollDivision permanentDivisionId) {
        this.permanentDivisionId = permanentDivisionId;
    }

    public EnrollUnion getPermanentUnionId() {
        return permanentUnionId;
    }

    public void setPermanentUnionId(EnrollUnion permanentUnionId) {
        this.permanentUnionId = permanentUnionId;
    }

    public EnrollUtils getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(EnrollUtils categoriesId) {
        this.categoriesId = categoriesId;
    }

    public EnrollUtils getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(EnrollUtils organizationId) {
        this.organizationId = organizationId;
    }

    public EnrollUpazila getPermanentUpazilaId() {
        return permanentUpazilaId;
    }

    public void setPermanentUpazilaId(EnrollUpazila permanentUpazilaId) {
        this.permanentUpazilaId = permanentUpazilaId;
    }


    public EnrollDistrict getPermanentDistrictId() {
        return permanentDistrictId;
    }

    public void setPermanentDistrictId(EnrollDistrict permanentDistrictId) {
        this.permanentDistrictId = permanentDistrictId;
    }

    public EnrollNationalityLookup getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(EnrollNationalityLookup nationalityId) {
        this.nationalityId = nationalityId;
    }

    public EnrollLookup getGender() {
        return gender;
    }

    public void setGender(EnrollLookup gender) {
        this.gender = gender;
    }

    public EnrollLookup getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(EnrollLookup applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public EnrollLookup getAfisMatchStatus() {
        return afisMatchStatus;
    }

    public void setAfisMatchStatus(EnrollLookup afisMatchStatus) {
        this.afisMatchStatus = afisMatchStatus;
    }

    public EnrollLookup getTextMatchStatus() {
        return textMatchStatus;
    }

    public void setTextMatchStatus(EnrollLookup textMatchStatus) {
        this.textMatchStatus = textMatchStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnrollPerson)) {
            return false;
        }
        EnrollPerson other = (EnrollPerson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EnrollPerson{" + "id=" + id + ", referenceNo=" + referenceNo + ", firstNameEn=" + firstNameEn + ", middleNameEn=" + middleNameEn + ", lastNameEn=" + lastNameEn + ", firstNameLocal=" + firstNameLocal + ", middleNameLocal=" + middleNameLocal + ", lastNameLocal=" + lastNameLocal + ", dateOfBirth=" + dateOfBirth + ", nationalId=" + nationalId + ", motherName=" + motherName + ", fatherName=" + fatherName + ", spouseName=" + spouseName + ", maritalStatus=" + maritalStatus + ", occupation=" + occupation + ", email=" + email + ", mobileNumber=" + mobileNumber + ", permanentPostCode=" + permanentPostCode + ", presentPostCode=" + presentPostCode + ", permanentAddress=" + permanentAddress + ", presentAddress=" + presentAddress + ", version=" + version + ", status=" + status + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", bloodGroup=" + bloodGroup + ", remarks=" + remarks + ", presentStationId=" + presentStationId + ", permanentStationId=" + permanentStationId + ", placeOfBirth=" + placeOfBirth + ", permanentDivisionId=" + permanentDivisionId + ", presentDivisionId=" + presentDivisionId + ", permanentUnionId=" + permanentUnionId + ", categoriesId=" + categoriesId + ", organizationId=" + organizationId + ", permanentUpazilaId=" + permanentUpazilaId + ", designation=" + designation + ", permanentDistrictId=" + permanentDistrictId + ", presentDistrictId=" + presentDistrictId + ", nationalityId=" + nationalityId + ", gender=" + gender + ", applicationStatus=" + applicationStatus + ", afisMatchStatus=" + afisMatchStatus + ", textMatchStatus=" + textMatchStatus + ", isCompleted=" + isCompleted + '}';
    }

   
   
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.istl.enroll_kit.model;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.xml.bind.annotation.XmlRootElement;
//
///**
// *
// * @author User
// */
//@Entity
//@Table(name = "enroll_person")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "EnrollPerson.findAll", query = "SELECT e FROM EnrollPerson e")
//    , @NamedQuery(name = "EnrollPerson.findById", query = "SELECT e FROM EnrollPerson e WHERE e.id = :id")
//    , @NamedQuery(name = "EnrollPerson.findByReferenceNo", query = "SELECT e FROM EnrollPerson e WHERE e.referenceNo = :referenceNo")
//    , @NamedQuery(name = "EnrollPerson.findByFirstNameEn", query = "SELECT e FROM EnrollPerson e WHERE e.firstNameEn = :firstNameEn")
//    , @NamedQuery(name = "EnrollPerson.findByMiddleNameEn", query = "SELECT e FROM EnrollPerson e WHERE e.middleNameEn = :middleNameEn")
//    , @NamedQuery(name = "EnrollPerson.findByLastNameEn", query = "SELECT e FROM EnrollPerson e WHERE e.lastNameEn = :lastNameEn")
//    , @NamedQuery(name = "EnrollPerson.findByFirstNameLocal", query = "SELECT e FROM EnrollPerson e WHERE e.firstNameLocal = :firstNameLocal")
//    , @NamedQuery(name = "EnrollPerson.findByMiddleNameLocal", query = "SELECT e FROM EnrollPerson e WHERE e.middleNameLocal = :middleNameLocal")
//    , @NamedQuery(name = "EnrollPerson.findByLastNameLocal", query = "SELECT e FROM EnrollPerson e WHERE e.lastNameLocal = :lastNameLocal")
//    , @NamedQuery(name = "EnrollPerson.findByDateOfBirth", query = "SELECT e FROM EnrollPerson e WHERE e.dateOfBirth = :dateOfBirth")
//    , @NamedQuery(name = "EnrollPerson.findByNationalId", query = "SELECT e FROM EnrollPerson e WHERE e.nationalId = :nationalId")
//    , @NamedQuery(name = "EnrollPerson.findByMotherName", query = "SELECT e FROM EnrollPerson e WHERE e.motherName = :motherName")
//    , @NamedQuery(name = "EnrollPerson.findByFatherName", query = "SELECT e FROM EnrollPerson e WHERE e.fatherName = :fatherName")
//    , @NamedQuery(name = "EnrollPerson.findBySpouseName", query = "SELECT e FROM EnrollPerson e WHERE e.spouseName = :spouseName")
//    , @NamedQuery(name = "EnrollPerson.findByMaritalStatus", query = "SELECT e FROM EnrollPerson e WHERE e.maritalStatus = :maritalStatus")
//    , @NamedQuery(name = "EnrollPerson.findByOccupation", query = "SELECT e FROM EnrollPerson e WHERE e.occupation = :occupation")
//    , @NamedQuery(name = "EnrollPerson.findByEmail", query = "SELECT e FROM EnrollPerson e WHERE e.email = :email")
//    , @NamedQuery(name = "EnrollPerson.findByMobileNumber", query = "SELECT e FROM EnrollPerson e WHERE e.mobileNumber = :mobileNumber")
//    , @NamedQuery(name = "EnrollPerson.findByPermanentPostCode", query = "SELECT e FROM EnrollPerson e WHERE e.permanentPostCode = :permanentPostCode")
//    , @NamedQuery(name = "EnrollPerson.findByVersion", query = "SELECT e FROM EnrollPerson e WHERE e.version = :version")
//    , @NamedQuery(name = "EnrollPerson.findByStatus", query = "SELECT e FROM EnrollPerson e WHERE e.status = :status")
//    , @NamedQuery(name = "EnrollPerson.findByCreatedBy", query = "SELECT e FROM EnrollPerson e WHERE e.createdBy = :createdBy")
//    , @NamedQuery(name = "EnrollPerson.findByCreationDate", query = "SELECT e FROM EnrollPerson e WHERE e.creationDate = :creationDate")
//    , @NamedQuery(name = "EnrollPerson.findByLastUpdatedBy", query = "SELECT e FROM EnrollPerson e WHERE e.lastUpdatedBy = :lastUpdatedBy")
//    , @NamedQuery(name = "EnrollPerson.findByLastUpdateDate", query = "SELECT e FROM EnrollPerson e WHERE e.lastUpdateDate = :lastUpdateDate")
//    , @NamedQuery(name = "EnrollPerson.findByBloodGroup", query = "SELECT e FROM EnrollPerson e WHERE e.bloodGroup = :bloodGroup")
//    , @NamedQuery(name = "EnrollPerson.findByIsCompleted", query = "SELECT e FROM EnrollPerson e WHERE e.isCompleted = :isCompleted")})
//public class EnrollPerson implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//    @Basic(optional = false)
//    @Column(name = "reference_no")
//    private String referenceNo;
//    @Basic(optional = false)
//    @Column(name = "first_name_en")
//    private String firstNameEn;
//    @Column(name = "middle_name_en")
//    private String middleNameEn;
//    @Column(name = "last_name_en")
//    private String lastNameEn;
//    @Column(name = "first_name_local")
//    private String firstNameLocal;
//    @Column(name = "middle_name_local")
//    private String middleNameLocal;
//    @Column(name = "last_name_local")
//    private String lastNameLocal;
//    @Basic(optional = false)
//    @Column(name = "date_of_birth")
//    @Temporal(TemporalType.DATE)
//    private Date dateOfBirth;
//    @Column(name = "national_id")
//    private String nationalId;
//    @Column(name = "mother_name")
//    private String motherName;
//    @Column(name = "father_name")
//    private String fatherName;
//    @Column(name = "spouse_name")
//    private String spouseName;
//    @Column(name = "marital_status")
//    private Short maritalStatus;
//    @Column(name = "occupation")
//    private Short occupation;
//    @Column(name = "email")
//    private String email;
//    @Basic(optional = false)
//    @Column(name = "mobile_number")
//    private String mobileNumber;
//    @Column(name = "permanent_post_code")
//    private Short permanentPostCode;
//    @Lob
//    @Column(name = "permanent_address")
//    private String permanentAddress;
//    @Lob
//    @Column(name = "present_address")
//    private String presentAddress;
//    @Basic(optional = false)
//    @Column(name = "version")
//    private int version;
//    @Basic(optional = false)
//    @Column(name = "status")
//    private String status;
//    @Basic(optional = false)
//    @Column(name = "created_by")
//    private int createdBy;
//    @Basic(optional = false)
//    @Column(name = "creation_date")
//    @Temporal(TemporalType.DATE)
//    private Date creationDate;
//    @Basic(optional = false)
//    @Column(name = "last_updated_by")
//    private int lastUpdatedBy;
//    @Basic(optional = false)
//    @Column(name = "last_update_date")
//    @Temporal(TemporalType.DATE)
//    private Date lastUpdateDate;
//    @Basic(optional = false)
//    @Column(name = "blood_group")
//    private String bloodGroup;
//    @Basic(optional = false)
//    @Lob
//    @Column(name = "remarks")
//    private String remarks;
//    @Basic(optional = false)
//    @Column(name = "is_completed")
//    private int isCompleted;
//
//    public EnrollPerson() {
//    }
//
//    public EnrollPerson(Integer id) {
//        this.id = id;
//    }
//
//    public EnrollPerson(Integer id, String referenceNo, String firstNameEn, Date dateOfBirth, String mobileNumber, int version, String status, int createdBy, Date creationDate, int lastUpdatedBy, Date lastUpdateDate, String bloodGroup, String remarks, int isCompleted) {
//        this.id = id;
//        this.referenceNo = referenceNo;
//        this.firstNameEn = firstNameEn;
//        this.dateOfBirth = dateOfBirth;
//        this.mobileNumber = mobileNumber;
//        this.version = version;
//        this.status = status;
//        this.createdBy = createdBy;
//        this.creationDate = creationDate;
//        this.lastUpdatedBy = lastUpdatedBy;
//        this.lastUpdateDate = lastUpdateDate;
//        this.bloodGroup = bloodGroup;
//        this.remarks = remarks;
//        this.isCompleted = isCompleted;
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
//    public String getReferenceNo() {
//        return referenceNo;
//    }
//
//    public void setReferenceNo(String referenceNo) {
//        this.referenceNo = referenceNo;
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
//    public String getMiddleNameEn() {
//        return middleNameEn;
//    }
//
//    public void setMiddleNameEn(String middleNameEn) {
//        this.middleNameEn = middleNameEn;
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
//    public String getFirstNameLocal() {
//        return firstNameLocal;
//    }
//
//    public void setFirstNameLocal(String firstNameLocal) {
//        this.firstNameLocal = firstNameLocal;
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
//    public String getLastNameLocal() {
//        return lastNameLocal;
//    }
//
//    public void setLastNameLocal(String lastNameLocal) {
//        this.lastNameLocal = lastNameLocal;
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
//    public String getNationalId() {
//        return nationalId;
//    }
//
//    public void setNationalId(String nationalId) {
//        this.nationalId = nationalId;
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
//    public String getFatherName() {
//        return fatherName;
//    }
//
//    public void setFatherName(String fatherName) {
//        this.fatherName = fatherName;
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
//    public Short getMaritalStatus() {
//        return maritalStatus;
//    }
//
//    public void setMaritalStatus(Short maritalStatus) {
//        this.maritalStatus = maritalStatus;
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
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
//    public Short getPermanentPostCode() {
//        return permanentPostCode;
//    }
//
//    public void setPermanentPostCode(Short permanentPostCode) {
//        this.permanentPostCode = permanentPostCode;
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
//    public String getPresentAddress() {
//        return presentAddress;
//    }
//
//    public void setPresentAddress(String presentAddress) {
//        this.presentAddress = presentAddress;
//    }
//
//    public int getVersion() {
//        return version;
//    }
//
//    public void setVersion(int version) {
//        this.version = version;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public int getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(int createdBy) {
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
//    public int getLastUpdatedBy() {
//        return lastUpdatedBy;
//    }
//
//    public void setLastUpdatedBy(int lastUpdatedBy) {
//        this.lastUpdatedBy = lastUpdatedBy;
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
//    public String getBloodGroup() {
//        return bloodGroup;
//    }
//
//    public void setBloodGroup(String bloodGroup) {
//        this.bloodGroup = bloodGroup;
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
//    public int getIsCompleted() {
//        return isCompleted;
//    }
//
//    public void setIsCompleted(int isCompleted) {
//        this.isCompleted = isCompleted;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof EnrollPerson)) {
//            return false;
//        }
//        EnrollPerson other = (EnrollPerson) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "javafxapplication9.EnrollPerson[ id=" + id + " ]";
//    }
//    
//}
