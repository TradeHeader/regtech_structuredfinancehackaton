package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to specify the Commodity Reference Prices specified in the Annex to the 2005 ISDA Commodity Definitions.
 * @version ${project.version}
 */
@RosettaEnum("CommodityReferencePriceEnum")
public enum CommodityReferencePriceEnum {

	/**
	 * Per 2005 ISDA Commodity Definitions, Sub-Annex A, Section 7.1 Commodity Reference Prices, as amended and supplemented through the date on which parties enter into the relevant transaction.
	 */
	@RosettaEnumValue(value = "ALUMINIUM_ALLOY_LME_15_MONTH", displayName = "ALUMINIUM ALLOY-LME 15 MONTH") ALUMINIUM_ALLOY_LME_15_MONTH("ALUMINIUM_ALLOY_LME_15_MONTH", "ALUMINIUM ALLOY-LME 15 MONTH"),
	
	/**
	 * A code for the NYMEX Central Appalachian Coal commodity
	 */
	@RosettaEnumValue(value = "COAL_CENTRAL_APPALACHIAN_NYMEX", displayName = "COAL-CENTRAL APPALACHIAN-NYMEX") COAL_CENTRAL_APPALACHIAN_NYMEX("COAL_CENTRAL_APPALACHIAN_NYMEX", "COAL-CENTRAL APPALACHIAN-NYMEX"),
	
	/**
	 * A code for the ICE Futures U.S. (‘ICUS’) Cocoa commodity
	 */
	@RosettaEnumValue(value = "COCOA_ICE", displayName = "COCOA-ICE") COCOA_ICE("COCOA_ICE", "COCOA-ICE"),
	
	/**
	 * A code for the ICUS Coffee C commodity
	 */
	@RosettaEnumValue(value = "COFFEE_ARABICA_ICE", displayName = "COFFEE ARABICA-ICE") COFFEE_ARABICA_ICE("COFFEE_ARABICA_ICE", "COFFEE ARABICA-ICE"),
	
	/**
	 * A code for the ICUS Coffee C commodity
	 */
	@RosettaEnumValue(value = "COFFEE_ROBUSTA_ICE", displayName = "COFFEE ROBUSTA-ICE") COFFEE_ROBUSTA_ICE("COFFEE_ROBUSTA_ICE", "COFFEE ROBUSTA-ICE"),
	
	/**
	 * A code for the COMEX (‘CMX’) Copper Grade #1 commodity
	 */
	@RosettaEnumValue(value = "COPPER_COMEX", displayName = "COPPER-COMEX") COPPER_COMEX("COPPER_COMEX", "COPPER-COMEX"),
	
	/**
	 * A code for the Chicago Board of Trade (‘CBOT’) Corn commodity
	 */
	@RosettaEnumValue(value = "CORN_CBOT", displayName = "CORN-CBOT") CORN_CBOT("CORN_CBOT", "CORN-CBOT"),
	
	/**
	 * A code for the ICUS Cotton No. 2 commodity
	 */
	@RosettaEnumValue(value = "COTTON_NO__2_ICE", displayName = "COTTON NO. 2-ICE") COTTON_NO__2_ICE("COTTON_NO__2_ICE", "COTTON NO. 2-ICE"),
	
	/**
	 * A code for the CBOT Ethanol commodity
	 */
	@RosettaEnumValue(value = "ETHANOL_CBOT", displayName = "ETHANOL-CBOT") ETHANOL_CBOT("ETHANOL_CBOT", "ETHANOL-CBOT"),
	
	/**
	 * A code for the CME Feeder Cattle commodity
	 */
	@RosettaEnumValue(value = "FEEDER_CATTLE_CME", displayName = "FEEDER CATTLE-CME") FEEDER_CATTLE_CME("FEEDER_CATTLE_CME", "FEEDER CATTLE-CME"),
	
	/**
	 * A code for the ICUS Frozen Concentrated Orange Juice commodity
	 */
	@RosettaEnumValue(value = "FROZEN_CONCENTRATED_ORANGE_JUICE_NO__1_ICE", displayName = "FROZEN CONCENTRATED ORANGE JUICE NO. 1-ICE") FROZEN_CONCENTRATED_ORANGE_JUICE_NO__1_ICE("FROZEN_CONCENTRATED_ORANGE_JUICE_NO__1_ICE", "FROZEN CONCENTRATED ORANGE JUICE NO. 1-ICE"),
	
	/**
	 * A code for the NYMEX Gasoline Blendstock (RBOB) commodity
	 */
	@RosettaEnumValue(value = "GASOLINE_RBOB_NEW_YORK_ICE", displayName = "GASOLINE-RBOB-NEW YORK-ICE") GASOLINE_RBOB_NEW_YORK_ICE("GASOLINE_RBOB_NEW_YORK_ICE", "GASOLINE-RBOB-NEW YORK-ICE"),
	
	/**
	 * A code for the NYMEX Gasoline Blendstock (RBOB) commodity
	 */
	@RosettaEnumValue(value = "GASOLINE_RBOB_NEW_YORK_NYMEX", displayName = "GASOLINE-RBOB-NEW YORK-NYMEX") GASOLINE_RBOB_NEW_YORK_NYMEX("GASOLINE_RBOB_NEW_YORK_NYMEX", "GASOLINE-RBOB-NEW YORK-NYMEX"),
	
	/**
	 * A code for the CMX Gold commodity
	 */
	@RosettaEnumValue(value = "GOLD_COMEX", displayName = "GOLD-COMEX") GOLD_COMEX("GOLD_COMEX", "GOLD-COMEX"),
	
	/**
	 * A code for the NYMEX No. 2 Heating Oil, New York Harbor commodity
	 */
	@RosettaEnumValue(value = "HEATING_OIL_NEW_YORK_NYMEX", displayName = "HEATING OIL-NEW YORK-NYMEX") HEATING_OIL_NEW_YORK_NYMEX("HEATING_OIL_NEW_YORK_NYMEX", "HEATING OIL-NEW YORK-NYMEX"),
	
	/**
	 * A code for the CME Lean Hogs commodity
	 */
	@RosettaEnumValue(value = "LEAN_HOGS_CME", displayName = "LEAN HOGS-CME") LEAN_HOGS_CME("LEAN_HOGS_CME", "LEAN HOGS-CME"),
	
	/**
	 * A code for the CME Live Cattle commodity
	 */
	@RosettaEnumValue(value = "LIVE_CATTLE_CME", displayName = "LIVE CATTLE-CME") LIVE_CATTLE_CME("LIVE_CATTLE_CME", "LIVE CATTLE-CME"),
	
	/**
	 * A code for the CME Random Length Lumber commodity
	 */
	@RosettaEnumValue(value = "LUMBER_CME", displayName = "LUMBER-CME") LUMBER_CME("LUMBER_CME", "LUMBER-CME"),
	
	/**
	 * A code for the CME Milk Class III commodity
	 */
	@RosettaEnumValue(value = "MILK_CLASS_III_CME", displayName = "MILK-CLASS III-CME") MILK_CLASS_III_CME("MILK_CLASS_III_CME", "MILK-CLASS III-CME"),
	
	/**
	 * A code for the CME Non Fat Dry Milk commodity
	 */
	@RosettaEnumValue(value = "MILK_NONFAT_DRY_CME", displayName = "MILK-NONFAT-DRY-CME") MILK_NONFAT_DRY_CME("MILK_NONFAT_DRY_CME", "MILK-NONFAT-DRY-CME"),
	
	/**
	 * A code for the NYMEX Natural Gas commodity
	 */
	@RosettaEnumValue(value = "NATURAL_GAS_NYMEX", displayName = "NATURAL GAS-NYMEX") NATURAL_GAS_NYMEX("NATURAL_GAS_NYMEX", "NATURAL GAS-NYMEX"),
	
	/**
	 * A code for the NYMEX Panhandle Basis Swap commodity
	 */
	@RosettaEnumValue(value = "NATURAL_GAS_PEPL__TEXOK_MAINLINE__INSIDE_FERC", displayName = "NATURAL GAS-PEPL (TEXOK MAINLINE)-INSIDE FERC") NATURAL_GAS_PEPL__TEXOK_MAINLINE__INSIDE_FERC("NATURAL_GAS_PEPL__TEXOK_MAINLINE__INSIDE_FERC", "NATURAL GAS-PEPL (TEXOK MAINLINE)-INSIDE FERC"),
	
	/**
	 * A code for the NYMEX Waha Basis Swap commodity
	 */
	@RosettaEnumValue(value = "NATURAL_GAS_W__TEXAS__WAHA__INSIDE_FERC", displayName = "NATURAL GAS-W. TEXAS (WAHA)-INSIDE FERC") NATURAL_GAS_W__TEXAS__WAHA__INSIDE_FERC("NATURAL_GAS_W__TEXAS__WAHA__INSIDE_FERC", "NATURAL GAS-W. TEXAS (WAHA)-INSIDE FERC"),
	
	/**
	 * A code for the CBOT Oats commodity
	 */
	@RosettaEnumValue(value = "OATS_CBOT", displayName = "OATS-CBOT") OATS_CBOT("OATS_CBOT", "OATS-CBOT"),
	
	/**
	 * A code for the NYMEX Crude Oil, Light Sweet commodity
	 */
	@RosettaEnumValue(value = "OIL_WTI_NYMEX", displayName = "OIL-WTI-NYMEX") OIL_WTI_NYMEX("OIL_WTI_NYMEX", "OIL-WTI-NYMEX"),
	
	/**
	 * A code for the NYMEX Palladium commodity
	 */
	@RosettaEnumValue(value = "PALLADIUM_NYMEX", displayName = "PALLADIUM-NYMEX") PALLADIUM_NYMEX("PALLADIUM_NYMEX", "PALLADIUM-NYMEX"),
	
	/**
	 * A code for the NYMEX Platinum commodity
	 */
	@RosettaEnumValue(value = "PLATINUM_NYMEX", displayName = "PLATINUM-NYMEX") PLATINUM_NYMEX("PLATINUM_NYMEX", "PLATINUM-NYMEX"),
	
	/**
	 * A code for the CBOT Rough Rice commodity
	 */
	@RosettaEnumValue(value = "RICE_CBOT", displayName = "RICE-CBOT") RICE_CBOT("RICE_CBOT", "RICE-CBOT"),
	
	/**
	 * A code for the CMX Silver commodity
	 */
	@RosettaEnumValue(value = "SILVER_COMEX", displayName = "SILVER-COMEX") SILVER_COMEX("SILVER_COMEX", "SILVER-COMEX"),
	
	/**
	 * A code for the CBOT Soybeans commodity
	 */
	@RosettaEnumValue(value = "SOYBEANS_CBOT", displayName = "SOYBEANS-CBOT") SOYBEANS_CBOT("SOYBEANS_CBOT", "SOYBEANS-CBOT"),
	
	/**
	 * A code for the CBOT Soybean Meal commodity
	 */
	@RosettaEnumValue(value = "SOYBEAN_MEAL_CBOT", displayName = "SOYBEAN MEAL-CBOT") SOYBEAN_MEAL_CBOT("SOYBEAN_MEAL_CBOT", "SOYBEAN MEAL-CBOT"),
	
	/**
	 * A code for the CBOT Soybean Oil commodity
	 */
	@RosettaEnumValue(value = "SOYBEAN_OIL_CBOT", displayName = "SOYBEAN OIL-CBOT") SOYBEAN_OIL_CBOT("SOYBEAN_OIL_CBOT", "SOYBEAN OIL-CBOT"),
	
	/**
	 * A code for the ICUS Sugar No. 11 commodity
	 */
	@RosettaEnumValue(value = "SUGAR___11__WORLD__ICE", displayName = "SUGAR # 11 (WORLD)-ICE") SUGAR___11__WORLD__ICE("SUGAR___11__WORLD__ICE", "SUGAR # 11 (WORLD)-ICE"),
	
	/**
	 * A code for the ICUS Sugar No. 16 commodity
	 */
	@RosettaEnumValue(value = "SUGAR___16__US__ICE", displayName = "SUGAR # 16 (US)-ICE") SUGAR___16__US__ICE("SUGAR___16__US__ICE", "SUGAR # 16 (US)-ICE"),
	
	/**
	 * A code for the CBOT Wheat commodity
	 */
	@RosettaEnumValue(value = "WHEAT_CBOT", displayName = "WHEAT-CBOT") WHEAT_CBOT("WHEAT_CBOT", "WHEAT-CBOT"),
	
	/**
	 * A code for the Kansas City Board of Trade (‘KCBT’)Wheat commodity
	 */
	@RosettaEnumValue(value = "WHEAT_HRW_KCBOT", displayName = "WHEAT HRW-KCBOT") WHEAT_HRW_KCBOT("WHEAT_HRW_KCBOT", "WHEAT HRW-KCBOT"),
	
	/**
	 * A code for the Wheat commodity
	 */
	@RosettaEnumValue(value = "WHEAT_RED_SPRING_MGE", displayName = "WHEAT RED SPRING-MGE") WHEAT_RED_SPRING_MGE("WHEAT_RED_SPRING_MGE", "WHEAT RED SPRING-MGE")
;
	private static Map<String, CommodityReferencePriceEnum> values;
	static {
        Map<String, CommodityReferencePriceEnum> map = new ConcurrentHashMap<>();
		for (CommodityReferencePriceEnum instance : CommodityReferencePriceEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CommodityReferencePriceEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CommodityReferencePriceEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CommodityReferencePriceEnum fromDisplayName(String name) {
		CommodityReferencePriceEnum value = values.get(name);
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
