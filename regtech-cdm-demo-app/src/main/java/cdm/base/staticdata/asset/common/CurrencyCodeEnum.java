package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Union of the enumerated values defined by the International Standards Organization (ISO) and the FpML nonISOCurrencyScheme which consists of offshore and historical currencies (https://www.fpml.org/coding-scheme/non-iso-currency), as of 28-Oct-2016.
 * @version ${project.version}
 */
@RosettaEnum("CurrencyCodeEnum")
public enum CurrencyCodeEnum {

	/**
	 * Offshore Chinese Yuan traded in Hong Kong.
	 */
	@RosettaEnumValue(value = "CNH") CNH("CNH"),
	
	/**
	 * Offshore Chinese Yuan traded in Taiwan.
	 */
	@RosettaEnumValue(value = "CNT") CNT("CNT"),
	
	/**
	 * Guernsey Pound.
	 */
	@RosettaEnumValue(value = "GGP") GGP("GGP"),
	
	/**
	 * Isle of Man Pound.
	 */
	@RosettaEnumValue(value = "IMP") IMP("IMP"),
	
	/**
	 * Jersey Pound.
	 */
	@RosettaEnumValue(value = "JEP") JEP("JEP"),
	
	/**
	 * Tuvaluan Dollar.
	 */
	@RosettaEnumValue(value = "KID") KID("KID"),
	
	/**
	 * Monegasque Franc.
	 */
	@RosettaEnumValue(value = "MCF") MCF("MCF"),
	
	/**
	 * Sammarinese Lira.
	 */
	@RosettaEnumValue(value = "SML") SML("SML"),
	
	/**
	 * Vatican Lira.
	 */
	@RosettaEnumValue(value = "VAL") VAL("VAL"),
	
	/**
	 * UAE Dirham
	 */
	@RosettaEnumValue(value = "AED") AED("AED"),
	
	/**
	 * Afghani
	 */
	@RosettaEnumValue(value = "AFN") AFN("AFN"),
	
	/**
	 * Lek
	 */
	@RosettaEnumValue(value = "ALL") ALL("ALL"),
	
	/**
	 * Armenian Dram
	 */
	@RosettaEnumValue(value = "AMD") AMD("AMD"),
	
	/**
	 * Netherlands Antillean Guilder
	 */
	@RosettaEnumValue(value = "ANG") ANG("ANG"),
	
	/**
	 * Kwanza
	 */
	@RosettaEnumValue(value = "AOA") AOA("AOA"),
	
	/**
	 * Argentine Peso
	 */
	@RosettaEnumValue(value = "ARS") ARS("ARS"),
	
	/**
	 * Australian Dollar
	 */
	@RosettaEnumValue(value = "AUD") AUD("AUD"),
	
	/**
	 * Aruban Florin
	 */
	@RosettaEnumValue(value = "AWG") AWG("AWG"),
	
	/**
	 * Azerbaijan Manat
	 */
	@RosettaEnumValue(value = "AZN") AZN("AZN"),
	
	/**
	 * Convertible Mark
	 */
	@RosettaEnumValue(value = "BAM") BAM("BAM"),
	
	/**
	 * Barbados Dollar
	 */
	@RosettaEnumValue(value = "BBD") BBD("BBD"),
	
	/**
	 * Taka
	 */
	@RosettaEnumValue(value = "BDT") BDT("BDT"),
	
	/**
	 * Bulgarian Lev
	 */
	@RosettaEnumValue(value = "BGN") BGN("BGN"),
	
	/**
	 * Bahraini Dinar
	 */
	@RosettaEnumValue(value = "BHD") BHD("BHD"),
	
	/**
	 * Burundi Franc
	 */
	@RosettaEnumValue(value = "BIF") BIF("BIF"),
	
	/**
	 * Bermudian Dollar
	 */
	@RosettaEnumValue(value = "BMD") BMD("BMD"),
	
	/**
	 * Brunei Dollar
	 */
	@RosettaEnumValue(value = "BND") BND("BND"),
	
	/**
	 * Boliviano
	 */
	@RosettaEnumValue(value = "BOB") BOB("BOB"),
	
	/**
	 * Mvdol
	 */
	@RosettaEnumValue(value = "BOV") BOV("BOV"),
	
	/**
	 * Brazilian Real
	 */
	@RosettaEnumValue(value = "BRL") BRL("BRL"),
	
	/**
	 * Bahamian Dollar
	 */
	@RosettaEnumValue(value = "BSD") BSD("BSD"),
	
	/**
	 * Ngultrum
	 */
	@RosettaEnumValue(value = "BTN") BTN("BTN"),
	
	/**
	 * Pula
	 */
	@RosettaEnumValue(value = "BWP") BWP("BWP"),
	
	/**
	 * Belarusian Ruble
	 */
	@RosettaEnumValue(value = "BYN") BYN("BYN"),
	
	/**
	 * Belize Dollar
	 */
	@RosettaEnumValue(value = "BZD") BZD("BZD"),
	
	/**
	 * Canadian Dollar
	 */
	@RosettaEnumValue(value = "CAD") CAD("CAD"),
	
	/**
	 * Congolese Franc
	 */
	@RosettaEnumValue(value = "CDF") CDF("CDF"),
	
	/**
	 * WIR Euro
	 */
	@RosettaEnumValue(value = "CHE") CHE("CHE"),
	
	/**
	 * Swiss Franc
	 */
	@RosettaEnumValue(value = "CHF") CHF("CHF"),
	
	/**
	 * WIR Franc
	 */
	@RosettaEnumValue(value = "CHW") CHW("CHW"),
	
	/**
	 * Unidad de Fomento
	 */
	@RosettaEnumValue(value = "CLF") CLF("CLF"),
	
	/**
	 * Chilean Peso
	 */
	@RosettaEnumValue(value = "CLP") CLP("CLP"),
	
	/**
	 * Yuan Renminbi
	 */
	@RosettaEnumValue(value = "CNY") CNY("CNY"),
	
	/**
	 * Colombian Peso
	 */
	@RosettaEnumValue(value = "COP") COP("COP"),
	
	/**
	 * Unidad de Valor Real
	 */
	@RosettaEnumValue(value = "COU") COU("COU"),
	
	/**
	 * Costa Rican Colon
	 */
	@RosettaEnumValue(value = "CRC") CRC("CRC"),
	
	/**
	 * Peso Convertible
	 */
	@RosettaEnumValue(value = "CUC") CUC("CUC"),
	
	/**
	 * Cuban Peso
	 */
	@RosettaEnumValue(value = "CUP") CUP("CUP"),
	
	/**
	 * Cabo Verde Escudo
	 */
	@RosettaEnumValue(value = "CVE") CVE("CVE"),
	
	/**
	 * Czech Koruna
	 */
	@RosettaEnumValue(value = "CZK") CZK("CZK"),
	
	/**
	 * Djibouti Franc
	 */
	@RosettaEnumValue(value = "DJF") DJF("DJF"),
	
	/**
	 * Danish Krone
	 */
	@RosettaEnumValue(value = "DKK") DKK("DKK"),
	
	/**
	 * Dominican Peso
	 */
	@RosettaEnumValue(value = "DOP") DOP("DOP"),
	
	/**
	 * Algerian Dinar
	 */
	@RosettaEnumValue(value = "DZD") DZD("DZD"),
	
	/**
	 * Egyptian Pound
	 */
	@RosettaEnumValue(value = "EGP") EGP("EGP"),
	
	/**
	 * Nakfa
	 */
	@RosettaEnumValue(value = "ERN") ERN("ERN"),
	
	/**
	 * Ethiopian Birr
	 */
	@RosettaEnumValue(value = "ETB") ETB("ETB"),
	
	/**
	 * Euro
	 */
	@RosettaEnumValue(value = "EUR") EUR("EUR"),
	
	/**
	 * Fiji Dollar
	 */
	@RosettaEnumValue(value = "FJD") FJD("FJD"),
	
	/**
	 * Falkland Islands Pound
	 */
	@RosettaEnumValue(value = "FKP") FKP("FKP"),
	
	/**
	 * Pound Sterling
	 */
	@RosettaEnumValue(value = "GBP") GBP("GBP"),
	
	/**
	 * Lari
	 */
	@RosettaEnumValue(value = "GEL") GEL("GEL"),
	
	/**
	 * Ghana Cedi
	 */
	@RosettaEnumValue(value = "GHS") GHS("GHS"),
	
	/**
	 * Gibraltar Pound
	 */
	@RosettaEnumValue(value = "GIP") GIP("GIP"),
	
	/**
	 * Dalasi
	 */
	@RosettaEnumValue(value = "GMD") GMD("GMD"),
	
	/**
	 * Guinean Franc
	 */
	@RosettaEnumValue(value = "GNF") GNF("GNF"),
	
	/**
	 * Quetzal
	 */
	@RosettaEnumValue(value = "GTQ") GTQ("GTQ"),
	
	/**
	 * Guyana Dollar
	 */
	@RosettaEnumValue(value = "GYD") GYD("GYD"),
	
	/**
	 * Hong Kong Dollar
	 */
	@RosettaEnumValue(value = "HKD") HKD("HKD"),
	
	/**
	 * Lempira
	 */
	@RosettaEnumValue(value = "HNL") HNL("HNL"),
	
	/**
	 * Gourde
	 */
	@RosettaEnumValue(value = "HTG") HTG("HTG"),
	
	/**
	 * Forint
	 */
	@RosettaEnumValue(value = "HUF") HUF("HUF"),
	
	/**
	 * Rupiah
	 */
	@RosettaEnumValue(value = "IDR") IDR("IDR"),
	
	/**
	 * New Israeli Sheqel
	 */
	@RosettaEnumValue(value = "ILS") ILS("ILS"),
	
	/**
	 * Indian Rupee
	 */
	@RosettaEnumValue(value = "INR") INR("INR"),
	
	/**
	 * Iraqi Dinar
	 */
	@RosettaEnumValue(value = "IQD") IQD("IQD"),
	
	/**
	 * Iranian Rial
	 */
	@RosettaEnumValue(value = "IRR") IRR("IRR"),
	
	/**
	 * Iceland Krona
	 */
	@RosettaEnumValue(value = "ISK") ISK("ISK"),
	
	/**
	 * Jamaican Dollar
	 */
	@RosettaEnumValue(value = "JMD") JMD("JMD"),
	
	/**
	 * Jordanian Dinar
	 */
	@RosettaEnumValue(value = "JOD") JOD("JOD"),
	
	/**
	 * Yen
	 */
	@RosettaEnumValue(value = "JPY") JPY("JPY"),
	
	/**
	 * Kenyan Shilling
	 */
	@RosettaEnumValue(value = "KES") KES("KES"),
	
	/**
	 * Som
	 */
	@RosettaEnumValue(value = "KGS") KGS("KGS"),
	
	/**
	 * Riel
	 */
	@RosettaEnumValue(value = "KHR") KHR("KHR"),
	
	/**
	 * Comorian Franc 
	 */
	@RosettaEnumValue(value = "KMF") KMF("KMF"),
	
	/**
	 * North Korean Won
	 */
	@RosettaEnumValue(value = "KPW") KPW("KPW"),
	
	/**
	 * Won
	 */
	@RosettaEnumValue(value = "KRW") KRW("KRW"),
	
	/**
	 * Kuwaiti Dinar
	 */
	@RosettaEnumValue(value = "KWD") KWD("KWD"),
	
	/**
	 * Cayman Islands Dollar
	 */
	@RosettaEnumValue(value = "KYD") KYD("KYD"),
	
	/**
	 * Tenge
	 */
	@RosettaEnumValue(value = "KZT") KZT("KZT"),
	
	/**
	 * Lao Kip
	 */
	@RosettaEnumValue(value = "LAK") LAK("LAK"),
	
	/**
	 * Lebanese Pound
	 */
	@RosettaEnumValue(value = "LBP") LBP("LBP"),
	
	/**
	 * Sri Lanka Rupee
	 */
	@RosettaEnumValue(value = "LKR") LKR("LKR"),
	
	/**
	 * Liberian Dollar
	 */
	@RosettaEnumValue(value = "LRD") LRD("LRD"),
	
	/**
	 * Loti
	 */
	@RosettaEnumValue(value = "LSL") LSL("LSL"),
	
	/**
	 * Libyan Dinar
	 */
	@RosettaEnumValue(value = "LYD") LYD("LYD"),
	
	/**
	 * Moroccan Dirham
	 */
	@RosettaEnumValue(value = "MAD") MAD("MAD"),
	
	/**
	 * Moldovan Leu
	 */
	@RosettaEnumValue(value = "MDL") MDL("MDL"),
	
	/**
	 * Malagasy Ariary
	 */
	@RosettaEnumValue(value = "MGA") MGA("MGA"),
	
	/**
	 * Denar
	 */
	@RosettaEnumValue(value = "MKD") MKD("MKD"),
	
	/**
	 * Kyat
	 */
	@RosettaEnumValue(value = "MMK") MMK("MMK"),
	
	/**
	 * Tugrik
	 */
	@RosettaEnumValue(value = "MNT") MNT("MNT"),
	
	/**
	 * Pataca
	 */
	@RosettaEnumValue(value = "MOP") MOP("MOP"),
	
	/**
	 * Ouguiya
	 */
	@RosettaEnumValue(value = "MRU") MRU("MRU"),
	
	/**
	 * Mauritius Rupee
	 */
	@RosettaEnumValue(value = "MUR") MUR("MUR"),
	
	/**
	 * Rufiyaa
	 */
	@RosettaEnumValue(value = "MVR") MVR("MVR"),
	
	/**
	 * Malawi Kwacha
	 */
	@RosettaEnumValue(value = "MWK") MWK("MWK"),
	
	/**
	 * Mexican Peso
	 */
	@RosettaEnumValue(value = "MXN") MXN("MXN"),
	
	/**
	 * Mexican Unidad de Inversion (UDI)
	 */
	@RosettaEnumValue(value = "MXV") MXV("MXV"),
	
	/**
	 * Malaysian Ringgit
	 */
	@RosettaEnumValue(value = "MYR") MYR("MYR"),
	
	/**
	 * Mozambique Metical
	 */
	@RosettaEnumValue(value = "MZN") MZN("MZN"),
	
	/**
	 * Namibia Dollar
	 */
	@RosettaEnumValue(value = "NAD") NAD("NAD"),
	
	/**
	 * Naira
	 */
	@RosettaEnumValue(value = "NGN") NGN("NGN"),
	
	/**
	 * Cordoba Oro
	 */
	@RosettaEnumValue(value = "NIO") NIO("NIO"),
	
	/**
	 * Norwegian Krone
	 */
	@RosettaEnumValue(value = "NOK") NOK("NOK"),
	
	/**
	 * Nepalese Rupee
	 */
	@RosettaEnumValue(value = "NPR") NPR("NPR"),
	
	/**
	 * New Zealand Dollar
	 */
	@RosettaEnumValue(value = "NZD") NZD("NZD"),
	
	/**
	 * Rial Omani
	 */
	@RosettaEnumValue(value = "OMR") OMR("OMR"),
	
	/**
	 * Balboa
	 */
	@RosettaEnumValue(value = "PAB") PAB("PAB"),
	
	/**
	 * Sol
	 */
	@RosettaEnumValue(value = "PEN") PEN("PEN"),
	
	/**
	 * Kina
	 */
	@RosettaEnumValue(value = "PGK") PGK("PGK"),
	
	/**
	 * Philippine Peso
	 */
	@RosettaEnumValue(value = "PHP") PHP("PHP"),
	
	/**
	 * Pakistan Rupee
	 */
	@RosettaEnumValue(value = "PKR") PKR("PKR"),
	
	/**
	 * Zloty
	 */
	@RosettaEnumValue(value = "PLN") PLN("PLN"),
	
	/**
	 * Guarani
	 */
	@RosettaEnumValue(value = "PYG") PYG("PYG"),
	
	/**
	 * Qatari Rial
	 */
	@RosettaEnumValue(value = "QAR") QAR("QAR"),
	
	/**
	 * Romanian Leu
	 */
	@RosettaEnumValue(value = "RON") RON("RON"),
	
	/**
	 * Serbian Dinar
	 */
	@RosettaEnumValue(value = "RSD") RSD("RSD"),
	
	/**
	 * Russian Ruble
	 */
	@RosettaEnumValue(value = "RUB") RUB("RUB"),
	
	/**
	 * Rwanda Franc
	 */
	@RosettaEnumValue(value = "RWF") RWF("RWF"),
	
	/**
	 * Saudi Riyal
	 */
	@RosettaEnumValue(value = "SAR") SAR("SAR"),
	
	/**
	 * Solomon Islands Dollar
	 */
	@RosettaEnumValue(value = "SBD") SBD("SBD"),
	
	/**
	 * Seychelles Rupee
	 */
	@RosettaEnumValue(value = "SCR") SCR("SCR"),
	
	/**
	 * Sudanese Pound
	 */
	@RosettaEnumValue(value = "SDG") SDG("SDG"),
	
	/**
	 * Swedish Krona
	 */
	@RosettaEnumValue(value = "SEK") SEK("SEK"),
	
	/**
	 * Singapore Dollar
	 */
	@RosettaEnumValue(value = "SGD") SGD("SGD"),
	
	/**
	 * Saint Helena Pound
	 */
	@RosettaEnumValue(value = "SHP") SHP("SHP"),
	
	/**
	 * Leone
	 */
	@RosettaEnumValue(value = "SLE") SLE("SLE"),
	
	/**
	 * Somali Shilling
	 */
	@RosettaEnumValue(value = "SOS") SOS("SOS"),
	
	/**
	 * Surinam Dollar
	 */
	@RosettaEnumValue(value = "SRD") SRD("SRD"),
	
	/**
	 * South Sudanese Pound
	 */
	@RosettaEnumValue(value = "SSP") SSP("SSP"),
	
	/**
	 * Dobra
	 */
	@RosettaEnumValue(value = "STN") STN("STN"),
	
	/**
	 * El Salvador Colon
	 */
	@RosettaEnumValue(value = "SVC") SVC("SVC"),
	
	/**
	 * Syrian Pound
	 */
	@RosettaEnumValue(value = "SYP") SYP("SYP"),
	
	/**
	 * Lilangeni
	 */
	@RosettaEnumValue(value = "SZL") SZL("SZL"),
	
	/**
	 * Baht
	 */
	@RosettaEnumValue(value = "THB") THB("THB"),
	
	/**
	 * Somoni
	 */
	@RosettaEnumValue(value = "TJS") TJS("TJS"),
	
	/**
	 * Turkmenistan New Manat
	 */
	@RosettaEnumValue(value = "TMT") TMT("TMT"),
	
	/**
	 * Tunisian Dinar
	 */
	@RosettaEnumValue(value = "TND") TND("TND"),
	
	/**
	 * Pa’anga
	 */
	@RosettaEnumValue(value = "TOP") TOP("TOP"),
	
	/**
	 * Turkish Lira
	 */
	@RosettaEnumValue(value = "TRY") TRY("TRY"),
	
	/**
	 * Trinidad and Tobago Dollar
	 */
	@RosettaEnumValue(value = "TTD") TTD("TTD"),
	
	/**
	 * New Taiwan Dollar
	 */
	@RosettaEnumValue(value = "TWD") TWD("TWD"),
	
	/**
	 * Tanzanian Shilling
	 */
	@RosettaEnumValue(value = "TZS") TZS("TZS"),
	
	/**
	 * Hryvnia
	 */
	@RosettaEnumValue(value = "UAH") UAH("UAH"),
	
	/**
	 * Uganda Shilling
	 */
	@RosettaEnumValue(value = "UGX") UGX("UGX"),
	
	/**
	 * US Dollar
	 */
	@RosettaEnumValue(value = "USD") USD("USD"),
	
	/**
	 * US Dollar (Next day)
	 */
	@RosettaEnumValue(value = "USN") USN("USN"),
	
	/**
	 * Uruguay Peso en Unidades Indexadas (UI)
	 */
	@RosettaEnumValue(value = "UYI") UYI("UYI"),
	
	/**
	 * Peso Uruguayo
	 */
	@RosettaEnumValue(value = "UYU") UYU("UYU"),
	
	/**
	 * Unidad Previsional
	 */
	@RosettaEnumValue(value = "UYW") UYW("UYW"),
	
	/**
	 * Uzbekistan Sum
	 */
	@RosettaEnumValue(value = "UZS") UZS("UZS"),
	
	/**
	 * Bolívar Soberano
	 */
	@RosettaEnumValue(value = "VED") VED("VED"),
	
	/**
	 * Bolívar Soberano
	 */
	@RosettaEnumValue(value = "VES") VES("VES"),
	
	/**
	 * Dong
	 */
	@RosettaEnumValue(value = "VND") VND("VND"),
	
	/**
	 * Vatu
	 */
	@RosettaEnumValue(value = "VUV") VUV("VUV"),
	
	/**
	 * Tala
	 */
	@RosettaEnumValue(value = "WST") WST("WST"),
	
	/**
	 * CFA Franc BEAC
	 */
	@RosettaEnumValue(value = "XAF") XAF("XAF"),
	
	/**
	 * Silver
	 */
	@RosettaEnumValue(value = "XAG") XAG("XAG"),
	
	/**
	 * Gold
	 */
	@RosettaEnumValue(value = "XAU") XAU("XAU"),
	
	/**
	 * Bond Markets Unit European Composite Unit (EURCO)
	 */
	@RosettaEnumValue(value = "XBA") XBA("XBA"),
	
	/**
	 * Bond Markets Unit European Monetary Unit (E.M.U.-6)
	 */
	@RosettaEnumValue(value = "XBB") XBB("XBB"),
	
	/**
	 * Bond Markets Unit European Unit of Account 9 (E.U.A.-9)
	 */
	@RosettaEnumValue(value = "XBC") XBC("XBC"),
	
	/**
	 * Bond Markets Unit European Unit of Account 17 (E.U.A.-17)
	 */
	@RosettaEnumValue(value = "XBD") XBD("XBD"),
	
	/**
	 * East Caribbean Dollar
	 */
	@RosettaEnumValue(value = "XCD") XCD("XCD"),
	
	/**
	 * SDR (Special Drawing Right)
	 */
	@RosettaEnumValue(value = "XDR") XDR("XDR"),
	
	/**
	 * CFA Franc BCEAO
	 */
	@RosettaEnumValue(value = "XOF") XOF("XOF"),
	
	/**
	 * Palladium
	 */
	@RosettaEnumValue(value = "XPD") XPD("XPD"),
	
	/**
	 * CFP Franc
	 */
	@RosettaEnumValue(value = "XPF") XPF("XPF"),
	
	/**
	 * Platinum
	 */
	@RosettaEnumValue(value = "XPT") XPT("XPT"),
	
	/**
	 * Sucre
	 */
	@RosettaEnumValue(value = "XSU") XSU("XSU"),
	
	/**
	 * Codes specifically reserved for testing purposes
	 */
	@RosettaEnumValue(value = "XTS") XTS("XTS"),
	
	/**
	 * ADB Unit of Account
	 */
	@RosettaEnumValue(value = "XUA") XUA("XUA"),
	
	/**
	 * The codes assigned for transactions where no currency is involved
	 */
	@RosettaEnumValue(value = "XXX") XXX("XXX"),
	
	/**
	 * Yemeni Rial
	 */
	@RosettaEnumValue(value = "YER") YER("YER"),
	
	/**
	 * Rand
	 */
	@RosettaEnumValue(value = "ZAR") ZAR("ZAR"),
	
	/**
	 * Zambian Kwacha
	 */
	@RosettaEnumValue(value = "ZMW") ZMW("ZMW"),
	
	/**
	 * Zimbabwe Dollar
	 */
	@RosettaEnumValue(value = "ZWL") ZWL("ZWL")
;
	private static Map<String, CurrencyCodeEnum> values;
	static {
        Map<String, CurrencyCodeEnum> map = new ConcurrentHashMap<>();
		for (CurrencyCodeEnum instance : CurrencyCodeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CurrencyCodeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CurrencyCodeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CurrencyCodeEnum fromDisplayName(String name) {
		CurrencyCodeEnum value = values.get(name);
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
