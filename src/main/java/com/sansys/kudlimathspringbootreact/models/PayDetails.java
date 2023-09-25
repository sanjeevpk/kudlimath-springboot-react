package com.sansys.kudlimathspringbootreact.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayDetails {

  private String atomTxnId;
  private List<ProdDetails> prodDetails;
  private String amount;
  private String surchargeAmount;
  private String totalAmount;
  private String custAccNo;
  private String clientCode;
  private String txnCurrency;
  private String signature;
  private String txnInitDate;
  private String txnInitCompleteDate;
  
  
  
  
}
