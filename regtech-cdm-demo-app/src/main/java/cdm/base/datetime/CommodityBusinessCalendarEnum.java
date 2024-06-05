package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/commodity-business-calendar"
 *
 * Provision 
 *
 */
@RosettaEnum("CommodityBusinessCalendarEnum")
public enum CommodityBusinessCalendarEnum {

	/**
	 * Abu Dhabi Securities Exchange https://www.adx.ae/
	 */
	@RosettaEnumValue(value = "ADSM") ADSM("ADSM"),
	
	/**
	 * Argus Media Fertilizer Reports. http://www.argusmedia.com/Fertilizer
	 */
	@RosettaEnumValue(value = "AGRUS_FMB", displayName = "AGRUS-FMB") AGRUS_FMB("AGRUS_FMB", "AGRUS-FMB"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "APPI") APPI("APPI"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ARGUS_CRUDE", displayName = "ARGUS-CRUDE") ARGUS_CRUDE("ARGUS_CRUDE", "ARGUS-CRUDE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ARGUS_EUROPEAN_GAS", displayName = "ARGUS-EUROPEAN-GAS") ARGUS_EUROPEAN_GAS("ARGUS_EUROPEAN_GAS", "ARGUS-EUROPEAN-GAS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ARGUS_EUROPEAN_PRODUCTS", displayName = "ARGUS-EUROPEAN-PRODUCTS") ARGUS_EUROPEAN_PRODUCTS("ARGUS_EUROPEAN_PRODUCTS", "ARGUS-EUROPEAN-PRODUCTS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ARGUS_INTERNATIONAL_LPG", displayName = "ARGUS-INTERNATIONAL-LPG") ARGUS_INTERNATIONAL_LPG("ARGUS_INTERNATIONAL_LPG", "ARGUS-INTERNATIONAL-LPG"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ARGUS_MCCLOSKEYS_COAL_REPORT", displayName = "ARGUS-MCCLOSKEYS-COAL-REPORT") ARGUS_MCCLOSKEYS_COAL_REPORT("ARGUS_MCCLOSKEYS_COAL_REPORT", "ARGUS-MCCLOSKEYS-COAL-REPORT"),
	
	/**
	 * The Argus US Products report. http://www.argusmedia.com/Petroleum/Petroleum-Products/Argus-US-Products
	 */
	@RosettaEnumValue(value = "ARGUS_US_PRODUCTS", displayName = "ARGUS-US-PRODUCTS") ARGUS_US_PRODUCTS("ARGUS_US_PRODUCTS", "ARGUS-US-PRODUCTS"),
	
	/**
	 * Australian Securities Exchange http://www.asx.com.au/
	 */
	@RosettaEnumValue(value = "ASX") ASX("ASX"),
	
	/**
	 * Australian Wheat Board. www.awb.com.au
	 */
	@RosettaEnumValue(value = "AWB") AWB("AWB"),
	
	/**
	 * Australian Wool Exchange. http://www.awex.com.au/home.html
	 */
	@RosettaEnumValue(value = "AWEX") AWEX("AWEX"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "BALTIC_EXCHANGE", displayName = "BALTIC-EXCHANGE") BALTIC_EXCHANGE("BALTIC_EXCHANGE", "BALTIC-EXCHANGE"),
	
	/**
	 * The business calendar of the Bank Negara Malaysia Policy Committee.
	 */
	@RosettaEnumValue(value = "BANK_NEGARA_MALAYSIA_POLICY_COMMITTEE", displayName = "BANK-NEGARA-MALAYSIA-POLICY-COMMITTEE") BANK_NEGARA_MALAYSIA_POLICY_COMMITTEE("BANK_NEGARA_MALAYSIA_POLICY_COMMITTEE", "BANK-NEGARA-MALAYSIA-POLICY-COMMITTEE"),
	
	/**
	 * The business calendar for the Belpex power exchange (www.belpex.be).
	 */
	@RosettaEnumValue(value = "BELPEX") BELPEX("BELPEX"),
	
	/**
	 * BlueNext Power Market.
	 */
	@RosettaEnumValue(value = "BLUENEXT") BLUENEXT("BLUENEXT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "BM_F", displayName = "BM&F") BM_F("BM_F", "BM&F"),
	
	/**
	 * The settlement business calendar for Bursa Malaysia.
	 */
	@RosettaEnumValue(value = "BURSA_MALAYSIA_SETTLEMENT", displayName = "BURSA-MALAYSIA-SETTLEMENT") BURSA_MALAYSIA_SETTLEMENT("BURSA_MALAYSIA_SETTLEMENT", "BURSA-MALAYSIA-SETTLEMENT"),
	
	/**
	 * The trading business calendar for Bursa Malaysia.
	 */
	@RosettaEnumValue(value = "BURSA_MALAYSIA_TRADING", displayName = "BURSA-MALAYSIA-TRADING") BURSA_MALAYSIA_TRADING("BURSA_MALAYSIA_TRADING", "BURSA-MALAYSIA-TRADING"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CANADIAN_GAS_PRICE_REPORTER", displayName = "CANADIAN-GAS-PRICE-REPORTER") CANADIAN_GAS_PRICE_REPORTER("CANADIAN_GAS_PRICE_REPORTER", "CANADIAN-GAS-PRICE-REPORTER"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CBOT_SOFT", displayName = "CBOT-SOFT") CBOT_SOFT("CBOT_SOFT", "CBOT-SOFT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CMAI_AROMATICS_MARKET_REPORT", displayName = "CMAI-AROMATICS-MARKET-REPORT") CMAI_AROMATICS_MARKET_REPORT("CMAI_AROMATICS_MARKET_REPORT", "CMAI-AROMATICS-MARKET-REPORT"),
	
	/**
	 * CMAI Global Plastics and Polymers Market Report. http://www.ihs.com/products/chemical/index.aspx?pu=1&amp;rd=cmai
	 */
	@RosettaEnumValue(value = "CMAI_GLOBAL_PLASTICS_AND_POLYMERS_MARKET_REPORT", displayName = "CMAI-GLOBAL-PLASTICS-AND-POLYMERS-MARKET-REPORT") CMAI_GLOBAL_PLASTICS_AND_POLYMERS_MARKET_REPORT("CMAI_GLOBAL_PLASTICS_AND_POLYMERS_MARKET_REPORT", "CMAI-GLOBAL-PLASTICS-AND-POLYMERS-MARKET-REPORT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CMAI_METHANOL_MARKET_REPORT", displayName = "CMAI-METHANOL-MARKET-REPORT") CMAI_METHANOL_MARKET_REPORT("CMAI_METHANOL_MARKET_REPORT", "CMAI-METHANOL-MARKET-REPORT"),
	
	/**
	 * CMAI Monomers Market Report. http://www.ihs.com/products/chemical/index.aspx?pu=1&amp;rd=cmai
	 */
	@RosettaEnumValue(value = "CMAI_MONOMERS_MARKET_REPORT", displayName = "CMAI-MONOMERS-MARKET-REPORT") CMAI_MONOMERS_MARKET_REPORT("CMAI_MONOMERS_MARKET_REPORT", "CMAI-MONOMERS-MARKET-REPORT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CME_DAIRY", displayName = "CME-DAIRY") CME_DAIRY("CME_DAIRY", "CME-DAIRY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CME_NON_DAIRY_SOFT", displayName = "CME-NON-DAIRY-SOFT") CME_NON_DAIRY_SOFT("CME_NON_DAIRY_SOFT", "CME-NON-DAIRY-SOFT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "COMEX") COMEX("COMEX"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CRU") CRU("CRU"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "CRU_LONG", displayName = "CRU-LONG") CRU_LONG("CRU_LONG", "CRU-LONG"),
	
	/**
	 * The business calendar for statistical publications by the by the United States Department of Energy (DOE).
	 */
	@RosettaEnumValue(value = "DEPARTMENT_OF_ENERGY", displayName = "DEPARTMENT-OF-ENERGY") DEPARTMENT_OF_ENERGY("DEPARTMENT_OF_ENERGY", "DEPARTMENT-OF-ENERGY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "DEWITT_BENZENE_DERIVATIVES", displayName = "DEWITT-BENZENE-DERIVATIVES") DEWITT_BENZENE_DERIVATIVES("DEWITT_BENZENE_DERIVATIVES", "DEWITT-BENZENE-DERIVATIVES"),
	
	/**
	 * Dubai Mercantile Exchange. http://www.dubaimerc.com/
	 */
	@RosettaEnumValue(value = "DME") DME("DME"),
	
	/**
	 * Dow Jones US Calendar. http://www.dowjones.com/
	 */
	@RosettaEnumValue(value = "DOW_JONES", displayName = "DOW-JONES") DOW_JONES("DOW_JONES", "DOW-JONES"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "DOW_JONES_ENERGY_SERVICE", displayName = "DOW-JONES-ENERGY-SERVICE") DOW_JONES_ENERGY_SERVICE("DOW_JONES_ENERGY_SERVICE", "DOW-JONES-ENERGY-SERVICE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "DowJonesPower") DOW_JONES_POWER("DowJonesPower"),
	
	/**
	 * European Energy Exchange-Coal
	 */
	@RosettaEnumValue(value = "EEX_COAL", displayName = "EEX-COAL") EEX_COAL("EEX_COAL", "EEX-COAL"),
	
	/**
	 * European Energy Exchange-Emissions Rights
	 */
	@RosettaEnumValue(value = "EEX_EMISSIONS", displayName = "EEX-EMISSIONS") EEX_EMISSIONS("EEX_EMISSIONS", "EEX-EMISSIONS"),
	
	/**
	 * European Energy Exchange-Gas
	 */
	@RosettaEnumValue(value = "EEX_GAS", displayName = "EEX-GAS") EEX_GAS("EEX_GAS", "EEX-GAS"),
	
	/**
	 * European Energy Exchange-Power
	 */
	@RosettaEnumValue(value = "EEX_POWER", displayName = "EEX-POWER") EEX_POWER("EEX_POWER", "EEX-POWER"),
	
	/**
	 * TBD.
	 */
	@RosettaEnumValue(value = "EURONEX_MATIF", displayName = "EURONEX-MATIF") EURONEX_MATIF("EURONEX_MATIF", "EURONEX-MATIF"),
	
	/**
	 * FERTECON Limited Information Services. http://fertecon.com/current_information_services.asp
	 */
	@RosettaEnumValue(value = "FERTECON") FERTECON("FERTECON"),
	
	/**
	 * Fertilizer Week. http://www.crugroup.com/market-analysis/products/fertilizerweek
	 */
	@RosettaEnumValue(value = "FERTILIZER_WEEK", displayName = "FERTILIZER-WEEK") FERTILIZER_WEEK("FERTILIZER_WEEK", "FERTILIZER-WEEK"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "GAS_DAILY", displayName = "GAS-DAILY") GAS_DAILY("GAS_DAILY", "GAS-DAILY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "GAS_DAILY_PRICE_GUIDE", displayName = "GAS-DAILY-PRICE-GUIDE") GAS_DAILY_PRICE_GUIDE("GAS_DAILY_PRICE_GUIDE", "GAS-DAILY-PRICE-GUIDE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "GLOBALCOAL") GLOBALCOAL("GLOBALCOAL"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "HEREN_REPORT", displayName = "HEREN-REPORT") HEREN_REPORT("HEREN_REPORT", "HEREN-REPORT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ICE_10X_DAILY", displayName = "ICE/10X-DAILY") ICE_10X_DAILY("ICE_10X_DAILY", "ICE/10X-DAILY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ICE_10X_MONTHLY", displayName = "ICE/10X-MONTHLY") ICE_10X_MONTHLY("ICE_10X_MONTHLY", "ICE/10X-MONTHLY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ICE_CANADA", displayName = "ICE-CANADA") ICE_CANADA("ICE_CANADA", "ICE-CANADA"),
	
	/**
	 * European Climate Exchange.
	 */
	@RosettaEnumValue(value = "ICE_ECX", displayName = "ICE-ECX") ICE_ECX("ICE_ECX", "ICE-ECX"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ICE_GAS", displayName = "ICE-GAS") ICE_GAS("ICE_GAS", "ICE-GAS"),
	
	/**
	 * The business calendar oil and refined product contracts on ICE Futures Europe.
	 */
	@RosettaEnumValue(value = "ICE_OIL", displayName = "ICE-OIL") ICE_OIL("ICE_OIL", "ICE-OIL"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "ICE_US_AGRICULTURAL", displayName = "ICE-US-AGRICULTURAL") ICE_US_AGRICULTURAL("ICE_US_AGRICULTURAL", "ICE-US-AGRICULTURAL"),
	
	/**
	 * The business calendar for publication of ICIS Benzene (Europe) data.
	 */
	@RosettaEnumValue(value = "ICIS_PRICING_BENZENE__EUROPE_", displayName = "ICIS-PRICING-BENZENE-(EUROPE)") ICIS_PRICING_BENZENE__EUROPE_("ICIS_PRICING_BENZENE__EUROPE_", "ICIS-PRICING-BENZENE-(EUROPE)"),
	
	/**
	 * The business calendar for publication of ICIS Ethylene (Europe) data.
	 */
	@RosettaEnumValue(value = "ICIS_PRICING_ETHYLENE__EUROPE_", displayName = "ICIS-PRICING-ETHYLENE-(EUROPE)") ICIS_PRICING_ETHYLENE__EUROPE_("ICIS_PRICING_ETHYLENE__EUROPE_", "ICIS-PRICING-ETHYLENE-(EUROPE)"),
	
	/**
	 * The business calendar for publication of ICIS Polyproylene (Europe) data.
	 */
	@RosettaEnumValue(value = "ICIS_PRICING_POLYPROPYLENE__EUROPE_", displayName = "ICIS-PRICING-POLYPROPYLENE-(EUROPE)") ICIS_PRICING_POLYPROPYLENE__EUROPE_("ICIS_PRICING_POLYPROPYLENE__EUROPE_", "ICIS-PRICING-POLYPROPYLENE-(EUROPE)"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "INSIDE_FERC", displayName = "INSIDE-FERC") INSIDE_FERC("INSIDE_FERC", "INSIDE-FERC"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "JAPAN_MOF_TSRR", displayName = "JAPAN-MOF-TSRR") JAPAN_MOF_TSRR("JAPAN_MOF_TSRR", "JAPAN-MOF-TSRR"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "KCBOT") KCBOT("KCBOT"),
	
	/**
	 * The banking business calendar in Kuala Lumpur.
	 */
	@RosettaEnumValue(value = "KUALA_LUMPUR_BANK", displayName = "KUALA-LUMPUR-BANK") KUALA_LUMPUR_BANK("KUALA_LUMPUR_BANK", "KUALA-LUMPUR-BANK"),
	
	/**
	 * The business calendar for the Labuan Bank (Malaysia).
	 */
	@RosettaEnumValue(value = "LABUAN_BANK", displayName = "LABUAN-BANK") LABUAN_BANK("LABUAN_BANK", "LABUAN-BANK"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "LIFFE_LONDON_SOFT", displayName = "LIFFE-LONDON-SOFT") LIFFE_LONDON_SOFT("LIFFE_LONDON_SOFT", "LIFFE-LONDON-SOFT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "LME") LME("LME"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "LONDON_BULLION_MARKET", displayName = "LONDON-BULLION-MARKET") LONDON_BULLION_MARKET("LONDON_BULLION_MARKET", "LONDON-BULLION-MARKET"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "LONDON_BULLION_MARKET_GOLD_A_M_ONLY", displayName = "LONDON-BULLION-MARKET-GOLD-A.M-ONLY") LONDON_BULLION_MARKET_GOLD_A_M_ONLY("LONDON_BULLION_MARKET_GOLD_A_M_ONLY", "LONDON-BULLION-MARKET-GOLD-A.M-ONLY"),
	
	/**
	 * The London Platinum and Palladium Market in London on which members quote prices for the buying and selling of Platinum and Palladium.
	 */
	@RosettaEnumValue(value = "LONDON_PLATINUM_PALLADIUM_MARKET", displayName = "LONDON-PLATINUM-PALLADIUM-MARKET") LONDON_PLATINUM_PALLADIUM_MARKET("LONDON_PLATINUM_PALLADIUM_MARKET", "LONDON-PLATINUM-PALLADIUM-MARKET"),
	
	/**
	 * Minneapolis Grain Exchange http://www.mgex.com/
	 */
	@RosettaEnumValue(value = "MGEX") MGEX("MGEX"),
	
	/**
	 * The business calendar for the N2EX UK power exchange (https://www.n2ex.com/aboutn2ex).
	 */
	@RosettaEnumValue(value = "N2EX") N2EX("N2EX"),
	
	/**
	 * NASDAQ-OMX (Formerly known as Nordpool). http://www.nasdaqomx.com/commodities
	 */
	@RosettaEnumValue(value = "NASDAQ_OMX", displayName = "NASDAQ-OMX") NASDAQ_OMX("NASDAQ_OMX", "NASDAQ-OMX"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NATURAL_GAS_WEEK", displayName = "NATURAL-GAS-WEEK") NATURAL_GAS_WEEK("NATURAL_GAS_WEEK", "NATURAL-GAS-WEEK"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Article XIV.
	 */
	@RosettaEnumValue(value = "NERC") NERC("NERC"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NGI") NGI("NGI"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NGX") NGX("NGX"),
	
	/**
	 * The Nuclear Market Review report as published by Trade tech. http://www.uranium.info/nuclear_market_review.php
	 */
	@RosettaEnumValue(value = "NUCLEAR_MARKET_REVIEW", displayName = "NUCLEAR-MARKET-REVIEW") NUCLEAR_MARKET_REVIEW("NUCLEAR_MARKET_REVIEW", "NUCLEAR-MARKET-REVIEW"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NYMEX_ELECTRICITY", displayName = "NYMEX-ELECTRICITY") NYMEX_ELECTRICITY("NYMEX_ELECTRICITY", "NYMEX-ELECTRICITY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NYMEX_GAS", displayName = "NYMEX-GAS") NYMEX_GAS("NYMEX_GAS", "NYMEX-GAS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NYMEX_NATURAL_GAS", displayName = "NYMEX-NATURAL-GAS") NYMEX_NATURAL_GAS("NYMEX_NATURAL_GAS", "NYMEX-NATURAL-GAS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "NYMEX_OIL", displayName = "NYMEX-OIL") NYMEX_OIL("NYMEX_OIL", "NYMEX-OIL"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "OFFICIAL_BOARD_MARKETS", displayName = "OFFICIAL-BOARD-MARKETS") OFFICIAL_BOARD_MARKETS("OFFICIAL_BOARD_MARKETS", "OFFICIAL-BOARD-MARKETS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "OPIS_LP_GAS", displayName = "OPIS-LP-GAS") OPIS_LP_GAS("OPIS_LP_GAS", "OPIS-LP-GAS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "OPIS_PROPANE", displayName = "OPIS-PROPANE") OPIS_PROPANE("OPIS_PROPANE", "OPIS-PROPANE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PAPER_PACKAGING_MONITOR", displayName = "PAPER-PACKAGING-MONITOR") PAPER_PACKAGING_MONITOR("PAPER_PACKAGING_MONITOR", "PAPER-PACKAGING-MONITOR"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PAPER_TRADER", displayName = "PAPER-TRADER") PAPER_TRADER("PAPER_TRADER", "PAPER-TRADER"),
	
	/**
	 * Pertamina-Indonesia. http://www.pertamina.com/
	 */
	@RosettaEnumValue(value = "PERTAMINA") PERTAMINA("PERTAMINA"),
	
	/**
	 * PetroChemWire Publication Calendar. http://www.petrochemwire.com/
	 */
	@RosettaEnumValue(value = "PETROCHEMWIRE") PETROCHEMWIRE("PETROCHEMWIRE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PIX_PULP_BENCHMARK_INDICES", displayName = "PIX-PULP-BENCHMARK-INDICES") PIX_PULP_BENCHMARK_INDICES("PIX_PULP_BENCHMARK_INDICES", "PIX-PULP-BENCHMARK-INDICES"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_APAG_MARKETSCAN", displayName = "PLATTS-APAG-MARKETSCAN") PLATTS_APAG_MARKETSCAN("PLATTS_APAG_MARKETSCAN", "PLATTS-APAG-MARKETSCAN"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_BUNKERWIRE", displayName = "PLATTS-BUNKERWIRE") PLATTS_BUNKERWIRE("PLATTS_BUNKERWIRE", "PLATTS-BUNKERWIRE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_CLEAN_TANKERWIRE", displayName = "PLATTS-CLEAN-TANKERWIRE") PLATTS_CLEAN_TANKERWIRE("PLATTS_CLEAN_TANKERWIRE", "PLATTS-CLEAN-TANKERWIRE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_CRUDE_OIL_MARKETWIRE", displayName = "PLATTS-CRUDE-OIL-MARKETWIRE") PLATTS_CRUDE_OIL_MARKETWIRE("PLATTS_CRUDE_OIL_MARKETWIRE", "PLATTS-CRUDE-OIL-MARKETWIRE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_DIRTY_TANKERWIRE", displayName = "PLATTS-DIRTY-TANKERWIRE") PLATTS_DIRTY_TANKERWIRE("PLATTS_DIRTY_TANKERWIRE", "PLATTS-DIRTY-TANKERWIRE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_EUROPEAN_GAS", displayName = "PLATTS-EUROPEAN-GAS") PLATTS_EUROPEAN_GAS("PLATTS_EUROPEAN_GAS", "PLATTS-EUROPEAN-GAS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_EUROPEAN_MARKETSCAN", displayName = "PLATTS-EUROPEAN-MARKETSCAN") PLATTS_EUROPEAN_MARKETSCAN("PLATTS_EUROPEAN_MARKETSCAN", "PLATTS-EUROPEAN-MARKETSCAN"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_METALS_ALERT", displayName = "PLATTS-METALS-ALERT") PLATTS_METALS_ALERT("PLATTS_METALS_ALERT", "PLATTS-METALS-ALERT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_OILGRAM", displayName = "PLATTS-OILGRAM") PLATTS_OILGRAM("PLATTS_OILGRAM", "PLATTS-OILGRAM"),
	
	/**
	 * The Steel Index Iron Ore Service. http://www.thesteelindex.com/en/iron-ore
	 */
	@RosettaEnumValue(value = "PLATTS_TSI_IRON_ORE", displayName = "PLATTS-TSI-IRON-ORE") PLATTS_TSI_IRON_ORE("PLATTS_TSI_IRON_ORE", "PLATTS-TSI-IRON-ORE"),
	
	/**
	 * The Steel Index Scrap Reference Prices. http://www.thesteelindex.com/en/scrapprices
	 */
	@RosettaEnumValue(value = "PLATTS_TSI_SCRAP", displayName = "PLATTS-TSI-SCRAP") PLATTS_TSI_SCRAP("PLATTS_TSI_SCRAP", "PLATTS-TSI-SCRAP"),
	
	/**
	 * The Steel Index. http://www.thesteelindex.com/en/price-specifications
	 */
	@RosettaEnumValue(value = "PLATTS_TSI_STEEL", displayName = "PLATTS-TSI-STEEL") PLATTS_TSI_STEEL("PLATTS_TSI_STEEL", "PLATTS-TSI-STEEL"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PLATTS_US_MARKETSCAN", displayName = "PLATTS-US-MARKETSCAN") PLATTS_US_MARKETSCAN("PLATTS_US_MARKETSCAN", "PLATTS-US-MARKETSCAN"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PULP_AND_PAPER_INTERNATIONAL", displayName = "PULP-AND-PAPER-INTERNATIONAL") PULP_AND_PAPER_INTERNATIONAL("PULP_AND_PAPER_INTERNATIONAL", "PULP-AND-PAPER-INTERNATIONAL"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "PULP_AND_PAPER_WEEK", displayName = "PULP-AND-PAPER-WEEK") PULP_AND_PAPER_WEEK("PULP_AND_PAPER_WEEK", "PULP-AND-PAPER-WEEK"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "RIM_PRODUCTS_INTELLIGENCE_DAILY", displayName = "RIM-PRODUCTS-INTELLIGENCE-DAILY") RIM_PRODUCTS_INTELLIGENCE_DAILY("RIM_PRODUCTS_INTELLIGENCE_DAILY", "RIM-PRODUCTS-INTELLIGENCE-DAILY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "SAFEX_SOFT", displayName = "SAFEX-SOFT") SAFEX_SOFT("SAFEX_SOFT", "SAFEX-SOFT"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "SFE_SOFT", displayName = "SFE-SOFT") SFE_SOFT("SFE_SOFT", "SFE-SOFT"),
	
	/**
	 * Singapore Exchange. www.sgx.com
	 */
	@RosettaEnumValue(value = "SGX") SGX("SGX"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "SICOM") SICOM("SICOM"),
	
	/**
	 * Standard and Poor&#39;s GSCI. http://us.spindices.com/index-family/commodities/sp-gsci
	 */
	@RosettaEnumValue(value = "SP_GSCI", displayName = "SP-GSCI") SP_GSCI("SP_GSCI", "SP-GSCI"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "STATISTICHES_BUNDESAMT", displayName = "STATISTICHES-BUNDESAMT") STATISTICHES_BUNDESAMT("STATISTICHES_BUNDESAMT", "STATISTICHES-BUNDESAMT"),
	
	/**
	 * Tokyo Grain Exchange. www.tge.or.jp
	 */
	@RosettaEnumValue(value = "TGE") TGE("TGE"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "TOCOM_OIL", displayName = "TOCOM-OIL") TOCOM_OIL("TOCOM_OIL", "TOCOM-OIL"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "TOCOM_PRECIOUS", displayName = "TOCOM-PRECIOUS") TOCOM_PRECIOUS("TOCOM_PRECIOUS", "TOCOM-PRECIOUS"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "TOCOM_SOFT", displayName = "TOCOM-SOFT") TOCOM_SOFT("TOCOM_SOFT", "TOCOM-SOFT"),
	
	/**
	 * The Ux Consulting Company. http://www.uxc.com/products/uxw_overview.aspx
	 */
	@RosettaEnumValue(value = "UX_WEEKLY", displayName = "UX-WEEKLY") UX_WEEKLY("UX_WEEKLY", "UX-WEEKLY"),
	
	/**
	 * Per 2005 ISDA Commodity Definitions, Section 7.2 Certain Definitions Relating To Commodity Reference Prices.
	 */
	@RosettaEnumValue(value = "WORLD_PULP_MONTHLY", displayName = "WORLD-PULP-MONTHLY") WORLD_PULP_MONTHLY("WORLD_PULP_MONTHLY", "WORLD-PULP-MONTHLY")
;
	private static Map<String, CommodityBusinessCalendarEnum> values;
	static {
        Map<String, CommodityBusinessCalendarEnum> map = new ConcurrentHashMap<>();
		for (CommodityBusinessCalendarEnum instance : CommodityBusinessCalendarEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CommodityBusinessCalendarEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CommodityBusinessCalendarEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CommodityBusinessCalendarEnum fromDisplayName(String name) {
		CommodityBusinessCalendarEnum value = values.get(name);
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
