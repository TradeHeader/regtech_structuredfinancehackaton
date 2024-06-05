package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides the enumerated values to specify the product identifier source.
 * @version ${project.version}
 */
@RosettaEnum("ProductIdTypeEnum")
public enum ProductIdTypeEnum {

	/**
	 * Published by Bloomberg, the BBGID is a 12-digit alphanumeric randomly generated ID covering active and non-active securities.
	 */
	@RosettaEnumValue(value = "BBGID") BBGID("BBGID"),
	
	/**
	 * Published by Bloomberg as a short code to identify publicly trades shares of a particular stock on a specific exchange.
	 */
	@RosettaEnumValue(value = "BBGTICKER") BBGTICKER("BBGTICKER"),
	
	/**
	 * Derived from the Committee on Uniform Security Identification Procedures, CUSIPs are 9-character identifiers that capture an issue’s important differentiating characteristics for issuers and their financial instruments in the U.S. and Canada.
	 */
	@RosettaEnumValue(value = "CUSIP") CUSIP("CUSIP"),
	
	/**
	 * Issued under the guidelines of the Object Management Group, the Financial Instrument Global Identifier (FIGI) is a 12 character, alphanumeric, randomly generated ID covering hundreds of millions of active and inactive instruments. The identifier acts as a Uniform Resource Identifier (URI) to link to a set of metadata that uniquely and clearly describes the instrument.
	 */
	@RosettaEnumValue(value = "FIGI") FIGI("FIGI"),
	
	/**
	 * Issued by the International Swaps Dealers Association as a string representing a Commodity Reference Price used for purposes of determining a relevant price for an underlying commodity in an OTC derivatives contract.
	 */
	@RosettaEnumValue(value = "ISDACRP") ISDACRP("ISDACRP"),
	
	/**
	 * Issued by The International Securities Identification Number (ISIN) Organization, the ISIN is a 12-character alpha-numerical code used to uniformly identify a security for trading and settlement purposes. Securities with which ISINs can be used include debt securities, such as notes or bonds as well shares, such as common stock or shares of a fund, options, derivatives, and futures. The ISIN structure is defined in ISO 6166.
	 */
	@RosettaEnumValue(value = "ISIN") ISIN("ISIN"),
	
	/**
	 * The name of the product.
	 */
	@RosettaEnumValue(value = "Name") NAME("Name"),
	
	/**
	 * Issued by Refinitiv (formerly Reuters), the Reuters Instrument Codes(RIC) uniquely identifies financial instruments, including where they are traded.
	 */
	@RosettaEnumValue(value = "RIC") RIC("RIC"),
	
	/**
	 * Used when the source is not otherwise in this enumerated list because it is internal or other reasons.  The source can be identified in the scheme which is part of the identifier attribute.
	 */
	@RosettaEnumValue(value = "Other") OTHER("Other"),
	
	/**
	 * Issued by the French Société Interprofessionnelle pour la Compensation des Valeurs Mobilières (SICOVAM) to identify French securities listed on French stock exchanges.
	 */
	@RosettaEnumValue(value = "Sicovam") SICOVAM("Sicovam"),
	
	/**
	 * Assigned by the London Stock Exchange, the Stock Exchange Daily Official List (SEDOL) is a list of security identifiers used in the United Kingdom and Ireland for clearing purposes.  SEDOLs serve as the National Securities Identifying Number for all securities issued in the United Kingdom and are therefore part of the security&#39;s ISIN as well.
	 */
	@RosettaEnumValue(value = "SEDOL") SEDOL("SEDOL"),
	
	/**
	 * Assigned by the Derivatives Service Bureau Ltd (DSB), the Unique Product Identifier (UPI) is a unique code to describe an over-the-counter (OTC) derivatives product.  The UPI is used for identifying the product in transaction reporting data.
	 */
	@RosettaEnumValue(value = "UPI") UPI("UPI"),
	
	/**
	 * Issued by the Institute for the Issuance and Administration of Securities in Germany (Securities Information), the Wertpapierkennnummer (WKN, WPKN, WPK or simply Wert) consists of six digits or capital letters (excluding I and O), and no check digit. It is used to identify German securities.
	 */
	@RosettaEnumValue(value = "Wertpapier") WERTPAPIER("Wertpapier")
;
	private static Map<String, ProductIdTypeEnum> values;
	static {
        Map<String, ProductIdTypeEnum> map = new ConcurrentHashMap<>();
		for (ProductIdTypeEnum instance : ProductIdTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ProductIdTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ProductIdTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ProductIdTypeEnum fromDisplayName(String name) {
		ProductIdTypeEnum value = values.get(name);
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
