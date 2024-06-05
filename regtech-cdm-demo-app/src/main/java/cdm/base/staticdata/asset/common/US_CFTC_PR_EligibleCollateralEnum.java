package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Identifies US Eligible Collateral Assets classification categories based on Uncleared Margin Rules published by the CFTC and the US Prudential Regulator. Note: While the same basic categories exist in the CFTC and US Prudential Regulators margin rules, the precise definitions or application of those rules could differ between the two rules.
 * @version ${project.version}
 */
@RosettaEnum("US_CFTC_PR_EligibleCollateralEnum")
public enum US_CFTC_PR_EligibleCollateralEnum {

	/**
	 * Denotes immediately available cash funds denominated in USD, a major currency, a currency of settlement for the uncleared swap.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType1") US_CFTC_PR_TYPE_1("US_CFTC_PRType1"),
	
	/**
	 * Denotes a security that is issued by, or unconditionally guaranteed as to the timely payment of principal and interest by, the U.S. Department of the Treasury.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType2") US_CFTC_PR_TYPE_2("US_CFTC_PRType2"),
	
	/**
	 * Denotes a security that is issued by, or unconditionally guaranteed as to the timely payment of principal and interest by, a U.S. government agency (other than the U.S. Department of Treasury) whose obligations are fully guaranteed by the full faith and credit of the United States government.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType3") US_CFTC_PR_TYPE_3("US_CFTC_PRType3"),
	
	/**
	 * Denotes a security that is issued by, or fully guaranteed as to the payment of principal and interest by, the European Central Bank or a sovereign entity that is assigned no higher than a 20 percent risk weight under the capital rules applicable to swap dealers subject to regulation by a prudential regulator.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType4") US_CFTC_PR_TYPE_4("US_CFTC_PRType4"),
	
	/**
	 * Denotes a publicly traded debt security issued by, or an asset-backed security fully guaranteed as to the timely payment of principal and interest by, a U.S. Government-sponsored enterprise that is operating with capital support or another form of direct financial assistance received from the U.S. government that enables the repayments of the U.S. Government-sponsored enterprise&#39;s eligible securities.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType5A") US_CFTC_PR_TYPE_5_A("US_CFTC_PRType5A"),
	
	/**
	 * Denotes a publicly traded debt security, but not an asset backed security, that is investment grade and issued by a U.S. Government-sponsored enterprise that is not operating with capital support or another form of direct financial assistance received from the U.S. government.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType5B") US_CFTC_PR_TYPE_5_B("US_CFTC_PRType5B"),
	
	/**
	 * Denotes a security that is issued by, or fully guaranteed as to the payment of principal and interest by, the Bank for International Settlements, the International Monetary Fund, or a multilateral development bank.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType6") US_CFTC_PR_TYPE_6("US_CFTC_PRType6"),
	
	/**
	 * Denotes publicly-traded debt, but not an asset backed security, that is investment grade and is not a debt security issued by a  U.S. Government-sponsored enterprise. This category excludes a security issued by a non-bank financial institution supervised by the board of governors of the Federal Reserve System under Title I of the Dodd-Frank Wall Street Reform and Consumer Protection Act. This category also excludes a security issued by any of the following entities, by a company that would be any of the following entities if it were the organized under the laws of the United States or any State, or in either case by an affiliate of such an entity: the party posting the collateral, a bank holding company, a savings and loan holding company, a U.S. intermediate holding company, a foreign bank, a depositary institution, a securities holding company, a broker, a dealer, a futures commission merchant, a swap dealer, or a security-based swap dealer.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType7") US_CFTC_PR_TYPE_7("US_CFTC_PRType7"),
	
	/**
	 * Denotes a publicly traded common equity security that is included in the Standard &amp; Poor&#39;s Composite 500 Index or related indexes. This category excludes a security issued by a non-bank financial institution supervised by the board of governors of the Federal Reserve System under Title I of the Dodd-Frank Wall Street Reform and Consumer Protection Act. This category also excludes a security issued by any of the following entities, by a company that would be any of the following entities if it were the organized under the laws of the United States or any State, or in either case by an affiliate of such an entity: the party posting the collateral, a bank holding company, a savings and loan holding company, a U.S. intermediate holding company, a foreign bank, a depositary institution, a securities holding company, a broker, a dealer, a futures commission merchant, a swap dealer, or a security-based swap dealer.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType8A") US_CFTC_PR_TYPE_8_A("US_CFTC_PRType8A"),
	
	/**
	 *  Denotes a publicly traded common equity security that is included in the Standard &amp; Poor&#39;s Composite 1500 Index or related indexes. This category excludes a security issued by a non-bank financial institution supervised by the board of governors of the Federal Reserve System under Title I of the Dodd-Frank Wall Street Reform and Consumer Protection Act. This category also excludes a security issued by any of the following entities, by a company that would be any of the following entities if it were the organized under the laws of the United States or any State, or in either case by an affiliate of such an entity: the party posting the collateral, a bank holding company, a savings and loan holding company, a U.S. intermediate holding company, a foreign bank, a depositary institution, a securities holding company, a broker, a dealer, a futures commission merchant, a swap dealer, or a security-based swap dealer.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType8B") US_CFTC_PR_TYPE_8_B("US_CFTC_PRType8B"),
	
	/**
	 * Denotes a publicly traded common equity security that is included in an index that a regulated swap entity&#39;s supervisor in a foreign jurisdiction recognizes for purposes of including publicly traded common equity as initial margin under applicable regulatory policy, if held in that foreign jurisdiction. This category excludes a security issued by a non-bank financial institution supervised by the board of governors of the Federal Reserve System under Title I of the Dodd-Frank Wall Street Reform and Consumer Protection Act. This category also excludes a security issued by any of the following entities, by a company that would be any of the following entities if it were the organized under the laws of the United States or any State, or in either case by an affiliate of such an entity: the party posting the collateral, a bank holding company, a savings and loan holding company, a U.S. intermediate holding company, a foreign bank, a depositary institution, a securities holding company, a broker, a dealer, a futures commission merchant, a swap dealer, or a security-based swap dealer.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType8C") US_CFTC_PR_TYPE_8_C("US_CFTC_PRType8C"),
	
	/**
	 * Denotes securities in the form of redeemable securities in a pooled investment fund representing the security-holder&#39;s proportional interest in the fund&#39;s net assets and that are issued and redeemed only on the basis of the market value of the fund&#39;s net assets prepared each business day after the security-holder makes its investment commitment or redemption request to the fund, if the fund&#39;s investments are limited to the following: (A) securities that are issued by, or unconditionally guaranteed as to the timely payment of principal and interest by, the U.S. Department of the Treasury, and immediately-available cash funds denominated in U.S. dollars; or (B) securities denominated in a common currency and issued by, or fully guaranteed as to the payment of principal and interest by, the European Central Bank or a sovereign entity that is assigned no higher than a 20 percent risk weight under the capital rules applicable to swap dealers subject to regulation by a prudential regulator, and immediately-available cash funds denominated in the same currency; and (C) assets of the fund may not be transferred through securities lending, securities borrowing, repurchase agreements, reverse repurchase agreements, or other means that involve the fund having rights to acquire the same or similar assets from the transferee.
	 */
	@RosettaEnumValue(value = "US_CFTC_PRType9") US_CFTC_PR_TYPE_9("US_CFTC_PRType9"),
	
	/**
	 * Denotes Gold.
	 */
	@RosettaEnumValue(value = "US_CTFC_PRType10") US_CTFC_PR_TYPE_10("US_CTFC_PRType10")
;
	private static Map<String, US_CFTC_PR_EligibleCollateralEnum> values;
	static {
        Map<String, US_CFTC_PR_EligibleCollateralEnum> map = new ConcurrentHashMap<>();
		for (US_CFTC_PR_EligibleCollateralEnum instance : US_CFTC_PR_EligibleCollateralEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	US_CFTC_PR_EligibleCollateralEnum(String rosettaName) {
		this(rosettaName, null);
	}

	US_CFTC_PR_EligibleCollateralEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static US_CFTC_PR_EligibleCollateralEnum fromDisplayName(String name) {
		US_CFTC_PR_EligibleCollateralEnum value = values.get(name);
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
