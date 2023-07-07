<ASI_Message>
	<Riyad_Bank_Header>
		<Msg_Format>Payment_Hub_Inquiry_Int_Rply_ID</Msg_Format>
		<#if request.riyadBankHeader.msgVersion??>
		<Msg_Version>${request.riyadBankHeader.msgVersion}</Msg_Version>
		</#if>
		<#if request.riyadBankHeader.requestorChannelID??>
		<Requestor_Channel_ID>${request.riyadBankHeader.requestorChannelID}</Requestor_Channel_ID>
		</#if>
		<#if request.riyadBankHeader.requestorChannelFunction??>
		<Requestor_Channel_Function>${request.riyadBankHeader.requestorChannelFunction}</Requestor_Channel_Function>
		</#if>
		<#if request.riyadBankHeader.requestorUserID??>
		<Requestor_User_ID>${request.riyadBankHeader.requestorUserID}</Requestor_User_ID>
		</#if>
		<#if request.riyadBankHeader.requestorLanguage??>
		<Requestor_Language>${request.riyadBankHeader.requestorLanguage}</Requestor_Language>
		</#if>
		<#if request.riyadBankHeader.requestorSecurityInfo??>
		<Requestor_Security_Info>${request.riyadBankHeader.requestorSecurityInfo}</Requestor_Security_Info>
		</#if>
		<ASI_Return_Code>${reasonCode}</ASI_Return_Code>
	</Riyad_Bank_Header>
	<#if request.referenceNo??>
	<Reference_No>${request.referenceNo}</Reference_No>
	</#if>
	<#if request.remittanceDirection??>
	<Remittance_Direction>${request.remittanceDirection}</Remittance_Direction>
	</#if>
	<#if request.remittanceType??>
	<Remittance_Type>${request.remittanceType}</Remittance_Type>
	</#if>
	<#if request.startDate??>
	<Start_Date>${request.startDate}</Start_Date>
	</#if>
	<#if request.endDate??>
	<End_Date>${request.endDate}</End_Date>
	</#if>
	<#if request.fromValDt??>
	<From_Val_Dt>${request.fromValDt}</From_Val_Dt>
	</#if>
	<#if request.toValDt??>
	<To_Val_Dt>${request.toValDt}</To_Val_Dt>
	</#if>
	<#if request.fromPaymentAmount??>
	<From_Payment_Amount>${request.fromPaymentAmount}</From_Payment_Amount>
	</#if>
	<#if request.toPaymentAmount??>
	<To_Payment_Amount>${request.toPaymentAmount}</To_Payment_Amount>
	</#if>
	<#if request.paymentCurrency??>
	<Payment_Currency>${request.paymentCurrency}</Payment_Currency>
	</#if>
	<#if request.acctNumber??>
	<Acct_Number>${request.acctNumber}</Acct_Number>
	</#if>
	<#if request.bankCode??>
	<Bank_Code>${request.bankCode}</Bank_Code>
	</#if>
	<#if SARIE_MSG_UTI??>
	<SARIE_MSG_UTI>${SARIE_MSG_UTI}</SARIE_MSG_UTI>
	</#if>
	<#if request.beneficiaryName??>
	<Beneficiary_Name>${request.beneficiaryName}</Beneficiary_Name>
	</#if>
	<#if request.beneficiaryAccount??>
	<Beneficiary_Account>${request.beneficiaryAccount}</Beneficiary_Account>
	</#if>
	<#if request.paymentStatus??>
	<Payment_Status>${request.paymentStatus}</Payment_Status>
    </#if>
    <#if request.custIdentificationDetails??>
	<Cust_Identification_Details>
	    <#if ID_Type??>
		<ID_Type>${ID_Type}></ID_Type>
		</#if>
		<#if request.custIdentificationDetails.custID??>
		<Cust_ID>${request.custIdentificationDetails.custID}</Cust_ID>
		</#if>
	</Cust_Identification_Details>
	</#if>
	<#if request.toReferenceNo??>
	<To_Reference_No>${request.toReferenceNo}</To_Reference_No>
	</#if>
	<#if request.fromBranchNumber??>
	<From_Branch_Number>${request.fromBranchNumber}</From_Branch_Number>
	</#if>
	<#if request.toBranchNumber??>
	<To_Branch_Number>${request.toBranchNumber}</To_Branch_Number>
	</#if>
	<#if RB_Reference_No??>
	<RB_Reference_No>${RB_Reference_No}</RB_Reference_No>
	</#if>
	<#if request.pageSize??>
	<Page_Size>${request.pageSize}</Page_Size>
	</#if>
	<#if request.lastRecKey??>
	<Last_Rec_Key>
	    <#if request.lastRecKey.lastKeyType??>
		<Last_Key_Type>${request.lastRecKey.lastKeyType}</Last_Key_Type>
		</#if>
		<#if request.lastRecKey.lastKeyRefNumber??>
		<Last_Key_Ref_Number>${request.lastRecKey.lastKeyRefNumber}</Last_Key_Ref_Number>
		</#if>
		<#if request.lastRecKey.lastKeyWithDate??>
		<Last_Key_WithDate>${request.lastRecKey.lastKeyWithDate}</Last_Key_WithDate>
		</#if>
	</Last_Rec_Key>
	</#if>
	<#if endOfData??>
	<End_Of_Data>${endOfData}</End_Of_Data>
	</#if>
	<#if noOfRows??>
    <No_Of_Rows>${noOfRows}</No_Of_Rows>
    </#if>
    <#if request.repeatingGroup??>
	<#list repeatingGroupList as repeatingGrp>
	<Repeating_Group>
		<#if repeatingGrp.remReferenceNo??>
		<Rem_Reference_No>${repeatingGrp.remReferenceNo}</Rem_Reference_No>
		</#if>
	</Repeating_Group>
	 </#list>
    </#if>
</ASI_Message>