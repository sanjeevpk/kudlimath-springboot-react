package com.sansys.kudlimathspringbootreact.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseParser
{
  private PayInstrument payInstrument;

  public PayInstrument getPayInstrument()
  {
    return this.payInstrument;
  }

  public void setPayInstrument(PayInstrument payInstrument) {
    this.payInstrument = payInstrument;
  }

  public String toString()
  {
    return "ClassPojo [payInstrument = " + this.payInstrument + "]";
  }
}
