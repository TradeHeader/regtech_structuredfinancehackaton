package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to specify how the option is to be settled when exercised.
 * @version ${project.version}
 */
@RosettaEnum("SettlementTypeEnum")
public enum SettlementTypeEnum {

	/**
	 * The intrinsic value of the option will be delivered by way of a cash settlement amount determined, (i) by reference to the differential between the strike price and the settlement price; or (ii) in accordance with a bilateral agreement between the parties.
	 */
	@RosettaEnumValue(value = "Cash") CASH("Cash"),
	
	/**
	 * The securities underlying the transaction will be delivered by (i) in the case of a call, the seller to the buyer, or (ii) in the case of a put, the buyer to the seller versus a settlement amount equivalent to the strike price per share.
	 */
	@RosettaEnumValue(value = "Physical") PHYSICAL("Physical"),
	
	/**
	 * Allow Election of either Cash or Physical settlement.
	 */
	@RosettaEnumValue(value = "Election") ELECTION("Election"),
	
	/**
	 * Allow use of either Cash or Physical settlement without prior Election.
	 */
	@RosettaEnumValue(value = "CashOrPhysical") CASH_OR_PHYSICAL("CashOrPhysical")
;
	private static Map<String, SettlementTypeEnum> values;
	static {
        Map<String, SettlementTypeEnum> map = new ConcurrentHashMap<>();
		for (SettlementTypeEnum instance : SettlementTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SettlementTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SettlementTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SettlementTypeEnum fromDisplayName(String name) {
		SettlementTypeEnum value = values.get(name);
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
