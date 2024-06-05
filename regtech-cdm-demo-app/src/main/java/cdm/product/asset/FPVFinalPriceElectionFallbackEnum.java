package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies the fallback provisions in respect to the applicable Futures Price Valuation.
 * @version ${project.version}
 */
@RosettaEnum("FPVFinalPriceElectionFallbackEnum")
public enum FPVFinalPriceElectionFallbackEnum {

	/**
	 * In respect of the Early Final Valuation Date, the provisions for FPV Close shall apply.
	 */
	@RosettaEnumValue(value = "FPVClose") FPV_CLOSE("FPVClose"),
	
	/**
	 * In respect of the Early Final Valuation Date, the provisions for FPV Hedge Execution shall apply.
	 */
	@RosettaEnumValue(value = "FPVHedgeExecution") FPV_HEDGE_EXECUTION("FPVHedgeExecution")
;
	private static Map<String, FPVFinalPriceElectionFallbackEnum> values;
	static {
        Map<String, FPVFinalPriceElectionFallbackEnum> map = new ConcurrentHashMap<>();
		for (FPVFinalPriceElectionFallbackEnum instance : FPVFinalPriceElectionFallbackEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FPVFinalPriceElectionFallbackEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FPVFinalPriceElectionFallbackEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FPVFinalPriceElectionFallbackEnum fromDisplayName(String name) {
		FPVFinalPriceElectionFallbackEnum value = values.get(name);
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
