package com.sansys.kudlimathspringbootreact.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MerchDetails {
	private String merchId;
	private String userId;
	private String password;
	private String merchTxnId;
	private String merchType;
	private String mccCode;
	private String merchTxnDate;

}
