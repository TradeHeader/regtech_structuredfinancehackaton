package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides enumerated values for types of prices in the Price data type in order to explain how to interpret the amount and use it in calculations.
 * @version ${project.version}
 */
@RosettaEnum("PriceTypeEnum")
public enum PriceTypeEnum {

	/**
	 * Denotes a price expressed as a cash amount in a given currency to purchase a unit of an asset (e.g. a security or a commodity).
	 */
	@RosettaEnumValue(value = "AssetPrice") ASSET_PRICE("AssetPrice"),
	
	/**
	 * Denotes a price expressed as a cash amount for an upfront fee or other purposes. For example, {amount, unitOfAmount, PerUnitOfAmount} = [12,500, USD, null] = USD 12,500.
	 */
	@RosettaEnumValue(value = "CashPrice") CASH_PRICE("CashPrice"),
	
	/**
	 * Denotes a price expressed as the weighted average of all pairwise correlation coefficients.
	 */
	@RosettaEnumValue(value = "Correlation") CORRELATION("Correlation"),
	
	/**
	 * Denotes a price expressed as the dividend payment from a index or share.
	 */
	@RosettaEnumValue(value = "Dividend") DIVIDEND("Dividend"),
	
	/**
	 * Denotes a rate to convert one currency or other measure of value to another. Foreign Exchange rates are represented in decimals, e.g. {amount, unitOfAmount, PerUnitOfAmount} = [1.23, USD, GBP] = USD 1.23 for every 1 GBP.
	 */
	@RosettaEnumValue(value = "ExchangeRate") EXCHANGE_RATE("ExchangeRate"),
	
	/**
	 * Denotes a price expressed as a rate to be applied to quantity/notional amount and represented as decimal, e.g. {amount, unitOfAmount, PerUnitOfAmount} = [0.08, EUR, EUR] = 8%  of the EUR notional quantity/amount or 8 cents for every EUR of notional amount.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Pricing Rate"
	 *
	 * Provision As defined in the GMRA, paragraph 2(ll) The pricing rate is the per annum percentage rate for calculation of the Price Differential agreed to by Buyer and Seller in relation to that Transaction.
	 *
	 *
	 * Body ICMA
	 * Corpus Guidance ERCCBestPractice ERCC Guide to Best Practice in the European Repo Market ERCC Guide to Best Practice in the European Repo Market "The ERCC Guide to Best Practice in the European Repo Market is published by ICMAs European Repo and Collateral Council (ERCC). Its purpose is to help foster a fair and efficient European repo market by recommending practices which market experience suggests can help avoid uncertainty or disagreement about transactions, and consequent delay or disruption to repo trading and settlement. With the same purpose in mind, the Guide also codifies market conventions, where this has been thought to be helpful, usually in response to queries from market participants." 
	 * namingConvention "Pricing Rate"
	 *
	 * Provision ERCC Guide: Annex II  Glossary of repo terminology. Repo rate is the market term for the annualised percentage rate of interest on the cash in a repo. Legally-speaking, however, the term is a misnomer, as the legal form of a repo is not an interest-paying loan or deposit. Rather, the return is just the difference between two securities prices. In the GMRA, the repo rate is called the Pricing Rate. Traditionally, the repo rate was the price of a Repurchase Transaction but Buy/Sell-Backs are now often quoted in the same way.
	 *
	 */
	@RosettaEnumValue(value = "InterestRate") INTEREST_RATE("InterestRate"),
	
	/**
	 * Denotes a price expressed as the the arithmetic average of the squared differences from the mean value of an observable price.
	 */
	@RosettaEnumValue(value = "Variance") VARIANCE("Variance"),
	
	/**
	 * Denotes a price expressed as the the square root of the arithmetic average of the squared differences from the mean value of an observable price.
	 */
	@RosettaEnumValue(value = "Volatility") VOLATILITY("Volatility")
;
	private static Map<String, PriceTypeEnum> values;
	static {
        Map<String, PriceTypeEnum> map = new ConcurrentHashMap<>();
		for (PriceTypeEnum instance : PriceTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PriceTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PriceTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PriceTypeEnum fromDisplayName(String name) {
		PriceTypeEnum value = values.get(name);
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
