package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines a publication in which the price can be found. (e.g Gas Daily, Platts Bloomberg.
 * @version ${project.version}
 */
@RosettaEnum("CommodityInformationPublisherEnum")
public enum CommodityInformationPublisherEnum {

	@RosettaEnumValue(value = "Argus") ARGUS("Argus"),
	
	@RosettaEnumValue(value = "ArgusMcCloskeys") ARGUS_MC_CLOSKEYS("ArgusMcCloskeys"),
	
	@RosettaEnumValue(value = "ArgusAmericasCrudeReport") ARGUS_AMERICAS_CRUDE_REPORT("ArgusAmericasCrudeReport"),
	
	@RosettaEnumValue(value = "ArgusBiofuelReport") ARGUS_BIOFUEL_REPORT("ArgusBiofuelReport"),
	
	@RosettaEnumValue(value = "ArgusCrudeReport") ARGUS_CRUDE_REPORT("ArgusCrudeReport"),
	
	@RosettaEnumValue(value = "ArgusEuropeanProductsReport") ARGUS_EUROPEAN_PRODUCTS_REPORT("ArgusEuropeanProductsReport"),
	
	@RosettaEnumValue(value = "ArgusFMB") ARGUS_FMB("ArgusFMB"),
	
	@RosettaEnumValue(value = "ArgusInternationalLPGReport") ARGUS_INTERNATIONAL_LPG_REPORT("ArgusInternationalLPGReport"),
	
	@RosettaEnumValue(value = "ArgusLPG") ARGUS_LPG("ArgusLPG"),
	
	@RosettaEnumValue(value = "ArgusNatGas") ARGUS_NAT_GAS("ArgusNatGas"),
	
	@RosettaEnumValue(value = "AssocBanksSingapore") ASSOC_BANKS_SINGAPORE("AssocBanksSingapore"),
	
	@RosettaEnumValue(value = "BandD") BAND_D("BandD"),
	
	@RosettaEnumValue(value = "BalticExchange") BALTIC_EXCHANGE("BalticExchange"),
	
	@RosettaEnumValue(value = "BankOfCanada") BANK_OF_CANADA("BankOfCanada"),
	
	@RosettaEnumValue(value = "BankOfEngland") BANK_OF_ENGLAND("BankOfEngland"),
	
	@RosettaEnumValue(value = "BankOfJapan") BANK_OF_JAPAN("BankOfJapan"),
	
	@RosettaEnumValue(value = "Bloomberg") BLOOMBERG("Bloomberg"),
	
	@RosettaEnumValue(value = "BLUENEXT") BLUENEXT("BLUENEXT"),
	
	@RosettaEnumValue(value = "CAISO") CAISO("CAISO"),
	
	@RosettaEnumValue(value = "CanadianGasPriceReporter") CANADIAN_GAS_PRICE_REPORTER("CanadianGasPriceReporter"),
	
	@RosettaEnumValue(value = "CanadianGasReporter") CANADIAN_GAS_REPORTER("CanadianGasReporter"),
	
	@RosettaEnumValue(value = "ChemicalMarketsAssociation") CHEMICAL_MARKETS_ASSOCIATION("ChemicalMarketsAssociation"),
	
	@RosettaEnumValue(value = "CMAIAromaticsMarketReport") CMAI_AROMATICS_MARKET_REPORT("CMAIAromaticsMarketReport"),
	
	@RosettaEnumValue(value = "CMAIWeeklyMethanolMarketReport") CMAI_WEEKLY_METHANOL_MARKET_REPORT("CMAIWeeklyMethanolMarketReport"),
	
	@RosettaEnumValue(value = "CRUSteelLongProductMonitor") CRU_STEEL_LONG_PRODUCT_MONITOR("CRUSteelLongProductMonitor"),
	
	@RosettaEnumValue(value = "CRUSteelSheetProductsMonitor") CRU_STEEL_SHEET_PRODUCTS_MONITOR("CRUSteelSheetProductsMonitor"),
	
	@RosettaEnumValue(value = "DowJonesEnergyService") DOW_JONES_ENERGY_SERVICE("DowJonesEnergyService"),
	
	@RosettaEnumValue(value = "DowJonesEnergyServiceScreen") DOW_JONES_ENERGY_SERVICE_SCREEN("DowJonesEnergyServiceScreen"),
	
	@RosettaEnumValue(value = "DowJonesNatGas") DOW_JONES_NAT_GAS("DowJonesNatGas"),
	
	@RosettaEnumValue(value = "EEX") EEX("EEX"),
	
	@RosettaEnumValue(value = "ERCOT") ERCOT("ERCOT"),
	
	@RosettaEnumValue(value = "EuroCentralBank") EURO_CENTRAL_BANK("EuroCentralBank"),
	
	@RosettaEnumValue(value = "EURONEXMATIF") EURONEXMATIF("EURONEXMATIF"),
	
	@RosettaEnumValue(value = "FederalReserve") FEDERAL_RESERVE("FederalReserve"),
	
	@RosettaEnumValue(value = "FERTECON") FERTECON("FERTECON"),
	
	@RosettaEnumValue(value = "FertilizerWeek") FERTILIZER_WEEK("FertilizerWeek"),
	
	@RosettaEnumValue(value = "FHLBSF") FHLBSF("FHLBSF"),
	
	@RosettaEnumValue(value = "GasDaily") GAS_DAILY("GasDaily"),
	
	@RosettaEnumValue(value = "GasDailyPriceGuide") GAS_DAILY_PRICE_GUIDE("GasDailyPriceGuide"),
	
	@RosettaEnumValue(value = "GlobalCoale") GLOBAL_COALE("GlobalCoale"),
	
	@RosettaEnumValue(value = "GME") GME("GME"),
	
	@RosettaEnumValue(value = "HerenReport") HEREN_REPORT("HerenReport"),
	
	@RosettaEnumValue(value = "ICE") ICE("ICE"),
	
	@RosettaEnumValue(value = "ICE10XDailyNaturalGas") ICE_10_X_DAILY_NATURAL_GAS("ICE10XDailyNaturalGas"),
	
	@RosettaEnumValue(value = "ICE10XDailyPower") ICE_10_X_DAILY_POWER("ICE10XDailyPower"),
	
	@RosettaEnumValue(value = "ICE10XMonthly") ICE_10_X_MONTHLY("ICE10XMonthly"),
	
	@RosettaEnumValue(value = "ICEDayAheadIndex") ICE_DAY_AHEAD_INDEX("ICEDayAheadIndex"),
	
	@RosettaEnumValue(value = "ICEECX") ICEECX("ICEECX"),
	
	@RosettaEnumValue(value = "ICIS") ICIS("ICIS"),
	
	@RosettaEnumValue(value = "InsideFERC") INSIDE_FERC("InsideFERC"),
	
	@RosettaEnumValue(value = "IPE") IPE("IPE"),
	
	@RosettaEnumValue(value = "ISDA") ISDA("ISDA"),
	
	@RosettaEnumValue(value = "ISONewEngland") ISO_NEW_ENGLAND("ISONewEngland"),
	
	@RosettaEnumValue(value = "JAPANMOFTSRR") JAPANMOFTSRR("JAPANMOFTSRR"),
	
	@RosettaEnumValue(value = "LEBA") LEBA("LEBA"),
	
	@RosettaEnumValue(value = "LondonBullionMarketAssociation") LONDON_BULLION_MARKET_ASSOCIATION("LondonBullionMarketAssociation"),
	
	@RosettaEnumValue(value = "LONDONPLATINUMPALLADIUMMARKET") LONDONPLATINUMPALLADIUMMARKET("LONDONPLATINUMPALLADIUMMARKET"),
	
	@RosettaEnumValue(value = "MegawattDaily") MEGAWATT_DAILY("MegawattDaily"),
	
	@RosettaEnumValue(value = "MetalBulletin") METAL_BULLETIN("MetalBulletin"),
	
	@RosettaEnumValue(value = "MISO") MISO("MISO"),
	
	@RosettaEnumValue(value = "NaturalGasWeek") NATURAL_GAS_WEEK("NaturalGasWeek"),
	
	@RosettaEnumValue(value = "NGIBidweekSurvey") NGI_BIDWEEK_SURVEY("NGIBidweekSurvey"),
	
	@RosettaEnumValue(value = "NYISO") NYISO("NYISO"),
	
	@RosettaEnumValue(value = "OBM") OBM("OBM"),
	
	@RosettaEnumValue(value = "OMEL") OMEL("OMEL"),
	
	@RosettaEnumValue(value = "OPIS") OPIS("OPIS"),
	
	@RosettaEnumValue(value = "PaperTrader") PAPER_TRADER("PaperTrader"),
	
	@RosettaEnumValue(value = "PIX") PIX("PIX"),
	
	@RosettaEnumValue(value = "PJM") PJM("PJM"),
	
	@RosettaEnumValue(value = "PlattsAsiaPacific") PLATTS_ASIA_PACIFIC("PlattsAsiaPacific"),
	
	@RosettaEnumValue(value = "PlattsAsiaPacificArabMarketscan") PLATTS_ASIA_PACIFIC_ARAB_MARKETSCAN("PlattsAsiaPacificArabMarketscan"),
	
	@RosettaEnumValue(value = "PlattsCleanTankerwire") PLATTS_CLEAN_TANKERWIRE("PlattsCleanTankerwire"),
	
	@RosettaEnumValue(value = "PlattsCoalTrader") PLATTS_COAL_TRADER("PlattsCoalTrader"),
	
	@RosettaEnumValue(value = "PlattsCrudeOilMarketwire") PLATTS_CRUDE_OIL_MARKETWIRE("PlattsCrudeOilMarketwire"),
	
	@RosettaEnumValue(value = "PlattsDirtyTakerwire") PLATTS_DIRTY_TAKERWIRE("PlattsDirtyTakerwire"),
	
	@RosettaEnumValue(value = "PlattsENGR") PLATTS_ENGR("PlattsENGR"),
	
	@RosettaEnumValue(value = "PlattsEuropean") PLATTS_EUROPEAN("PlattsEuropean"),
	
	@RosettaEnumValue(value = "PlattsEuropeanMarketscan") PLATTS_EUROPEAN_MARKETSCAN("PlattsEuropeanMarketscan"),
	
	@RosettaEnumValue(value = "PlattsGasDaily") PLATTS_GAS_DAILY("PlattsGasDaily"),
	
	@RosettaEnumValue(value = "PlattsGasDailyPriceGuide") PLATTS_GAS_DAILY_PRICE_GUIDE("PlattsGasDailyPriceGuide"),
	
	@RosettaEnumValue(value = "PlattsInsideFERC") PLATTS_INSIDE_FERC("PlattsInsideFERC"),
	
	@RosettaEnumValue(value = "PlattsLPG") PLATTS_LPG("PlattsLPG"),
	
	@RosettaEnumValue(value = "PlattsMarketwire") PLATTS_MARKETWIRE("PlattsMarketwire"),
	
	@RosettaEnumValue(value = "PlattsMegawattDaily") PLATTS_MEGAWATT_DAILY("PlattsMegawattDaily"),
	
	@RosettaEnumValue(value = "PlattsMetalsAlert") PLATTS_METALS_ALERT("PlattsMetalsAlert"),
	
	@RosettaEnumValue(value = "PlattsOilgram") PLATTS_OILGRAM("PlattsOilgram"),
	
	@RosettaEnumValue(value = "PlattsOilgramBunkerwire") PLATTS_OILGRAM_BUNKERWIRE("PlattsOilgramBunkerwire"),
	
	@RosettaEnumValue(value = "PlattsPolymerscan") PLATTS_POLYMERSCAN("PlattsPolymerscan"),
	
	@RosettaEnumValue(value = "PlattsTSIIronOre") PLATTS_TSI_IRON_ORE("PlattsTSIIronOre"),
	
	@RosettaEnumValue(value = "TSIScrap") TSI_SCRAP("TSIScrap"),
	
	@RosettaEnumValue(value = "TSISteel") TSI_STEEL("TSISteel"),
	
	@RosettaEnumValue(value = "PlattsUS") PLATTS_US("PlattsUS"),
	
	@RosettaEnumValue(value = "PlattsUSMarketscan") PLATTS_US_MARKETSCAN("PlattsUSMarketscan"),
	
	@RosettaEnumValue(value = "PPM") PPM("PPM"),
	
	@RosettaEnumValue(value = "PPMEurope") PPM_EUROPE("PPMEurope"),
	
	@RosettaEnumValue(value = "PPW") PPW("PPW"),
	
	@RosettaEnumValue(value = "ReserveBankAustralia") RESERVE_BANK_AUSTRALIA("ReserveBankAustralia"),
	
	@RosettaEnumValue(value = "ReserveBankNewZealand") RESERVE_BANK_NEW_ZEALAND("ReserveBankNewZealand"),
	
	@RosettaEnumValue(value = "Reuters") REUTERS("Reuters"),
	
	@RosettaEnumValue(value = "ReutersScreen") REUTERS_SCREEN("ReutersScreen"),
	
	@RosettaEnumValue(value = "RIMIntelligenceProducts") RIM_INTELLIGENCE_PRODUCTS("RIMIntelligenceProducts"),
	
	@RosettaEnumValue(value = "SeaPac") SEA_PAC("SeaPac"),
	
	@RosettaEnumValue(value = "Telerate") TELERATE("Telerate"),
	
	@RosettaEnumValue(value = "TelerateScreen") TELERATE_SCREEN("TelerateScreen"),
	
	@RosettaEnumValue(value = "UXWEEKLY") UXWEEKLY("UXWEEKLY"),
	
	@RosettaEnumValue(value = "WorldCrudeReport") WORLD_CRUDE_REPORT("WorldCrudeReport"),
	
	@RosettaEnumValue(value = "WorldPulpMonthly") WORLD_PULP_MONTHLY("WorldPulpMonthly")
;
	private static Map<String, CommodityInformationPublisherEnum> values;
	static {
        Map<String, CommodityInformationPublisherEnum> map = new ConcurrentHashMap<>();
		for (CommodityInformationPublisherEnum instance : CommodityInformationPublisherEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CommodityInformationPublisherEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CommodityInformationPublisherEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CommodityInformationPublisherEnum fromDisplayName(String name) {
		CommodityInformationPublisherEnum value = values.get(name);
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
