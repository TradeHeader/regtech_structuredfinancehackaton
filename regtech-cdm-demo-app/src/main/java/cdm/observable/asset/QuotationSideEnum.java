package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the side from which perspective a value is quoted.
 * @version ${project.version}
 */
@RosettaEnum("QuotationSideEnum")
public enum QuotationSideEnum {

	/**
	 * Denotes a value as the Afternoon fixing reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Afternoon") AFTERNOON("Afternoon"),
	
	/**
	 * Denotes a value &#39;asked&#39; by a seller for an asset, i.e. the value at which a seller is willing to sell.
	 */
	@RosettaEnumValue(value = "Ask") ASK("Ask"),
	
	/**
	 * Denotes a value &#39;bid&#39; by a buyer for an asset, i.e. the value a buyer is willing to pay.
	 */
	@RosettaEnumValue(value = "Bid") BID("Bid"),
	
	/**
	 * Denotes a value as the Closing price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Closing") CLOSING("Closing"),
	
	/**
	 * Denotes a value as the High price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "High") HIGH("High"),
	
	/**
	 * Denotes a value as the Index price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Index") INDEX("Index"),
	
	/**
	 * Denotes a value as the Average of the Bid and Ask prices reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "MeanOfBidAndAsk") MEAN_OF_BID_AND_ASK("MeanOfBidAndAsk"),
	
	/**
	 * Denotes a value as the Locational Marginal price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "LocationalMarginal") LOCATIONAL_MARGINAL("LocationalMarginal"),
	
	/**
	 * Denotes a value as the Low price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Low") LOW("Low"),
	
	/**
	 * Denotes a value as the Marginal Hourly price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "MarginalHourly") MARGINAL_HOURLY("MarginalHourly"),
	
	/**
	 * Denotes a value as the Market Clearing price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "MarketClearing") MARKET_CLEARING("MarketClearing"),
	
	/**
	 * Denotes a value as the Average of the High and Low prices reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "MeanOfHighAndLow") MEAN_OF_HIGH_AND_LOW("MeanOfHighAndLow"),
	
	/**
	 * Denotes a value as the Morning fixing reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Morning") MORNING("Morning"),
	
	/**
	 * Denotes a value as the Official price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Official") OFFICIAL("Official"),
	
	/**
	 * Denotes a value as the Opening price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Opening") OPENING("Opening"),
	
	/**
	 * Denotes a value as the Official Settlement Price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "OSP") OSP("OSP"),
	
	/**
	 * Denotes a value as the Settlement price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Settlement") SETTLEMENT("Settlement"),
	
	/**
	 * Denotes a value as the Spot price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Spot") SPOT("Spot"),
	
	/**
	 * Denotes a value as the Average of the Midpoint of prices reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "Mid") MID("Mid"),
	
	/**
	 * Denotes a value as the National Single price reported in or by the relevant Price Source as specified in the relevant Confirmation.
	 */
	@RosettaEnumValue(value = "NationalSingle") NATIONAL_SINGLE("NationalSingle"),
	
	/**
	 * Denotes a value as the Volume Weighted Average of prices effective on the Pricing Date.
	 */
	@RosettaEnumValue(value = "WeightedAverage") WEIGHTED_AVERAGE("WeightedAverage"),
	
	/**
	 * Denotes a value as the Non-volume Weighted Average of prices effective on the Pricing Date.
	 */
	@RosettaEnumValue(value = "UnWeightedAverage") UN_WEIGHTED_AVERAGE("UnWeightedAverage")
;
	private static Map<String, QuotationSideEnum> values;
	static {
        Map<String, QuotationSideEnum> map = new ConcurrentHashMap<>();
		for (QuotationSideEnum instance : QuotationSideEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	QuotationSideEnum(String rosettaName) {
		this(rosettaName, null);
	}

	QuotationSideEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static QuotationSideEnum fromDisplayName(String name) {
		QuotationSideEnum value = values.get(name);
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
