package cdm.base.staticdata.asset.rates;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the list of floating rate index.
 * @version ${project.version}
 */
@RosettaEnum("FloatingRateIndexEnum")
public enum FloatingRateIndexEnum {

	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AED_EBOR_Reuters", displayName = "AED-EBOR-Reuters") AED_EBOR_REUTERS("AED_EBOR_Reuters", "AED-EBOR-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AED_EIBOR", displayName = "AED-EIBOR") AED_EIBOR("AED_EIBOR", "AED-EIBOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_AONIA", displayName = "AUD-AONIA") AUD_AONIA("AUD_AONIA", "AUD-AONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_AONIA_OIS_Compound_1", displayName = "AUD-AONIA-OIS Compound") AUD_AONIA_OIS_COMPOUND_1("AUD_AONIA_OIS_Compound_1", "AUD-AONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_AONIA_OIS_COMPOUND", displayName = "AUD-AONIA-OIS-COMPOUND") AUD_AONIA_OIS_COMPOUND("AUD_AONIA_OIS_COMPOUND", "AUD-AONIA-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_AONIA_OIS_COMPOUND_SwapMarker", displayName = "AUD-AONIA-OIS-COMPOUND-SwapMarker") AUD_AONIA_OIS_COMPOUND_SWAP_MARKER("AUD_AONIA_OIS_COMPOUND_SwapMarker", "AUD-AONIA-OIS-COMPOUND-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBR_AUBBSW", displayName = "AUD-BBR-AUBBSW") AUD_BBR_AUBBSW("AUD_BBR_AUBBSW", "AUD-BBR-AUBBSW"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBR_BBSW", displayName = "AUD-BBR-BBSW") AUD_BBR_BBSW("AUD_BBR_BBSW", "AUD-BBR-BBSW"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBR_BBSW_Bloomberg", displayName = "AUD-BBR-BBSW-Bloomberg") AUD_BBR_BBSW_BLOOMBERG("AUD_BBR_BBSW_Bloomberg", "AUD-BBR-BBSW-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBR_BBSY__BID_", displayName = "AUD-BBR-BBSY (BID)") AUD_BBR_BBSY__BID_("AUD_BBR_BBSY__BID_", "AUD-BBR-BBSY (BID)"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBR_ISDC", displayName = "AUD-BBR-ISDC") AUD_BBR_ISDC("AUD_BBR_ISDC", "AUD-BBR-ISDC"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBSW", displayName = "AUD-BBSW") AUD_BBSW("AUD_BBSW", "AUD-BBSW"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBSW_Quarterly_Swap_Rate_ICAP", displayName = "AUD-BBSW Quarterly Swap Rate ICAP") AUD_BBSW_QUARTERLY_SWAP_RATE_ICAP("AUD_BBSW_Quarterly_Swap_Rate_ICAP", "AUD-BBSW Quarterly Swap Rate ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBSW_Semi_Annual_Swap_Rate_ICAP", displayName = "AUD-BBSW Semi Annual Swap Rate ICAP") AUD_BBSW_SEMI_ANNUAL_SWAP_RATE_ICAP("AUD_BBSW_Semi_Annual_Swap_Rate_ICAP", "AUD-BBSW Semi Annual Swap Rate ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_BBSY_Bid", displayName = "AUD-BBSY Bid") AUD_BBSY_BID("AUD_BBSY_Bid", "AUD-BBSY Bid"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_LIBOR_BBA", displayName = "AUD-LIBOR-BBA") AUD_LIBOR_BBA("AUD_LIBOR_BBA", "AUD-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_LIBOR_BBA_Bloomberg", displayName = "AUD-LIBOR-BBA-Bloomberg") AUD_LIBOR_BBA_BLOOMBERG("AUD_LIBOR_BBA_Bloomberg", "AUD-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_LIBOR_Reference_Banks", displayName = "AUD-LIBOR-Reference Banks") AUD_LIBOR_REFERENCE_BANKS("AUD_LIBOR_Reference_Banks", "AUD-LIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Quarterly_Swap_Rate_ICAP", displayName = "AUD-Quarterly Swap Rate-ICAP") AUD_QUARTERLY_SWAP_RATE_ICAP("AUD_Quarterly_Swap_Rate_ICAP", "AUD-Quarterly Swap Rate-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Quarterly_Swap_Rate_ICAP_Reference_Banks", displayName = "AUD-Quarterly Swap Rate-ICAP-Reference Banks") AUD_QUARTERLY_SWAP_RATE_ICAP_REFERENCE_BANKS("AUD_Quarterly_Swap_Rate_ICAP_Reference_Banks", "AUD-Quarterly Swap Rate-ICAP-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "AUD-Semi-Annual Swap Rate-11:00-BGCANTOR") AUD_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("AUD_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "AUD-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Semi_Annual_Swap_Rate_BGCANTOR_Reference_Banks", displayName = "AUD-Semi-Annual Swap Rate-BGCANTOR-Reference Banks") AUD_SEMI_ANNUAL_SWAP_RATE_BGCANTOR_REFERENCE_BANKS("AUD_Semi_Annual_Swap_Rate_BGCANTOR_Reference_Banks", "AUD-Semi-Annual Swap Rate-BGCANTOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Semi_annual_Swap_Rate_ICAP", displayName = "AUD-Semi-annual Swap Rate-ICAP") AUD_SEMI_ANNUAL_SWAP_RATE_ICAP("AUD_Semi_annual_Swap_Rate_ICAP", "AUD-Semi-annual Swap Rate-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Semi_Annual_Swap_Rate_ICAP_Reference_Banks", displayName = "AUD-Semi-Annual Swap Rate-ICAP-Reference Banks") AUD_SEMI_ANNUAL_SWAP_RATE_ICAP_REFERENCE_BANKS("AUD_Semi_Annual_Swap_Rate_ICAP_Reference_Banks", "AUD-Semi-Annual Swap Rate-ICAP-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "AUD_Swap_Rate_Reuters", displayName = "AUD-Swap Rate-Reuters") AUD_SWAP_RATE_REUTERS("AUD_Swap_Rate_Reuters", "AUD-Swap Rate-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "BRL_CDI", displayName = "BRL-CDI") BRL_CDI("BRL_CDI", "BRL-CDI"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_BA_CDOR", displayName = "CAD-BA-CDOR") CAD_BA_CDOR("CAD_BA_CDOR", "CAD-BA-CDOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_BA_CDOR_Bloomberg", displayName = "CAD-BA-CDOR-Bloomberg") CAD_BA_CDOR_BLOOMBERG("CAD_BA_CDOR_Bloomberg", "CAD-BA-CDOR-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_BA_ISDD", displayName = "CAD-BA-ISDD") CAD_BA_ISDD("CAD_BA_ISDD", "CAD-BA-ISDD"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_BA_Reference_Banks", displayName = "CAD-BA-Reference Banks") CAD_BA_REFERENCE_BANKS("CAD_BA_Reference_Banks", "CAD-BA-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_BA_Reuters", displayName = "CAD-BA-Reuters") CAD_BA_REUTERS("CAD_BA_Reuters", "CAD-BA-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_BA_Telerate", displayName = "CAD-BA-Telerate") CAD_BA_TELERATE("CAD_BA_Telerate", "CAD-BA-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_CDOR", displayName = "CAD-CDOR") CAD_CDOR("CAD_CDOR", "CAD-CDOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_CORRA", displayName = "CAD-CORRA") CAD_CORRA("CAD_CORRA", "CAD-CORRA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_CORRA_CanDeal_TMX_Term", displayName = "CAD-CORRA CanDeal TMX Term") CAD_CORRA_CAN_DEAL_TMX_TERM("CAD_CORRA_CanDeal_TMX_Term", "CAD-CORRA CanDeal TMX Term"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_CORRA_Compounded_Index", displayName = "CAD-CORRA Compounded Index") CAD_CORRA_COMPOUNDED_INDEX("CAD_CORRA_Compounded_Index", "CAD-CORRA Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_CORRA_OIS_Compound_1", displayName = "CAD-CORRA-OIS Compound") CAD_CORRA_OIS_COMPOUND_1("CAD_CORRA_OIS_Compound_1", "CAD-CORRA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_CORRA_OIS_COMPOUND", displayName = "CAD-CORRA-OIS-COMPOUND") CAD_CORRA_OIS_COMPOUND("CAD_CORRA_OIS_COMPOUND", "CAD-CORRA-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_ISDA_Swap_Rate", displayName = "CAD-ISDA-Swap Rate") CAD_ISDA_SWAP_RATE("CAD_ISDA_Swap_Rate", "CAD-ISDA-Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_LIBOR_BBA", displayName = "CAD-LIBOR-BBA") CAD_LIBOR_BBA("CAD_LIBOR_BBA", "CAD-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_LIBOR_BBA_Bloomberg", displayName = "CAD-LIBOR-BBA-Bloomberg") CAD_LIBOR_BBA_BLOOMBERG("CAD_LIBOR_BBA_Bloomberg", "CAD-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_LIBOR_BBA_SwapMarker", displayName = "CAD-LIBOR-BBA-SwapMarker") CAD_LIBOR_BBA_SWAP_MARKER("CAD_LIBOR_BBA_SwapMarker", "CAD-LIBOR-BBA-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_LIBOR_Reference_Banks", displayName = "CAD-LIBOR-Reference Banks") CAD_LIBOR_REFERENCE_BANKS("CAD_LIBOR_Reference_Banks", "CAD-LIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_REPO_CORRA", displayName = "CAD-REPO-CORRA") CAD_REPO_CORRA("CAD_REPO_CORRA", "CAD-REPO-CORRA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_TBILL_ISDD", displayName = "CAD-TBILL-ISDD") CAD_TBILL_ISDD("CAD_TBILL_ISDD", "CAD-TBILL-ISDD"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_TBILL_Reference_Banks", displayName = "CAD-TBILL-Reference Banks") CAD_TBILL_REFERENCE_BANKS("CAD_TBILL_Reference_Banks", "CAD-TBILL-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_TBILL_Reuters", displayName = "CAD-TBILL-Reuters") CAD_TBILL_REUTERS("CAD_TBILL_Reuters", "CAD-TBILL-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CAD_TBILL_Telerate", displayName = "CAD-TBILL-Telerate") CAD_TBILL_TELERATE("CAD_TBILL_Telerate", "CAD-TBILL-Telerate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_3M_LIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "CHF-3M LIBOR SWAP-CME vs LCH-ICAP") CHF_3_M_LIBOR_SWAP_CME_VS_LCH_ICAP("CHF_3M_LIBOR_SWAP_CME_vs_LCH_ICAP", "CHF-3M LIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_3M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "CHF-3M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg") CHF_3_M_LIBOR_SWAP_CME_VS_LCH_ICAP_BLOOMBERG("CHF_3M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", "CHF-3M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_3M_LIBOR_SWAP_EUREX_vs_LCH_ICAP", displayName = "CHF-3M LIBOR SWAP-EUREX vs LCH-ICAP") CHF_3_M_LIBOR_SWAP_EUREX_VS_LCH_ICAP("CHF_3M_LIBOR_SWAP_EUREX_vs_LCH_ICAP", "CHF-3M LIBOR SWAP-EUREX vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_3M_LIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", displayName = "CHF-3M LIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg") CHF_3_M_LIBOR_SWAP_EUREX_VS_LCH_ICAP_BLOOMBERG("CHF_3M_LIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", "CHF-3M LIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_6M_LIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "CHF-6M LIBOR SWAP-CME vs LCH-ICAP") CHF_6_M_LIBOR_SWAP_CME_VS_LCH_ICAP("CHF_6M_LIBOR_SWAP_CME_vs_LCH_ICAP", "CHF-6M LIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_6M_LIBORSWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "CHF-6M LIBORSWAP-CME vs LCH-ICAP-Bloomberg") CHF_6_M_LIBORSWAP_CME_VS_LCH_ICAP_BLOOMBERG("CHF_6M_LIBORSWAP_CME_vs_LCH_ICAP_Bloomberg", "CHF-6M LIBORSWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP", displayName = "CHF-6M LIBOR SWAP-EUREX vs LCH-ICAP") CHF_6_M_LIBOR_SWAP_EUREX_VS_LCH_ICAP("CHF_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP", "CHF-6M LIBOR SWAP-EUREX vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", displayName = "CHF-6M LIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg") CHF_6_M_LIBOR_SWAP_EUREX_VS_LCH_ICAP_BLOOMBERG("CHF_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", "CHF-6M LIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_Annual_Swap_Rate", displayName = "CHF-Annual Swap Rate") CHF_ANNUAL_SWAP_RATE("CHF_Annual_Swap_Rate", "CHF-Annual Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_Annual_Swap_Rate_11_00_ICAP", displayName = "CHF-Annual Swap Rate-11:00-ICAP") CHF_ANNUAL_SWAP_RATE_11_00_ICAP("CHF_Annual_Swap_Rate_11_00_ICAP", "CHF-Annual Swap Rate-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_Annual_Swap_Rate_Reference_Banks", displayName = "CHF-Annual Swap Rate-Reference Banks") CHF_ANNUAL_SWAP_RATE_REFERENCE_BANKS("CHF_Annual_Swap_Rate_Reference_Banks", "CHF-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_Basis_Swap_3m_vs_6m_LIBOR_11_00_ICAP", displayName = "CHF-Basis Swap-3m vs 6m-LIBOR-11:00-ICAP") CHF_BASIS_SWAP_3_M_VS_6_M_LIBOR_11_00_ICAP("CHF_Basis_Swap_3m_vs_6m_LIBOR_11_00_ICAP", "CHF-Basis Swap-3m vs 6m-LIBOR-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_ISDAFIX_Swap_Rate", displayName = "CHF-ISDAFIX-Swap Rate") CHF_ISDAFIX_SWAP_RATE("CHF_ISDAFIX_Swap_Rate", "CHF-ISDAFIX-Swap Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_LIBOR", displayName = "CHF-LIBOR") CHF_LIBOR("CHF_LIBOR", "CHF-LIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_LIBOR_BBA", displayName = "CHF-LIBOR-BBA") CHF_LIBOR_BBA("CHF_LIBOR_BBA", "CHF-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_LIBOR_BBA_Bloomberg", displayName = "CHF-LIBOR-BBA-Bloomberg") CHF_LIBOR_BBA_BLOOMBERG("CHF_LIBOR_BBA_Bloomberg", "CHF-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_LIBOR_ISDA", displayName = "CHF-LIBOR-ISDA") CHF_LIBOR_ISDA("CHF_LIBOR_ISDA", "CHF-LIBOR-ISDA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_LIBOR_Reference_Banks", displayName = "CHF-LIBOR-Reference Banks") CHF_LIBOR_REFERENCE_BANKS("CHF_LIBOR_Reference_Banks", "CHF-LIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_OIS_11_00_ICAP", displayName = "CHF-OIS-11:00-ICAP") CHF_OIS_11_00_ICAP("CHF_OIS_11_00_ICAP", "CHF-OIS-11:00-ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON", displayName = "CHF-SARON") CHF_SARON("CHF_SARON", "CHF-SARON"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_12M", displayName = "CHF-SARON Average 12M") CHF_SARON_AVERAGE_12_M("CHF_SARON_Average_12M", "CHF-SARON Average 12M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_1M", displayName = "CHF-SARON Average 1M") CHF_SARON_AVERAGE_1_M("CHF_SARON_Average_1M", "CHF-SARON Average 1M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_1W", displayName = "CHF-SARON Average 1W") CHF_SARON_AVERAGE_1_W("CHF_SARON_Average_1W", "CHF-SARON Average 1W"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_2M", displayName = "CHF-SARON Average 2M") CHF_SARON_AVERAGE_2_M("CHF_SARON_Average_2M", "CHF-SARON Average 2M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_3M", displayName = "CHF-SARON Average 3M") CHF_SARON_AVERAGE_3_M("CHF_SARON_Average_3M", "CHF-SARON Average 3M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_6M", displayName = "CHF-SARON Average 6M") CHF_SARON_AVERAGE_6_M("CHF_SARON_Average_6M", "CHF-SARON Average 6M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Average_9M", displayName = "CHF-SARON Average 9M") CHF_SARON_AVERAGE_9_M("CHF_SARON_Average_9M", "CHF-SARON Average 9M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_Compounded_Index", displayName = "CHF-SARON Compounded Index") CHF_SARON_COMPOUNDED_INDEX("CHF_SARON_Compounded_Index", "CHF-SARON Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_OIS_Compound_1", displayName = "CHF-SARON-OIS Compound") CHF_SARON_OIS_COMPOUND_1("CHF_SARON_OIS_Compound_1", "CHF-SARON-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_SARON_OIS_COMPOUND", displayName = "CHF-SARON-OIS-COMPOUND") CHF_SARON_OIS_COMPOUND("CHF_SARON_OIS_COMPOUND", "CHF-SARON-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_TOIS_OIS_COMPOUND", displayName = "CHF-TOIS-OIS-COMPOUND") CHF_TOIS_OIS_COMPOUND("CHF_TOIS_OIS_COMPOUND", "CHF-TOIS-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CHF_USD_Basis_Swaps_11_00_ICAP", displayName = "CHF USD-Basis Swaps-11:00-ICAP") CHF_USD_BASIS_SWAPS_11_00_ICAP("CHF_USD_Basis_Swaps_11_00_ICAP", "CHF USD-Basis Swaps-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CL_CLICP_Bloomberg", displayName = "CL-CLICP-Bloomberg") CL_CLICP_BLOOMBERG("CL_CLICP_Bloomberg", "CL-CLICP-Bloomberg"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CLP_ICP", displayName = "CLP-ICP") CLP_ICP("CLP_ICP", "CLP-ICP"),
	
	/**
	 * Refers to the Indice Camara Promedio (&#39;ICP&#39;) rate for Chilean Pesos which, for a Reset Date, is determined and published by the Asociacion de Bancos e Instituciones Financieras de Chile A.G. (&#39;ABIF&#39;) in accordance with the &#39;Reglamento Indice de Camara Promedio&#39; of the ABIF as published in the Diario Oficial de la Republica de Chile (the &#39;ICP Rules&#39;) and which is reported on the ABIF website by not later than 10:00 a.m., Santiago time, on that Reset Date.
	 */
	@RosettaEnumValue(value = "CLP_TNA", displayName = "CLP-TNA") CLP_TNA("CLP_TNA", "CLP-TNA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNH_HIBOR", displayName = "CNH-HIBOR") CNH_HIBOR("CNH_HIBOR", "CNH-HIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNH_HIBOR_Reference_Banks", displayName = "CNH-HIBOR-Reference Banks") CNH_HIBOR_REFERENCE_BANKS("CNH_HIBOR_Reference_Banks", "CNH-HIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNH_HIBOR_TMA", displayName = "CNH-HIBOR-TMA") CNH_HIBOR_TMA("CNH_HIBOR_TMA", "CNH-HIBOR-TMA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_7_Repo_Compounding_Date", displayName = "CNY 7-Repo Compounding Date") CNY_7_REPO_COMPOUNDING_DATE("CNY_7_Repo_Compounding_Date", "CNY 7-Repo Compounding Date"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_CNREPOFIX_CFXS_Reuters", displayName = "CNY-CNREPOFIX=CFXS-Reuters") CNY_CNREPOFIX_CFXS_REUTERS("CNY_CNREPOFIX_CFXS_Reuters", "CNY-CNREPOFIX=CFXS-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Deposit_Rate", displayName = "CNY-Deposit Rate") CNY_DEPOSIT_RATE("CNY_Deposit_Rate", "CNY-Deposit Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Fixing_Repo_Rate", displayName = "CNY-Fixing Repo Rate") CNY_FIXING_REPO_RATE("CNY_Fixing_Repo_Rate", "CNY-Fixing Repo Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_LPR", displayName = "CNY-LPR") CNY_LPR("CNY_LPR", "CNY-LPR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_PBOCB_Reuters", displayName = "CNY-PBOCB-Reuters") CNY_PBOCB_REUTERS("CNY_PBOCB_Reuters", "CNY-PBOCB-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Quarterly_7_day_Repo_Non_Deliverable_Swap_Rate_TRADITION", displayName = "CNY-Quarterly 7 day Repo Non Deliverable Swap Rate-TRADITION") CNY_QUARTERLY_7_DAY_REPO_NON_DELIVERABLE_SWAP_RATE_TRADITION("CNY_Quarterly_7_day_Repo_Non_Deliverable_Swap_Rate_TRADITION", "CNY-Quarterly 7 day Repo Non Deliverable Swap Rate-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Quarterly_7_day_Repo_Non_Deliverable_Swap_Rate_TRADITION_Reference_Banks", displayName = "CNY-Quarterly 7 day Repo Non Deliverable Swap Rate-TRADITION-Reference Banks") CNY_QUARTERLY_7_DAY_REPO_NON_DELIVERABLE_SWAP_RATE_TRADITION_REFERENCE_BANKS("CNY_Quarterly_7_day_Repo_Non_Deliverable_Swap_Rate_TRADITION_Reference_Banks", "CNY-Quarterly 7 day Repo Non Deliverable Swap Rate-TRADITION-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Quarterly_7D_Repo_NDS_Rate_Tradition", displayName = "CNY-Quarterly 7D Repo NDS Rate Tradition") CNY_QUARTERLY_7_D_REPO_NDS_RATE_TRADITION("CNY_Quarterly_7D_Repo_NDS_Rate_Tradition", "CNY-Quarterly 7D Repo NDS Rate Tradition"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "CNY-Semi-Annual Swap Rate-11:00-BGCANTOR") CNY_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("CNY_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "CNY-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "CNY-Semi-Annual Swap Rate-Reference Banks") CNY_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("CNY_Semi_Annual_Swap_Rate_Reference_Banks", "CNY-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_SHIBOR", displayName = "CNY-SHIBOR") CNY_SHIBOR("CNY_SHIBOR", "CNY-SHIBOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_SHIBOR_OIS_Compound", displayName = "CNY-SHIBOR-OIS Compound") CNY_SHIBOR_OIS_COMPOUND("CNY_SHIBOR_OIS_Compound", "CNY-SHIBOR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CNY_Shibor_OIS_Compounding", displayName = "CNY-Shibor-OIS-Compounding") CNY_SHIBOR_OIS_COMPOUNDING("CNY_Shibor_OIS_Compounding", "CNY-Shibor-OIS-Compounding"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction..
	 */
	@RosettaEnumValue(value = "CNY_SHIBOR_Reuters", displayName = "CNY-SHIBOR-Reuters") CNY_SHIBOR_REUTERS("CNY_SHIBOR_Reuters", "CNY-SHIBOR-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "COP_IBR_OIS_Compound_1", displayName = "COP-IBR-OIS Compound") COP_IBR_OIS_COMPOUND_1("COP_IBR_OIS_Compound_1", "COP-IBR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "COP_IBR_OIS_COMPOUND", displayName = "COP-IBR-OIS-COMPOUND") COP_IBR_OIS_COMPOUND("COP_IBR_OIS_COMPOUND", "COP-IBR-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "CZK-Annual Swap Rate-11:00-BGCANTOR") CZK_ANNUAL_SWAP_RATE_11_00_BGCANTOR("CZK_Annual_Swap_Rate_11_00_BGCANTOR", "CZK-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_Annual_Swap_Rate_Reference_Banks", displayName = "CZK-Annual Swap Rate-Reference Banks") CZK_ANNUAL_SWAP_RATE_REFERENCE_BANKS("CZK_Annual_Swap_Rate_Reference_Banks", "CZK-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_CZEONIA", displayName = "CZK-CZEONIA") CZK_CZEONIA("CZK_CZEONIA", "CZK-CZEONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_CZEONIA_OIS_Compound", displayName = "CZK-CZEONIA-OIS Compound") CZK_CZEONIA_OIS_COMPOUND("CZK_CZEONIA_OIS_Compound", "CZK-CZEONIA-OIS Compound"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_PRIBOR", displayName = "CZK-PRIBOR") CZK_PRIBOR("CZK_PRIBOR", "CZK-PRIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_PRIBOR_PRBO", displayName = "CZK-PRIBOR-PRBO") CZK_PRIBOR_PRBO("CZK_PRIBOR_PRBO", "CZK-PRIBOR-PRBO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "CZK_PRIBOR_Reference_Banks", displayName = "CZK-PRIBOR-Reference Banks") CZK_PRIBOR_REFERENCE_BANKS("CZK_PRIBOR_Reference_Banks", "CZK-PRIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR", displayName = "DKK-CIBOR") DKK_CIBOR("DKK_CIBOR", "DKK-CIBOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR2", displayName = "DKK-CIBOR2") DKK_CIBOR2("DKK_CIBOR2", "DKK-CIBOR2"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR2_Bloomberg", displayName = "DKK-CIBOR2-Bloomberg") DKK_CIBOR_2_BLOOMBERG("DKK_CIBOR2_Bloomberg", "DKK-CIBOR2-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR2_DKNA13", displayName = "DKK-CIBOR2-DKNA13") DKK_CIBOR2_DKNA13("DKK_CIBOR2_DKNA13", "DKK-CIBOR2-DKNA13"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR_DKNA13", displayName = "DKK-CIBOR-DKNA13") DKK_CIBOR_DKNA13("DKK_CIBOR_DKNA13", "DKK-CIBOR-DKNA13"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR_DKNA13_Bloomberg", displayName = "DKK-CIBOR-DKNA13-Bloomberg") DKK_CIBOR_DKNA_13_BLOOMBERG("DKK_CIBOR_DKNA13_Bloomberg", "DKK-CIBOR-DKNA13-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CIBOR_Reference_Banks", displayName = "DKK-CIBOR-Reference Banks") DKK_CIBOR_REFERENCE_BANKS("DKK_CIBOR_Reference_Banks", "DKK-CIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CITA", displayName = "DKK-CITA") DKK_CITA("DKK_CITA", "DKK-CITA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_CITA_DKNA14_COMPOUND", displayName = "DKK-CITA-DKNA14-COMPOUND") DKK_CITA_DKNA14_COMPOUND("DKK_CITA_DKNA14_COMPOUND", "DKK-CITA-DKNA14-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_DESTR", displayName = "DKK-DESTR") DKK_DESTR("DKK_DESTR", "DKK-DESTR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_DESTR_Compounded_Index", displayName = "DKK-DESTR Compounded Index") DKK_DESTR_COMPOUNDED_INDEX("DKK_DESTR_Compounded_Index", "DKK-DESTR Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_DESTR_OIS_Compound", displayName = "DKK-DESTR-OIS Compound") DKK_DESTR_OIS_COMPOUND("DKK_DESTR_OIS_Compound", "DKK-DESTR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_DKKOIS_OIS_COMPOUND", displayName = "DKK-DKKOIS-OIS-COMPOUND") DKK_DKKOIS_OIS_COMPOUND("DKK_DKKOIS_OIS_COMPOUND", "DKK-DKKOIS-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "DKK_Tom_Next_OIS_Compound", displayName = "DKK-Tom Next-OIS Compound") DKK_TOM_NEXT_OIS_COMPOUND("DKK_Tom_Next_OIS_Compound", "DKK-Tom Next-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_3M_EURIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "EUR-3M EURIBOR SWAP-CME vs LCH-ICAP") EUR_3_M_EURIBOR_SWAP_CME_VS_LCH_ICAP("EUR_3M_EURIBOR_SWAP_CME_vs_LCH_ICAP", "EUR-3M EURIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_3M_EURIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "EUR-3M EURIBOR SWAP-CME vs LCH-ICAP-Bloomberg") EUR_3_M_EURIBOR_SWAP_CME_VS_LCH_ICAP_BLOOMBERG("EUR_3M_EURIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", "EUR-3M EURIBOR SWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_3M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP", displayName = "EUR-3M EURIBOR SWAP-EUREX vs LCH-ICAP") EUR_3_M_EURIBOR_SWAP_EUREX_VS_LCH_ICAP("EUR_3M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP", "EUR-3M EURIBOR SWAP-EUREX vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_3M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", displayName = "EUR-3M EURIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg") EUR_3_M_EURIBOR_SWAP_EUREX_VS_LCH_ICAP_BLOOMBERG("EUR_3M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", "EUR-3M EURIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_6M_EURIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "EUR-6M EURIBOR SWAP-CME vs LCH-ICAP") EUR_6_M_EURIBOR_SWAP_CME_VS_LCH_ICAP("EUR_6M_EURIBOR_SWAP_CME_vs_LCH_ICAP", "EUR-6M EURIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_6M_EURIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "EUR-6M EURIBOR SWAP-CME vs LCH-ICAP-Bloomberg") EUR_6_M_EURIBOR_SWAP_CME_VS_LCH_ICAP_BLOOMBERG("EUR_6M_EURIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", "EUR-6M EURIBOR SWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_6M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP", displayName = "EUR-6M EURIBOR SWAP-EUREX vs LCH-ICAP") EUR_6_M_EURIBOR_SWAP_EUREX_VS_LCH_ICAP("EUR_6M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP", "EUR-6M EURIBOR SWAP-EUREX vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_6M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", displayName = "EUR-6M EURIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg") EUR_6_M_EURIBOR_SWAP_EUREX_VS_LCH_ICAP_BLOOMBERG("EUR_6M_EURIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", "EUR-6M EURIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_10_00", displayName = "EUR-Annual Swap Rate-10:00") EUR_ANNUAL_SWAP_RATE_10_00("EUR_Annual_Swap_Rate_10_00", "EUR-Annual Swap Rate-10:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_10_00_BGCANTOR", displayName = "EUR-Annual Swap Rate-10:00-BGCANTOR") EUR_ANNUAL_SWAP_RATE_10_00_BGCANTOR("EUR_Annual_Swap_Rate_10_00_BGCANTOR", "EUR-Annual Swap Rate-10:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_10_00_Bloomberg", displayName = "EUR-Annual Swap Rate-10:00-Bloomberg") EUR_ANNUAL_SWAP_RATE_10_00_BLOOMBERG("EUR_Annual_Swap_Rate_10_00_Bloomberg", "EUR-Annual Swap Rate-10:00-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_10_00_ICAP", displayName = "EUR-Annual Swap Rate-10:00-ICAP") EUR_ANNUAL_SWAP_RATE_10_00_ICAP("EUR_Annual_Swap_Rate_10_00_ICAP", "EUR-Annual Swap Rate-10:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_10_00_SwapMarker", displayName = "EUR-Annual Swap Rate-10:00-SwapMarker") EUR_ANNUAL_SWAP_RATE_10_00_SWAP_MARKER("EUR_Annual_Swap_Rate_10_00_SwapMarker", "EUR-Annual Swap Rate-10:00-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_10_00_TRADITION", displayName = "EUR-Annual Swap Rate-10:00-TRADITION") EUR_ANNUAL_SWAP_RATE_10_00_TRADITION("EUR_Annual_Swap_Rate_10_00_TRADITION", "EUR-Annual Swap Rate-10:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_11_00", displayName = "EUR-Annual Swap Rate-11:00") EUR_ANNUAL_SWAP_RATE_11_00("EUR_Annual_Swap_Rate_11_00", "EUR-Annual Swap Rate-11:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_11_00_Bloomberg", displayName = "EUR-Annual Swap Rate-11:00-Bloomberg") EUR_ANNUAL_SWAP_RATE_11_00_BLOOMBERG("EUR_Annual_Swap_Rate_11_00_Bloomberg", "EUR-Annual Swap Rate-11:00-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_11_00_ICAP", displayName = "EUR-Annual Swap Rate-11:00-ICAP") EUR_ANNUAL_SWAP_RATE_11_00_ICAP("EUR_Annual_Swap_Rate_11_00_ICAP", "EUR-Annual Swap Rate-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_11_00_SwapMarker", displayName = "EUR-Annual Swap Rate-11:00-SwapMarker") EUR_ANNUAL_SWAP_RATE_11_00_SWAP_MARKER("EUR_Annual_Swap_Rate_11_00_SwapMarker", "EUR-Annual Swap Rate-11:00-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_3_Month", displayName = "EUR-Annual Swap Rate-3 Month") EUR_ANNUAL_SWAP_RATE_3_MONTH("EUR_Annual_Swap_Rate_3_Month", "EUR-Annual Swap Rate-3 Month"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_3_Month_SwapMarker", displayName = "EUR-Annual Swap Rate-3 Month-SwapMarker") EUR_ANNUAL_SWAP_RATE_3_MONTH_SWAP_MARKER("EUR_Annual_Swap_Rate_3_Month_SwapMarker", "EUR-Annual Swap Rate-3 Month-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_4_15_TRADITION", displayName = "EUR-Annual Swap Rate-4:15-TRADITION") EUR_ANNUAL_SWAP_RATE_4_15_TRADITION("EUR_Annual_Swap_Rate_4_15_TRADITION", "EUR-Annual Swap Rate-4:15-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Annual_Swap_Rate_Reference_Banks", displayName = "EUR-Annual Swap Rate-Reference Banks") EUR_ANNUAL_SWAP_RATE_REFERENCE_BANKS("EUR_Annual_Swap_Rate_Reference_Banks", "EUR-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_Basis_Swap_EONIA_vs_3m_EUR_IBOR_Swap_Rates_A_360_10_00_ICAP", displayName = "EUR Basis Swap-EONIA vs 3m EUR+IBOR Swap Rates-A/360-10:00-ICAP") EUR_BASIS_SWAP_EONIA_VS_3_M_EUR_IBOR_SWAP_RATES_A_360_10_00_ICAP("EUR_Basis_Swap_EONIA_vs_3m_EUR_IBOR_Swap_Rates_A_360_10_00_ICAP", "EUR Basis Swap-EONIA vs 3m EUR+IBOR Swap Rates-A/360-10:00-ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_CNO_TEC10", displayName = "EUR-CNO TEC10") EUR_CNO_TEC10("EUR_CNO_TEC10", "EUR-CNO TEC10"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA", displayName = "EUR-EONIA") EUR_EONIA("EUR_EONIA", "EUR-EONIA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_AVERAGE_1", displayName = "EUR-EONIA-AVERAGE") EUR_EONIA_AVERAGE_1("EUR_EONIA_AVERAGE_1", "EUR-EONIA-AVERAGE"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_Average", displayName = "EUR-EONIA-Average") EUR_EONIA_AVERAGE("EUR_EONIA_Average", "EUR-EONIA-Average"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_10_00_BGCANTOR", displayName = "EUR-EONIA-OIS-10:00-BGCANTOR") EUR_EONIA_OIS_10_00_BGCANTOR("EUR_EONIA_OIS_10_00_BGCANTOR", "EUR-EONIA-OIS-10:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_10_00_ICAP", displayName = "EUR-EONIA-OIS-10:00-ICAP") EUR_EONIA_OIS_10_00_ICAP("EUR_EONIA_OIS_10_00_ICAP", "EUR-EONIA-OIS-10:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_10_00_TRADITION", displayName = "EUR-EONIA-OIS-10:00-TRADITION") EUR_EONIA_OIS_10_00_TRADITION("EUR_EONIA_OIS_10_00_TRADITION", "EUR-EONIA-OIS-10:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_11_00_ICAP", displayName = "EUR-EONIA-OIS-11:00-ICAP") EUR_EONIA_OIS_11_00_ICAP("EUR_EONIA_OIS_11_00_ICAP", "EUR-EONIA-OIS-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_4_15_TRADITION", displayName = "EUR-EONIA-OIS-4:15-TRADITION") EUR_EONIA_OIS_4_15_TRADITION("EUR_EONIA_OIS_4_15_TRADITION", "EUR-EONIA-OIS-4:15-TRADITION"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_Compound_1", displayName = "EUR-EONIA-OIS Compound") EUR_EONIA_OIS_COMPOUND_1("EUR_EONIA_OIS_Compound_1", "EUR-EONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_COMPOUND", displayName = "EUR-EONIA-OIS-COMPOUND") EUR_EONIA_OIS_COMPOUND("EUR_EONIA_OIS_COMPOUND", "EUR-EONIA-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_OIS_COMPOUND_Bloomberg", displayName = "EUR-EONIA-OIS-COMPOUND-Bloomberg") EUR_EONIA_OIS_COMPOUND_BLOOMBERG("EUR_EONIA_OIS_COMPOUND_Bloomberg", "EUR-EONIA-OIS-COMPOUND-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EONIA_Swap_Index", displayName = "EUR-EONIA-Swap-Index") EUR_EONIA_SWAP_INDEX("EUR_EONIA_Swap_Index", "EUR-EONIA-Swap-Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR", displayName = "EUR-EURIBOR") EUR_EURIBOR("EUR_EURIBOR", "EUR-EURIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Act_365", displayName = "EUR-EURIBOR-Act/365") EUR_EURIBOR_ACT_365("EUR_EURIBOR_Act_365", "EUR-EURIBOR-Act/365"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Act_365_Bloomberg", displayName = "EUR-EURIBOR-Act/365-Bloomberg") EUR_EURIBOR_ACT_365_BLOOMBERG("EUR_EURIBOR_Act_365_Bloomberg", "EUR-EURIBOR-Act/365-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Annual_Bond_Swap_vs_1m_11_00_ICAP", displayName = "EUR EURIBOR-Annual Bond Swap vs 1m-11:00-ICAP") EUR_EURIBOR_ANNUAL_BOND_SWAP_VS_1_M_11_00_ICAP("EUR_EURIBOR_Annual_Bond_Swap_vs_1m_11_00_ICAP", "EUR EURIBOR-Annual Bond Swap vs 1m-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Basis_Swap_1m_vs_3m_Euribor_11_00_ICAP", displayName = "EUR EURIBOR-Basis Swap-1m vs 3m-Euribor-11:00-ICAP") EUR_EURIBOR_BASIS_SWAP_1_M_VS_3_M_EURIBOR_11_00_ICAP("EUR_EURIBOR_Basis_Swap_1m_vs_3m_Euribor_11_00_ICAP", "EUR EURIBOR-Basis Swap-1m vs 3m-Euribor-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Basis_Swap_3m_vs_6m_11_00_ICAP", displayName = "EUR EURIBOR-Basis Swap-3m vs 6m-11:00-ICAP") EUR_EURIBOR_BASIS_SWAP_3_M_VS_6_M_11_00_ICAP("EUR_EURIBOR_Basis_Swap_3m_vs_6m_11_00_ICAP", "EUR EURIBOR-Basis Swap-3m vs 6m-11:00-ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_ICE_Swap_Rate_11_00", displayName = "EUR-EURIBOR ICE Swap Rate-11:00") EUR_EURIBOR_ICE_SWAP_RATE_11_00("EUR_EURIBOR_ICE_Swap_Rate_11_00", "EUR-EURIBOR ICE Swap Rate-11:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_ICE_Swap_Rate_12_00", displayName = "EUR-EURIBOR ICE Swap Rate-12:00") EUR_EURIBOR_ICE_SWAP_RATE_12_00("EUR_EURIBOR_ICE_Swap_Rate_12_00", "EUR-EURIBOR ICE Swap Rate-12:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Reference_Banks", displayName = "EUR-EURIBOR-Reference Banks") EUR_EURIBOR_REFERENCE_BANKS("EUR_EURIBOR_Reference_Banks", "EUR-EURIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Reuters", displayName = "EUR-EURIBOR-Reuters") EUR_EURIBOR_REUTERS("EUR_EURIBOR_Reuters", "EUR-EURIBOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURIBOR_Telerate", displayName = "EUR-EURIBOR-Telerate") EUR_EURIBOR_TELERATE("EUR_EURIBOR_Telerate", "EUR-EURIBOR-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURONIA_OIS_Compound_1", displayName = "EUR-EURONIA-OIS Compound") EUR_EURONIA_OIS_COMPOUND_1("EUR_EURONIA_OIS_Compound_1", "EUR-EURONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EURONIA_OIS_COMPOUND", displayName = "EUR-EURONIA-OIS-COMPOUND") EUR_EURONIA_OIS_COMPOUND("EUR_EURONIA_OIS_COMPOUND", "EUR-EURONIA-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR", displayName = "EUR-EuroSTR") EUR_EURO_STR("EUR_EuroSTR", "EUR-EuroSTR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Average_12M", displayName = "EUR-EuroSTR Average 12M") EUR_EURO_STR_AVERAGE_12_M("EUR_EuroSTR_Average_12M", "EUR-EuroSTR Average 12M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Average_1M", displayName = "EUR-EuroSTR Average 1M") EUR_EURO_STR_AVERAGE_1_M("EUR_EuroSTR_Average_1M", "EUR-EuroSTR Average 1M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Average_1W", displayName = "EUR-EuroSTR Average 1W") EUR_EURO_STR_AVERAGE_1_W("EUR_EuroSTR_Average_1W", "EUR-EuroSTR Average 1W"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Average_3M", displayName = "EUR-EuroSTR Average 3M") EUR_EURO_STR_AVERAGE_3_M("EUR_EuroSTR_Average_3M", "EUR-EuroSTR Average 3M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Average_6M", displayName = "EUR-EuroSTR Average 6M") EUR_EURO_STR_AVERAGE_6_M("EUR_EuroSTR_Average_6M", "EUR-EuroSTR Average 6M"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_COMPOUND", displayName = "EUR-EuroSTR-COMPOUND") EUR_EURO_STR_COMPOUND("EUR_EuroSTR_COMPOUND", "EUR-EuroSTR-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Compounded_Index", displayName = "EUR-EuroSTR Compounded Index") EUR_EURO_STR_COMPOUNDED_INDEX("EUR_EuroSTR_Compounded_Index", "EUR-EuroSTR Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_FTSE_Term", displayName = "EUR-EuroSTR FTSE Term") EUR_EURO_STR_FTSE_TERM("EUR_EuroSTR_FTSE_Term", "EUR-EuroSTR FTSE Term"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_ICE_Compounded_Index", displayName = "EUR-EuroSTR ICE Compounded Index") EUR_EURO_STR_ICE_COMPOUNDED_INDEX("EUR_EuroSTR_ICE_Compounded_Index", "EUR-EuroSTR ICE Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_ICE_Compounded_Index_0_Floor", displayName = "EUR-EuroSTR ICE Compounded Index 0 Floor") EUR_EURO_STR_ICE_COMPOUNDED_INDEX_0_FLOOR("EUR_EuroSTR_ICE_Compounded_Index_0_Floor", "EUR-EuroSTR ICE Compounded Index 0 Floor"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_ICE_Compounded_Index_0_Floor_2D_Lag", displayName = "EUR-EuroSTR ICE Compounded Index 0 Floor 2D Lag") EUR_EURO_STR_ICE_COMPOUNDED_INDEX_0_FLOOR_2_D_LAG("EUR_EuroSTR_ICE_Compounded_Index_0_Floor_2D_Lag", "EUR-EuroSTR ICE Compounded Index 0 Floor 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_ICE_Compounded_Index_0_Floor_5D_Lag", displayName = "EUR-EuroSTR ICE Compounded Index 0 Floor 5D Lag") EUR_EURO_STR_ICE_COMPOUNDED_INDEX_0_FLOOR_5_D_LAG("EUR_EuroSTR_ICE_Compounded_Index_0_Floor_5D_Lag", "EUR-EuroSTR ICE Compounded Index 0 Floor 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_ICE_Compounded_Index_2D_Lag", displayName = "EUR-EuroSTR ICE Compounded Index 2D Lag") EUR_EURO_STR_ICE_COMPOUNDED_INDEX_2_D_LAG("EUR_EuroSTR_ICE_Compounded_Index_2D_Lag", "EUR-EuroSTR ICE Compounded Index 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_ICE_Compounded_Index_5D_Lag", displayName = "EUR-EuroSTR ICE Compounded Index 5D Lag") EUR_EURO_STR_ICE_COMPOUNDED_INDEX_5_D_LAG("EUR_EuroSTR_ICE_Compounded_Index_5D_Lag", "EUR-EuroSTR ICE Compounded Index 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_OIS_Compound", displayName = "EUR-EuroSTR-OIS Compound") EUR_EURO_STR_OIS_COMPOUND("EUR_EuroSTR_OIS_Compound", "EUR-EuroSTR-OIS Compound"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_EuroSTR_Term", displayName = "EUR-EuroSTR Term") EUR_EURO_STR_TERM("EUR_EuroSTR_Term", "EUR-EuroSTR Term"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_ISDA_EURIBOR_Swap_Rate_11_00", displayName = "EUR-ISDA-EURIBOR Swap Rate-11:00") EUR_ISDA_EURIBOR_SWAP_RATE_11_00("EUR_ISDA_EURIBOR_Swap_Rate_11_00", "EUR-ISDA-EURIBOR Swap Rate-11:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_ISDA_EURIBOR_Swap_Rate_12_00", displayName = "EUR-ISDA-EURIBOR Swap Rate-12:00") EUR_ISDA_EURIBOR_SWAP_RATE_12_00("EUR_ISDA_EURIBOR_Swap_Rate_12_00", "EUR-ISDA-EURIBOR Swap Rate-12:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_ISDA_LIBOR_Swap_Rate_10_00", displayName = "EUR-ISDA-LIBOR Swap Rate-10:00") EUR_ISDA_LIBOR_SWAP_RATE_10_00("EUR_ISDA_LIBOR_Swap_Rate_10_00", "EUR-ISDA-LIBOR Swap Rate-10:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_ISDA_LIBOR_Swap_Rate_11_00", displayName = "EUR-ISDA-LIBOR Swap Rate-11:00") EUR_ISDA_LIBOR_SWAP_RATE_11_00("EUR_ISDA_LIBOR_Swap_Rate_11_00", "EUR-ISDA-LIBOR Swap Rate-11:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_LIBOR", displayName = "EUR-LIBOR") EUR_LIBOR("EUR_LIBOR", "EUR-LIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_LIBOR_BBA", displayName = "EUR-LIBOR-BBA") EUR_LIBOR_BBA("EUR_LIBOR_BBA", "EUR-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_LIBOR_BBA_Bloomberg", displayName = "EUR-LIBOR-BBA-Bloomberg") EUR_LIBOR_BBA_BLOOMBERG("EUR_LIBOR_BBA_Bloomberg", "EUR-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_LIBOR_Reference_Banks", displayName = "EUR-LIBOR-Reference Banks") EUR_LIBOR_REFERENCE_BANKS("EUR_LIBOR_Reference_Banks", "EUR-LIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TAM_CDC", displayName = "EUR-TAM-CDC") EUR_TAM_CDC("EUR_TAM_CDC", "EUR-TAM-CDC"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TEC10_CNO", displayName = "EUR-TEC10-CNO") EUR_TEC10_CNO("EUR_TEC10_CNO", "EUR-TEC10-CNO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TEC10_CNO_SwapMarker", displayName = "EUR-TEC10-CNO-SwapMarker") EUR_TEC_10_CNO_SWAP_MARKER("EUR_TEC10_CNO_SwapMarker", "EUR-TEC10-CNO-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TEC10_Reference_Banks", displayName = "EUR-TEC10-Reference Banks") EUR_TEC_10_REFERENCE_BANKS("EUR_TEC10_Reference_Banks", "EUR-TEC10-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TEC5_CNO", displayName = "EUR-TEC5-CNO") EUR_TEC5_CNO("EUR_TEC5_CNO", "EUR-TEC5-CNO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TEC5_CNO_SwapMarker", displayName = "EUR-TEC5-CNO-SwapMarker") EUR_TEC_5_CNO_SWAP_MARKER("EUR_TEC5_CNO_SwapMarker", "EUR-TEC5-CNO-SwapMarker"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TEC5_Reference_Banks", displayName = "EUR-TEC5-Reference Banks") EUR_TEC_5_REFERENCE_BANKS("EUR_TEC5_Reference_Banks", "EUR-TEC5-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_TMM_CDC_COMPOUND", displayName = "EUR-TMM-CDC-COMPOUND") EUR_TMM_CDC_COMPOUND("EUR_TMM_CDC_COMPOUND", "EUR-TMM-CDC-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "EUR_USD_Basis_Swaps_11_00_ICAP", displayName = "EUR USD-Basis Swaps-11:00-ICAP") EUR_USD_BASIS_SWAPS_11_00_ICAP("EUR_USD_Basis_Swaps_11_00_ICAP", "EUR USD-Basis Swaps-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_6M_LIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "GBP-6M LIBOR SWAP-CME vs LCH-ICAP") GBP_6_M_LIBOR_SWAP_CME_VS_LCH_ICAP("GBP_6M_LIBOR_SWAP_CME_vs_LCH_ICAP", "GBP-6M LIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_6M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "GBP-6M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg") GBP_6_M_LIBOR_SWAP_CME_VS_LCH_ICAP_BLOOMBERG("GBP_6M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", "GBP-6M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP", displayName = "GBP-6M LIBOR SWAP-EUREX vs LCH-ICAP") GBP_6_M_LIBOR_SWAP_EUREX_VS_LCH_ICAP("GBP_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP", "GBP-6M LIBOR SWAP-EUREX vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", displayName = "GBP-6M LIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg") GBP_6_M_LIBOR_SWAP_EUREX_VS_LCH_ICAP_BLOOMBERG("GBP_6M_LIBOR_SWAP_EUREX_vs_LCH_ICAP_Bloomberg", "GBP-6M LIBOR SWAP-EUREX vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_ISDA_Swap_Rate", displayName = "GBP-ISDA-Swap Rate") GBP_ISDA_SWAP_RATE("GBP_ISDA_Swap_Rate", "GBP-ISDA-Swap Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_LIBOR", displayName = "GBP-LIBOR") GBP_LIBOR("GBP_LIBOR", "GBP-LIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_LIBOR_BBA", displayName = "GBP-LIBOR-BBA") GBP_LIBOR_BBA("GBP_LIBOR_BBA", "GBP-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_LIBOR_BBA_Bloomberg", displayName = "GBP-LIBOR-BBA-Bloomberg") GBP_LIBOR_BBA_BLOOMBERG("GBP_LIBOR_BBA_Bloomberg", "GBP-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_LIBOR_ICE_Swap_Rate", displayName = "GBP-LIBOR ICE Swap Rate") GBP_LIBOR_ICE_SWAP_RATE("GBP_LIBOR_ICE_Swap_Rate", "GBP-LIBOR ICE Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_LIBOR_ISDA", displayName = "GBP-LIBOR-ISDA") GBP_LIBOR_ISDA("GBP_LIBOR_ISDA", "GBP-LIBOR-ISDA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_LIBOR_Reference_Banks", displayName = "GBP-LIBOR-Reference Banks") GBP_LIBOR_REFERENCE_BANKS("GBP_LIBOR_Reference_Banks", "GBP-LIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_RONIA", displayName = "GBP-RONIA") GBP_RONIA("GBP_RONIA", "GBP-RONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_RONIA_OIS_Compound", displayName = "GBP-RONIA-OIS Compound") GBP_RONIA_OIS_COMPOUND("GBP_RONIA_OIS_Compound", "GBP-RONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_Semi_Annual_Swap_Rate", displayName = "GBP-Semi-Annual Swap Rate") GBP_SEMI_ANNUAL_SWAP_RATE("GBP_Semi_Annual_Swap_Rate", "GBP-Semi-Annual Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_Semi_Annual_Swap_Rate_11_00_ICAP", displayName = "GBP-Semi-Annual Swap Rate-11:00-ICAP") GBP_SEMI_ANNUAL_SWAP_RATE_11_00_ICAP("GBP_Semi_Annual_Swap_Rate_11_00_ICAP", "GBP-Semi-Annual Swap Rate-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_Semi_Annual_Swap_Rate_11_00_TRADITION", displayName = "GBP-Semi Annual Swap Rate-11:00-TRADITION") GBP_SEMI_ANNUAL_SWAP_RATE_11_00_TRADITION("GBP_Semi_Annual_Swap_Rate_11_00_TRADITION", "GBP-Semi Annual Swap Rate-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_Semi_Annual_Swap_Rate_4_15_TRADITION", displayName = "GBP-Semi Annual Swap Rate-4:15-TRADITION") GBP_SEMI_ANNUAL_SWAP_RATE_4_15_TRADITION("GBP_Semi_Annual_Swap_Rate_4_15_TRADITION", "GBP-Semi Annual Swap Rate-4:15-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "GBP-Semi-Annual Swap Rate-Reference Banks") GBP_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("GBP_Semi_Annual_Swap_Rate_Reference_Banks", "GBP-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_Semi_Annual_Swap_Rate_SwapMarker26", displayName = "GBP-Semi-Annual Swap Rate-SwapMarker26") GBP_SEMI_ANNUAL_SWAP_RATE_SWAP_MARKER_26("GBP_Semi_Annual_Swap_Rate_SwapMarker26", "GBP-Semi-Annual Swap Rate-SwapMarker26"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA", displayName = "GBP-SONIA") GBP_SONIA("GBP_SONIA", "GBP-SONIA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_COMPOUND", displayName = "GBP-SONIA-COMPOUND") GBP_SONIA_COMPOUND("GBP_SONIA_COMPOUND", "GBP-SONIA-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_Compounded_Index", displayName = "GBP-SONIA Compounded Index") GBP_SONIA_COMPOUNDED_INDEX("GBP_SONIA_Compounded_Index", "GBP-SONIA Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_FTSE_Term", displayName = "GBP-SONIA FTSE Term") GBP_SONIA_FTSE_TERM("GBP_SONIA_FTSE_Term", "GBP-SONIA FTSE Term"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Compounded_Index", displayName = "GBP-SONIA ICE Compounded Index") GBP_SONIA_ICE_COMPOUNDED_INDEX("GBP_SONIA_ICE_Compounded_Index", "GBP-SONIA ICE Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Compounded_Index_0_Floor", displayName = "GBP-SONIA ICE Compounded Index 0 Floor") GBP_SONIA_ICE_COMPOUNDED_INDEX_0_FLOOR("GBP_SONIA_ICE_Compounded_Index_0_Floor", "GBP-SONIA ICE Compounded Index 0 Floor"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Compounded_Index_0_Floor_2D_Lag", displayName = "GBP-SONIA ICE Compounded Index 0 Floor 2D Lag") GBP_SONIA_ICE_COMPOUNDED_INDEX_0_FLOOR_2_D_LAG("GBP_SONIA_ICE_Compounded_Index_0_Floor_2D_Lag", "GBP-SONIA ICE Compounded Index 0 Floor 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Compounded_Index_0_Floor_5D_Lag", displayName = "GBP-SONIA ICE Compounded Index 0 Floor 5D Lag") GBP_SONIA_ICE_COMPOUNDED_INDEX_0_FLOOR_5_D_LAG("GBP_SONIA_ICE_Compounded_Index_0_Floor_5D_Lag", "GBP-SONIA ICE Compounded Index 0 Floor 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Compounded_Index_2D_Lag", displayName = "GBP-SONIA ICE Compounded Index 2D Lag") GBP_SONIA_ICE_COMPOUNDED_INDEX_2_D_LAG("GBP_SONIA_ICE_Compounded_Index_2D_Lag", "GBP-SONIA ICE Compounded Index 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Compounded_Index_5D_Lag", displayName = "GBP-SONIA ICE Compounded Index 5D Lag") GBP_SONIA_ICE_COMPOUNDED_INDEX_5_D_LAG("GBP_SONIA_ICE_Compounded_Index_5D_Lag", "GBP-SONIA ICE Compounded Index 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Swap_Rate", displayName = "GBP-SONIA ICE Swap Rate") GBP_SONIA_ICE_SWAP_RATE("GBP_SONIA_ICE_Swap_Rate", "GBP-SONIA ICE Swap Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_ICE_Term", displayName = "GBP-SONIA ICE Term") GBP_SONIA_ICE_TERM("GBP_SONIA_ICE_Term", "GBP-SONIA ICE Term"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_OIS_11_00_ICAP", displayName = "GBP-SONIA-OIS-11:00-ICAP") GBP_SONIA_OIS_11_00_ICAP("GBP_SONIA_OIS_11_00_ICAP", "GBP-SONIA-OIS-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_OIS_11_00_TRADITION", displayName = "GBP-SONIA-OIS-11:00-TRADITION") GBP_SONIA_OIS_11_00_TRADITION("GBP_SONIA_OIS_11_00_TRADITION", "GBP-SONIA-OIS-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_OIS_4_15_TRADITION", displayName = "GBP-SONIA-OIS-4:15-TRADITION") GBP_SONIA_OIS_4_15_TRADITION("GBP_SONIA_OIS_4_15_TRADITION", "GBP-SONIA-OIS-4:15-TRADITION"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_OIS_Compound", displayName = "GBP-SONIA-OIS Compound") GBP_SONIA_OIS_COMPOUND("GBP_SONIA_OIS_Compound", "GBP-SONIA-OIS Compound"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_Refinitiv_Term", displayName = "GBP-SONIA Refinitiv Term") GBP_SONIA_REFINITIV_TERM("GBP_SONIA_Refinitiv_Term", "GBP-SONIA Refinitiv Term"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_SONIA_Swap_Rate", displayName = "GBP-SONIA Swap Rate") GBP_SONIA_SWAP_RATE("GBP_SONIA_Swap_Rate", "GBP-SONIA Swap Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_UK_Base_Rate", displayName = "GBP-UK Base Rate") GBP_UK_BASE_RATE("GBP_UK_Base_Rate", "GBP-UK Base Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_USD_Basis_Swaps_11_00_ICAP", displayName = "GBP USD-Basis Swaps-11:00-ICAP") GBP_USD_BASIS_SWAPS_11_00_ICAP("GBP_USD_Basis_Swaps_11_00_ICAP", "GBP USD-Basis Swaps-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_WMBA_RONIA_COMPOUND", displayName = "GBP-WMBA-RONIA-COMPOUND") GBP_WMBA_RONIA_COMPOUND("GBP_WMBA_RONIA_COMPOUND", "GBP-WMBA-RONIA-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GBP_WMBA_SONIA_COMPOUND", displayName = "GBP-WMBA-SONIA-COMPOUND") GBP_WMBA_SONIA_COMPOUND("GBP_WMBA_SONIA_COMPOUND", "GBP-WMBA-SONIA-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GRD_ATHIBOR_ATHIBOR", displayName = "GRD-ATHIBOR-ATHIBOR") GRD_ATHIBOR_ATHIBOR("GRD_ATHIBOR_ATHIBOR", "GRD-ATHIBOR-ATHIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GRD_ATHIBOR_Reference_Banks", displayName = "GRD-ATHIBOR-Reference Banks") GRD_ATHIBOR_REFERENCE_BANKS("GRD_ATHIBOR_Reference_Banks", "GRD-ATHIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GRD_ATHIBOR_Telerate", displayName = "GRD-ATHIBOR-Telerate") GRD_ATHIBOR_TELERATE("GRD_ATHIBOR_Telerate", "GRD-ATHIBOR-Telerate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GRD_ATHIMID_Reference_Banks", displayName = "GRD-ATHIMID-Reference Banks") GRD_ATHIMID_REFERENCE_BANKS("GRD_ATHIMID_Reference_Banks", "GRD-ATHIMID-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "GRD_ATHIMID_Reuters", displayName = "GRD-ATHIMID-Reuters") GRD_ATHIMID_REUTERS("GRD_ATHIMID_Reuters", "GRD-ATHIMID-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR", displayName = "HKD-HIBOR") HKD_HIBOR("HKD_HIBOR", "HKD-HIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR_HIBOR_", displayName = "HKD-HIBOR-HIBOR=") HKD_HIBOR_HIBOR_("HKD_HIBOR_HIBOR_", "HKD-HIBOR-HIBOR="),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR_HIBOR_Bloomberg", displayName = "HKD-HIBOR-HIBOR-Bloomberg") HKD_HIBOR_HIBOR_BLOOMBERG("HKD_HIBOR_HIBOR_Bloomberg", "HKD-HIBOR-HIBOR-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR_HKAB", displayName = "HKD-HIBOR-HKAB") HKD_HIBOR_HKAB("HKD_HIBOR_HKAB", "HKD-HIBOR-HKAB"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR_HKAB_Bloomberg", displayName = "HKD-HIBOR-HKAB-Bloomberg") HKD_HIBOR_HKAB_BLOOMBERG("HKD_HIBOR_HKAB_Bloomberg", "HKD-HIBOR-HKAB-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR_ISDC", displayName = "HKD-HIBOR-ISDC") HKD_HIBOR_ISDC("HKD_HIBOR_ISDC", "HKD-HIBOR-ISDC"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HIBOR_Reference_Banks", displayName = "HKD-HIBOR-Reference Banks") HKD_HIBOR_REFERENCE_BANKS("HKD_HIBOR_Reference_Banks", "HKD-HIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HONIA", displayName = "HKD-HONIA") HKD_HONIA("HKD_HONIA", "HKD-HONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HONIA_OIS_Compound", displayName = "HKD-HONIA-OIS Compound") HKD_HONIA_OIS_COMPOUND("HKD_HONIA_OIS_Compound", "HKD-HONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_HONIX_OIS_COMPOUND", displayName = "HKD-HONIX-OIS-COMPOUND") HKD_HONIX_OIS_COMPOUND("HKD_HONIX_OIS_COMPOUND", "HKD-HONIX-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_ISDA_Swap_Rate_11_00", displayName = "HKD-ISDA-Swap Rate-11:00") HKD_ISDA_SWAP_RATE_11_00("HKD_ISDA_Swap_Rate_11_00", "HKD-ISDA-Swap Rate-11:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_ISDA_Swap_Rate_4_00", displayName = "HKD-ISDA-Swap Rate-4:00") HKD_ISDA_SWAP_RATE_4_00("HKD_ISDA_Swap_Rate_4_00", "HKD-ISDA-Swap Rate-4:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "HKD-Quarterly-Annual Swap Rate-11:00-BGCANTOR") HKD_QUARTERLY_ANNUAL_SWAP_RATE_11_00_BGCANTOR("HKD_Quarterly_Annual_Swap_Rate_11_00_BGCANTOR", "HKD-Quarterly-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Annual_Swap_Rate_11_00_TRADITION", displayName = "HKD-Quarterly-Annual Swap Rate-11:00-TRADITION") HKD_QUARTERLY_ANNUAL_SWAP_RATE_11_00_TRADITION("HKD_Quarterly_Annual_Swap_Rate_11_00_TRADITION", "HKD-Quarterly-Annual Swap Rate-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Annual_Swap_Rate_4_00_BGCANTOR", displayName = "HKD-Quarterly-Annual Swap Rate-4:00-BGCANTOR") HKD_QUARTERLY_ANNUAL_SWAP_RATE_4_00_BGCANTOR("HKD_Quarterly_Annual_Swap_Rate_4_00_BGCANTOR", "HKD-Quarterly-Annual Swap Rate-4:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Annual_Swap_Rate_Reference_Banks", displayName = "HKD-Quarterly-Annual Swap Rate-Reference Banks") HKD_QUARTERLY_ANNUAL_SWAP_RATE_REFERENCE_BANKS("HKD_Quarterly_Annual_Swap_Rate_Reference_Banks", "HKD-Quarterly-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Quarterly_Swap_Rate_11_00_ICAP", displayName = "HKD-Quarterly-Quarterly Swap Rate-11:00-ICAP") HKD_QUARTERLY_QUARTERLY_SWAP_RATE_11_00_ICAP("HKD_Quarterly_Quarterly_Swap_Rate_11_00_ICAP", "HKD-Quarterly-Quarterly Swap Rate-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Quarterly_Swap_Rate_4_00_ICAP", displayName = "HKD-Quarterly-Quarterly Swap Rate-4:00-ICAP") HKD_QUARTERLY_QUARTERLY_SWAP_RATE_4_00_ICAP("HKD_Quarterly_Quarterly_Swap_Rate_4_00_ICAP", "HKD-Quarterly-Quarterly Swap Rate-4:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HKD_Quarterly_Quarterly_Swap_Rate_Reference_Banks", displayName = "HKD-Quarterly-Quarterly Swap Rate-Reference Banks") HKD_QUARTERLY_QUARTERLY_SWAP_RATE_REFERENCE_BANKS("HKD_Quarterly_Quarterly_Swap_Rate_Reference_Banks", "HKD-Quarterly-Quarterly Swap Rate-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HUF_BUBOR", displayName = "HUF-BUBOR") HUF_BUBOR("HUF_BUBOR", "HUF-BUBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HUF_BUBOR_Reference_Banks", displayName = "HUF-BUBOR-Reference Banks") HUF_BUBOR_REFERENCE_BANKS("HUF_BUBOR_Reference_Banks", "HUF-BUBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HUF_BUBOR_Reuters", displayName = "HUF-BUBOR-Reuters") HUF_BUBOR_REUTERS("HUF_BUBOR_Reuters", "HUF-BUBOR-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HUF_HUFONIA", displayName = "HUF-HUFONIA") HUF_HUFONIA("HUF_HUFONIA", "HUF-HUFONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "HUF_HUFONIA_OIS_Compound", displayName = "HUF-HUFONIA-OIS Compound") HUF_HUFONIA_OIS_COMPOUND("HUF_HUFONIA_OIS_Compound", "HUF-HUFONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_IDMA_Bloomberg", displayName = "IDR-IDMA-Bloomberg") IDR_IDMA_BLOOMBERG("IDR_IDMA_Bloomberg", "IDR-IDMA-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_IDRFIX", displayName = "IDR-IDRFIX") IDR_IDRFIX("IDR_IDRFIX", "IDR-IDRFIX"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_JIBOR", displayName = "IDR-JIBOR") IDR_JIBOR("IDR_JIBOR", "IDR-JIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_JIBOR_Reuters", displayName = "IDR-JIBOR-Reuters") IDR_JIBOR_REUTERS("IDR_JIBOR_Reuters", "IDR-JIBOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_SBI_Reuters", displayName = "IDR-SBI-Reuters") IDR_SBI_REUTERS("IDR_SBI_Reuters", "IDR-SBI-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "IDR-Semi-Annual Swap Rate-11:00-BGCANTOR") IDR_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("IDR_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "IDR-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_Semi_Annual_Swap_Rate_Non_deliverable_16_00_Tullett_Prebon", displayName = "IDR-Semi Annual Swap Rate-Non-deliverable-16:00-Tullett Prebon") IDR_SEMI_ANNUAL_SWAP_RATE_NON_DELIVERABLE_16_00_TULLETT_PREBON("IDR_Semi_Annual_Swap_Rate_Non_deliverable_16_00_Tullett_Prebon", "IDR-Semi Annual Swap Rate-Non-deliverable-16:00-Tullett Prebon"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "IDR-Semi-Annual Swap Rate-Reference Banks") IDR_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("IDR_Semi_Annual_Swap_Rate_Reference_Banks", "IDR-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_SOR_Reference_Banks", displayName = "IDR-SOR-Reference Banks") IDR_SOR_REFERENCE_BANKS("IDR_SOR_Reference_Banks", "IDR-SOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_SOR_Reuters", displayName = "IDR-SOR-Reuters") IDR_SOR_REUTERS("IDR_SOR_Reuters", "IDR-SOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "IDR_SOR_Telerate", displayName = "IDR-SOR-Telerate") IDR_SOR_TELERATE("IDR_SOR_Telerate", "IDR-SOR-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ILS_SHIR", displayName = "ILS-SHIR") ILS_SHIR("ILS_SHIR", "ILS-SHIR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ILS_SHIR_OIS_Compound", displayName = "ILS-SHIR-OIS Compound") ILS_SHIR_OIS_COMPOUND("ILS_SHIR_OIS_Compound", "ILS-SHIR-OIS Compound"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ILS_TELBOR", displayName = "ILS-TELBOR") ILS_TELBOR("ILS_TELBOR", "ILS-TELBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ILS_TELBOR01_Reuters", displayName = "ILS-TELBOR01-Reuters") ILS_TELBOR_01_REUTERS("ILS_TELBOR01_Reuters", "ILS-TELBOR01-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ILS_TELBOR_Reference_Banks", displayName = "ILS-TELBOR-Reference Banks") ILS_TELBOR_REFERENCE_BANKS("ILS_TELBOR_Reference_Banks", "ILS-TELBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_BMK", displayName = "INR-BMK") INR_BMK("INR_BMK", "INR-BMK"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_CMT", displayName = "INR-CMT") INR_CMT("INR_CMT", "INR-CMT"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_FBIL_MIBOR_OIS_COMPOUND", displayName = "INR-FBIL-MIBOR-OIS-COMPOUND") INR_FBIL_MIBOR_OIS_COMPOUND("INR_FBIL_MIBOR_OIS_COMPOUND", "INR-FBIL-MIBOR-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_INBMK_REUTERS", displayName = "INR-INBMK-REUTERS") INR_INBMK_REUTERS("INR_INBMK_REUTERS", "INR-INBMK-REUTERS"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_MIBOR_OIS", displayName = "INR-MIBOR OIS") INR_MIBOR_OIS("INR_MIBOR_OIS", "INR-MIBOR OIS"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_MIBOR_OIS_Compound_1", displayName = "INR-MIBOR-OIS Compound") INR_MIBOR_OIS_COMPOUND_1("INR_MIBOR_OIS_Compound_1", "INR-MIBOR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_MIBOR_OIS_COMPOUND", displayName = "INR-MIBOR-OIS-COMPOUND") INR_MIBOR_OIS_COMPOUND("INR_MIBOR_OIS_COMPOUND", "INR-MIBOR-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_MIFOR", displayName = "INR-MIFOR") INR_MIFOR("INR_MIFOR", "INR-MIFOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_MIOIS", displayName = "INR-MIOIS") INR_MIOIS("INR_MIOIS", "INR-MIOIS"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_MITOR_OIS_COMPOUND", displayName = "INR-MITOR-OIS-COMPOUND") INR_MITOR_OIS_COMPOUND("INR_MITOR_OIS_COMPOUND", "INR-MITOR-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_Modified_MIFOR", displayName = "INR-Modified MIFOR") INR_MODIFIED_MIFOR("INR_Modified_MIFOR", "INR-Modified MIFOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_Reference_Banks", displayName = "INR-Reference Banks") INR_REFERENCE_BANKS("INR_Reference_Banks", "INR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_Semi_Annual_Swap_Rate_11_30_BGCANTOR", displayName = "INR-Semi-Annual Swap Rate-11:30-BGCANTOR") INR_SEMI_ANNUAL_SWAP_RATE_11_30_BGCANTOR("INR_Semi_Annual_Swap_Rate_11_30_BGCANTOR", "INR-Semi-Annual Swap Rate-11:30-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_Semi_Annual_Swap_Rate_Non_deliverable_16_00_Tullett_Prebon", displayName = "INR-Semi Annual Swap Rate-Non-deliverable-16:00-Tullett Prebon") INR_SEMI_ANNUAL_SWAP_RATE_NON_DELIVERABLE_16_00_TULLETT_PREBON("INR_Semi_Annual_Swap_Rate_Non_deliverable_16_00_Tullett_Prebon", "INR-Semi Annual Swap Rate-Non-deliverable-16:00-Tullett Prebon"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "INR_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "INR-Semi-Annual Swap Rate-Reference Banks") INR_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("INR_Semi_Annual_Swap_Rate_Reference_Banks", "INR-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ISK_REIBOR", displayName = "ISK-REIBOR") ISK_REIBOR("ISK_REIBOR", "ISK-REIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ISK_REIBOR_Reference_Banks", displayName = "ISK-REIBOR-Reference Banks") ISK_REIBOR_REFERENCE_BANKS("ISK_REIBOR_Reference_Banks", "ISK-REIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ISK_REIBOR_Reuters", displayName = "ISK-REIBOR-Reuters") ISK_REIBOR_REUTERS("ISK_REIBOR_Reuters", "ISK-REIBOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_Annual_Swap_Rate_11_00_TRADITION", displayName = "JPY-Annual Swap Rate-11:00-TRADITION") JPY_ANNUAL_SWAP_RATE_11_00_TRADITION("JPY_Annual_Swap_Rate_11_00_TRADITION", "JPY-Annual Swap Rate-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_Annual_Swap_Rate_3_00_TRADITION", displayName = "JPY-Annual Swap Rate-3:00-TRADITION") JPY_ANNUAL_SWAP_RATE_3_00_TRADITION("JPY_Annual_Swap_Rate_3_00_TRADITION", "JPY-Annual Swap Rate-3:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_BBSF_Bloomberg_10_00", displayName = "JPY-BBSF-Bloomberg-10:00") JPY_BBSF_BLOOMBERG_10_00("JPY_BBSF_Bloomberg_10_00", "JPY-BBSF-Bloomberg-10:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_BBSF_Bloomberg_15_00", displayName = "JPY-BBSF-Bloomberg-15:00") JPY_BBSF_BLOOMBERG_15_00("JPY_BBSF_Bloomberg_15_00", "JPY-BBSF-Bloomberg-15:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_Euroyen_TIBOR", displayName = "JPY-Euroyen TIBOR") JPY_EUROYEN_TIBOR("JPY_Euroyen_TIBOR", "JPY-Euroyen TIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_ISDA_Swap_Rate_10_00", displayName = "JPY-ISDA-Swap Rate-10:00") JPY_ISDA_SWAP_RATE_10_00("JPY_ISDA_Swap_Rate_10_00", "JPY-ISDA-Swap Rate-10:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_ISDA_Swap_Rate_15_00", displayName = "JPY-ISDA-Swap Rate-15:00") JPY_ISDA_SWAP_RATE_15_00("JPY_ISDA_Swap_Rate_15_00", "JPY-ISDA-Swap Rate-15:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR", displayName = "JPY-LIBOR") JPY_LIBOR("JPY_LIBOR", "JPY-LIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_BBA", displayName = "JPY-LIBOR-BBA") JPY_LIBOR_BBA("JPY_LIBOR_BBA", "JPY-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_BBA_Bloomberg", displayName = "JPY-LIBOR-BBA-Bloomberg") JPY_LIBOR_BBA_BLOOMBERG("JPY_LIBOR_BBA_Bloomberg", "JPY-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_FRASETT", displayName = "JPY-LIBOR-FRASETT") JPY_LIBOR_FRASETT("JPY_LIBOR_FRASETT", "JPY-LIBOR-FRASETT"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_ISDA", displayName = "JPY-LIBOR-ISDA") JPY_LIBOR_ISDA("JPY_LIBOR_ISDA", "JPY-LIBOR-ISDA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_Reference_Banks", displayName = "JPY-LIBOR-Reference Banks") JPY_LIBOR_REFERENCE_BANKS("JPY_LIBOR_Reference_Banks", "JPY-LIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_TSR_10_00", displayName = "JPY-LIBOR TSR-10:00") JPY_LIBOR_TSR_10_00("JPY_LIBOR_TSR_10_00", "JPY-LIBOR TSR-10:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LIBOR_TSR_15_00", displayName = "JPY-LIBOR TSR-15:00") JPY_LIBOR_TSR_15_00("JPY_LIBOR_TSR_15_00", "JPY-LIBOR TSR-15:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LTPR_MHBK", displayName = "JPY-LTPR MHBK") JPY_LTPR_MHBK("JPY_LTPR_MHBK", "JPY-LTPR MHBK"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LTPR_MHCB", displayName = "JPY-LTPR-MHCB") JPY_LTPR_MHCB("JPY_LTPR_MHCB", "JPY-LTPR-MHCB"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_LTPR_TBC", displayName = "JPY-LTPR-TBC") JPY_LTPR_TBC("JPY_LTPR_TBC", "JPY-LTPR-TBC"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_MUTANCALL_TONAR", displayName = "JPY-MUTANCALL-TONAR") JPY_MUTANCALL_TONAR("JPY_MUTANCALL_TONAR", "JPY-MUTANCALL-TONAR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_OIS_11_00_ICAP", displayName = "JPY-OIS-11:00-ICAP") JPY_OIS_11_00_ICAP("JPY_OIS_11_00_ICAP", "JPY-OIS-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_OIS_11_00_TRADITION", displayName = "JPY-OIS-11:00-TRADITION") JPY_OIS_11_00_TRADITION("JPY_OIS_11_00_TRADITION", "JPY-OIS-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_OIS_3_00_TRADITION", displayName = "JPY-OIS-3:00-TRADITION") JPY_OIS_3_00_TRADITION("JPY_OIS_3_00_TRADITION", "JPY-OIS-3:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_Quoting_Banks_LIBOR", displayName = "JPY-Quoting Banks-LIBOR") JPY_QUOTING_BANKS_LIBOR("JPY_Quoting_Banks_LIBOR", "JPY-Quoting Banks-LIBOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_STPR_Quoting_Banks", displayName = "JPY-STPR-Quoting Banks") JPY_STPR_QUOTING_BANKS("JPY_STPR_Quoting_Banks", "JPY-STPR-Quoting Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR", displayName = "JPY-TIBOR") JPY_TIBOR("JPY_TIBOR", "JPY-TIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_17096", displayName = "JPY-TIBOR-17096") JPY_TIBOR_17096("JPY_TIBOR_17096", "JPY-TIBOR-17096"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_17097", displayName = "JPY-TIBOR-17097") JPY_TIBOR_17097("JPY_TIBOR_17097", "JPY-TIBOR-17097"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_DTIBOR01", displayName = "JPY-TIBOR-DTIBOR01") JPY_TIBOR_DTIBOR01("JPY_TIBOR_DTIBOR01", "JPY-TIBOR-DTIBOR01"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_TIBM", displayName = "JPY-TIBOR-TIBM") JPY_TIBOR_TIBM("JPY_TIBOR_TIBM", "JPY-TIBOR-TIBM"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_TIBM__10_Banks_", displayName = "JPY-TIBOR-TIBM (10 Banks)") JPY_TIBOR_TIBM_10_BANKS("JPY_TIBOR_TIBM__10_Banks_", "JPY-TIBOR-TIBM (10 Banks)"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_TIBM__5_Banks_", displayName = "JPY-TIBOR-TIBM (5 Banks)") JPY_TIBOR_TIBM_5_BANKS("JPY_TIBOR_TIBM__5_Banks_", "JPY-TIBOR-TIBM (5 Banks)"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_TIBM__All_Banks_", displayName = "JPY-TIBOR-TIBM (All Banks)") JPY_TIBOR_TIBM_ALL_BANKS("JPY_TIBOR_TIBM__All_Banks_", "JPY-TIBOR-TIBM (All Banks)"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_TIBM__All_Banks__Bloomberg", displayName = "JPY-TIBOR-TIBM (All Banks)-Bloomberg") JPY_TIBOR_TIBM_ALL_BANKS_BLOOMBERG("JPY_TIBOR_TIBM__All_Banks__Bloomberg", "JPY-TIBOR-TIBM (All Banks)-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_TIBM_Reference_Banks", displayName = "JPY-TIBOR-TIBM-Reference Banks") JPY_TIBOR_TIBM_REFERENCE_BANKS("JPY_TIBOR_TIBM_Reference_Banks", "JPY-TIBOR-TIBM-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TIBOR_ZTIBOR", displayName = "JPY-TIBOR-ZTIBOR") JPY_TIBOR_ZTIBOR("JPY_TIBOR_ZTIBOR", "JPY-TIBOR-ZTIBOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA", displayName = "JPY-TONA") JPY_TONA("JPY_TONA", "JPY-TONA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_Average_180D", displayName = "JPY-TONA Average 180D") JPY_TONA_AVERAGE_180_D("JPY_TONA_Average_180D", "JPY-TONA Average 180D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_Average_30D", displayName = "JPY-TONA Average 30D") JPY_TONA_AVERAGE_30_D("JPY_TONA_Average_30D", "JPY-TONA Average 30D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_Average_90D", displayName = "JPY-TONA Average 90D") JPY_TONA_AVERAGE_90_D("JPY_TONA_Average_90D", "JPY-TONA Average 90D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_Compounded_Index", displayName = "JPY-TONA Compounded Index") JPY_TONA_COMPOUNDED_INDEX("JPY_TONA_Compounded_Index", "JPY-TONA Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_ICE_Compounded_Index", displayName = "JPY-TONA ICE Compounded Index") JPY_TONA_ICE_COMPOUNDED_INDEX("JPY_TONA_ICE_Compounded_Index", "JPY-TONA ICE Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_ICE_Compounded_Index_0_Floor", displayName = "JPY-TONA ICE Compounded Index 0 Floor") JPY_TONA_ICE_COMPOUNDED_INDEX_0_FLOOR("JPY_TONA_ICE_Compounded_Index_0_Floor", "JPY-TONA ICE Compounded Index 0 Floor"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_ICE_Compounded_Index_0_Floor_2D_Lag", displayName = "JPY-TONA ICE Compounded Index 0 Floor 2D Lag") JPY_TONA_ICE_COMPOUNDED_INDEX_0_FLOOR_2_D_LAG("JPY_TONA_ICE_Compounded_Index_0_Floor_2D_Lag", "JPY-TONA ICE Compounded Index 0 Floor 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_ICE_Compounded_Index_0_Floor_5D_Lag", displayName = "JPY-TONA ICE Compounded Index 0 Floor 5D Lag") JPY_TONA_ICE_COMPOUNDED_INDEX_0_FLOOR_5_D_LAG("JPY_TONA_ICE_Compounded_Index_0_Floor_5D_Lag", "JPY-TONA ICE Compounded Index 0 Floor 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_ICE_Compounded_Index_2D_Lag", displayName = "JPY-TONA ICE Compounded Index 2D Lag") JPY_TONA_ICE_COMPOUNDED_INDEX_2_D_LAG("JPY_TONA_ICE_Compounded_Index_2D_Lag", "JPY-TONA ICE Compounded Index 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_ICE_Compounded_Index_5D_Lag", displayName = "JPY-TONA ICE Compounded Index 5D Lag") JPY_TONA_ICE_COMPOUNDED_INDEX_5_D_LAG("JPY_TONA_ICE_Compounded_Index_5D_Lag", "JPY-TONA ICE Compounded Index 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_OIS_Compound_1", displayName = "JPY-TONA-OIS Compound") JPY_TONA_OIS_COMPOUND_1("JPY_TONA_OIS_Compound_1", "JPY-TONA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_OIS_COMPOUND", displayName = "JPY-TONA-OIS-COMPOUND") JPY_TONA_OIS_COMPOUND("JPY_TONA_OIS_COMPOUND", "JPY-TONA-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_TSR_10_00", displayName = "JPY-TONA TSR-10:00") JPY_TONA_TSR_10_00("JPY_TONA_TSR_10_00", "JPY-TONA TSR-10:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TONA_TSR_15_00", displayName = "JPY-TONA TSR-15:00") JPY_TONA_TSR_15_00("JPY_TONA_TSR_15_00", "JPY-TONA TSR-15:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TORF_QUICK", displayName = "JPY-TORF QUICK") JPY_TORF_QUICK("JPY_TORF_QUICK", "JPY-TORF QUICK"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TSR_Reference_Banks", displayName = "JPY-TSR-Reference Banks") JPY_TSR_REFERENCE_BANKS("JPY_TSR_Reference_Banks", "JPY-TSR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TSR_Reuters_10_00", displayName = "JPY-TSR-Reuters-10:00") JPY_TSR_REUTERS_10_00("JPY_TSR_Reuters_10_00", "JPY-TSR-Reuters-10:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TSR_Reuters_15_00", displayName = "JPY-TSR-Reuters-15:00") JPY_TSR_REUTERS_15_00("JPY_TSR_Reuters_15_00", "JPY-TSR-Reuters-15:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TSR_Telerate_10_00", displayName = "JPY-TSR-Telerate-10:00") JPY_TSR_TELERATE_10_00("JPY_TSR_Telerate_10_00", "JPY-TSR-Telerate-10:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_TSR_Telerate_15_00", displayName = "JPY-TSR-Telerate-15:00") JPY_TSR_TELERATE_15_00("JPY_TSR_Telerate_15_00", "JPY-TSR-Telerate-15:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "JPY_USD_Basis_Swaps_11_00_ICAP", displayName = "JPY USD-Basis Swaps-11:00-ICAP") JPY_USD_BASIS_SWAPS_11_00_ICAP("JPY_USD_Basis_Swaps_11_00_ICAP", "JPY USD-Basis Swaps-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_Bond_3222", displayName = "KRW-Bond-3222") KRW_BOND_3222("KRW_Bond_3222", "KRW-Bond-3222"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_CD_3220", displayName = "KRW-CD-3220") KRW_CD_3220("KRW_CD_3220", "KRW-CD-3220"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_CD_91D", displayName = "KRW-CD 91D") KRW_CD_91D("KRW_CD_91D", "KRW-CD 91D"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_CD_KSDA_Bloomberg", displayName = "KRW-CD-KSDA-Bloomberg") KRW_CD_KSDA_BLOOMBERG("KRW_CD_KSDA_Bloomberg", "KRW-CD-KSDA-Bloomberg"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_KOFR", displayName = "KRW-KOFR") KRW_KOFR("KRW_KOFR", "KRW-KOFR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_KOFR_OIS_Compound", displayName = "KRW-KOFR-OIS Compound") KRW_KOFR_OIS_COMPOUND("KRW_KOFR_OIS_Compound", "KRW-KOFR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "KRW_Quarterly_Annual_Swap_Rate_3_30_ICAP", displayName = "KRW-Quarterly Annual Swap Rate-3:30-ICAP") KRW_QUARTERLY_ANNUAL_SWAP_RATE_3_30_ICAP("KRW_Quarterly_Annual_Swap_Rate_3_30_ICAP", "KRW-Quarterly Annual Swap Rate-3:30-ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE", displayName = "MXN-TIIE") MXN_TIIE("MXN_TIIE", "MXN-TIIE"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE_Banxico", displayName = "MXN-TIIE-Banxico") MXN_TIIE_BANXICO("MXN_TIIE_Banxico", "MXN-TIIE-Banxico"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE_Banxico_Bloomberg", displayName = "MXN-TIIE-Banxico-Bloomberg") MXN_TIIE_BANXICO_BLOOMBERG("MXN_TIIE_Banxico_Bloomberg", "MXN-TIIE-Banxico-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE_Banxico_Reference_Banks", displayName = "MXN-TIIE-Banxico-Reference Banks") MXN_TIIE_BANXICO_REFERENCE_BANKS("MXN_TIIE_Banxico_Reference_Banks", "MXN-TIIE-Banxico-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE_ON", displayName = "MXN-TIIE ON") MXN_TIIE_ON("MXN_TIIE_ON", "MXN-TIIE ON"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE_ON_OIS_Compound", displayName = "MXN-TIIE ON-OIS Compound") MXN_TIIE_ON_OIS_COMPOUND("MXN_TIIE_ON_OIS_Compound", "MXN-TIIE ON-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MXN_TIIE_Reference_Banks", displayName = "MXN-TIIE-Reference Banks") MXN_TIIE_REFERENCE_BANKS("MXN_TIIE_Reference_Banks", "MXN-TIIE-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_KLIBOR", displayName = "MYR-KLIBOR") MYR_KLIBOR("MYR_KLIBOR", "MYR-KLIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_KLIBOR_BNM", displayName = "MYR-KLIBOR-BNM") MYR_KLIBOR_BNM("MYR_KLIBOR_BNM", "MYR-KLIBOR-BNM"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_KLIBOR_Reference_Banks", displayName = "MYR-KLIBOR-Reference Banks") MYR_KLIBOR_REFERENCE_BANKS("MYR_KLIBOR_Reference_Banks", "MYR-KLIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_MYOR", displayName = "MYR-MYOR") MYR_MYOR("MYR_MYOR", "MYR-MYOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_MYOR_OIS_Compound", displayName = "MYR-MYOR-OIS Compound") MYR_MYOR_OIS_COMPOUND("MYR_MYOR_OIS_Compound", "MYR-MYOR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_Quarterly_Swap_Rate_11_00_TRADITION", displayName = "MYR-Quarterly Swap Rate-11:00-TRADITION") MYR_QUARTERLY_SWAP_RATE_11_00_TRADITION("MYR_Quarterly_Swap_Rate_11_00_TRADITION", "MYR-Quarterly Swap Rate-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "MYR_Quarterly_Swap_Rate_TRADITION_Reference_Banks", displayName = "MYR-Quarterly Swap Rate-TRADITION-Reference Banks") MYR_QUARTERLY_SWAP_RATE_TRADITION_REFERENCE_BANKS("MYR_Quarterly_Swap_Rate_TRADITION_Reference_Banks", "MYR-Quarterly Swap Rate-TRADITION-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NIBOR", displayName = "NOK-NIBOR") NOK_NIBOR("NOK_NIBOR", "NOK-NIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NIBOR_NIBR", displayName = "NOK-NIBOR-NIBR") NOK_NIBOR_NIBR("NOK_NIBOR_NIBR", "NOK-NIBOR-NIBR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NIBOR_NIBR_Bloomberg", displayName = "NOK-NIBOR-NIBR-Bloomberg") NOK_NIBOR_NIBR_BLOOMBERG("NOK_NIBOR_NIBR_Bloomberg", "NOK-NIBOR-NIBR-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NIBOR_NIBR_Reference_Banks", displayName = "NOK-NIBOR-NIBR-Reference Banks") NOK_NIBOR_NIBR_REFERENCE_BANKS("NOK_NIBOR_NIBR_Reference_Banks", "NOK-NIBOR-NIBR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NIBOR_OIBOR", displayName = "NOK-NIBOR-OIBOR") NOK_NIBOR_OIBOR("NOK_NIBOR_OIBOR", "NOK-NIBOR-OIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NIBOR_Reference_Banks", displayName = "NOK-NIBOR-Reference Banks") NOK_NIBOR_REFERENCE_BANKS("NOK_NIBOR_Reference_Banks", "NOK-NIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NOWA", displayName = "NOK-NOWA") NOK_NOWA("NOK_NOWA", "NOK-NOWA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NOK_NOWA_OIS_Compound", displayName = "NOK-NOWA-OIS Compound") NOK_NOWA_OIS_COMPOUND("NOK_NOWA_OIS_Compound", "NOK-NOWA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BBR_BID", displayName = "NZD-BBR-BID") NZD_BBR_BID("NZD_BBR_BID", "NZD-BBR-BID"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BBR_FRA", displayName = "NZD-BBR-FRA") NZD_BBR_FRA("NZD_BBR_FRA", "NZD-BBR-FRA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BBR_ISDC", displayName = "NZD-BBR-ISDC") NZD_BBR_ISDC("NZD_BBR_ISDC", "NZD-BBR-ISDC"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BBR_Reference_Banks", displayName = "NZD-BBR-Reference Banks") NZD_BBR_REFERENCE_BANKS("NZD_BBR_Reference_Banks", "NZD-BBR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BBR_Telerate", displayName = "NZD-BBR-Telerate") NZD_BBR_TELERATE("NZD_BBR_Telerate", "NZD-BBR-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BKBM_Bid", displayName = "NZD-BKBM Bid") NZD_BKBM_BID("NZD_BKBM_Bid", "NZD-BKBM Bid"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BKBM_FRA", displayName = "NZD-BKBM FRA") NZD_BKBM_FRA("NZD_BKBM_FRA", "NZD-BKBM FRA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_BKBM_FRA_Swap_Rate_ICAP", displayName = "NZD-BKBM FRA Swap Rate ICAP") NZD_BKBM_FRA_SWAP_RATE_ICAP("NZD_BKBM_FRA_Swap_Rate_ICAP", "NZD-BKBM FRA Swap Rate ICAP"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_NZIONA", displayName = "NZD-NZIONA") NZD_NZIONA("NZD_NZIONA", "NZD-NZIONA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_NZIONA_OIS_Compound_1", displayName = "NZD-NZIONA-OIS Compound") NZD_NZIONA_OIS_COMPOUND_1("NZD_NZIONA_OIS_Compound_1", "NZD-NZIONA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_NZIONA_OIS_COMPOUND", displayName = "NZD-NZIONA-OIS-COMPOUND") NZD_NZIONA_OIS_COMPOUND("NZD_NZIONA_OIS_COMPOUND", "NZD-NZIONA-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "NZD-Semi-Annual Swap Rate-11:00-BGCANTOR") NZD_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("NZD_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "NZD-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_Semi_Annual_Swap_Rate_BGCANTOR_Reference_Banks", displayName = "NZD-Semi-Annual Swap Rate-BGCANTOR-Reference Banks") NZD_SEMI_ANNUAL_SWAP_RATE_BGCANTOR_REFERENCE_BANKS("NZD_Semi_Annual_Swap_Rate_BGCANTOR_Reference_Banks", "NZD-Semi-Annual Swap Rate-BGCANTOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_Swap_Rate_ICAP", displayName = "NZD-Swap Rate-ICAP") NZD_SWAP_RATE_ICAP("NZD_Swap_Rate_ICAP", "NZD-Swap Rate-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "NZD_Swap_Rate_ICAP_Reference_Banks", displayName = "NZD-Swap Rate-ICAP-Reference Banks") NZD_SWAP_RATE_ICAP_REFERENCE_BANKS("NZD_Swap_Rate_ICAP_Reference_Banks", "NZD-Swap Rate-ICAP-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PHP_PHIREF", displayName = "PHP-PHIREF") PHP_PHIREF("PHP_PHIREF", "PHP-PHIREF"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PHP_PHIREF_BAP", displayName = "PHP-PHIREF-BAP") PHP_PHIREF_BAP("PHP_PHIREF_BAP", "PHP-PHIREF-BAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PHP_PHIREF_Bloomberg", displayName = "PHP-PHIREF-Bloomberg") PHP_PHIREF_BLOOMBERG("PHP_PHIREF_Bloomberg", "PHP-PHIREF-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PHP_PHIREF_Reference_Banks", displayName = "PHP-PHIREF-Reference Banks") PHP_PHIREF_REFERENCE_BANKS("PHP_PHIREF_Reference_Banks", "PHP-PHIREF-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PHP_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "PHP-Semi-Annual Swap Rate-11:00-BGCANTOR") PHP_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("PHP_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "PHP-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PHP_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "PHP-Semi-Annual Swap Rate-Reference Banks") PHP_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("PHP_Semi_Annual_Swap_Rate_Reference_Banks", "PHP-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_POLONIA", displayName = "PLN-POLONIA") PLN_POLONIA("PLN_POLONIA", "PLN-POLONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_POLONIA_OIS_Compound_1", displayName = "PLN-POLONIA-OIS Compound") PLN_POLONIA_OIS_COMPOUND_1("PLN_POLONIA_OIS_Compound_1", "PLN-POLONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_POLONIA_OIS_COMPOUND", displayName = "PLN-POLONIA-OIS-COMPOUND") PLN_POLONIA_OIS_COMPOUND("PLN_POLONIA_OIS_COMPOUND", "PLN-POLONIA-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_WIBID", displayName = "PLN-WIBID") PLN_WIBID("PLN_WIBID", "PLN-WIBID"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_WIBOR", displayName = "PLN-WIBOR") PLN_WIBOR("PLN_WIBOR", "PLN-WIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_WIBOR_Reference_Banks", displayName = "PLN-WIBOR-Reference Banks") PLN_WIBOR_REFERENCE_BANKS("PLN_WIBOR_Reference_Banks", "PLN-WIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_WIBOR_WIBO", displayName = "PLN-WIBOR-WIBO") PLN_WIBOR_WIBO("PLN_WIBOR_WIBO", "PLN-WIBOR-WIBO"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_WIRON", displayName = "PLN-WIRON") PLN_WIRON("PLN_WIRON", "PLN-WIRON"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLN_WIRON_OIS_Compound", displayName = "PLN-WIRON-OIS Compound") PLN_WIRON_OIS_COMPOUND("PLN_WIRON_OIS_Compound", "PLN-WIRON-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLZ_WIBOR_Reference_Banks", displayName = "PLZ-WIBOR-Reference Banks") PLZ_WIBOR_REFERENCE_BANKS("PLZ_WIBOR_Reference_Banks", "PLZ-WIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "PLZ_WIBOR_WIBO", displayName = "PLZ-WIBOR-WIBO") PLZ_WIBOR_WIBO("PLZ_WIBOR_WIBO", "PLZ-WIBOR-WIBO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "REPOFUNDS_RATE_FRANCE_OIS_COMPOUND", displayName = "REPOFUNDS RATE-FRANCE-OIS-COMPOUND") REPOFUNDS_RATE_FRANCE_OIS_COMPOUND("REPOFUNDS_RATE_FRANCE_OIS_COMPOUND", "REPOFUNDS RATE-FRANCE-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "REPOFUNDS_RATE_GERMANY_OIS_COMPOUND", displayName = "REPOFUNDS RATE-GERMANY-OIS-COMPOUND") REPOFUNDS_RATE_GERMANY_OIS_COMPOUND("REPOFUNDS_RATE_GERMANY_OIS_COMPOUND", "REPOFUNDS RATE-GERMANY-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "REPOFUNDS_RATE_ITALY_OIS_COMPOUND", displayName = "REPOFUNDS RATE-ITALY-OIS-COMPOUND") REPOFUNDS_RATE_ITALY_OIS_COMPOUND("REPOFUNDS_RATE_ITALY_OIS_COMPOUND", "REPOFUNDS RATE-ITALY-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RON_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "RON-Annual Swap Rate-11:00-BGCANTOR") RON_ANNUAL_SWAP_RATE_11_00_BGCANTOR("RON_Annual_Swap_Rate_11_00_BGCANTOR", "RON-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RON_Annual_Swap_Rate_Reference_Banks", displayName = "RON-Annual Swap Rate-Reference Banks") RON_ANNUAL_SWAP_RATE_REFERENCE_BANKS("RON_Annual_Swap_Rate_Reference_Banks", "RON-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RON_RBOR_Reuters", displayName = "RON-RBOR-Reuters") RON_RBOR_REUTERS("RON_RBOR_Reuters", "RON-RBOR-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RON_ROBID", displayName = "RON-ROBID") RON_ROBID("RON_ROBID", "RON-ROBID"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RON_ROBOR", displayName = "RON-ROBOR") RON_ROBOR("RON_ROBOR", "RON-ROBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "RUB-Annual Swap Rate-11:00-BGCANTOR") RUB_ANNUAL_SWAP_RATE_11_00_BGCANTOR("RUB_Annual_Swap_Rate_11_00_BGCANTOR", "RUB-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_Annual_Swap_Rate_12_45_TRADITION", displayName = "RUB-Annual Swap Rate-12:45-TRADITION") RUB_ANNUAL_SWAP_RATE_12_45_TRADITION("RUB_Annual_Swap_Rate_12_45_TRADITION", "RUB-Annual Swap Rate-12:45-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_Annual_Swap_Rate_4_15_TRADITION", displayName = "RUB-Annual Swap Rate-4:15-TRADITION") RUB_ANNUAL_SWAP_RATE_4_15_TRADITION("RUB_Annual_Swap_Rate_4_15_TRADITION", "RUB-Annual Swap Rate-4:15-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_Annual_Swap_Rate_Reference_Banks", displayName = "RUB-Annual Swap Rate-Reference Banks") RUB_ANNUAL_SWAP_RATE_REFERENCE_BANKS("RUB_Annual_Swap_Rate_Reference_Banks", "RUB-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_Annual_Swap_Rate_TRADITION_Reference_Banks", displayName = "RUB-Annual Swap Rate-TRADITION-Reference Banks") RUB_ANNUAL_SWAP_RATE_TRADITION_REFERENCE_BANKS("RUB_Annual_Swap_Rate_TRADITION_Reference_Banks", "RUB-Annual Swap Rate-TRADITION-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_Key_Rate_CBRF", displayName = "RUB-Key Rate CBRF") RUB_KEY_RATE_CBRF("RUB_Key_Rate_CBRF", "RUB-Key Rate CBRF"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_MosPrime", displayName = "RUB-MosPrime") RUB_MOS_PRIME("RUB_MosPrime", "RUB-MosPrime"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_MOSPRIME_NFEA", displayName = "RUB-MOSPRIME-NFEA") RUB_MOSPRIME_NFEA("RUB_MOSPRIME_NFEA", "RUB-MOSPRIME-NFEA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_MOSPRIME_Reference_Banks", displayName = "RUB-MOSPRIME-Reference Banks") RUB_MOSPRIME_REFERENCE_BANKS("RUB_MOSPRIME_Reference_Banks", "RUB-MOSPRIME-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_RUONIA", displayName = "RUB-RUONIA") RUB_RUONIA("RUB_RUONIA", "RUB-RUONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_RUONIA_OIS_Compound_1", displayName = "RUB-RUONIA-OIS Compound") RUB_RUONIA_OIS_COMPOUND_1("RUB_RUONIA_OIS_Compound_1", "RUB-RUONIA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "RUB_RUONIA_OIS_COMPOUND", displayName = "RUB-RUONIA-OIS-COMPOUND") RUB_RUONIA_OIS_COMPOUND("RUB_RUONIA_OIS_COMPOUND", "RUB-RUONIA-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SAR_SAIBOR", displayName = "SAR-SAIBOR") SAR_SAIBOR("SAR_SAIBOR", "SAR-SAIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SAR_SRIOR_Reference_Banks", displayName = "SAR-SRIOR-Reference Banks") SAR_SRIOR_REFERENCE_BANKS("SAR_SRIOR_Reference_Banks", "SAR-SRIOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SAR_SRIOR_SUAA", displayName = "SAR-SRIOR-SUAA") SAR_SRIOR_SUAA("SAR_SRIOR_SUAA", "SAR-SRIOR-SUAA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_Annual_Swap_Rate", displayName = "SEK-Annual Swap Rate") SEK_ANNUAL_SWAP_RATE("SEK_Annual_Swap_Rate", "SEK-Annual Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_Annual_Swap_Rate_SESWFI", displayName = "SEK-Annual Swap Rate-SESWFI") SEK_ANNUAL_SWAP_RATE_SESWFI("SEK_Annual_Swap_Rate_SESWFI", "SEK-Annual Swap Rate-SESWFI"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SIOR_OIS_COMPOUND", displayName = "SEK-SIOR-OIS-COMPOUND") SEK_SIOR_OIS_COMPOUND("SEK_SIOR_OIS_COMPOUND", "SEK-SIOR-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_STIBOR", displayName = "SEK-STIBOR") SEK_STIBOR("SEK_STIBOR", "SEK-STIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_STIBOR_Bloomberg", displayName = "SEK-STIBOR-Bloomberg") SEK_STIBOR_BLOOMBERG("SEK_STIBOR_Bloomberg", "SEK-STIBOR-Bloomberg"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_STIBOR_OIS_Compound", displayName = "SEK-STIBOR-OIS Compound") SEK_STIBOR_OIS_COMPOUND("SEK_STIBOR_OIS_Compound", "SEK-STIBOR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_STIBOR_Reference_Banks", displayName = "SEK-STIBOR-Reference Banks") SEK_STIBOR_REFERENCE_BANKS("SEK_STIBOR_Reference_Banks", "SEK-STIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_STIBOR_SIDE", displayName = "SEK-STIBOR-SIDE") SEK_STIBOR_SIDE("SEK_STIBOR_SIDE", "SEK-STIBOR-SIDE"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR", displayName = "SEK-SWESTR") SEK_SWESTR("SEK_SWESTR", "SEK-SWESTR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_Average_1M", displayName = "SEK-SWESTR Average 1M") SEK_SWESTR_AVERAGE_1_M("SEK_SWESTR_Average_1M", "SEK-SWESTR Average 1M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_Average_1W", displayName = "SEK-SWESTR Average 1W") SEK_SWESTR_AVERAGE_1_W("SEK_SWESTR_Average_1W", "SEK-SWESTR Average 1W"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_Average_2M", displayName = "SEK-SWESTR Average 2M") SEK_SWESTR_AVERAGE_2_M("SEK_SWESTR_Average_2M", "SEK-SWESTR Average 2M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_Average_3M", displayName = "SEK-SWESTR Average 3M") SEK_SWESTR_AVERAGE_3_M("SEK_SWESTR_Average_3M", "SEK-SWESTR Average 3M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_Average_6M", displayName = "SEK-SWESTR Average 6M") SEK_SWESTR_AVERAGE_6_M("SEK_SWESTR_Average_6M", "SEK-SWESTR Average 6M"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_Compounded_Index", displayName = "SEK-SWESTR Compounded Index") SEK_SWESTR_COMPOUNDED_INDEX("SEK_SWESTR_Compounded_Index", "SEK-SWESTR Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SEK_SWESTR_OIS_Compound", displayName = "SEK-SWESTR-OIS Compound") SEK_SWESTR_OIS_COMPOUND("SEK_SWESTR_OIS_Compound", "SEK-SWESTR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Currency_Basis_Swap_Rate_11_00_Tullett_Prebon", displayName = "SGD-Semi-Annual Currency Basis Swap Rate-11:00-Tullett Prebon") SGD_SEMI_ANNUAL_CURRENCY_BASIS_SWAP_RATE_11_00_TULLETT_PREBON("SGD_Semi_Annual_Currency_Basis_Swap_Rate_11_00_Tullett_Prebon", "SGD-Semi-Annual Currency Basis Swap Rate-11:00-Tullett Prebon"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Currency_Basis_Swap_Rate_16_00_Tullett_Prebon", displayName = "SGD-Semi-Annual Currency Basis Swap Rate-16:00-Tullett Prebon") SGD_SEMI_ANNUAL_CURRENCY_BASIS_SWAP_RATE_16_00_TULLETT_PREBON("SGD_Semi_Annual_Currency_Basis_Swap_Rate_16_00_Tullett_Prebon", "SGD-Semi-Annual Currency Basis Swap Rate-16:00-Tullett Prebon"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "SGD-Semi-Annual Swap Rate-11:00-BGCANTOR") SGD_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("SGD_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "SGD-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_11_00_Tullett_Prebon", displayName = "SGD-Semi-Annual Swap Rate-11:00-Tullett Prebon") SGD_SEMI_ANNUAL_SWAP_RATE_11_00_TULLETT_PREBON("SGD_Semi_Annual_Swap_Rate_11_00_Tullett_Prebon", "SGD-Semi-Annual Swap Rate-11:00-Tullett Prebon"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_11_00_TRADITION", displayName = "SGD-Semi-Annual Swap Rate-11.00-TRADITION") SGD_SEMI_ANNUAL_SWAP_RATE_11_00_TRADITION("SGD_Semi_Annual_Swap_Rate_11_00_TRADITION", "SGD-Semi-Annual Swap Rate-11.00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_16_00_Tullett_Prebon", displayName = "SGD-Semi-Annual Swap Rate-16:00-Tullett Prebon") SGD_SEMI_ANNUAL_SWAP_RATE_16_00_TULLETT_PREBON("SGD_Semi_Annual_Swap_Rate_16_00_Tullett_Prebon", "SGD-Semi-Annual Swap Rate-16:00-Tullett Prebon"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_ICAP", displayName = "SGD-Semi-Annual Swap Rate-ICAP") SGD_SEMI_ANNUAL_SWAP_RATE_ICAP("SGD_Semi_Annual_Swap_Rate_ICAP", "SGD-Semi-Annual Swap Rate-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_ICAP_Reference_Banks", displayName = "SGD-Semi-Annual Swap Rate-ICAP-Reference Banks") SGD_SEMI_ANNUAL_SWAP_RATE_ICAP_REFERENCE_BANKS("SGD_Semi_Annual_Swap_Rate_ICAP_Reference_Banks", "SGD-Semi-Annual Swap Rate-ICAP-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "SGD-Semi-Annual Swap Rate-Reference Banks") SGD_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("SGD_Semi_Annual_Swap_Rate_Reference_Banks", "SGD-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_Semi_Annual_Swap_Rate_TRADITION_Reference_Banks", displayName = "SGD-Semi-Annual Swap Rate-TRADITION-Reference Banks") SGD_SEMI_ANNUAL_SWAP_RATE_TRADITION_REFERENCE_BANKS("SGD_Semi_Annual_Swap_Rate_TRADITION_Reference_Banks", "SGD-Semi-Annual Swap Rate-TRADITION-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SIBOR", displayName = "SGD-SIBOR") SGD_SIBOR("SGD_SIBOR", "SGD-SIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SIBOR_Reference_Banks", displayName = "SGD-SIBOR-Reference Banks") SGD_SIBOR_REFERENCE_BANKS("SGD_SIBOR_Reference_Banks", "SGD-SIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SIBOR_Reuters", displayName = "SGD-SIBOR-Reuters") SGD_SIBOR_REUTERS("SGD_SIBOR_Reuters", "SGD-SIBOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SIBOR_Telerate", displayName = "SGD-SIBOR-Telerate") SGD_SIBOR_TELERATE("SGD_SIBOR_Telerate", "SGD-SIBOR-Telerate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SONAR_OIS_COMPOUND", displayName = "SGD-SONAR-OIS-COMPOUND") SGD_SONAR_OIS_COMPOUND("SGD_SONAR_OIS_COMPOUND", "SGD-SONAR-OIS-COMPOUND"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SONAR_OIS_VWAP_COMPOUND", displayName = "SGD-SONAR-OIS-VWAP-COMPOUND") SGD_SONAR_OIS_VWAP_COMPOUND("SGD_SONAR_OIS_VWAP_COMPOUND", "SGD-SONAR-OIS-VWAP-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SOR", displayName = "SGD-SOR") SGD_SOR("SGD_SOR", "SGD-SOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SORA", displayName = "SGD-SORA") SGD_SORA("SGD_SORA", "SGD-SORA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SORA_COMPOUND", displayName = "SGD-SORA-COMPOUND") SGD_SORA_COMPOUND("SGD_SORA_COMPOUND", "SGD-SORA-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SORA_OIS_Compound", displayName = "SGD-SORA-OIS Compound") SGD_SORA_OIS_COMPOUND("SGD_SORA_OIS_Compound", "SGD-SORA-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SOR_Reference_Banks", displayName = "SGD-SOR-Reference Banks") SGD_SOR_REFERENCE_BANKS("SGD_SOR_Reference_Banks", "SGD-SOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SOR_Reuters", displayName = "SGD-SOR-Reuters") SGD_SOR_REUTERS("SGD_SOR_Reuters", "SGD-SOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SOR_Telerate", displayName = "SGD-SOR-Telerate") SGD_SOR_TELERATE("SGD_SOR_Telerate", "SGD-SOR-Telerate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SOR_VWAP", displayName = "SGD-SOR-VWAP") SGD_SOR_VWAP("SGD_SOR_VWAP", "SGD-SOR-VWAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SGD_SOR_VWAP_Reference_Banks", displayName = "SGD-SOR-VWAP-Reference Banks") SGD_SOR_VWAP_REFERENCE_BANKS("SGD_SOR_VWAP_Reference_Banks", "SGD-SOR-VWAP-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SKK_BRIBOR_Bloomberg", displayName = "SKK-BRIBOR-Bloomberg") SKK_BRIBOR_BLOOMBERG("SKK_BRIBOR_Bloomberg", "SKK-BRIBOR-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SKK_BRIBOR_BRBO", displayName = "SKK-BRIBOR-BRBO") SKK_BRIBOR_BRBO("SKK_BRIBOR_BRBO", "SKK-BRIBOR-BRBO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SKK_BRIBOR_NBSK07", displayName = "SKK-BRIBOR-NBSK07") SKK_BRIBOR_NBSK07("SKK_BRIBOR_NBSK07", "SKK-BRIBOR-NBSK07"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "SKK_BRIBOR_Reference_Banks", displayName = "SKK-BRIBOR-Reference Banks") SKK_BRIBOR_REFERENCE_BANKS("SKK_BRIBOR_Reference_Banks", "SKK-BRIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "THB-Semi-Annual Swap Rate-11:00-BGCANTOR") THB_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("THB_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "THB-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "THB-Semi-Annual Swap Rate-Reference Banks") THB_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("THB_Semi_Annual_Swap_Rate_Reference_Banks", "THB-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_SOR_Reference_Banks", displayName = "THB-SOR-Reference Banks") THB_SOR_REFERENCE_BANKS("THB_SOR_Reference_Banks", "THB-SOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_SOR_Reuters", displayName = "THB-SOR-Reuters") THB_SOR_REUTERS("THB_SOR_Reuters", "THB-SOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_SOR_Telerate", displayName = "THB-SOR-Telerate") THB_SOR_TELERATE("THB_SOR_Telerate", "THB-SOR-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_THBFIX", displayName = "THB-THBFIX") THB_THBFIX("THB_THBFIX", "THB-THBFIX"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_THBFIX_Reference_Banks", displayName = "THB-THBFIX-Reference Banks") THB_THBFIX_REFERENCE_BANKS("THB_THBFIX_Reference_Banks", "THB-THBFIX-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_THBFIX_Reuters", displayName = "THB-THBFIX-Reuters") THB_THBFIX_REUTERS("THB_THBFIX_Reuters", "THB-THBFIX-Reuters"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_THOR", displayName = "THB-THOR") THB_THOR("THB_THOR", "THB-THOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_THOR_COMPOUND", displayName = "THB-THOR-COMPOUND") THB_THOR_COMPOUND("THB_THOR_COMPOUND", "THB-THOR-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "THB_THOR_OIS_Compound", displayName = "THB-THOR-OIS Compound") THB_THOR_OIS_COMPOUND("THB_THOR_OIS_Compound", "THB-THOR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_Annual_Swap_Rate_11_00_TRADITION", displayName = "TRY Annual Swap Rate-11:00-TRADITION") TRY_ANNUAL_SWAP_RATE_11_00_TRADITION("TRY_Annual_Swap_Rate_11_00_TRADITION", "TRY Annual Swap Rate-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_Annual_Swap_Rate_11_15_BGCANTOR", displayName = "TRY-Annual Swap Rate-11:15-BGCANTOR") TRY_ANNUAL_SWAP_RATE_11_15_BGCANTOR("TRY_Annual_Swap_Rate_11_15_BGCANTOR", "TRY-Annual Swap Rate-11:15-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_Annual_Swap_Rate_Reference_Banks", displayName = "TRY-Annual Swap Rate-Reference Banks") TRY_ANNUAL_SWAP_RATE_REFERENCE_BANKS("TRY_Annual_Swap_Rate_Reference_Banks", "TRY-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_Semi_Annual_Swap_Rate_TRADITION_Reference_Banks", displayName = "TRY-Semi-Annual Swap Rate-TRADITION-Reference Banks") TRY_SEMI_ANNUAL_SWAP_RATE_TRADITION_REFERENCE_BANKS("TRY_Semi_Annual_Swap_Rate_TRADITION_Reference_Banks", "TRY-Semi-Annual Swap Rate-TRADITION-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_TLREF", displayName = "TRY-TLREF") TRY_TLREF("TRY_TLREF", "TRY-TLREF"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_TLREF_OIS_Compound_1", displayName = "TRY-TLREF-OIS Compound") TRY_TLREF_OIS_COMPOUND_1("TRY_TLREF_OIS_Compound_1", "TRY-TLREF-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_TLREF_OIS_COMPOUND", displayName = "TRY-TLREF-OIS-COMPOUND") TRY_TLREF_OIS_COMPOUND("TRY_TLREF_OIS_COMPOUND", "TRY-TLREF-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_TRLIBOR", displayName = "TRY-TRLIBOR") TRY_TRLIBOR("TRY_TRLIBOR", "TRY-TRLIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_TRYIBOR_Reference_Banks", displayName = "TRY-TRYIBOR-Reference Banks") TRY_TRYIBOR_REFERENCE_BANKS("TRY_TRYIBOR_Reference_Banks", "TRY-TRYIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TRY_TRYIBOR_Reuters", displayName = "TRY-TRYIBOR-Reuters") TRY_TRYIBOR_REUTERS("TRY_TRYIBOR_Reuters", "TRY-TRYIBOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_Quarterly_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "TWD-Quarterly-Annual Swap Rate-11:00-BGCANTOR") TWD_QUARTERLY_ANNUAL_SWAP_RATE_11_00_BGCANTOR("TWD_Quarterly_Annual_Swap_Rate_11_00_BGCANTOR", "TWD-Quarterly-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_Quarterly_Annual_Swap_Rate_Reference_Banks", displayName = "TWD-Quarterly-Annual Swap Rate-Reference Banks") TWD_QUARTERLY_ANNUAL_SWAP_RATE_REFERENCE_BANKS("TWD_Quarterly_Annual_Swap_Rate_Reference_Banks", "TWD-Quarterly-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_Reference_Dealers", displayName = "TWD-Reference Dealers") TWD_REFERENCE_DEALERS("TWD_Reference_Dealers", "TWD-Reference Dealers"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_Reuters_6165", displayName = "TWD-Reuters-6165") TWD_REUTERS_6165("TWD_Reuters_6165", "TWD-Reuters-6165"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_TAIBIR01", displayName = "TWD-TAIBIR01") TWD_TAIBIR01("TWD_TAIBIR01", "TWD-TAIBIR01"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_TAIBIR02", displayName = "TWD-TAIBIR02") TWD_TAIBIR02("TWD_TAIBIR02", "TWD-TAIBIR02"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_TAIBOR", displayName = "TWD-TAIBOR") TWD_TAIBOR("TWD_TAIBOR", "TWD-TAIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_TAIBOR_Bloomberg", displayName = "TWD-TAIBOR-Bloomberg") TWD_TAIBOR_BLOOMBERG("TWD_TAIBOR_Bloomberg", "TWD-TAIBOR-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_TAIBOR_Reuters", displayName = "TWD-TAIBOR-Reuters") TWD_TAIBOR_REUTERS("TWD_TAIBOR_Reuters", "TWD-TAIBOR-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_Telerate_6165", displayName = "TWD-Telerate-6165") TWD_TELERATE_6165("TWD_Telerate_6165", "TWD-Telerate-6165"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "TWD_TWCPBA", displayName = "TWD-TWCPBA") TWD_TWCPBA("TWD_TWCPBA", "TWD-TWCPBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "UK_Base_Rate", displayName = "UK Base Rate") UK_BASE_RATE("UK_Base_Rate", "UK Base Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_3M_LIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "USD-3M LIBOR SWAP-CME vs LCH-ICAP") USD_3_M_LIBOR_SWAP_CME_VS_LCH_ICAP("USD_3M_LIBOR_SWAP_CME_vs_LCH_ICAP", "USD-3M LIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_3M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "USD-3M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg") USD_3_M_LIBOR_SWAP_CME_VS_LCH_ICAP_BLOOMBERG("USD_3M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", "USD-3M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_6M_LIBOR_SWAP_CME_vs_LCH_ICAP", displayName = "USD-6M LIBOR SWAP-CME vs LCH-ICAP") USD_6_M_LIBOR_SWAP_CME_VS_LCH_ICAP("USD_6M_LIBOR_SWAP_CME_vs_LCH_ICAP", "USD-6M LIBOR SWAP-CME vs LCH-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_6M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", displayName = "USD-6M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg") USD_6_M_LIBOR_SWAP_CME_VS_LCH_ICAP_BLOOMBERG("USD_6M_LIBOR_SWAP_CME_vs_LCH_ICAP_Bloomberg", "USD-6M LIBOR SWAP-CME vs LCH-ICAP-Bloomberg"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_AMERIBOR", displayName = "USD-AMERIBOR") USD_AMERIBOR("USD_AMERIBOR", "USD-AMERIBOR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_AMERIBOR_Average_30D", displayName = "USD-AMERIBOR Average 30D") USD_AMERIBOR_AVERAGE_30_D("USD_AMERIBOR_Average_30D", "USD-AMERIBOR Average 30D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_AMERIBOR_Average_90D", displayName = "USD-AMERIBOR Average 90D") USD_AMERIBOR_AVERAGE_90_D("USD_AMERIBOR_Average_90D", "USD-AMERIBOR Average 90D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_AMERIBOR_Term", displayName = "USD-AMERIBOR Term") USD_AMERIBOR_TERM("USD_AMERIBOR_Term", "USD-AMERIBOR Term"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_AMERIBOR_Term_Structure", displayName = "USD-AMERIBOR Term Structure") USD_AMERIBOR_TERM_STRUCTURE("USD_AMERIBOR_Term_Structure", "USD-AMERIBOR Term Structure"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "USD-Annual Swap Rate-11:00-BGCANTOR") USD_ANNUAL_SWAP_RATE_11_00_BGCANTOR("USD_Annual_Swap_Rate_11_00_BGCANTOR", "USD-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Annual_Swap_Rate_11_00_TRADITION", displayName = "USD-Annual Swap Rate-11:00-TRADITION") USD_ANNUAL_SWAP_RATE_11_00_TRADITION("USD_Annual_Swap_Rate_11_00_TRADITION", "USD-Annual Swap Rate-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Annual_Swap_Rate_4_00_TRADITION", displayName = "USD-Annual Swap Rate-4:00-TRADITION") USD_ANNUAL_SWAP_RATE_4_00_TRADITION("USD_Annual_Swap_Rate_4_00_TRADITION", "USD-Annual Swap Rate-4:00-TRADITION"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_AXI_Term", displayName = "USD-AXI Term") USD_AXI_TERM("USD_AXI_Term", "USD-AXI Term"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_BA_H_15", displayName = "USD-BA-H.15") USD_BA_H_15("USD_BA_H_15", "USD-BA-H.15"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_BA_Reference_Dealers", displayName = "USD-BA-Reference Dealers") USD_BA_REFERENCE_DEALERS("USD_BA_Reference_Dealers", "USD-BA-Reference Dealers"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_BMA_Municipal_Swap_Index", displayName = "USD-BMA Municipal Swap Index") USD_BMA_MUNICIPAL_SWAP_INDEX("USD_BMA_Municipal_Swap_Index", "USD-BMA Municipal Swap Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_BSBY", displayName = "USD-BSBY") USD_BSBY("USD_BSBY", "USD-BSBY"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CD_H_15", displayName = "USD-CD-H.15") USD_CD_H_15("USD_CD_H_15", "USD-CD-H.15"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CD_Reference_Dealers", displayName = "USD-CD-Reference Dealers") USD_CD_REFERENCE_DEALERS("USD_CD_Reference_Dealers", "USD-CD-Reference Dealers"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMS_Reference_Banks", displayName = "USD-CMS-Reference Banks") USD_CMS_REFERENCE_BANKS("USD_CMS_Reference_Banks", "USD-CMS-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMS_Reference_Banks_ICAP_SwapPX", displayName = "USD-CMS-Reference Banks-ICAP SwapPX") USD_CMS_REFERENCE_BANKS_ICAP_SWAP_PX("USD_CMS_Reference_Banks_ICAP_SwapPX", "USD-CMS-Reference Banks-ICAP SwapPX"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMS_Reuters", displayName = "USD-CMS-Reuters") USD_CMS_REUTERS("USD_CMS_Reuters", "USD-CMS-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMS_Telerate", displayName = "USD-CMS-Telerate") USD_CMS_TELERATE("USD_CMS_Telerate", "USD-CMS-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMT", displayName = "USD-CMT") USD_CMT("USD_CMT", "USD-CMT"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMT_Average_1W", displayName = "USD-CMT Average 1W") USD_CMT_AVERAGE_1_W("USD_CMT_Average_1W", "USD-CMT Average 1W"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMT_T7051", displayName = "USD-CMT-T7051") USD_CMT_T7051("USD_CMT_T7051", "USD-CMT-T7051"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CMT_T7052", displayName = "USD-CMT-T7052") USD_CMT_T7052("USD_CMT_T7052", "USD-CMT-T7052"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_COF11_FHLBSF", displayName = "USD-COF11-FHLBSF") USD_COF11_FHLBSF("USD_COF11_FHLBSF", "USD-COF11-FHLBSF"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_COF11_Reuters", displayName = "USD-COF11-Reuters") USD_COF_11_REUTERS("USD_COF11_Reuters", "USD-COF11-Reuters"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_COF11_Telerate", displayName = "USD-COF11-Telerate") USD_COF_11_TELERATE("USD_COF11_Telerate", "USD-COF11-Telerate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_COFI", displayName = "USD-COFI") USD_COFI("USD_COFI", "USD-COFI"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CP_H_15", displayName = "USD-CP-H.15") USD_CP_H_15("USD_CP_H_15", "USD-CP-H.15"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CP_Money_Market_Yield", displayName = "USD-CP-Money Market Yield") USD_CP_MONEY_MARKET_YIELD("USD_CP_Money_Market_Yield", "USD-CP-Money Market Yield"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CP_Reference_Dealers", displayName = "USD-CP-Reference Dealers") USD_CP_REFERENCE_DEALERS("USD_CP_Reference_Dealers", "USD-CP-Reference Dealers"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_CRITR", displayName = "USD-CRITR") USD_CRITR("USD_CRITR", "USD-CRITR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Federal_Funds", displayName = "USD-Federal Funds") USD_FEDERAL_FUNDS("USD_Federal_Funds", "USD-Federal Funds"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Federal_Funds_H_15", displayName = "USD-Federal Funds-H.15") USD_FEDERAL_FUNDS_H_15("USD_Federal_Funds_H_15", "USD-Federal Funds-H.15"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Federal_Funds_H_15_Bloomberg", displayName = "USD-Federal Funds-H.15-Bloomberg") USD_FEDERAL_FUNDS_H_15_BLOOMBERG("USD_Federal_Funds_H_15_Bloomberg", "USD-Federal Funds-H.15-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Federal_Funds_H_15_OIS_COMPOUND", displayName = "USD-Federal Funds-H.15-OIS-COMPOUND") USD_FEDERAL_FUNDS_H_15_OIS_COMPOUND("USD_Federal_Funds_H_15_OIS_COMPOUND", "USD-Federal Funds-H.15-OIS-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Federal_Funds_OIS_Compound", displayName = "USD-Federal Funds-OIS Compound") USD_FEDERAL_FUNDS_OIS_COMPOUND("USD_Federal_Funds_OIS_Compound", "USD-Federal Funds-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Federal_Funds_Reference_Dealers", displayName = "USD-Federal Funds-Reference Dealers") USD_FEDERAL_FUNDS_REFERENCE_DEALERS("USD_Federal_Funds_Reference_Dealers", "USD-Federal Funds-Reference Dealers"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_FFCB_DISCO", displayName = "USD-FFCB-DISCO") USD_FFCB_DISCO("USD_FFCB_DISCO", "USD-FFCB-DISCO"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_FXI_Term", displayName = "USD-FXI Term") USD_FXI_TERM("USD_FXI_Term", "USD-FXI Term"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_ISDAFIX3_Swap_Rate", displayName = "USD-ISDAFIX3-Swap Rate") USD_ISDAFIX_3_SWAP_RATE("USD_ISDAFIX3_Swap_Rate", "USD-ISDAFIX3-Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_ISDAFIX3_Swap_Rate_3_00", displayName = "USD-ISDAFIX3-Swap Rate-3:00") USD_ISDAFIX_3_SWAP_RATE_3_00("USD_ISDAFIX3_Swap_Rate_3_00", "USD-ISDAFIX3-Swap Rate-3:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_ISDA_Swap_Rate", displayName = "USD-ISDA-Swap Rate") USD_ISDA_SWAP_RATE("USD_ISDA_Swap_Rate", "USD-ISDA-Swap Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_ISDA_Swap_Rate_3_00", displayName = "USD-ISDA-Swap Rate-3:00") USD_ISDA_SWAP_RATE_3_00("USD_ISDA_Swap_Rate_3_00", "USD-ISDA-Swap Rate-3:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR", displayName = "USD-LIBOR") USD_LIBOR("USD_LIBOR", "USD-LIBOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_BBA", displayName = "USD-LIBOR-BBA") USD_LIBOR_BBA("USD_LIBOR_BBA", "USD-LIBOR-BBA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_BBA_Bloomberg", displayName = "USD-LIBOR-BBA-Bloomberg") USD_LIBOR_BBA_BLOOMBERG("USD_LIBOR_BBA_Bloomberg", "USD-LIBOR-BBA-Bloomberg"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_ICE_Swap_Rate_11_00", displayName = "USD-LIBOR ICE Swap Rate-11:00") USD_LIBOR_ICE_SWAP_RATE_11_00("USD_LIBOR_ICE_Swap_Rate_11_00", "USD-LIBOR ICE Swap Rate-11:00"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_ICE_Swap_Rate_15_00", displayName = "USD-LIBOR ICE Swap Rate-15:00") USD_LIBOR_ICE_SWAP_RATE_15_00("USD_LIBOR_ICE_Swap_Rate_15_00", "USD-LIBOR ICE Swap Rate-15:00"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_ISDA", displayName = "USD-LIBOR-ISDA") USD_LIBOR_ISDA("USD_LIBOR_ISDA", "USD-LIBOR-ISDA"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_LIBO", displayName = "USD-LIBOR-LIBO") USD_LIBOR_LIBO("USD_LIBOR_LIBO", "USD-LIBOR-LIBO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_LIBOR_Reference_Banks", displayName = "USD-LIBOR-Reference Banks") USD_LIBOR_REFERENCE_BANKS("USD_LIBOR_Reference_Banks", "USD-LIBOR-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Municipal_Swap_Index", displayName = "USD-Municipal Swap Index") USD_MUNICIPAL_SWAP_INDEX("USD_Municipal_Swap_Index", "USD-Municipal Swap Index"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Municipal_Swap_Libor_Ratio_11_00_ICAP", displayName = "USD-Municipal Swap Libor Ratio-11:00-ICAP") USD_MUNICIPAL_SWAP_LIBOR_RATIO_11_00_ICAP("USD_Municipal_Swap_Libor_Ratio_11_00_ICAP", "USD-Municipal Swap Libor Ratio-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Municipal_Swap_Rate_11_00_ICAP", displayName = "USD-Municipal Swap Rate-11:00-ICAP") USD_MUNICIPAL_SWAP_RATE_11_00_ICAP("USD_Municipal_Swap_Rate_11_00_ICAP", "USD-Municipal Swap Rate-11:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_11_00_BGCANTOR", displayName = "USD-OIS-11:00-BGCANTOR") USD_OIS_11_00_BGCANTOR("USD_OIS_11_00_BGCANTOR", "USD-OIS-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_11_00_LON_ICAP", displayName = "USD-OIS-11:00-LON-ICAP") USD_OIS_11_00_LON_ICAP("USD_OIS_11_00_LON_ICAP", "USD-OIS-11:00-LON-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_11_00_NY_ICAP", displayName = "USD-OIS-11:00-NY-ICAP") USD_OIS_11_00_NY_ICAP("USD_OIS_11_00_NY_ICAP", "USD-OIS-11:00-NY-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_11_00_TRADITION", displayName = "USD-OIS-11:00-TRADITION") USD_OIS_11_00_TRADITION("USD_OIS_11_00_TRADITION", "USD-OIS-11:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_3_00_BGCANTOR", displayName = "USD-OIS-3:00-BGCANTOR") USD_OIS_3_00_BGCANTOR("USD_OIS_3_00_BGCANTOR", "USD-OIS-3:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_3_00_NY_ICAP", displayName = "USD-OIS-3:00-NY-ICAP") USD_OIS_3_00_NY_ICAP("USD_OIS_3_00_NY_ICAP", "USD-OIS-3:00-NY-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_OIS_4_00_TRADITION", displayName = "USD-OIS-4:00-TRADITION") USD_OIS_4_00_TRADITION("USD_OIS_4_00_TRADITION", "USD-OIS-4:00-TRADITION"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Overnight_Bank_Funding_Rate", displayName = "USD-Overnight Bank Funding Rate") USD_OVERNIGHT_BANK_FUNDING_RATE("USD_Overnight_Bank_Funding_Rate", "USD-Overnight Bank Funding Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Prime", displayName = "USD-Prime") USD_PRIME("USD_Prime", "USD-Prime"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Prime_H_15", displayName = "USD-Prime-H.15") USD_PRIME_H_15("USD_Prime_H_15", "USD-Prime-H.15"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Prime_Reference_Banks", displayName = "USD-Prime-Reference Banks") USD_PRIME_REFERENCE_BANKS("USD_Prime_Reference_Banks", "USD-Prime-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_S_P_Index_High_Grade", displayName = "USD-S&P Index-High Grade") USD_S_P_INDEX_HIGH_GRADE("USD_S_P_Index_High_Grade", "USD-S&P Index-High Grade"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SandP_Index_High_Grade", displayName = "USD-SandP Index High Grade") USD_SAND_P_INDEX_HIGH_GRADE("USD_SandP_Index_High_Grade", "USD-SandP Index High Grade"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SIBOR_Reference_Banks", displayName = "USD-SIBOR-Reference Banks") USD_SIBOR_REFERENCE_BANKS("USD_SIBOR_Reference_Banks", "USD-SIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SIBOR_SIBO", displayName = "USD-SIBOR-SIBO") USD_SIBOR_SIBO("USD_SIBOR_SIBO", "USD-SIBOR-SIBO"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SIFMA_Municipal_Swap_Index", displayName = "USD-SIFMA Municipal Swap Index") USD_SIFMA_MUNICIPAL_SWAP_INDEX("USD_SIFMA_Municipal_Swap_Index", "USD-SIFMA Municipal Swap Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR", displayName = "USD-SOFR") USD_SOFR("USD_SOFR", "USD-SOFR"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_Average_180D", displayName = "USD-SOFR Average 180D") USD_SOFR_AVERAGE_180_D("USD_SOFR_Average_180D", "USD-SOFR Average 180D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_Average_30D", displayName = "USD-SOFR Average 30D") USD_SOFR_AVERAGE_30_D("USD_SOFR_Average_30D", "USD-SOFR Average 30D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_Average_90D", displayName = "USD-SOFR Average 90D") USD_SOFR_AVERAGE_90_D("USD_SOFR_Average_90D", "USD-SOFR Average 90D"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_CME_Term", displayName = "USD-SOFR CME Term") USD_SOFR_CME_TERM("USD_SOFR_CME_Term", "USD-SOFR CME Term"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_COMPOUND", displayName = "USD-SOFR-COMPOUND") USD_SOFR_COMPOUND("USD_SOFR_COMPOUND", "USD-SOFR-COMPOUND"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_Compounded_Index", displayName = "USD-SOFR Compounded Index") USD_SOFR_COMPOUNDED_INDEX("USD_SOFR_Compounded_Index", "USD-SOFR Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Compounded_Index", displayName = "USD-SOFR ICE Compounded Index") USD_SOFR_ICE_COMPOUNDED_INDEX("USD_SOFR_ICE_Compounded_Index", "USD-SOFR ICE Compounded Index"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Compounded_Index_0_Floor", displayName = "USD-SOFR ICE Compounded Index 0 Floor") USD_SOFR_ICE_COMPOUNDED_INDEX_0_FLOOR("USD_SOFR_ICE_Compounded_Index_0_Floor", "USD-SOFR ICE Compounded Index 0 Floor"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Compounded_Index_0_Floor_2D_Lag", displayName = "USD-SOFR ICE Compounded Index 0 Floor 2D Lag") USD_SOFR_ICE_COMPOUNDED_INDEX_0_FLOOR_2_D_LAG("USD_SOFR_ICE_Compounded_Index_0_Floor_2D_Lag", "USD-SOFR ICE Compounded Index 0 Floor 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Compounded_Index_0_Floor_5D_Lag", displayName = "USD-SOFR ICE Compounded Index 0 Floor 5D Lag") USD_SOFR_ICE_COMPOUNDED_INDEX_0_FLOOR_5_D_LAG("USD_SOFR_ICE_Compounded_Index_0_Floor_5D_Lag", "USD-SOFR ICE Compounded Index 0 Floor 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Compounded_Index_2D_Lag", displayName = "USD-SOFR ICE Compounded Index 2D Lag") USD_SOFR_ICE_COMPOUNDED_INDEX_2_D_LAG("USD_SOFR_ICE_Compounded_Index_2D_Lag", "USD-SOFR ICE Compounded Index 2D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Compounded_Index_5D_Lag", displayName = "USD-SOFR ICE Compounded Index 5D Lag") USD_SOFR_ICE_COMPOUNDED_INDEX_5_D_LAG("USD_SOFR_ICE_Compounded_Index_5D_Lag", "USD-SOFR ICE Compounded Index 5D Lag"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix and 2006 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Swap_Rate", displayName = "USD-SOFR ICE Swap Rate") USD_SOFR_ICE_SWAP_RATE("USD_SOFR_ICE_Swap_Rate", "USD-SOFR ICE Swap Rate"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_ICE_Term", displayName = "USD-SOFR ICE Term") USD_SOFR_ICE_TERM("USD_SOFR_ICE_Term", "USD-SOFR ICE Term"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_SOFR_OIS_Compound", displayName = "USD-SOFR-OIS Compound") USD_SOFR_OIS_COMPOUND("USD_SOFR_OIS_Compound", "USD-SOFR-OIS Compound"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Swap_Rate_BCMP1", displayName = "USD Swap Rate-BCMP1") USD_SWAP_RATE_BCMP_1("USD_Swap_Rate_BCMP1", "USD Swap Rate-BCMP1"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TBILL_Auction_High_Rate", displayName = "USD-TBILL Auction High Rate") USD_TBILL_AUCTION_HIGH_RATE("USD_TBILL_Auction_High_Rate", "USD-TBILL Auction High Rate"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TBILL_H_15", displayName = "USD-TBILL-H.15") USD_TBILL_H_15("USD_TBILL_H_15", "USD-TBILL-H.15"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TBILL_H_15_Bloomberg", displayName = "USD-TBILL-H.15-Bloomberg") USD_TBILL_H_15_BLOOMBERG("USD_TBILL_H_15_Bloomberg", "USD-TBILL-H.15-Bloomberg"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TBILL_Secondary_Market", displayName = "USD-TBILL-Secondary Market") USD_TBILL_SECONDARY_MARKET("USD_TBILL_Secondary_Market", "USD-TBILL-Secondary Market"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TBILL_Secondary_Market_Bond_Equivalent_Yield", displayName = "USD-TBILL Secondary Market-Bond Equivalent Yield") USD_TBILL_SECONDARY_MARKET_BOND_EQUIVALENT_YIELD("USD_TBILL_Secondary_Market_Bond_Equivalent_Yield", "USD-TBILL Secondary Market-Bond Equivalent Yield"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TIBOR_ISDC", displayName = "USD-TIBOR-ISDC") USD_TIBOR_ISDC("USD_TIBOR_ISDC", "USD-TIBOR-ISDC"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_TIBOR_Reference_Banks", displayName = "USD-TIBOR-Reference Banks") USD_TIBOR_REFERENCE_BANKS("USD_TIBOR_Reference_Banks", "USD-TIBOR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_19901_3_00_ICAP", displayName = "USD-Treasury-19901-3:00-ICAP") USD_TREASURY_19901_3_00_ICAP("USD_Treasury_19901_3_00_ICAP", "USD-Treasury-19901-3:00-ICAP"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_Rate_BCMP1", displayName = "USD Treasury Rate-BCMP1") USD_TREASURY_RATE_BCMP_1("USD_Treasury_Rate_BCMP1", "USD Treasury Rate-BCMP1"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_Rate_ICAP_BrokerTec", displayName = "USD-Treasury Rate-ICAP BrokerTec") USD_TREASURY_RATE_ICAP_BROKER_TEC("USD_Treasury_Rate_ICAP_BrokerTec", "USD-Treasury Rate-ICAP BrokerTec"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_Rate_SwapMarker100", displayName = "USD-Treasury Rate-SwapMarker100") USD_TREASURY_RATE_SWAP_MARKER_100("USD_Treasury_Rate_SwapMarker100", "USD-Treasury Rate-SwapMarker100"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_Rate_SwapMarker99", displayName = "USD-Treasury Rate-SwapMarker99") USD_TREASURY_RATE_SWAP_MARKER_99("USD_Treasury_Rate_SwapMarker99", "USD-Treasury Rate-SwapMarker99"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_Rate_T19901", displayName = "USD-Treasury Rate-T19901") USD_TREASURY_RATE_T_19901("USD_Treasury_Rate_T19901", "USD-Treasury Rate-T19901"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "USD_Treasury_Rate_T500", displayName = "USD-Treasury Rate-T500") USD_TREASURY_RATE_T_500("USD_Treasury_Rate_T500", "USD-Treasury Rate-T500"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "VND_Semi_Annual_Swap_Rate_11_00_BGCANTOR", displayName = "VND-Semi-Annual Swap Rate-11:00-BGCANTOR") VND_SEMI_ANNUAL_SWAP_RATE_11_00_BGCANTOR("VND_Semi_Annual_Swap_Rate_11_00_BGCANTOR", "VND-Semi-Annual Swap Rate-11:00-BGCANTOR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "VND_Semi_Annual_Swap_Rate_Reference_Banks", displayName = "VND-Semi-Annual Swap Rate-Reference Banks") VND_SEMI_ANNUAL_SWAP_RATE_REFERENCE_BANKS("VND_Semi_Annual_Swap_Rate_Reference_Banks", "VND-Semi-Annual Swap Rate-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_DEPOSIT_Reference_Banks", displayName = "ZAR-DEPOSIT-Reference Banks") ZAR_DEPOSIT_REFERENCE_BANKS("ZAR_DEPOSIT_Reference_Banks", "ZAR-DEPOSIT-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_DEPOSIT_SAFEX", displayName = "ZAR-DEPOSIT-SAFEX") ZAR_DEPOSIT_SAFEX("ZAR_DEPOSIT_SAFEX", "ZAR-DEPOSIT-SAFEX"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_JIBAR", displayName = "ZAR-JIBAR") ZAR_JIBAR("ZAR_JIBAR", "ZAR-JIBAR"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_JIBAR_Reference_Banks", displayName = "ZAR-JIBAR-Reference Banks") ZAR_JIBAR_REFERENCE_BANKS("ZAR_JIBAR_Reference_Banks", "ZAR-JIBAR-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_JIBAR_SAFEX", displayName = "ZAR-JIBAR-SAFEX") ZAR_JIBAR_SAFEX("ZAR_JIBAR_SAFEX", "ZAR-JIBAR-SAFEX"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_Prime_Average_1", displayName = "ZAR-Prime Average") ZAR_PRIME_AVERAGE_1("ZAR_Prime_Average_1", "ZAR-Prime Average"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_PRIME_AVERAGE", displayName = "ZAR-PRIME-AVERAGE") ZAR_PRIME_AVERAGE("ZAR_PRIME_AVERAGE", "ZAR-PRIME-AVERAGE"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_PRIME_AVERAGE_Reference_Banks", displayName = "ZAR-PRIME-AVERAGE-Reference Banks") ZAR_PRIME_AVERAGE_REFERENCE_BANKS("ZAR_PRIME_AVERAGE_Reference_Banks", "ZAR-PRIME-AVERAGE-Reference Banks"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_Quarterly_Swap_Rate_1_00_TRADITION", displayName = "ZAR-Quarterly Swap Rate-1:00-TRADITION") ZAR_QUARTERLY_SWAP_RATE_1_00_TRADITION("ZAR_Quarterly_Swap_Rate_1_00_TRADITION", "ZAR-Quarterly Swap Rate-1:00-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_Quarterly_Swap_Rate_5_30_TRADITION", displayName = "ZAR-Quarterly Swap Rate-5:30-TRADITION") ZAR_QUARTERLY_SWAP_RATE_5_30_TRADITION("ZAR_Quarterly_Swap_Rate_5_30_TRADITION", "ZAR-Quarterly Swap Rate-5:30-TRADITION"),
	
	/**
	 * Per 2006 ISDA Definitions or Annex to the 2000 ISDA Definitions, Section 7.1 Rate Options, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_Quarterly_Swap_Rate_TRADITION_Reference_Banks", displayName = "ZAR-Quarterly Swap Rate-TRADITION-Reference Banks") ZAR_QUARTERLY_SWAP_RATE_TRADITION_REFERENCE_BANKS("ZAR_Quarterly_Swap_Rate_TRADITION_Reference_Banks", "ZAR-Quarterly Swap Rate-TRADITION-Reference Banks"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_ZARONIA", displayName = "ZAR-ZARONIA") ZAR_ZARONIA("ZAR_ZARONIA", "ZAR-ZARONIA"),
	
	/**
	 * Per 2021 ISDA Interest Rate Derivatives Definitions Floating Rate Matrix, as amended through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ZAR_ZARONIA_OIS_Compound", displayName = "ZAR-ZARONIA-OIS Compound") ZAR_ZARONIA_OIS_COMPOUND("ZAR_ZARONIA_OIS_Compound", "ZAR-ZARONIA-OIS Compound")
;
	private static Map<String, FloatingRateIndexEnum> values;
	static {
        Map<String, FloatingRateIndexEnum> map = new ConcurrentHashMap<>();
		for (FloatingRateIndexEnum instance : FloatingRateIndexEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FloatingRateIndexEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FloatingRateIndexEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FloatingRateIndexEnum fromDisplayName(String name) {
		FloatingRateIndexEnum value = values.get(name);
		if (value == null) {
			throw new IllegalArgumentException("No enum constant with display name \"" + name + "\".");
		}
		return value;
	}

	@Override
	public String toString() {
		return toDisplayString();
	}

	public String toDisplayString() {
		return displayName != null ?  displayName : rosettaName;
	}
}
