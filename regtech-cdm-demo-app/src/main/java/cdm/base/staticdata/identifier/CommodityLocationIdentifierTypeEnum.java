package cdm.base.staticdata.identifier;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines the enumerated values to specify the nature of a location identifier.
 * @version ${project.version}
 */
@RosettaEnum("CommodityLocationIdentifierTypeEnum")
public enum CommodityLocationIdentifierTypeEnum {

	/**
	 * The physical or virtual point at which the commodity will be delivered.
	 */
	@RosettaEnumValue(value = "DeliveryPoint") DELIVERY_POINT("DeliveryPoint"),
	
	/**
	 * The physical or virtual point at which the commodity enters a transportation system.
	 */
	@RosettaEnumValue(value = "EntryPoint") ENTRY_POINT("EntryPoint"),
	
	/**
	 * Identification of the border(s) or border point(s) of a transportation contract.
	 */
	@RosettaEnumValue(value = "InterconnectionPoint") INTERCONNECTION_POINT("InterconnectionPoint"),
	
	/**
	 * The physical or virtual point at which the commodity is withdrawn from a transportation system.
	 */
	@RosettaEnumValue(value = "WithdrawalPoint") WITHDRAWAL_POINT("WithdrawalPoint"),
	
	/**
	 * The zone covering potential delivery points for the commodity
	 */
	@RosettaEnumValue(value = "DeliveryZone") DELIVERY_ZONE("DeliveryZone"),
	
	/**
	 * The hub code of the buyer.
	 */
	@RosettaEnumValue(value = "BuyerHub") BUYER_HUB("BuyerHub"),
	
	/**
	 * The hub code of the seller.
	 */
	@RosettaEnumValue(value = "SellerHub") SELLER_HUB("SellerHub")
;
	private static Map<String, CommodityLocationIdentifierTypeEnum> values;
	static {
        Map<String, CommodityLocationIdentifierTypeEnum> map = new ConcurrentHashMap<>();
		for (CommodityLocationIdentifierTypeEnum instance : CommodityLocationIdentifierTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CommodityLocationIdentifierTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CommodityLocationIdentifierTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CommodityLocationIdentifierTypeEnum fromDisplayName(String name) {
		CommodityLocationIdentifierTypeEnum value = values.get(name);
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
