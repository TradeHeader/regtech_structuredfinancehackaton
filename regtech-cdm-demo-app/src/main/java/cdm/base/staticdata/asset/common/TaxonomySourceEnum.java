package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to specify taxonomy sources.
 * @version ${project.version}
 */
@RosettaEnum("TaxonomySourceEnum")
public enum TaxonomySourceEnum {

	/**
	 * Represents the ISO 10962 Classification of Financial Instruments code.
	 */
	@RosettaEnumValue(value = "CFI") CFI("CFI"),
	
	/**
	 * Represents the ISDA product taxonomy.
	 */
	@RosettaEnumValue(value = "ISDA") ISDA("ISDA"),
	
	/**
	 * Represents the ISDA Collateral Asset Definition Idenifier code.
	 */
	@RosettaEnumValue(value = "ICAD") ICAD("ICAD"),
	
	/**
	 * Represents the EMIR Article 9 Asset Definition Identifier code.
	 */
	@RosettaEnumValue(value = "EMIR") EMIR("EMIR"),
	
	/**
	 * Identifies European Union Eligible Collateral Assets classification categories based on EMIR Uncleared Margin Rules.
	 */
	@RosettaEnumValue(value = "EU_EMIR_EligibleCollateralAssetClass") EU_EMIR_ELIGIBLE_COLLATERAL_ASSET_CLASS("EU_EMIR_EligibleCollateralAssetClass"),
	
	/**
	 * Identifies United Kingdom Eligible Collateral Assets classification categories based on UK Onshored EMIR Uncleared Margin Rules Eligible Collateral asset classes for both initial margin (IM) and variation margin (VM) posted and collected between specified entities.Please note: UK EMIR regulation will detail which eligible collateral assets classes apply to each type of entity pairing (counterparty) and which apply to posting of IM and VM.
	 */
	@RosettaEnumValue(value = "UK_EMIR_EligibleCollateralAssetClass") UK_EMIR_ELIGIBLE_COLLATERAL_ASSET_CLASS("UK_EMIR_EligibleCollateralAssetClass"),
	
	/**
	 * Identifies US Eligible Collateral Assets classification categories based on Uncleared Margin Rules published by the CFTC and the US Prudential Regulator. Note: While the same basic categories exist in the CFTC and US Prudential Regulators margin rules, the precise definitions or application of those rules could differ between the two rules.
	 */
	@RosettaEnumValue(value = "US_CFTC_PR_EligibleCollateralAssetClass") US_CFTC_PR_ELIGIBLE_COLLATERAL_ASSET_CLASS("US_CFTC_PR_EligibleCollateralAssetClass"),
	
	/**
	 * Denotes a user-specific scheme or taxonomy or other external sources not listed here.
	 */
	@RosettaEnumValue(value = "Other") OTHER("Other")
;
	private static Map<String, TaxonomySourceEnum> values;
	static {
        Map<String, TaxonomySourceEnum> map = new ConcurrentHashMap<>();
		for (TaxonomySourceEnum instance : TaxonomySourceEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TaxonomySourceEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TaxonomySourceEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TaxonomySourceEnum fromDisplayName(String name) {
		TaxonomySourceEnum value = values.get(name);
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
