package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the origin of a corporate action transfer.
 * @version ${project.version}
 */
@RosettaEnum("CorporateActionTypeEnum")
public enum CorporateActionTypeEnum {

	/**
	 * Corporate action triggered by the distribution of a cash dividend.
	 */
	@RosettaEnumValue(value = "CashDividend") CASH_DIVIDEND("CashDividend"),
	
	/**
	 * Corporate action triggered by the distribution of a stock dividend.
	 */
	@RosettaEnumValue(value = "StockDividend") STOCK_DIVIDEND("StockDividend"),
	
	/**
	 * Corporate action triggered by a stock split. A stock split or stock divide increases the number of shares in a public company. The price is adjusted such that the before and after market capitalization of the company remains the same and dilutiondoes not occur. The value maps closely to the ISO code (SPLF) defined as a distribution of subsidiary stock to the shareholders of the parent company without a surrender of shares.
	 */
	@RosettaEnumValue(value = "StockSplit") STOCK_SPLIT("StockSplit"),
	
	/**
	 * Corporate action triggered by a reverse split. A reverse stock split or reverse split is a process by a company of issuing to each shareholder in that company a smaller number of new shares in proportion to that shareholder&#39;s original shares that are subsequently canceled. A reverse stock split is also called a stock merge. The reduction in the number of issued shares is accompanied by a proportional increase in the share price. The value maps closely to the ISO code (SPLR) defined as a decrease in a company&#39;s number of outstanding equities without any change in the shareholder&#39;s equity or the aggregate market value at the time of the split. Equity price and nominal value are increased accordingly.
	 */
	@RosettaEnumValue(value = "ReverseStockSplit") REVERSE_STOCK_SPLIT("ReverseStockSplit"),
	
	/**
	 * Corporate action triggered by a spin Off. A spin-out, also known as a spin-off or a starburst, refers to a type of corporate action where a company splits off sections of itself as a separate business. The value maps closely to the ISO code (SOFF) defined as a a distribution of subsidiary stock to the shareholders of the parent company without a surrender of shares. Spin-off represents a form of divestiture usually resulting in an independent company or in an existing company. For example, demerger, distribution, unbundling.
	 */
	@RosettaEnumValue(value = "SpinOff") SPIN_OFF("SpinOff"),
	
	/**
	 * Corporate action triggered by a merger. Mergers and acquisitions (abbreviated M&amp;A) is an aspect of corporate strategy, corporate finance and management dealing with the buying, selling, dividing and combining of different companies and similar entities that can help an enterprise grow rapidly in its sector or location of origin, or a new field or new location, without creating a subsidiary, other child entity or using a joint venture. The distinction between a merger and an acquisition has become increasingly blurred in various respects (particularly in terms of the ultimate economic outcome), although it has not completely disappeared in all situations. The value maps closely to the ISO code (MRGR) defined as an offer made to shareholders, normally by a third party, requesting them to sell (tender) or exchange their equities.
	 */
	@RosettaEnumValue(value = "Merger") MERGER("Merger"),
	
	/**
	 * Corporate action triggered by the removal of a security from a stock exchange.
	 */
	@RosettaEnumValue(value = "Delisting") DELISTING("Delisting"),
	
	/**
	 * Corporate action triggered by a change in the name used to trade the security.
	 */
	@RosettaEnumValue(value = "StockNameChange") STOCK_NAME_CHANGE("StockNameChange"),
	
	/**
	 * Corporate action triggered by a change in the code used to trade the security.
	 */
	@RosettaEnumValue(value = "StockIdentifierChange") STOCK_IDENTIFIER_CHANGE("StockIdentifierChange"),
	
	/**
	 * Corporate action triggered by an issuance to shareholders of rights to purchase additional shares at a discount.
	 */
	@RosettaEnumValue(value = "RightsIssue") RIGHTS_ISSUE("RightsIssue"),
	
	/**
	 * Corporate action triggered by a takeover. A takeover is the purchase of onecompany (the target) by another (the acquirer, or bidder). The value maps to the ISO code (TEND) but is finer grained than TEND which emcompasses Tender/Acquisition/Takeover/Purchase Offer/Buyback. ISO defines the TEND code as an offer made to shareholders, normally by a third party, requesting them to sell (tender) or exchange their equities.
	 */
	@RosettaEnumValue(value = "Takeover") TAKEOVER("Takeover"),
	
	/**
	 * Corporate action triggered by a Stock Reclassification.
	 */
	@RosettaEnumValue(value = "StockReclassification") STOCK_RECLASSIFICATION("StockReclassification"),
	
	/**
	 * Corporate action triggered by a bonus issue. A bonus issue or bonus share is a free share of stock given to current shareholders in a company, based upon the number of shares that the shareholder already owns. While the issue of bonus shares increases the total number of shares issued and owned, it does not change the value of the company. The value maps closely to the ISO code (BONU) defined as a bonus, scrip or capitalisation issue. Security holders receive additional assets free of payment from the issuer, in proportion to their holding.
	 */
	@RosettaEnumValue(value = "BonusIssue") BONUS_ISSUE("BonusIssue"),
	
	/**
	 * Corporate action triggered by a Class Action. An action where an individual represents a group in a court claim. The judgment from the suit is for all the members of the group (class). The value maps closely to the ISO code (CLSA) defined as the situation where interested parties seek restitution for financial loss. The security holder may be offered the opportunity to join a class action proceeding and would need to respond with an instruction.
	 */
	@RosettaEnumValue(value = "ClassAction") CLASS_ACTION("ClassAction"),
	
	/**
	 * Corporate action triggered by an early redemption. The value maps closely to the ISO code (MCAL) defined as the redemption of an entire issue outstanding of securities, for example, bonds, preferred equity, funds, by the issuer or its agent, for example, asset manager, before final maturity.
	 */
	@RosettaEnumValue(value = "EarlyRedemption") EARLY_REDEMPTION("EarlyRedemption"),
	
	/**
	 * Corporate action triggered by a liquidation. When a business or firm is terminated or bankrupt, its assets are sold (liquidated) and the proceeds pay creditors. Any leftovers are distributed to shareholders. The value maps closely to the ISO code (LIQU) defined as a distribution of cash, assets or both. Debt may be paid in order of priority based on preferred claims to assets specified by the security.
	 */
	@RosettaEnumValue(value = "Liquidation") LIQUIDATION("Liquidation")
;
	private static Map<String, CorporateActionTypeEnum> values;
	static {
        Map<String, CorporateActionTypeEnum> map = new ConcurrentHashMap<>();
		for (CorporateActionTypeEnum instance : CorporateActionTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CorporateActionTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CorporateActionTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CorporateActionTypeEnum fromDisplayName(String name) {
		CorporateActionTypeEnum value = values.get(name);
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
