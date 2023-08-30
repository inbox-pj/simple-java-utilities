package com.clover.labs.phonetic;

public interface ADIBValidationCnsts {

    String PARAM_BENE_NAME_CHECK = "BeneficiaryNameCheck";

    // Parameter related to Sanatization
    String PARAM_CSL_ATTR_NAME = "CharacterSanatizationList";

    String PARAM_CSL_KEY_NAME = "Type";

    String PARAM_CSL_KEY_VAL = "Value";

    String PARAM_CHA_VAL = "Character";

    String PARAM_SAL_VAL = "Salutation";

    // Parameter related to threshold amount
    String PARAM_CAR_ATTR_NAME = "BeneNameCheckApplicabilityRule";

    String PARAM_CAR_MSG_TYPE_KEY_NAME = "MessageName";

    String PARAM_CAR_CURR_KEY_NAME = "Currency";

    String PARAM_CAR_AMT_VAL_NAME = "Amount";

    // Parameter related to Valid Match Criteria
    String PARAM_VMC_ATTR_NAME = "ValidMatchCriteriaValue";

    String PARAM_VMC_KEY_NAME = "ValidMatchValue";

    String PARAM_VMC_VAL_NAME = "Value";

    String PARAM_THRESHOLD_VALUE = "Threshold";

    /** ISSUE_NO_BENE_NAME_AVAILABLE */
    String ISSUE_NO_BENE_NAME_AVAILABLE = "ISSUE_NO_BENE_NAME_AVAILABLE";

    /** ISSUE_SINGLE_NAME_AVAILABLE */
    String ISSUE_SINGLE_NAME_AVAILABLE = "ISSUE_SINGLE_NAME_AVAILABLE";

    /** ISSUE_VALID_MATCH_FOUND */
    String ISSUE_VALID_MATCH_FOUND = "ISSUE_VALID_MATCH_FOUND";

    /** ISSUE_VALID_MATCH_FOUND */
    String BENE_NAME_CHECK_SKIPPED = "BENE_NAME_CHECK_SKIPPED";

    /** ISSUE_BENE_NAME_CHECK_FAIL */
    String ISSUE_BENE_NAME_CHECK_FAIL = "ISSUE_BENE_NAME_CHECK_FAIL";


    String SPECIAL_CHAR = "-";

    String ALIAS_DEFAULT_MATCH_PERCENTAGE = "100";

    /**
     * SERVICENAME_CODEWORDSHANDLING
     */
    String  SERVICENAME_CODEWORDSHANDLING = "CodeWordsHandling";

    /**
     * ATTRIBUTENAME_CODEWORDSHANDLING
     */
    String ATTRIBUTENAME_CODEWORDSHANDLING = "CodeWordsHandling";

    /**
     * BANKGROUP
     */
    String BANKGROUP ="ADIBBankGroup";

    enum CHECKPOINT {
        PRIMARY,
        JOINT,
        ALIAS
    }
}
