package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import com.rosetta.model.lib.annotations.RosettaSynonym;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the date on which the receiver of the equity payout is entitled to the dividend.
 * @version ${project.version}
 */
@RosettaEnum("DividendEntitlementEnum")
public enum DividendEntitlementEnum {

	/**
	 * Dividend entitlement is on the dividend ex-date.
	 */
	@RosettaEnumValue(value = "ExDate") EX_DATE("ExDate"),
	
	/**
	 * Dividend entitlement is on the dividend record date.
	 */
	@RosettaSynonym(value = "RecordDate", source = "FIX_5_0_SP2")
	@RosettaEnumValue(value = "RecordDate") RECORD_DATE("RecordDate")
;
	private static Map<String, DividendEntitlementEnum> values;
	static {
        Map<String, DividendEntitlementEnum> map = new ConcurrentHashMap<>();
		for (DividendEntitlementEnum instance : DividendEntitlementEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DividendEntitlementEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DividendEntitlementEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DividendEntitlementEnum fromDisplayName(String name) {
		DividendEntitlementEnum value = values.get(name);
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
