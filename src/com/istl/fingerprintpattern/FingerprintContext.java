/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.fingerprintpattern;


public class FingerprintContext {
    private FingerprintStrategy strategy;

   public FingerprintContext(FingerprintStrategy strategy){
      this.strategy = strategy;
   }

   public boolean executeStrategy(){
       return strategy.execute();
   }
}
