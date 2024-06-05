package cdm.base.staticdata.asset.rates;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the list of inflation rate indices.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/inflation-index-description"
 *
 * Provision 
 *
 */
@RosettaEnum("InflationRateIndexEnum")
public enum InflationRateIndexEnum {

	/**
	 * Australia: AUD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "AUD_CPI", displayName = "AUD-CPI") AUD_CPI("AUD_CPI", "AUD-CPI"),
	
	/**
	 * Austria: AUS - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "AUS_CPI", displayName = "AUS-CPI") AUS_CPI("AUS_CPI", "AUS-CPI"),
	
	/**
	 * Austria: AUS - Non-revised Harmonised Indices of Consumer Prices (HICP)
	 */
	@RosettaEnumValue(value = "AUS_HICP", displayName = "AUS-HICP") AUS_HICP("AUS_HICP", "AUS-HICP"),
	
	/**
	 * Belgium: BLG - Non-revised Consumer Price Index - General Index (CPI)
	 */
	@RosettaEnumValue(value = "BLG_CPI_GI", displayName = "BLG-CPI-GI") BLG_CPI_GI("BLG_CPI_GI", "BLG-CPI-GI"),
	
	/**
	 * Belgium: BLG - Non-revised Consumer Price Index - Health Index (CPI)
	 */
	@RosettaEnumValue(value = "BLG_CPI_HI", displayName = "BLG-CPI-HI") BLG_CPI_HI("BLG_CPI_HI", "BLG-CPI-HI"),
	
	/**
	 * Belgium: BLG - Non-revised Harmonised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "BLG_HICP", displayName = "BLG-HICP") BLG_HICP("BLG_HICP", "BLG-HICP"),
	
	/**
	 * Brazil: BRL - Non-revised Price Index (IGP-M)
	 */
	@RosettaEnumValue(value = "BRL_IGPM", displayName = "BRL-IGPM") BRL_IGPM("BRL_IGPM", "BRL-IGPM"),
	
	/**
	 * Brazil: BRL - Non-revised Consumer Price Index (IPCA)
	 */
	@RosettaEnumValue(value = "BRL_IPCA", displayName = "BRL-IPCA") BRL_IPCA("BRL_IPCA", "BRL-IPCA"),
	
	/**
	 * Canada: CAD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "CAD_CPI", displayName = "CAD-CPI") CAD_CPI("CAD_CPI", "CAD-CPI"),
	
	/**
	 * Chile: CLP - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "CLP_CPI", displayName = "CLP-CPI") CLP_CPI("CLP_CPI", "CLP-CPI"),
	
	/**
	 * China: CNY - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "CNY_CPI", displayName = "CNY-CPI") CNY_CPI("CNY_CPI", "CNY-CPI"),
	
	/**
	 * Czech Republic: CZK - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "CZK_CPI", displayName = "CZK-CPI") CZK_CPI("CZK_CPI", "CZK-CPI"),
	
	/**
	 * Denmark: DEK - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "DEK_CPI", displayName = "DEK-CPI") DEK_CPI("DEK_CPI", "DEK-CPI"),
	
	/**
	 * Denmark: DEK - Non-revised Harmonised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "DEK_HICP", displayName = "DEK-HICP") DEK_HICP("DEK_HICP", "DEK-HICP"),
	
	/**
	 * Germany: DEM - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "DEM_CPI", displayName = "DEM-CPI") DEM_CPI("DEM_CPI", "DEM-CPI"),
	
	/**
	 * Germany: DEM - Non-revised Consumer Price Index for North Rhine-Westphalia
	 */
	@RosettaEnumValue(value = "DEM_CPI_NRW", displayName = "DEM-CPI-NRW") DEM_CPI_NRW("DEM_CPI_NRW", "DEM-CPI-NRW"),
	
	/**
	 * Germany: DEM - Non-revised Harmonised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "DEM_HICP", displayName = "DEM-HICP") DEM_HICP("DEM_HICP", "DEM-HICP"),
	
	/**
	 * Spain: ESP - National-Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "ESP_CPI", displayName = "ESP-CPI") ESP_CPI("ESP_CPI", "ESP-CPI"),
	
	/**
	 * Spain: ESP - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "ESP_HICP", displayName = "ESP-HICP") ESP_HICP("ESP_HICP", "ESP-HICP"),
	
	/**
	 * Spain: ESP - National-Revised Consumer Price Index (CPI).
	 */
	@RosettaEnumValue(value = "ESP_R_CPI", displayName = "ESP-R-CPI") ESP_R_CPI("ESP_R_CPI", "ESP-R-CPI"),
	
	/**
	 * Spain: ESP - Harmonised-Revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "ESP_R_HICP", displayName = "ESP-R-HICP") ESP_R_HICP("ESP_R_HICP", "ESP-R-HICP"),
	
	/**
	 * European Union: EUR - All Items-Non-revised Consumer Price Index
	 */
	@RosettaEnumValue(value = "EUR_AI_CPI", displayName = "EUR-AI-CPI") EUR_AI_CPI("EUR_AI_CPI", "EUR-AI-CPI"),
	
	/**
	 * European Union: EUR - All Items-Revised Consumer Price Index
	 */
	@RosettaEnumValue(value = "EUR_AI_R_CPI", displayName = "EUR-AI-R-CPI") EUR_AI_R_CPI("EUR_AI_R_CPI", "EUR-AI-R-CPI"),
	
	/**
	 * European Union: EUR - Excluding Tobacco-Non-revised Consumer Price Index
	 */
	@RosettaEnumValue(value = "EUR_EXT_CPI", displayName = "EUR-EXT-CPI") EUR_EXT_CPI("EUR_EXT_CPI", "EUR-EXT-CPI"),
	
	/**
	 * European Union: EUR - Excluding Tobacco-Revised Consumer Price Index
	 */
	@RosettaEnumValue(value = "EUR_EXT_R_CPI", displayName = "EUR-EXT-R-CPI") EUR_EXT_R_CPI("EUR_EXT_R_CPI", "EUR-EXT-R-CPI"),
	
	/**
	 * Finland: FIN - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "FIN_CPI", displayName = "FIN-CPI") FIN_CPI("FIN_CPI", "FIN-CPI"),
	
	/**
	 * Finland: FIN - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "FIN_HICP", displayName = "FIN-HICP") FIN_HICP("FIN_HICP", "FIN-HICP"),
	
	/**
	 * France: FRC - Excluding Tobacco-Non-Revised Consumer Price Index
	 */
	@RosettaEnumValue(value = "FRC_EXT_CPI", displayName = "FRC-EXT-CPI") FRC_EXT_CPI("FRC_EXT_CPI", "FRC-EXT-CPI"),
	
	/**
	 * France: FRC - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "FRC_HICP", displayName = "FRC-HICP") FRC_HICP("FRC_HICP", "FRC-HICP"),
	
	/**
	 * Greece: GRD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "GRD_CPI", displayName = "GRD-CPI") GRD_CPI("GRD_CPI", "GRD-CPI"),
	
	/**
	 * Greece: GRD - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "GRD_HICP", displayName = "GRD-HICP") GRD_HICP("GRD_HICP", "GRD-HICP"),
	
	/**
	 * Hong Kong: HKD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "HKD_CPI", displayName = "HKD-CPI") HKD_CPI("HKD_CPI", "HKD-CPI"),
	
	/**
	 * Hungary: HUF - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "HUF_CPI", displayName = "HUF-CPI") HUF_CPI("HUF_CPI", "HUF-CPI"),
	
	/**
	 * Indonesia: IDR - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "IDR_CPI", displayName = "IDR-CPI") IDR_CPI("IDR_CPI", "IDR-CPI"),
	
	/**
	 * Israel: ILS - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "ILS_CPI", displayName = "ILS-CPI") ILS_CPI("ILS_CPI", "ILS-CPI"),
	
	/**
	 * Ireland: IRL - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "IRL_CPI", displayName = "IRL-CPI") IRL_CPI("IRL_CPI", "IRL-CPI"),
	
	/**
	 * Ireland: IRL - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "IRL_HICP", displayName = "IRL-HICP") IRL_HICP("IRL_HICP", "IRL-HICP"),
	
	/**
	 * Iceland: ISK - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "ISK_CPI", displayName = "ISK-CPI") ISK_CPI("ISK_CPI", "ISK-CPI"),
	
	/**
	 * Iceland: ISK - Harmonised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "ISK_HICP", displayName = "ISK-HICP") ISK_HICP("ISK_HICP", "ISK-HICP"),
	
	/**
	 * Italy: ITL - Inflation for Blue Collar Workers and Employees-Excluding Tobacco Consumer Price Index
	 */
	@RosettaEnumValue(value = "ITL_BC_EXT_CPI", displayName = "ITL-BC-EXT-CPI") ITL_BC_EXT_CPI("ITL_BC_EXT_CPI", "ITL-BC-EXT-CPI"),
	
	/**
	 * Italy: ITL - Inflation for Blue Collar Workers and Employees-Including Tobacco Consumer Price Index
	 */
	@RosettaEnumValue(value = "ITL_BC_INT_CPI", displayName = "ITL-BC-INT-CPI") ITL_BC_INT_CPI("ITL_BC_INT_CPI", "ITL-BC-INT-CPI"),
	
	/**
	 * Italy: ITL - Non-revised Harmonised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "ITL_HICP", displayName = "ITL-HICP") ITL_HICP("ITL_HICP", "ITL-HICP"),
	
	/**
	 * Italy: ITL - Whole Community - Excluding Tobacco Consumer Price Index
	 */
	@RosettaEnumValue(value = "ITL_WC_EXT_CPI", displayName = "ITL-WC-EXT-CPI") ITL_WC_EXT_CPI("ITL_WC_EXT_CPI", "ITL-WC-EXT-CPI"),
	
	/**
	 * Italy: ITL - Whole Community - Including Tobacco Consumer Price Index
	 */
	@RosettaEnumValue(value = "ITL_WC_INT_CPI", displayName = "ITL-WC-INT-CPI") ITL_WC_INT_CPI("ITL_WC_INT_CPI", "ITL-WC-INT-CPI"),
	
	/**
	 * Japan: JPY - Non-revised Consumer Price Index Nationwide General Excluding Fresh Food (CPI)
	 */
	@RosettaEnumValue(value = "JPY_CPI_EXF", displayName = "JPY-CPI-EXF") JPY_CPI_EXF("JPY_CPI_EXF", "JPY-CPI-EXF"),
	
	/**
	 * South Korea: KRW - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "KRW_CPI", displayName = "KRW-CPI") KRW_CPI("KRW_CPI", "KRW-CPI"),
	
	/**
	 * Luxembourg: LUX - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "LUX_CPI", displayName = "LUX-CPI") LUX_CPI("LUX_CPI", "LUX-CPI"),
	
	/**
	 * Luxembourg: LUX - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "LUX_HICP", displayName = "LUX-HICP") LUX_HICP("LUX_HICP", "LUX-HICP"),
	
	/**
	 * Mexico: MXN - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "MXN_CPI", displayName = "MXN-CPI") MXN_CPI("MXN_CPI", "MXN-CPI"),
	
	/**
	 * Mexico: MXN - Unidad de Inversion Index (UDI)
	 */
	@RosettaEnumValue(value = "MXN_UDI", displayName = "MXN-UDI") MXN_UDI("MXN_UDI", "MXN-UDI"),
	
	/**
	 * Malaysia: MYR - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "MYR_CPI", displayName = "MYR-CPI") MYR_CPI("MYR_CPI", "MYR-CPI"),
	
	/**
	 * Netherlands: NLG - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "NLG_CPI", displayName = "NLG-CPI") NLG_CPI("NLG_CPI", "NLG-CPI"),
	
	/**
	 * Netherlands: NLG - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "NLG_HICP", displayName = "NLG-HICP") NLG_HICP("NLG_HICP", "NLG-HICP"),
	
	/**
	 * Norway: NOK - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "NOK_CPI", displayName = "NOK-CPI") NOK_CPI("NOK_CPI", "NOK-CPI"),
	
	/**
	 * New Zealand: NZD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "NZD_CPI", displayName = "NZD-CPI") NZD_CPI("NZD_CPI", "NZD-CPI"),
	
	/**
	 * Peru: PER - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "PER_CPI", displayName = "PER-CPI") PER_CPI("PER_CPI", "PER-CPI"),
	
	/**
	 * Poland: PLN - Non-Revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "PLN_CPI", displayName = "PLN-CPI") PLN_CPI("PLN_CPI", "PLN-CPI"),
	
	/**
	 * Portugal: POR - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "POR_CPI", displayName = "POR-CPI") POR_CPI("POR_CPI", "POR-CPI"),
	
	/**
	 * Portugal: POR - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "POR_HICP", displayName = "POR-HICP") POR_HICP("POR_HICP", "POR-HICP"),
	
	/**
	 * Russia: RUB - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "RUB_CPI", displayName = "RUB-CPI") RUB_CPI("RUB_CPI", "RUB-CPI"),
	
	/**
	 * Sweden: SEK - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "SEK_CPI", displayName = "SEK-CPI") SEK_CPI("SEK_CPI", "SEK-CPI"),
	
	/**
	 * Singapore: SGD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "SGD_CPI", displayName = "SGD-CPI") SGD_CPI("SGD_CPI", "SGD-CPI"),
	
	/**
	 * Switzerland: SWF - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "SWF_CPI", displayName = "SWF-CPI") SWF_CPI("SWF_CPI", "SWF-CPI"),
	
	/**
	 * Turkey: TRY - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "TRY_CPI", displayName = "TRY-CPI") TRY_CPI("TRY_CPI", "TRY-CPI"),
	
	/**
	 * Taiwan: TWD - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "TWD_CPI", displayName = "TWD-CPI") TWD_CPI("TWD_CPI", "TWD-CPI"),
	
	/**
	 * United Kingdom: GBP - Non-revised Consumer Prices Index including Housing (UKCPIH)
	 */
	@RosettaEnumValue(value = "UK_CPIH", displayName = "UK-CPIH") UK_CPIH("UK_CPIH", "UK-CPIH"),
	
	/**
	 * United Kingdom: GBP - Harmonised-Non-revised Consumer Price Index (HICP)
	 */
	@RosettaEnumValue(value = "UK_HICP", displayName = "UK-HICP") UK_HICP("UK_HICP", "UK-HICP"),
	
	/**
	 * United Kingdom: GBP - Non-revised Retail Price Index (UKRPI)
	 */
	@RosettaEnumValue(value = "UK_RPI", displayName = "UK-RPI") UK_RPI("UK_RPI", "UK-RPI"),
	
	/**
	 * United Kingdom: GBP - Non-revised Retail Price Index Excluding Mortgage Interest Payments (UKRPIX)
	 */
	@RosettaEnumValue(value = "UK_RPIX", displayName = "UK-RPIX") UK_RPIX("UK_RPIX", "UK-RPIX"),
	
	/**
	 * United States: USA - Non-revised Consumer Price Index - Urban (CPI-U)
	 */
	@RosettaEnumValue(value = "USA_CPI_U", displayName = "USA-CPI-U") USA_CPI_U("USA_CPI_U", "USA-CPI-U"),
	
	/**
	 * South Africa: ZAR - Non-revised Consumer Price Index (CPI)
	 */
	@RosettaEnumValue(value = "ZAR_CPI", displayName = "ZAR-CPI") ZAR_CPI("ZAR_CPI", "ZAR-CPI"),
	
	/**
	 * South Africa: ZAR - Non-revised Consumer Price Index Excluding Mortgages (CPIX)
	 */
	@RosettaEnumValue(value = "ZAR_CPIX", displayName = "ZAR-CPIX") ZAR_CPIX("ZAR_CPIX", "ZAR-CPIX")
;
	private static Map<String, InflationRateIndexEnum> values;
	static {
        Map<String, InflationRateIndexEnum> map = new ConcurrentHashMap<>();
		for (InflationRateIndexEnum instance : InflationRateIndexEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	InflationRateIndexEnum(String rosettaName) {
		this(rosettaName, null);
	}

	InflationRateIndexEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static InflationRateIndexEnum fromDisplayName(String name) {
		InflationRateIndexEnum value = values.get(name);
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
