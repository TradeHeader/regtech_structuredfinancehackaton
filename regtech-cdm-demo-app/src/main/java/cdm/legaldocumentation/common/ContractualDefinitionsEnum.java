package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify a set of standard contract definitions relevant to the transaction.
 * @version ${project.version}
 */
@RosettaEnum("ContractualDefinitionsEnum")
public enum ContractualDefinitionsEnum {

	/**
	 * ISDA 1991 Interest Rate Definitions
	 */
	@RosettaEnumValue(value = "ISDA1991InterestRate") ISDA_1991_INTEREST_RATE("ISDA1991InterestRate"),
	
	/**
	 * ISDA 1993 Commodity Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA1993CommodityDerivatives") ISDA_1993_COMMODITY_DERIVATIVES("ISDA1993CommodityDerivatives"),
	
	/**
	 * ISDA 1996 Equity Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA1996EquityDerivatives") ISDA_1996_EQUITY_DERIVATIVES("ISDA1996EquityDerivatives"),
	
	/**
	 * ISDA 1997 Bullion Definitions
	 */
	@RosettaEnumValue(value = "ISDA1997Bullion") ISDA_1997_BULLION("ISDA1997Bullion"),
	
	/**
	 * ISDA 1997 Government Bond Option Definitions
	 */
	@RosettaEnumValue(value = "ISDA1997GovernmentBondOption") ISDA_1997_GOVERNMENT_BOND_OPTION("ISDA1997GovernmentBondOption"),
	
	/**
	 * ISDA 1998 FX and Currency Option Definitions
	 */
	@RosettaEnumValue(value = "ISDA1998FxAndCurrencyOption") ISDA_1998_FX_AND_CURRENCY_OPTION("ISDA1998FxAndCurrencyOption"),
	
	/**
	 * ISDA 1999 Credit Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA1999CreditDerivatives") ISDA_1999_CREDIT_DERIVATIVES("ISDA1999CreditDerivatives"),
	
	/**
	 * ISDA 2000 Definitions
	 */
	@RosettaEnumValue(value = "ISDA2000") ISDA2000("ISDA2000"),
	
	/**
	 * ISDA 2002 Equity Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2002EquityDerivatives") ISDA_2002_EQUITY_DERIVATIVES("ISDA2002EquityDerivatives"),
	
	/**
	 * ISDA 2003 Credit Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2003CreditDerivatives") ISDA_2003_CREDIT_DERIVATIVES("ISDA2003CreditDerivatives"),
	
	/**
	 * ISDA 2004 Novation Definitions
	 */
	@RosettaEnumValue(value = "ISDA2004Novation") ISDA_2004_NOVATION("ISDA2004Novation"),
	
	/**
	 * ISDA 2005 Commodity Definitions
	 */
	@RosettaEnumValue(value = "ISDA2005Commodity") ISDA_2005_COMMODITY("ISDA2005Commodity"),
	
	/**
	 * ISDA 2006 Definitions
	 */
	@RosettaEnumValue(value = "ISDA2006") ISDA2006("ISDA2006"),
	
	/**
	 * ISDA 2006 Inflation Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2006InflationDerivatives") ISDA_2006_INFLATION_DERIVATIVES("ISDA2006InflationDerivatives"),
	
	/**
	 * ISDA 2008 Inflation Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2008InflationDerivatives") ISDA_2008_INFLATION_DERIVATIVES("ISDA2008InflationDerivatives"),
	
	/**
	 * ISDA 2011 Equity Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2011EquityDerivatives") ISDA_2011_EQUITY_DERIVATIVES("ISDA2011EquityDerivatives"),
	
	/**
	 * ISDA 2014 Credit Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2014CreditDerivatives") ISDA_2014_CREDIT_DERIVATIVES("ISDA2014CreditDerivatives"),
	
	/**
	 * ISDA 2021 Interest Rate Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2021InterestRateDerivatives") ISDA_2021_INTEREST_RATE_DERIVATIVES("ISDA2021InterestRateDerivatives"),
	
	/**
	 * ISDA 2021 Digital Asset Derivatives Definitions
	 */
	@RosettaEnumValue(value = "ISDA2023DigitalAssetDerivatives") ISDA_2023_DIGITAL_ASSET_DERIVATIVES("ISDA2023DigitalAssetDerivatives")
;
	private static Map<String, ContractualDefinitionsEnum> values;
	static {
        Map<String, ContractualDefinitionsEnum> map = new ConcurrentHashMap<>();
		for (ContractualDefinitionsEnum instance : ContractualDefinitionsEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ContractualDefinitionsEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ContractualDefinitionsEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ContractualDefinitionsEnum fromDisplayName(String name) {
		ContractualDefinitionsEnum value = values.get(name);
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
