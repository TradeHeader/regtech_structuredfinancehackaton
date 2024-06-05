package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * If there is an alternative to interest amounts, how is it specified?
 * @version ${project.version}
 */
@RosettaEnum("AlternativeToInterestAmountEnum")
public enum AlternativeToInterestAmountEnum {

	/**
	 * Interest amount is not transferred if transfer would create or increase a delivery amount.
	 */
	@RosettaEnumValue(value = "Standard") STANDARD("Standard"),
	
	/**
	 * The standard calculation of the Interest Amount is replaced with the amount of interest the secured party actually receives in relation to the Cash collateral.
	 */
	@RosettaEnumValue(value = "ActualAmountReceived") ACTUAL_AMOUNT_RECEIVED("ActualAmountReceived"),
	
	/**
	 * Interest amount is not transferred if transfer would create or increase a delivery amount. (This is the &#39;Standard&#39; provision). However, interest Amount will be transferred if Delivery Amount is below Minimum Transfer Amount.
	 */
	@RosettaEnumValue(value = "TransferIfDeliveryAmountBelowMTA") TRANSFER_IF_DELIVERY_AMOUNT_BELOW_MTA("TransferIfDeliveryAmountBelowMTA"),
	
	/**
	 * An other alternative option outside these choices that can be described as an alternative provision.
	 */
	@RosettaEnumValue(value = "Other") OTHER("Other")
;
	private static Map<String, AlternativeToInterestAmountEnum> values;
	static {
        Map<String, AlternativeToInterestAmountEnum> map = new ConcurrentHashMap<>();
		for (AlternativeToInterestAmountEnum instance : AlternativeToInterestAmountEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AlternativeToInterestAmountEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AlternativeToInterestAmountEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AlternativeToInterestAmountEnum fromDisplayName(String name) {
		AlternativeToInterestAmountEnum value = values.get(name);
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
