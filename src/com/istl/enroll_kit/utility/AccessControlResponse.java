/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.enroll_kit.utility;

import java.io.Serializable;

/**
 *
 * @author Raju
 */
public class AccessControlResponse implements Serializable{

    protected boolean operationResult;
    protected String errorMsg;
    protected Integer errorCode;
    private String responseMessage;

    public boolean isOperationResult() {
        return operationResult;
    }

    public void setOperationResult(boolean operationResult) {
        this.operationResult = operationResult;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "AccessControlResponse{" + "operationResult=" + operationResult + ", errorMsg=" + errorMsg + ", errorCode=" + errorCode + ", responseMessage=" + responseMessage + '}';
    }

}
