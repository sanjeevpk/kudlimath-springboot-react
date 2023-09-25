package com.sansys.kudlimathspringbootreact.pojos;

import com.sansys.kudlimathspringbootreact.models.BillingInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustDetails {
	
    private String custFirstName;
	private String custEmail;
	private String custMobile;
	private BillingInfo billingInfo;
			
}
