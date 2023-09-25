package com.sansys.kudlimathspringbootreact.pojos;


import com.sansys.kudlimathspringbootreact.models.PayModeSpecificData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayInstrument {

	private HeadDetails headDetails;
	private MerchDetails merchDetails;
	private PayDetails payDetails;
	private ResponseUrls responseUrls;
	private PayModeSpecificData payModeSpecificData;
	private Extras extras;
	private CustDetails custDetails;

}
