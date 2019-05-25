/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.utility;

/**
 *
 * @author Raju
 */
public enum AccessControlErrorCode {
    GENERAL_ERROR,
    REQUIRED_PARAMETER_MISSING,
    REQUIRED_PARAMETER_INVALID,
    DUPLICATE_ENTRY,
    AUTHENTICATION_ERROR,
    SERVICE_ERROR,
    UNSPECIFIED_ERROR,
    SERVICE_NOT_IMPLEMENTED,
    DATA_NOT_FOUND;

    public static AccessControlErrorCode getValue(int index) {
        try {
            return values()[index];
        } catch (Exception ex) {
            return GENERAL_ERROR;
        }
    }
}
