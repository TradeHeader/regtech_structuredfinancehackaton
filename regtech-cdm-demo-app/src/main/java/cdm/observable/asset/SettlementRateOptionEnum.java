package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the settlement rate options as specified in the Annex A to the 1998 FX and Currency Options Definitions.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/settlement-rate-option"
 *
 * Provision 
 *
 */
@RosettaEnum("SettlementRateOptionEnum")
public enum SettlementRateOptionEnum {

	/**
	 * The Spot Rate for a Rate Calculation Date will be the Argentine Peso/U.S. Dollar Specified Rate, expressed as the amount of Argentine Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Buenos Aires and New York) which appears on the Reuters Screen BNAR Page at the close of business in Buenos Aires on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "ARS_BNAR_ARS01", displayName = "ARS.BNAR/ARS01") ARS_BNAR_ARS01("ARS_BNAR_ARS01", "ARS.BNAR/ARS01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Argentine Peso/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Argentine Pesos per one U.S. Dollar, for settlement on the same day, as published on EMTA&#39;s web site (www.emta.org) at approximately 1:00 p.m. (Buenos Aires time), or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA ARS Indicative Survey Methodology (which means a methodology, dated as of January 2, 2003, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Argentine Peso/U.S. Dollar markets for the purpose of determining the EMTA ARS Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "ARS_EMTA_INDICATIVE_SURVEY_RATE_ARS04", displayName = "ARS.EMTA.INDICATIVE.SURVEY.RATE/ARS04") ARS_EMTA_INDICATIVE_SURVEY_RATE_ARS04("ARS_EMTA_INDICATIVE_SURVEY_RATE_ARS04", "ARS.EMTA.INDICATIVE.SURVEY.RATE/ARS04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Argentine Peso/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Argentine Pesos per one U.S. Dollar, for settlement on the same day, as published on EMTA&#39;s web site (www.emta.org) at approximately 1:00 p.m. (Buenos Aires time), or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA ARS Industry Survey Methodology (which means a methodology, dated as of January 2, 2003, as amended from time to time, for a centralized industry-wide survey of financial institutions in Buenos Aires that are active participants in the Argentine Peso/U.S. Dollar spot markets for the purpose of determining the EMTA ARS Industry Survey Rate).
	 */
	@RosettaEnumValue(value = "ARS_EMTA_INDUSTRY_SURVEY_RATE_ARS03", displayName = "ARS.EMTA.INDUSTRY.SURVEY.RATE/ARS03") ARS_EMTA_INDUSTRY_SURVEY_RATE_ARS03("ARS_EMTA_INDUSTRY_SURVEY_RATE_ARS03", "ARS.EMTA.INDUSTRY.SURVEY.RATE/ARS03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the volume weighted average Argentine Peso/U.S. Dollar Rate of all trades executed in the electronic market for a Rate Calculation Day expressed as the amount of Argentine Pesos per one U.S. Dollar, for settlement on the same day, reported by the Mercado Abierto Electronico (the &#39;MAE&#39;) at approximately 3:00 pm, Buenos Aires time, and published on the FOREX-MAE Page as the &#39;PPN&#39; rate (&#39;Promedio Ponderado Noticiado&#39;) on www.mae.com.ar on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "ARS_MAE_ARS05", displayName = "ARS.MAE/ARS05") ARS_MAE_ARS05("ARS_MAE_ARS05", "ARS.MAE/ARS05"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Argentine Peso/U.S. Dollar offered rate for U.S. Dollars, expressed as the amount of Argentine Pesos per one U.S. Dollar, for settlement on the same day quoted by Banco de la Nacion (in accordance with the Convertibility Law of March 27, 1991 and Regulatory Decree No. 529/91 of April 1, 1991, as may be amended from time to time) for that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "ARS_OFFICIAL_RATE_ARS02", displayName = "ARS.OFFICIAL.RATE/ARS02") ARS_OFFICIAL_RATE_ARS02("ARS_OFFICIAL_RATE_ARS02", "ARS.OFFICIAL.RATE/ARS02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar Specified Rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) which appears on the Reuters Screen BRBY Page under the caption &#39;INTBK FLTING (LAST)&#39; at approximately 11:00 a.m., Sao Paulo time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_BRBY_BRL01", displayName = "BRL.BRBY/BRL01") BRL_BRBY_BRL01("BRL_BRBY_BRL01", "BRL.BRBY/BRL01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days, as published on EMTA&#39;s web site (www.emta.org) at approximately 12:00 p.m. (Sao Paulo time), or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA BRL Indicative Survey Methodology (which means a methodology, dated as of March 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Brazilian Real/U.S. Dollar markets for the purpose of determining the EMTA BRL Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "BRL_EMTA_INDICATIVE_SURVEY_RATE_BRL13", displayName = "BRL.EMTA.INDICATIVE.SURVEY.RATE/BRL13") BRL_EMTA_INDICATIVE_SURVEY_RATE_BRL13("BRL_EMTA_INDICATIVE_SURVEY_RATE_BRL13", "BRL.EMTA.INDICATIVE.SURVEY.RATE/BRL13"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days, as published on EMTA&#39;s web site (www.emta.org) at approximately 3:45 p.m. (Sao Paulo time), or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA BRL Industry Survey Methodology (which means a methodology, dated as of March 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions in Brazil that are active participants in the Brazilian Real/U.S. Dollar spot markets for the purpose of determining the EMTA BRL Industry Survey Rate).
	 */
	@RosettaEnumValue(value = "BRL_EMTA_INDUSTRY_SURVEY_RATE_BRL12", displayName = "BRL.EMTA.INDUSTRY.SURVEY.RATE/BRL12") BRL_EMTA_INDUSTRY_SURVEY_RATE_BRL12("BRL_EMTA_INDUSTRY_SURVEY_RATE_BRL12", "BRL.EMTA.INDUSTRY.SURVEY.RATE/BRL12"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar Specified Rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil in the &#39;Diario Oficial da Uniao&#39; on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_OFFICIAL_RATE_BRL02", displayName = "BRL.OFFICIAL.RATE/BRL02") BRL_OFFICIAL_RATE_BRL02("BRL_OFFICIAL_RATE_BRL02", "BRL.OFFICIAL.RATE/BRL02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar commercial rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil on SISBACEN Data System under transaction code PCOT- 390, Option 3, at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_PCOT_COMMERCIAL_BRL03", displayName = "BRL.PCOT-COMMERCIAL/BRL03") BRL_PCOT_COMMERCIAL_BRL03("BRL_PCOT_COMMERCIAL_BRL03", "BRL.PCOT-COMMERCIAL/BRL03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar floating rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil on SISBACEN Data System under transaction code PCOT- 390, Option 3, at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_PCOT_FLOATING_BRL04", displayName = "BRL.PCOT-FLOATING/BRL04") BRL_PCOT_FLOATING_BRL04("BRL_PCOT_FLOATING_BRL04", "BRL.PCOT-FLOATING/BRL04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar offered rate for U.S. Dollars, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days reported by the Banco Central do Brasil on SISBACEN Data System under transaction code PTAX-800 (&#39;Consulta de Cambio&#39; or Exchange Rate Inquiry), Option 5 (&#39;Cotacoes para Contabilidade&#39; or &#39;Rates for Accounting Purposes&#39;) by approximately 6:00 p.m., Sao Paulo time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_PTAX_BRL09", displayName = "BRL.PTAX/BRL09") BRL_PTAX_BRL09("BRL_PTAX_BRL09", "BRL.PTAX/BRL09"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar commercial rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil on SISBACEN Data System under transaction code PTAX- 800 (&#39;Consultas de Cambio&#39; or Exchange Rate Inquiry), Option 5 (&#39;Cotacoes para Contabilidad&#39; or Rates for Accounting Purposes) market type &#39;L&#39; (corresponding to U.S. Dollars traded in the foreign exchange market segment officially denominated &#39;Livre&#39; and commonly known as &#39;Comercial&#39;) as of 7:30 p.m., Sao Paulo time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_PTAX_COMMERCIAL_BRL05", displayName = "BRL.PTAX-COMMERCIAL/BRL05") BRL_PTAX_COMMERCIAL_BRL05("BRL_PTAX_COMMERCIAL_BRL05", "BRL.PTAX-COMMERCIAL/BRL05"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar commercial rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil which appears on the Reuters Screen BRFR Page at PTAX-800 as of 11:00 a.m., Sao Paulo time, on the first Business Day following that Rate Calculation Date. 23
	 */
	@RosettaEnumValue(value = "BRL_PTAX_COMMERCIAL_BRFR_BRL06", displayName = "BRL.PTAX-COMMERCIAL.BRFR/BRL06") BRL_PTAX_COMMERCIAL_BRFR_BRL06("BRL_PTAX_COMMERCIAL_BRFR_BRL06", "BRL.PTAX-COMMERCIAL.BRFR/BRL06"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar floating rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil on SISBACEN Data System under transaction code PTAX- 800 (&#39;Consultas de Cambio&#39; or Exchange Rate Inquiry), Option 5 (&#39;Cotacoes para Contabilidad&#39; or Rates for Accounting Purposes) market type &#39;F&#39; (corresponding to U.S. Dollars traded in the foreign exchange market segment officially denominated &#39;Flutuante&#39;) as of 7:30 p.m., Sao Paulo time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_PTAX_FLOATING_BRL07", displayName = "BRL.PTAX-FLOATING/BRL07") BRL_PTAX_FLOATING_BRL07("BRL_PTAX_FLOATING_BRL07", "BRL.PTAX-FLOATING/BRL07"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Brazilian Real/U.S. Dollar floating rate, expressed as the amount of Brazilian Reais per one U.S. Dollar, for settlement in two Business Days (where such days are Business Days in both Sao Paulo and New York) reported by the Banco Central do Brasil on the SISBACEN Data System which appears on the Reuters Screen BRFR Page at PTAX-800 as of 11:00 a.m., Sao Paulo time, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "BRL_PTAX_FLOATING_BRFR_BRL08", displayName = "BRL.PTAX-FLOATING.BRFR/BRL08") BRL_PTAX_FLOATING_BRFR_BRL08("BRL_PTAX_FLOATING_BRFR_BRL08", "BRL.PTAX-FLOATING.BRFR/BRL08"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar observado rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) reported by the Banco Central de Chile which appears on the Reuters Screen BCCH Page under the caption &#39;OBSERVADO&#39; at 10:00 a.m., Santiago time, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_BCCH_CLP01", displayName = "CLP.BCCH/CLP01") CLP_BCCH_CLP01("CLP_BCCH_CLP01", "CLP.BCCH/CLP01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar informal rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) of the informal exchange market which appears on the Reuters Screen CHILD Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_CHILD_INFORMAL_CLP02", displayName = "CLP.CHILD-INFORMAL/CLP02") CLP_CHILD_INFORMAL_CLP02("CLP_CHILD_INFORMAL_CLP02", "CLP.CHILD-INFORMAL/CLP02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar interbank rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) reported by the Banco Central de Chile for the formal exchange market which appears on the Reuters Screen CHILD Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_CHILD_INTERBANK_CLP03", displayName = "CLP.CHILD-INTERBANK/CLP03") CLP_CHILD_INTERBANK_CLP03("CLP_CHILD_INTERBANK_CLP03", "CLP.CHILD-INTERBANK/CLP03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar observado rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) reported by the Banco Central de Chile which appears on the Reuters Screen CHILD Page on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_CHILD_OBSERVADO_CLP04", displayName = "CLP.CHILD-OBSERVADO/CLP04") CLP_CHILD_OBSERVADO_CLP04("CLP_CHILD_OBSERVADO_CLP04", "CLP.CHILD-OBSERVADO/CLP04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar informal rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) of the informal exchange market which appears on the Reuters Screen CHILG Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_CHILG_INFORMAL_CLP05", displayName = "CLP.CHILG-INFORMAL/CLP05") CLP_CHILG_INFORMAL_CLP05("CLP_CHILG_INFORMAL_CLP05", "CLP.CHILG-INFORMAL/CLP05"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar interbank rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) reported by the Banco Central de Chile for the formal exchange market which appears on the Reuters Screen CHILG Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_CHILG_INTERBANK_CLP06", displayName = "CLP.CHILG-INTERBANK/CLP06") CLP_CHILG_INTERBANK_CLP06("CLP_CHILG_INTERBANK_CLP06", "CLP.CHILG-INTERBANK/CLP06"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar observado rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) reported by the Banco Central de Chile which appears on the Reuters Screen CHILG Page under &#39;OBSERVADO&#39; at the Specified Time, if any, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_CHILG_OBSERVADO_CLP07", displayName = "CLP.CHILG-OBSERVADO/CLP07") CLP_CHILG_OBSERVADO_CLP07("CLP_CHILG_OBSERVADO_CLP07", "CLP.CHILG-OBSERVADO/CLP07"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar &#39;observado&#39; rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement in one Business Day reported by the Banco Central de Chile (www.bcentral.cl) as the &#39;Dolar Observado&#39; (Dollar Observado) rate by not later than 10:30 a.m., Santiago time, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_DOLAR_OBS_CLP10", displayName = "CLP.DOLAR.OBS/CLP10") CLP_DOLAR_OBS_CLP10("CLP_DOLAR_OBS_CLP10", "CLP.DOLAR.OBS/CLP10"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day, as published on EMTA&#39;s web site (www.emta.org) at approximately 11:00 a.m., Santiago time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA CLP Indicative Survey Methodology (which means a methodology, dated as of August 1, 2006, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Chilean Peso/U.S. Dollar markets for the purpose of determining the EMTA CLP Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "CLP_EMTA_INDICATIVE_SURVEY_RATE_CLP11", displayName = "CLP.EMTA.INDICATIVE.SURVEY.RATE/CLP11") CLP_EMTA_INDICATIVE_SURVEY_RATE_CLP11("CLP_EMTA_INDICATIVE_SURVEY_RATE_CLP11", "CLP.EMTA.INDICATIVE.SURVEY.RATE/CLP11"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar Specified Rate, expressed as the amount of Chilean Pesos per one U.S. Dollar (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York), calculated in accordance with Title I, Chapter 1 Number 6 of the Compendium of International Exchange Norms of the Banco Central de Chile and published by the Banco Central de Chile at the Specified Time, if any, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_OFFICIAL_RATE_CLP08", displayName = "CLP.OFFICIAL.RATE/CLP08") CLP_OFFICIAL_RATE_CLP08("CLP_OFFICIAL_RATE_CLP08", "CLP.OFFICIAL.RATE/CLP08"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chilean Peso/U.S. Dollar observado rate, expressed as the amount of Chilean Pesos per one U.S. Dollar, for settlement on the same day (or, if such day is not a Business Day in New York, for settlement on the first succeeding day that is a Business Day in both Santiago and New York) reported by the Banco Central de Chile which appears on the Telerate Page 38942 opposite the caption &#39;Observado&#39; at the Specified Time, if any, on the first Business Day following the Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CLP_TELERATE_38942_CLP09", displayName = "CLP.TELERATE.38942/CLP09") CLP_TELERATE_38942_CLP09("CLP_TELERATE_38942_CLP09", "CLP.TELERATE.38942/CLP09"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chinese Renminbi/U.S. Dollar official fixing rate, expressed as the amount of Chinese Renminbi per one U.S. Dollar, for settlement in two Business Days reported by the People&#39;s Bank of China, Beijing, People&#39;s Republic of China, which appears on the Reuters Screen &#39;SAEC&#39; Page opposite the symbol &#39;USDCNY=&#39; at approximately 9:15 a.m., Beijing time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "CNY_SAEC_CNY01", displayName = "CNY.SAEC/CNY01") CNY_SAEC_CNY01("CNY_SAEC_CNY01", "CNY.SAEC/CNY01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Chinese Renminbi/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Chinese Renminbi per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m. (Singapore time), or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC CNY Indicative Survey Methodology (which means a methodology, dated as of December 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Chinese Renminbi/U.S. Dollar markets for the purpose of determining the SFEMC CNY Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "CNY_SFEMC_INDICATIVE_SURVEY_RATE_CNY02", displayName = "CNY.SFEMC.INDICATIVE.SURVEY.RATE/CNY02") CNY_SFEMC_INDICATIVE_SURVEY_RATE_CNY02("CNY_SFEMC_INDICATIVE_SURVEY_RATE_CNY02", "CNY.SFEMC.INDICATIVE.SURVEY.RATE/CNY02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Colombian Peso/U.S. Dollar fixing rate, expressed as the amount of Colombian Pesos per one U.S. Dollar, for settlement on the same day (unless such day is not a Business Day in New York, then for settlement on the first succeeding day that is a Business Day in Bogota and New York) reported by the Colombian Banking Superintendency which appears on the Reuters Screen CO/COL03 Page opposite the caption &#39;TRCM&#39; (&#39;Tasa de Cierre Representative del Mercado&#39; or closing market price) at 12:00 noon, Bogota time, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "COP_CO_COL03_COP01", displayName = "COP.CO/COL03/COP01") COP_CO_COL03_COP01("COP_CO_COL03_COP01", "COP.CO/COL03/COP01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Colombian Peso/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Colombian Pesos per one U.S. Dollar, for settlement on the same day, as published on EMTA&#39;s web site (www.emta.org) at approximately 11:30 a.m., Bogota time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA COP Indicative Survey Methodology (which means a methodology, dated as of August 1, 2006, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Colombian Peso/U.S. Dollar markets for the purpose of determining the EMTA COP Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "COP_EMTA_INDICATIVE_SURVEY_RATE_COP03", displayName = "COP.EMTA.INDICATIVE.SURVEY.RATE/COP03") COP_EMTA_INDICATIVE_SURVEY_RATE_COP03("COP_EMTA_INDICATIVE_SURVEY_RATE_COP03", "COP.EMTA.INDICATIVE.SURVEY.RATE/COP03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Colombian Peso/U.S. Dollar fixing rate, expressed as the amount of Colombian Pesos per one U.S. Dollar, for settlement on the same day reported by the Colombian Financial Superintendency (www.banrep.gov.co) as the &#39;Tasa Representativa del Mercado (TRM)&#39; (also referred to as the &#39;Tasa de Cambio Representativa del Mercado&#39; (TCRM)) by not later than 10:30 a.m., Bogota time, on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "COP_TRM_COP02", displayName = "COP.TRM/COP02") COP_TRM_COP02("COP_TRM_COP02", "COP.TRM/COP02"),
	
	/**
	 * the Spot Rate for a Rate Calculation Date will be the Reference Currency/U.S. Dollar exchange rate, expressed as the amount of Reference Currency per one U.S. Dollar, determined on the basis of quotations provided by Reference Dealers on that Rate Calculation Date of that day&#39;s price of a Specified Company&#39;s American Depositary Receipt or American Depositary Receipts (the &#39;ADR&#39; or &#39;ADRs&#39;, as appropriate) and the price of the local share or shares of such Specified Company of the same type and in the same quantity represented by such ADR or ADRs, as the case may be (the &#39;Share&#39; or &#39;Shares&#39;, as appropriate). The Calculation Agent will request each of the Reference Dealers to provide a firm quotation of (A) in the case where one ADR represents less than one Share, its bid and offer price (in the Reference Currency) for one Share and its bid and offer price (in U.S. Dollars) for the number of ADRs which represent such Share and (B) in all other cases, its bid and offer price (in the Reference Currency) for the Share or Shares, as the case may be, and its bid and offer price (in U.S. Dollars) for one ADR. If one or more quotations are provided, the rate for a Rate Calculation Date will equal the ratio of (1) the arithmetic mean of the midpoint of the bid and offer prices quoted in the Reference Currency by each Reference Dealer for such Share or Shares, as the case may be, and (2) the arithmetic mean of the midpoint of the bid and offer prices quoted in U.S. Dollars by each Reference Dealer for such ADR or ADRs, as the case may be, subject to an adjustment, if any, by the Calculation Agent to reduce the effect of momentary disparities in the prices of the Share or Shares and the ADR or ADRs, as appropriate. The quotations used to determine the Spot Rate for a Rate Calculation Date will be determined in each case at the Specified Time on the Rate Calculation Date or, if no such time is specified, the time chosen by the Calculation Agent.
	 */
	@RosettaEnumValue(value = "CURRENCY_IMPLIED_RATE__ADR__CURA1", displayName = "CURRENCY-IMPLIED.RATE.(ADR)/CURA1") CURRENCY_IMPLIED_RATE__ADR__CURA1("CURRENCY_IMPLIED_RATE__ADR__CURA1", "CURRENCY-IMPLIED.RATE.(ADR)/CURA1"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Reference Currency/Settlement Currency exchange rate, expressed as the amount of Reference Currency per one unit of Settlement Currency, determined on the basis of quotations provided by Reference Dealers on that Rate Calculation Date for that day&#39;s price of Local Assets. The Calculation Agent will request each of the Reference Dealers to provide a firm quotation of its bid and offer price (in both the Reference Currency and the Settlement Currency) for an amount of Local Assets whose face value equals the Specified Amount. If one or more quotations are provided, the rate for a Rate Calculation Date will equal the ratio of (A) the arithmetic mean of the midpoint of the bid and offer prices quoted in the Reference Currency by each Reference Dealer for such Local Assets and (B) the arithmetic mean of the midpoint of the bid and offer prices quoted in the Settlement Currency by each Reference Dealer for such Local Assets. The quotations used to determine the Spot Rate for a Rate Calculation Date will be determined in each case at the Specified Time on the Rate Calculation Date or, if no such time is specified, the time chosen by the Calculation Agent.
	 */
	@RosettaEnumValue(value = "CURRENCY_IMPLIED_RATE__LOCAL_ASSET__CURA2", displayName = "CURRENCY-IMPLIED.RATE.(LOCAL.ASSET)/CURA2") CURRENCY_IMPLIED_RATE__LOCAL_ASSET__CURA2("CURRENCY_IMPLIED_RATE__LOCAL_ASSET__CURA2", "CURRENCY-IMPLIED.RATE.(LOCAL.ASSET)/CURA2"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Reference Currency/Settlement Currency Specified Rate, expressed as the amount of the Reference Currency per one unit of Settlement Currency, for settlement on the Settlement Date agreed upon by the parties on or prior to that Rate Calculation Date (or, if different, the day on which rates for that date would, in the ordinary course, be published or announced).
	 */
	@RosettaEnumValue(value = "CURRENCY_MUTUAL_AGREEMENT_CURA3", displayName = "CURRENCY-MUTUAL.AGREEMENT/CURA3") CURRENCY_MUTUAL_AGREEMENT_CURA3("CURRENCY_MUTUAL_AGREEMENT_CURA3", "CURRENCY-MUTUAL.AGREEMENT/CURA3"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be determined on the basis of quotations provided by Reference Dealers on that Rate Calculation Date of that day&#39;s Specified Rate, expressed as the amount of Reference Currency per one unit of Settlement Currency, for settlement on the Settlement Date. The Calculation Agent will request the Specified Office of each of the Reference Dealers to provide a firm quotation of its Specified Rate for a transaction where the amount of Reference Currency equals the Specified Amount. If four quotations are provided, the rate for a Rate Calculation Date will be the arithmetic mean of the Specified Rates, without regard to the Specified Rates having the highest and lowest value. If exactly three quotations are provided, the rate for a Rate Calculation Date will be the Specified Rate provided by the Reference Dealer that remains after disregarding the Specified Rates having the highest and lowest values. For this purpose, if more than one quotation has the same highest value or lowest value, then the Specified Rate of one of such quotations shall be disregarded. If exactly two quotations are provided, the rate for a Rate Calculation Date will be the arithmetic mean of the Specified Rates. If only one quotation is provided, the rate for a Rate Calculation Date will be the Specified Rate quoted by that Reference Dealer. The quotations used to determine the Spot Rate for a Rate Calculation Date will be determined in each case at the Specified Time on that Rate Calculation Date or, if no such time is specified, the time chosen by the Calculation Agent.
	 */
	@RosettaEnumValue(value = "CURRENCY_REFERENCE_DEALERS_CURA4", displayName = "CURRENCY-REFERENCE.DEALERS/CURA4") CURRENCY_REFERENCE_DEALERS_CURA4("CURRENCY_REFERENCE_DEALERS_CURA4", "CURRENCY-REFERENCE.DEALERS/CURA4"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be determined by the Calculation Agent on the basis of that day&#39;s Specified Rate, expressed as the amount of Reference Currency per one unit of Settlement Currency, in a legal and customary wholesale market in which there is no, or minimal, Governmental Authority controls or interference, except as a participant in such market.
	 */
	@RosettaEnumValue(value = "CURRENCY_WHOLESALE_MARKET_CURA5", displayName = "CURRENCY-WHOLESALE.MARKET/CURA5") CURRENCY_WHOLESALE_MARKET_CURA5("CURRENCY_WHOLESALE_MARKET_CURA5", "CURRENCY-WHOLESALE.MARKET/CURA5"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Ecuadorian Sucre/U.S. Dollar Specified Rate, expressed as the amount of Ecuadorian Sucres per one U.S. Dollar, for settlement in one Business Day (where such day is a Business Day in Guayaquil and New York) which appears on Reuters Screen DNRP Page at 12:00 noon, Guayaquil time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "ECS_DNRP_ECS01", displayName = "ECS.DNRP/ECS01") ECS_DNRP_ECS01("ECS_DNRP_ECS01", "ECS.DNRP/ECS01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indonesian Rupiah/U.S. Dollar spot rate at 11:00 a.m., Singapore time, expressed as the amount of Indonesian Rupiah per one U.S. Dollar, for settlement in two Business Days, reported by the Association of Banks in Singapore which appears on the Telerate Page 50157 to the right of the caption &#39;Spot&#39; under the column &#39;IDR&#39; at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "IDR_ABS_IDR01", displayName = "IDR.ABS/IDR01") IDR_ABS_IDR01("IDR_ABS_IDR01", "IDR.ABS/IDR01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indonesian Rupiah/U.S. Dollar weighted average spot rate in the interbank market based on traded IDR/USD spot foreign exchange transactions during a specified time period which are captured on a real time basis, expressed as the amount of Indonesian Rupiah per one U.S. Dollar, for settlement in two Business Days, published by Bank Indonesia at approximately 10:00 a.m., Jakarta time, on that Rate Calculation Date as the Jakarta Interbank Spot Dollar Rate USD - IDR on Bank Indonesia&#39;s website or otherwise made available by Bank Indonesia (or its successor as administrator).
	 */
	@RosettaEnumValue(value = "IDR_JISDOR_IDR04", displayName = "IDR.JISDOR/IDR04") IDR_JISDOR_IDR04("IDR_JISDOR_IDR04", "IDR.JISDOR/IDR04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indonesian Rupiah/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Indonesian Rupiah per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m., Singapore time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC IDR Indicative Survey Methodology (which means a methodology, dated as of December 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Indonesian Rupiah/U.S. Dollar markets for the purpose of determining the SFEMC IDR Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "IDR_SFEMC_INDICATIVE_SURVEY_RATE_IDR02", displayName = "IDR.SFEMC.INDICATIVE.SURVEY.RATE/IDR02") IDR_SFEMC_INDICATIVE_SURVEY_RATE_IDR02("IDR_SFEMC_INDICATIVE_SURVEY_RATE_IDR02", "IDR.SFEMC.INDICATIVE.SURVEY.RATE/IDR02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indonesian Rupiah/U.S. Dollar implied spot rate expressed as the amount of Indonesian Rupiah per one U.S. Dollar, for settlement in two Business Days, reported by ABS Benchmarks Administration Co Pte. Ltd. (or its successor as administrator or sponsor of that rate), which appears on Thomson Reuters Screen ABSFIX01 Page at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "IDR_VWAP_IDR03", displayName = "IDR.VWAP/IDR03") IDR_VWAP_IDR03("IDR_VWAP_IDR03", "IDR.VWAP/IDR03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Israeli Shekel/U.S. Dollar Specified Rate, expressed as the amount of Israeli Shekels per one U.S. Dollar, for settlement in two Business Days which appears on the Reuters Screen BOIJ Page as of 1:00 p.m., Tel Aviv time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "ILS_BOIJ_ILS01", displayName = "ILS.BOIJ/ILS01") ILS_BOIJ_ILS01("ILS_BOIJ_ILS01", "ILS.BOIJ/ILS01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Israeli Shekel/U.S. Dollar Specified Rate, expressed as the amount of Israeli Shekels per one U.S. Dollar, for settlement in two Business Days which appears on the Reuters Screen FXIL Page as of 1:00 p.m., Tel Aviv time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "ILS_FXIL_ILS02", displayName = "ILS.FXIL/ILS02") ILS_FXIL_ILS02("ILS_FXIL_ILS02", "ILS.FXIL/ILS02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indian Rupee/U.S. Dollar reference rate, expressed as the amount of Indian Rupee per one U.S. Dollar, for settlement in two Business Days, reported by Financial Benchmarks India Pvt. Ltd. (www.fbil.org.in) at approximately 1:30 p.m., Mumbai time, or as soon thereafter as practicable, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "INR_FBIL_INR01", displayName = "INR.FBIL/INR01") INR_FBIL_INR01("INR_FBIL_INR01", "INR.FBIL/INR01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indian Rupee/U.S. Dollar reference rate, expressed as the amount of Indian Rupee per one U.S. Dollar, for settlement in two Business Days reported by the Reserve Bank of India which appears on the Reuters Screen RBIB Page at approximately 12:30 p.m., Mumbai time, or as soon thereafter as practicable, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "INR_RBIB_INR01", displayName = "INR.RBIB/INR01") INR_RBIB_INR01("INR_RBIB_INR01", "INR.RBIB/INR01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Indian Rupee/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Indian Rupee per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m. (Singapore time), or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC INR Indicative Survey Methodology (which means a methodology, dated as of December 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Indian Rupee/U.S. Dollar markets for the purpose of determining the SFEMC INR Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "INR_SFEMC_INDICATIVE_SURVEY_RATE_INR02", displayName = "INR.SFEMC.INDICATIVE.SURVEY.RATE/INR02") INR_SFEMC_INDICATIVE_SURVEY_RATE_INR02("INR_SFEMC_INDICATIVE_SURVEY_RATE_INR02", "INR.SFEMC.INDICATIVE.SURVEY.RATE/INR02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Korean Won/U.S. Dollar Specified Rate, expressed as the amount of Korean Won per one U.S. Dollar, for settlement in two Business Days which appears on the Reuters Screen KEBEY Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "KRW_KEBEY_KRW01", displayName = "KRW.KEBEY/KRW01") KRW_KEBEY_KRW01("KRW_KEBEY_KRW01", "KRW.KEBEY/KRW01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Korean Won/U.S. Dollar market average rate, expressed as the amount of Korean Won per one U.S. Dollar, for settlement in two Business Days reported by the Korea Financial Telecommunications and Clearing Corporation which appears on the Reuters Screen KFTC18 Page to the right of the caption &#39;USD Today&#39; that is available at approximately 3:30 p.m., Seoul time, on the Rate Calculation Date or as soon thereafter as practicable.
	 */
	@RosettaEnumValue(value = "KRW_KFTC18_KRW02", displayName = "KRW.KFTC18/KRW02") KRW_KFTC18_KRW02("KRW_KFTC18_KRW02", "KRW.KFTC18/KRW02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Korean Won/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Korean Won per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m., Singapore time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC KRW Indicative Survey Methodology (which means a methodology, dated as of December 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Korean Won/U.S. Dollar markets for the purpose of determining the SFEMC KRW Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "KRW_SFEMC_INDICATIVE_SURVEY_RATE_KRW04", displayName = "KRW.SFEMC.INDICATIVE.SURVEY.RATE/KRW04") KRW_SFEMC_INDICATIVE_SURVEY_RATE_KRW04("KRW_SFEMC_INDICATIVE_SURVEY_RATE_KRW04", "KRW.SFEMC.INDICATIVE.SURVEY.RATE/KRW04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Korean Won/U.S. Dollar market average rate, expressed as the amount of Korean Won per one U.S. Dollar, for settlement in two Business Days reported by the Korea Financial Telecommunications and Clearing Corporation which appears on Telerate Page 45644 to the right of the caption &#39;USD Today&#39; that is available at approximately 3:30 p.m., Seoul time, on the Rate Calculation Date or as soon thereafter as practicable.
	 */
	@RosettaEnumValue(value = "KRW_TELERATE_45644_KRW03", displayName = "KRW.TELERATE.45644/KRW03") KRW_TELERATE_45644_KRW03("KRW_TELERATE_45644_KRW03", "KRW.TELERATE.45644/KRW03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Kazakhstan Tenge / U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Kazakhstan Tenge per one U.S. Dollar, for settlement on the same Business Day, as published on EMTA&#39;s website (www.emta.org) at approximately 1:00 p.m., Almaty time, or as soon thereafter as practicable, on that Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA KZT Indicative Survey Methodology (which means a methodology, dated as of March 16, 2009, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Kazakhstan Tenge/U.S. Dollar markets for the purpose of determining the EMTA KZT Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "KZT_EMTA_INDICATIVE_SURVEY_RATE_KZT02", displayName = "KZT.EMTA.INDICATIVE.SURVEY.RATE/KZT02") KZT_EMTA_INDICATIVE_SURVEY_RATE_KZT02("KZT_EMTA_INDICATIVE_SURVEY_RATE_KZT02", "KZT.EMTA.INDICATIVE.SURVEY.RATE/KZT02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Kazakhstan Tenge / U.S. Dollar weighted average rate, expressed as the amount of Kazakhstan Tenge per one U.S. Dollar, for settlement on the same Business Day reported by the Kazakhstan Stock Exchange (www.kase.kz) at approximately 11:00 am, Almaty time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "KZT_KASE_KZT01", displayName = "KZT.KASE/KZT01") KZT_KASE_KZT01("KZT_KASE_KZT01", "KZT.KASE/KZT01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Lebanese Pound/U.S. Dollar Specified Rate, expressed as the amount of Lebanese Pounds per one U.S. Dollar, for settlement in two Business Days which appears on the Reuters Screen BDLX Page as of 12:00 noon, Beirut time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "LBP_BDLX_LBP01", displayName = "LBP.BDLX/LBP01") LBP_BDLX_LBP01("LBP_BDLX_LBP01", "LBP.BDLX/LBP01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Moroccan Dirham/U.S. Dollar Specified Rate, expressed as the amount of Moroccan Dirham per one U.S. Dollar, for settlement in two Business Days reported by the Central Bank of Morocco as of 1:00 p.m., Rabat time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MAD_OFFICIAL_RATE_MAD01", displayName = "MAD.OFFICIAL.RATE/MAD01") MAD_OFFICIAL_RATE_MAD01("MAD_OFFICIAL_RATE_MAD01", "MAD.OFFICIAL.RATE/MAD01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Mexican Pesos/U.S. Dollar Specified rate, expressed as the amount of Mexican Pesos per one U.S. Dollar, for settlement in two Business Days reported by Banco de Mexico which appears on the Reuters Screen BNMX Page opposite the caption &#39;Fix&#39; at the close of business in Mexico City on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MXP_BNMX_MXP01", displayName = "MXP.BNMX/MXP01") MXP_BNMX_MXP01("MXP_BNMX_MXP01", "MXP.BNMX/MXP01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Mexican Peso/U.S. Dollar fixing rate, expressed as the amount of Mexican Pesos per one U.S. Dollar, for settlement in two Business Days which is published by Banco de Mexico in the Official Gazette of the Federation pursuant to the &#39;Disposiciones aplicables a la determinacion del tipo de Cambio para solventar obligaciones denominadas en moneda extranjera pagaderas en la Republica Mexicana&#39; (Rules applicable to determine the exchange rate to pay obligations denominated in foreign currency payable in Mexico) on the first Business Day following that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MXP_FIXING_RATE_MXP02", displayName = "MXP.FIXING.RATE/MXP02") MXP_FIXING_RATE_MXP02("MXP_FIXING_RATE_MXP02", "MXP.FIXING.RATE/MXP02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Mexican Peso/U.S. Dollar fixing rate, expressed as the amount of Mexican Pesos per one U.S. Dollar, for settlement in two Business Days reported by Banco de Mexico which appears on Reuters Screen MEX01 Page under the heading &#39;MXNFIX=RR&#39;, at the close of business in Mexico City on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MXP_MEX01_MXP03", displayName = "MXP.MEX01/MXP03") MXP_MEX01_MXP03("MXP_MEX01_MXP03", "MXP.MEX01/MXP03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Mexican Peso/U.S. Dollar fixing rate, expressed as the amount of Mexican Pesos per one U.S. Dollar, for settlement in two Business Days which is published by the Bolsa Mexicana de Valores, S.A. de C.V. (as established in Section 2 of the &#39;Resolution concerning the exchange rate applicable for calculating the Mexican Peso equivalent of principal and interest of Mexican Treasury Notes denominated in foreign currency and payable in Mexican Pesos&#39; published in the Diario Oficial de la Federacion on November 11, 1991) in the Movimiento Diario del Mercado de Valores de la Bolsa Mexicana de Valores, S.A. de C.V. under the heading &#39;Movimiento Diario del Mercado de Valores&#39; on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MXP_PUBLISHED_MXP04", displayName = "MXP.PUBLISHED/MXP04") MXP_PUBLISHED_MXP04("MXP_PUBLISHED_MXP04", "MXP.PUBLISHED/MXP04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Malaysian Ringgit/U.S. Dollar spot rate at 11:00 a.m., Singapore time, expressed as the amount of Malaysian Ringgit per one U.S. Dollar, for settlement in two Business Days, reported by the Association of Banks in Singapore, which appears on the Telerate Page 50157 to the right of the caption &#39;Spot&#39; under the column &#39;MYR&#39; at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MYR_ABS_MYR01", displayName = "MYR.ABS/MYR01") MYR_ABS_MYR01("MYR_ABS_MYR01", "MYR.ABS/MYR01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Malaysian Ringgit/U.S. Dollar reference rate, expressed as the amount of Malaysian Ringgit per one U.S. Dollar, for settlement in two Business Days, calculated and reported by Bank Negara Malaysia as its Kuala Lumpur USD/MYR Reference Rate, which appears on Thomson Reuters Screen MYRFIX2 Page at approximately 3:30 p.m., Kuala Lumpur time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MYR_KL_REF_MYR04", displayName = "MYR.KL.REF/MYR04") MYR_KL_REF_MYR04("MYR_KL_REF_MYR04", "MYR.KL.REF/MYR04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Malaysian Ringgit/U.S. Dollar spot rate expressed as the amount of Malaysian Ringgit per one U.S. Dollar, for settlement in two Business Days, reported by Persatuan Pasaran Kewangan Malaysia (ACI - Malaysia), which appears on Thomson Reuters Screen MYRFIX2 Page at approximately 11:10 a.m., Kuala Lumpur time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "MYR_PPKM_MYR03", displayName = "MYR.PPKM/MYR03") MYR_PPKM_MYR03("MYR_PPKM_MYR03", "MYR.PPKM/MYR03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Malaysian Ringgit/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Malaysian Ringgit per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m., Singapore time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC MYR Indicative Survey Methodology (which means a methodology, dated as of July 15, 2005, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Malaysian Ringgit/U.S. Dollar markets for the purpose of determining the SFEMC MYR Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "MYR_SFEMC_INDICATIVE_SURVEY_RATE_MYR02", displayName = "MYR.SFEMC.INDICATIVE.SURVEY.RATE/MYR02") MYR_SFEMC_INDICATIVE_SURVEY_RATE_MYR02("MYR_SFEMC_INDICATIVE_SURVEY_RATE_MYR02", "MYR.SFEMC.INDICATIVE.SURVEY.RATE/MYR02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Peruvian Sol/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Peruvian Soles per one U.S. Dollar, for settlement on the same day, as published on EMTA&#39;s web site (www.emta.org) at approximately 11:00 a.m., Lima time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA PEN Indicative Survey Methodology (which means a methodology, dated as of August 1, 2006, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Peruvian Sol/U.S. Dollar markets for the purpose of determining the EMTA PEN Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "PEN_EMTA_INDICATIVE_SURVEY_RATE_PEN04", displayName = "PEN.EMTA.INDICATIVE.SURVEY.RATE/PEN04") PEN_EMTA_INDICATIVE_SURVEY_RATE_PEN04("PEN_EMTA_INDICATIVE_SURVEY_RATE_PEN04", "PEN.EMTA.INDICATIVE.SURVEY.RATE/PEN04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Peruvian Sol/U.S. Dollar average exchange rate in the interbank market expressed as the amount of Peruvian New Soles per one U.S. Dollar for settlement on the same day reported by the Banco Central de Reserva del Peru (www.bcrp.gob.pe) as the &#39;Tipo de Cambio Interbancario Promedio&#39; at approximately 2:00 p.m., Lima time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PEN_INTERBANK_AVE_PEN05", displayName = "PEN.INTERBANK.AVE/PEN05") PEN_INTERBANK_AVE_PEN05("PEN_INTERBANK_AVE_PEN05", "PEN.INTERBANK.AVE/PEN05"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Peruvian Sol/U.S. Dollar fixing rate (mid market last), expressed as the amount of Peruvian Sols per one U.S. Dollar, for settlement on that same day which appears on the Reuters Screen PDSB Page opposite the caption &#39;PEN=&#39; as of 12:00 noon, Lima time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PEN_PDSB_PEN01", displayName = "PEN.PDSB/PEN01") PEN_PDSB_PEN01("PEN_PDSB_PEN01", "PEN.PDSB/PEN01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the midpoint of the Peruvian Sol/U.S. Dollar closing weighted average bid and offer (&#39;compra y venta&#39;) exchange rates expressed as the amount of Peruvian New Soles per one U.S. Dollar for settlement on the same day, reported by the Superintendencia de Banca, Seguros y AFP (www.sbs.gob.pe) of the Republic of Peru at approximately 5:00 p.m., Lima time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PEN_WT_AVE_PEN03", displayName = "PEN.WT.AVE/PEN03") PEN_WT_AVE_PEN03("PEN_WT_AVE_PEN03", "PEN.WT.AVE/PEN03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Philippine Peso/U.S. Dollar morning weighted average rate for that Rate Calculation Date, expressed as the amount of Philippine Pesos per one U.S. Dollar, for settlement in one Business Day, sponsored by Bankers Association of the Philippines (www.bap.org.ph) as its &#39;BAP AM Weighted Average Rate&#39; at approximately 11:30 a.m., Manila time, or as soon thereafter as practicable, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PHP_BAPPESO_PHP06", displayName = "PHP.BAPPESO/PHP06") PHP_BAPPESO_PHP06("PHP_BAPPESO_PHP06", "PHP.BAPPESO/PHP06"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Philippine Peso/U.S. Dollar morning weighted average rate for that Rate Calculation Date, expressed as the amount of Philippine Pesos per one U.S. Dollar, for settlement in one Business Day reported by the Philippine Dealing System PDEX which appears on the Reuters Screen PDSPESO Page to the right of the caption &#39;AM WT AVE&#39; at approximately 11:30 a.m., Manila time, or as soon thereafter as practicable, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PHP_PDSPESO_PHP06", displayName = "PHP.PDSPESO/PHP06") PHP_PDSPESO_PHP06("PHP_PDSPESO_PHP06", "PHP.PDSPESO/PHP06"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Philippine Peso/U.S. Dollar tom rate (mid market), expressed as the amount of Philippine Pesos per one U.S. Dollar, for settlement in one Business Day which appears on the Reuters Screen PHPESO Page at approximately 11:00 a.m., Manila time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PHP_PHPESO_PHP01", displayName = "PHP.PHPESO/PHP01") PHP_PHPESO_PHP01("PHP_PHPESO_PHP01", "PHP.PHPESO/PHP01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Philippine Peso/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Philippine Pesos per one U.S. Dollar, for settlement in one Business Day, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m., Singapore time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC PHP Indicative Survey Methodology (which means a methodology, dated as of December 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Philippine Peso/U.S. Dollar markets for the purpose of determining the SFEMC PHP Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "PHP_SFEMC_INDICATIVE_SURVEY_RATE_PHP05", displayName = "PHP.SFEMC.INDICATIVE.SURVEY.RATE/PHP05") PHP_SFEMC_INDICATIVE_SURVEY_RATE_PHP05("PHP_SFEMC_INDICATIVE_SURVEY_RATE_PHP05", "PHP.SFEMC.INDICATIVE.SURVEY.RATE/PHP05"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Philippine Peso/U.S. Dollar tom rate (mid market), expressed as the amount of Philippine Pesos per one U.S. Dollar, for settlement in one Business Day which appears on the Telerate Page 15439 at approximately 11:00 a.m., Manila time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PHP_TELERATE_15439_PHP03", displayName = "PHP.TELERATE.15439/PHP03") PHP_TELERATE_15439_PHP03("PHP_TELERATE_15439_PHP03", "PHP.TELERATE.15439/PHP03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Philippine Peso/U.S. Dollar Specified Rate, expressed as the amount of Philippine Pesos per one U.S. Dollar, for settlement in one Business Day which appears on the Telerate Page 2920 at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PHP_TELERATE_2920_PHP02", displayName = "PHP.TELERATE.2920/PHP02") PHP_TELERATE_2920_PHP02("PHP_TELERATE_2920_PHP02", "PHP.TELERATE.2920/PHP02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Pakistani Rupee/U.S. Dollar reference rate expressed as the amount of Pakistani Rupees per one U.S. Dollar, for settlement in two Business Days reported by the State Bank of Pakistan (www.sbp.org.pk) at approximately 2:30 pm, Karachi time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PKR_SBPK_PKR01", displayName = "PKR.SBPK/PKR01") PKR_SBPK_PKR01("PKR_SBPK_PKR01", "PKR.SBPK/PKR01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Pakistani Rupee/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Pakistani Rupees per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m. Singapore time, or as soon thereafter as practicable, on that Rate Calculation Date. The Spot Rate shall be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC PKR Indicative Survey Methodology (which means a methodology, dated as of July 14, 2008, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Pakistani Rupee/U.S. Dollar markets for the purpose of determining the SFEMC PKR Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "PKR_SFEMC_INDICATIVE_SURVEY_RATE_PKR02", displayName = "PKR.SFEMC.INDICATIVE.SURVEY.RATE/PKR02") PKR_SFEMC_INDICATIVE_SURVEY_RATE_PKR02("PKR_SFEMC_INDICATIVE_SURVEY_RATE_PKR02", "PKR.SFEMC.INDICATIVE.SURVEY.RATE/PKR02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Polish Zloty/U.S. Dollar Specified Rate, expressed as the amount of Polish Zloty per one U.S. Dollar, for settlement in two Business Days reported by the National Bank of Poland which appears on the Reuters Screen NBPQ Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PLZ_NBPQ_PLZ01", displayName = "PLZ.NBPQ/PLZ01") PLZ_NBPQ_PLZ01("PLZ_NBPQ_PLZ01", "PLZ.NBPQ/PLZ01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Polish Zloty/U.S. Dollar fixing rate, expressed as the amount of Polish Zloty per one U.S. Dollar, for settlement in two Business Days reported by the National Bank of Poland which appears on the Reuters Screen NBPR Page at the Specified Time, if any, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "PLZ_NBPR_PLZ02", displayName = "PLZ.NBPR/PLZ02") PLZ_NBPR_PLZ02("PLZ_NBPR_PLZ02", "PLZ.NBPR/PLZ02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Russian Ruble/U.S. Dollar Specified Rate, expressed as the amount of Russian Rubles per one U.S. Dollar, for settlement in one Business Day, calculated by the Chicago Mercantile Exchange (&#39;CME&#39;) and as published on CME&#39;s website, which appears on the Reuters Screen EMTA Page, at approximately 1:30 p.m., Moscow time, on that Rate Calculation Date. The Spot Rate shall be calculated by the CME pursuant to the Chicago Mercantile Exchange / EMTA, Inc. Daily Russian Ruble Per U.S. Dollar Reference Rate Methodology (which means a methodology, effective as of June 16, 2005, as amended from time to time, for a centralized industry-wide survey of financial institutions in Russia that are active participants in the Russian Ruble/U.S. Dollar spot market for the purpose of determining the RUB CME-EMTA Rate).
	 */
	@RosettaEnumValue(value = "RUB_CME_EMTA_RUB03", displayName = "RUB.CME-EMTA/RUB03") RUB_CME_EMTA_RUB03("RUB_CME_EMTA_RUB03", "RUB.CME-EMTA/RUB03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Russian Ruble/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Russian Rubles per one U.S. Dollar, for settlement in one Business Day, as published on EMTA&#39;s web site (www.emta.org) at approximately 2:45 p.m., Moscow time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA RUB Indicative Survey Methodology (which means a methodology dated as of June 16, 2005, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Russian Ruble/U.S. Dollar spot market for the purpose of determining the EMTA RUB Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "RUB_EMTA_INDICATIVE_SURVEY_RATE_RUB04", displayName = "RUB.EMTA.INDICATIVE.SURVEY.RATE/RUB04") RUB_EMTA_INDICATIVE_SURVEY_RATE_RUB04("RUB_EMTA_INDICATIVE_SURVEY_RATE_RUB04", "RUB.EMTA.INDICATIVE.SURVEY.RATE/RUB04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Russian Ruble/U.S. Dollar Specified Rate, expressed as the amount of Russian Rubies per one U.S. Dollar, for settlement on the same day reported by the Moscow Interbank Currency Exchange which appears on the Reuters Screen MICEXFRX Page as of 10:30 a.m., Moscow time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "RUB_MICEXFRX_RUB01", displayName = "RUB.MICEXFRX/RUB01") RUB_MICEXFRX_RUB01("RUB_MICEXFRX_RUB01", "RUB.MICEXFRX/RUB01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Russian Ruble/U.S. Dollar Specified Rate, expressed as the amount of Russian Rubies per one U.S. Dollar, for settlement on the same day reported by the Moscow Interbank Currency Exchange which appears on the Reuters Screen MMVB Page as of 10:30 a.m., Moscow time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "RUB_MMVB_RUB02", displayName = "RUB.MMVB/RUB02") RUB_MMVB_RUB02("RUB_MMVB_RUB02", "RUB.MMVB/RUB02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Singapore Dollar/U.S. Dollar spot rate expressed as the amount of Singapore Dollar per one U.S. Dollar for settlement in two Business Days, reported by ABS Benchmarks Administration Co Pte. Ltd. (or its successor as administrator or sponsor of the rate), which appears on Thomson Reuters Screen ABSFIX01 Page at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "SGD_VWAP_SGD3", displayName = "SGD.VWAP/SGD3") SGD_VWAP_SGD3("SGD_VWAP_SGD3", "SGD.VWAP/SGD3"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Slovak Koruna/U.S. Dollar Specified Rate, expressed as the amount of Slovak Koruna per one U.S. Dollar, for settlement in two Business Days reported by the National Bank of Slovakia which appears on the Reuters Screen NBSB Page as of 11:40 a.m., Bratislava time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "SKK_NBSB_SKK01", displayName = "SKK.NBSB/SKK01") SKK_NBSB_SKK01("SKK_NBSB_SKK01", "SKK.NBSB/SKK01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Thai Baht/U.S. Dollar spot rate at 11:00 a.m., Singapore time, expressed as the amount of Thai Bhaht per one U.S. Dollar, for settlement in two Business Days, reported by the Association of Banks in Singapore which appears on the Reuters Screen ABSIRFIX01 Page to the right of the caption &#39;Spot&#39; under the column &#39;THB&#39; at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "THB_ABS_THB01", displayName = "THB.ABS/THB01") THB_ABS_THB01("THB_ABS_THB01", "THB.ABS/THB01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Thai Baht / U.S. Dollar spot rate expressed as the amount of Thai Baht per one U.S. Dollar for settlement in two Business Days, reported by ABS Benchmarks Administration Co Pte. Ltd. (or its successor as administrator or sponsor of the rate), which appears on Thomson Reuters Screen ABSFIX01 Page at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "THB_VWAP_THB01", displayName = "THB.VWAP/THB01") THB_VWAP_THB01("THB_VWAP_THB01", "THB.VWAP/THB01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Taiwanese Dollar/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Taiwanese Dollars per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m., Singapore time, or as soon thereafter as practicable, on such Rate Calculation Date. The Spot Rate will be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC TWD Indicative Survey Methodology (which means a methodology, dated as of December 1, 2004, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Taiwanese Dollar/U.S. Dollar markets for the purpose of determining the SFEMC TWD Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "TWD_SFEMC_INDICATIVE_SURVEY_RATE_TWD04", displayName = "TWD.SFEMC.INDICATIVE.SURVEY.RATE/TWD04") TWD_SFEMC_INDICATIVE_SURVEY_RATE_TWD04("TWD_SFEMC_INDICATIVE_SURVEY_RATE_TWD04", "TWD.SFEMC.INDICATIVE.SURVEY.RATE/TWD04"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Taiwanese Dollar/U.S. Dollar spot rate, expressed as the amount of Taiwanese Dollars per one U.S. Dollar, for settlement in two Business Days, reported by the Taipei Forex Inc. which appears on the Reuters Screen TAIFX1 Page under the heading &#39;Spot&#39; as of 11:00 a.m. Taipei time, on that Rate Calculation Date, or if no rate appears as of 11:00 a.m., Taipei time, the rate that first appears in any of the next succeeding 15 minute intervals after such time, up to and including 12:00 noon, Taipei time on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "TWD_TAIFX1_TWD03", displayName = "TWD.TAIFX1/TWD03") TWD_TAIFX1_TWD03("TWD_TAIFX1_TWD03", "TWD.TAIFX1/TWD03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Taiwanese Dollar/U.S. Dollar spot rate, expressed as the amount of Taiwanese Dollars per one U.S. Dollar, for settlement in two Business Days, reported by the Taipei Forex Inc. which appears on the Telerate Page 6161 under the heading &#39;Spot&#39; as of 11:00 a.m., Taipei time, on that Rate Calculation Date, or if no rate appears as of 11:00 a.m., Taipei time, the rate that first appears in any of the next succeeding 15 minute intervals after such time, up to and including 12:00 noon, Taipei time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "TWD_TELERATE_6161_TWD01", displayName = "TWD.TELERATE.6161/TWD01") TWD_TELERATE_6161_TWD01("TWD_TELERATE_6161_TWD01", "TWD.TELERATE.6161/TWD01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Taiwanese Dollar/U.S. Dollar Specified Rate, expressed as the amount of Taiwanese Dollars per one U.S. Dollar, for settlement in two Business Days which appears on the Reuters Screen TFEMA Page as of 11:00 a.m., Taipei time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "TWD_TFEMA_TWD02", displayName = "TWD.TFEMA/TWD02") TWD_TFEMA_TWD02("TWD_TFEMA_TWD02", "TWD.TFEMA/TWD02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Ukrainian Hryvnia/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Ukrainian Hryvnia per one U.S. Dollar, for settlement on the same Business Day, as published on EMTA&#39;s website (www.emta.org) at approximately 2:00 p.m., Kiev time, or as soon thereafter as practicable, on that Rate Calculation Date. The Spot Rate shall be calculated by EMTA (or a service provider EMTA may select in its sole discretion) pursuant to the EMTA UAH Indicative Survey Methodology (which means a methodology, dated as of March 16, 2009, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Ukrainian Hryvnia / U.S. Dollar markets for the purpose of determining the EMTA UAH Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "UAH_EMTA_INDICATIVE_SURVEY_RATE_UAH03", displayName = "UAH.EMTA.INDICATIVE.SURVEY.RATE/UAH03") UAH_EMTA_INDICATIVE_SURVEY_RATE_UAH03("UAH_EMTA_INDICATIVE_SURVEY_RATE_UAH03", "UAH.EMTA.INDICATIVE.SURVEY.RATE/UAH03"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Ukrainian Hryvnia/U.S. Dollar Specified Rate for U.S. Dollars expressed as the amount of Ukrainian Hryvnia per one U.S. Dollar, for settlement on the same Business Day calculated by Thomson Reuters pursuant to the EMTA UAH Industry Survey Methodology, which rate appears on EMTA&#39;s website (www.emta.org) and on Thomson Reuters Page EMTAUAHFIX at approximately 11:30 am, Kiev time, on that Rate Calculation Date. The &#39;EMTA UAH Industry Survey Methodology&#39; as used herein means the methodology dated as of March 16, 2009, for a centralized industry wide survey of financial institutions in the Ukrainian Hryvnia/U.S. Dollar spot market for the purposes of determining the EMTA UAH Industry Survey Rate.
	 */
	@RosettaEnumValue(value = "UAH_EMTA_INDUSTRY_SURVEY_RATE_UAH02", displayName = "UAH.EMTA.INDUSTRY.SURVEY.RATE/UAH02") UAH_EMTA_INDUSTRY_SURVEY_RATE_UAH02("UAH_EMTA_INDUSTRY_SURVEY_RATE_UAH02", "UAH.EMTA.INDUSTRY.SURVEY.RATE/UAH02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Ukrainian Hryvnia/U.S. Dollar spot rate, expressed as the amount of Ukrainian Hryvnia per one U.S. Dollar, for settlement on the same Business Day reported by GFI Brokers on Thomson Reuters Page GFIU by 9:30 am, London time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "UAH_GFI_UAH01", displayName = "UAH.GFI/UAH01") UAH_GFI_UAH01("UAH_GFI_UAH01", "UAH.GFI/UAH01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the midpoint of the Venezuelan Bolivar /U.S. Dollar Tipo de Cambio De Referencia buying and selling rates, expressed as the amount of Venezuelan Bolivar per one U.S. Dollar, for settlement in two Business Days reported by the Banco Central de Venezuela (www.bcv.org.ve) at approximately 5:00 p.m., Caracas time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "VEF_FIX_VEF01", displayName = "VEF.FIX/VEF01") VEF_FIX_VEF01("VEF_FIX_VEF01", "VEF.FIX/VEF01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Vietnamese Dong/U.S. Dollar spot rate at 11:00 a.m., Singapore time, expressed as the amount of Vietnamese Dong per one U.S. Dollar, for settlement in two Business Days reported by the Association of Banks in Singapore, which appears on the Reuters Screen ABSIRFIX01 Page to the right of the caption &#39;Spot&#39; under the column &#39;VND&#39; at approximately 11:30 a.m., Singapore time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "VND_ABS_VND01", displayName = "VND.ABS/VND01") VND_ABS_VND01("VND_ABS_VND01", "VND.ABS/VND01"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Vietnamese Dong/U.S. Dollar spot rate expressed as the amount of Vietnamese Dong per one U.S. Dollar, for settlement in two Business Days which appears on Reuters Screen VNDFIX=VN Page under the caption &#39;Spot&#39; and to the right of the caption &#39;Average&#39; at approximately 11:00 am, Hanoi time, on that Rate Calculation Date.
	 */
	@RosettaEnumValue(value = "VND_FX_VND02", displayName = "VND.FX/VND02") VND_FX_VND02("VND_FX_VND02", "VND.FX/VND02"),
	
	/**
	 * The Spot Rate for a Rate Calculation Date will be the Vietnamese Dong/U.S. Dollar Specified Rate for U.S. Dollars, expressed as the amount of Vietnamese Dong per one U.S. Dollar, for settlement in two Business Days, as published on SFEMC&#39;s website (www.sfemc.org) at approximately 3:30 p.m., Singapore time, or as soon as thereafter as practicable, on that Rate Calculation Date. The Spot Rate shall be calculated by SFEMC (or a service provider SFEMC may select in its sole discretion) pursuant to the SFEMC VND Indicative Survey Methodology (which means a methodology, dated as of July 14, 2008, as amended from time to time, for a centralized industry-wide survey of financial institutions that are active participants in the Vietnamese Dong/U.S. Dollar markets for the purpose of determining the SFEMC VND Indicative Survey Rate).
	 */
	@RosettaEnumValue(value = "VND_SFEMC_INDICATIVE_SURVEY_RATE_VND03", displayName = "VND.SFEMC.INDICATIVE.SURVEY.RATE/VND03") VND_SFEMC_INDICATIVE_SURVEY_RATE_VND03("VND_SFEMC_INDICATIVE_SURVEY_RATE_VND03", "VND.SFEMC.INDICATIVE.SURVEY.RATE/VND03")
;
	private static Map<String, SettlementRateOptionEnum> values;
	static {
        Map<String, SettlementRateOptionEnum> map = new ConcurrentHashMap<>();
		for (SettlementRateOptionEnum instance : SettlementRateOptionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SettlementRateOptionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SettlementRateOptionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SettlementRateOptionEnum fromDisplayName(String name) {
		SettlementRateOptionEnum value = values.get(name);
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
