/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.service;


import com.istl.accesscontrol.model.Person;
import com.istl.accesscontrol.model.EnrollUtilsRespons;
import com.istl.enroll_kit.utility.AccessControlResponse;
import com.istl.enroll_kit.utility.AccessControlBaseRestTemplate;
import com.istl.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;


/**
 *
 * @author Raju
 */
@Service
public class AccessControlServiceManager extends AccessControlBaseRestTemplate {

    public AccessControlResponse addEnrollPersonAndBiometric(Person p) {
        AccessControlResponse tr = new AccessControlResponse();
        try {
            tr = getRestTemplate().postForObject(getUrl(), p, AccessControlResponse.class);
        } catch (RestClientException restClientException) {
            System.out.println("com.istl.enroll_kit.service.AccessControlServiceManager.addEnrollPersonAndBiometric()"+restClientException);
        }
        
        return tr;
    }
    
    public EnrollUtilsRespons getUtilsList() {
        EnrollUtilsRespons tr = new EnrollUtilsRespons();
        try {
            tr = getRestTemplate().getForObject(getContext()+"getEnrollUtilsList", EnrollUtilsRespons.class);
        } catch (RestClientException restClientException) {
            System.out.println("com.istl.enroll_kit.service.AccessControlServiceManager.addEnrollPersonAndBiometric()"+restClientException);
        }
        
        return tr;
    }

}
