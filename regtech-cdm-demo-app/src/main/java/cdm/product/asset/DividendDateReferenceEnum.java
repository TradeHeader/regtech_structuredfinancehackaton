package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the date by reference to which the dividend will be paid.
 * @version ${project.version}
 */
@RosettaEnum("DividendDateReferenceEnum")
public enum DividendDateReferenceEnum {

	/**
	 * The dividend date will be specified ad-hoc by the parties, typically on the dividend ex-date.
	 */
	@RosettaEnumValue(value = "AdHocDate") AD_HOC_DATE("AdHocDate"),
	
	/**
	 * If &#39;Dividend Payment Date(s)&#39; is specified in the Transaction Supplement as &#39;Cash Settlement Payment Date&#39;, then the Dividend Payment Date in respect of a Dividend Amount shall be the Cash Settlement Payment Date relating to the end of the Dividend Period during which the Shares commenced trading &#39;ex&#39; the relevant dividend on the Exchange.
	 */
	@RosettaEnumValue(value = "CashSettlementPaymentDate") CASH_SETTLEMENT_PAYMENT_DATE("CashSettlementPaymentDate"),
	
	/**
	 * If &#39;Dividend Payment Date(s)&#39; is specified in the Transaction Supplement as &#39;Cash Settlement Payment Date – Ex Dividend&#39;&#39;, then the Dividend Payment Date in respect of a Dividend Amount shall be the Cash Settlement Payment Date relating to the end of the Dividend Period during which the Shares commenced trading &#39;ex&#39; the relevant dividend on the Exchange.
	 */
	@RosettaEnumValue(value = "CashSettlePaymentDateExDiv") CASH_SETTLE_PAYMENT_DATE_EX_DIV("CashSettlePaymentDateExDiv"),
	
	/**
	 * If &#39;Dividend Payment Date(s)&#39; is specified in the Transaction Supplement as &#39;Cash Settlement Payment Date – Issuer Payment&#39;, then the Dividend Payment Date in respect of a Dividend Amount shall be the Cash Settlement Payment Date relating to the end of the Dividend Period during which the issuer pays the relevant dividend to a holder of record provided that in the case where the Equity Amount Payer is the party specified to be the sole Hedging Party and the Hedging Party has not received the Dividend Amount by such date, then the date falling a number of Currency Business Days as specified in the Cash Settlement Payment Date after actual receipt by the Hedging Party of the Received Ex Amount or Paid Ex Amount (as applicable).
	 */
	@RosettaEnumValue(value = "CashSettlePaymentDateIssuerPayment") CASH_SETTLE_PAYMENT_DATE_ISSUER_PAYMENT("CashSettlePaymentDateIssuerPayment"),
	
	/**
	 * Total of dividends which go ex, paid on next following Equity Payment Date, which is immediately following the Dividend Period during which the Shares commence trading ex-dividend on the Exchange.
	 */
	@RosettaEnumValue(value = "CumulativeEquityExDiv") CUMULATIVE_EQUITY_EX_DIV("CumulativeEquityExDiv"),
	
	/**
	 * Total of paid dividends, paid on next following Equity Payment Date, which is immediately following the Dividend Period during which the dividend is paid by the Issuer to the holders of record of a Share.
	 */
	@RosettaEnumValue(value = "CumulativeEquityPaid") CUMULATIVE_EQUITY_PAID("CumulativeEquityPaid"),
	
	/**
	 * Total of paid dividends, paid on next following Equity Payment Date, which is immediately following the Dividend Ex Date, unless the Dividend Ex Date is between the Equity Valuation and Payment Date in which case the dividend is deferred to the following Equity Payment Date
	 */
	@RosettaEnumValue(value = "CumulativeEquityExDivBeforeReset") CUMULATIVE_EQUITY_EX_DIV_BEFORE_RESET("CumulativeEquityExDivBeforeReset"),
	
	/**
	 * Total of paid dividends, paid on next following Equity Payment Date, which is immediately following the Dividend Pay Date, unless the Dividend Pay Date is between the Equity Valuation and Payment Date (not including the Valuation Date) in which case the dividend is deferred to the following Equity Payment Date
	 */
	@RosettaEnumValue(value = "CumulativeEquityPaidBeforeReset") CUMULATIVE_EQUITY_PAID_BEFORE_RESET("CumulativeEquityPaidBeforeReset"),
	
	/**
	 * Total of paid dividends, paid on next following Equity Payment Date, which is immediately following the Dividend Pay Date, unless the Dividend Pay Date is between the Equity Valuation and Payment Date (including the Valuation Date) in which case the dividend is deferred to the following Equity Payment Date
	 */
	@RosettaEnumValue(value = "CumulativeEquityPaidInclReset") CUMULATIVE_EQUITY_PAID_INCL_RESET("CumulativeEquityPaidInclReset"),
	
	/**
	 * Total of dividends which go ex, paid on next following Interest Payment Date, which is immediately following the Dividend Period during which the Shares commence trading ex-dividend on the Exchange, or where the date on which the Shares commence trading ex-dividend is a Payment Date, such Payment Date.
	 */
	@RosettaEnumValue(value = "CumulativeInterestExDiv") CUMULATIVE_INTEREST_EX_DIV("CumulativeInterestExDiv"),
	
	/**
	 * Total of paid dividends, paid on next following Interest Payment Date, which is immediately following the Dividend Period during which the dividend is paid by the Issuer to the holders of record of a Share.
	 */
	@RosettaEnumValue(value = "CumulativeInterestPaid") CUMULATIVE_INTEREST_PAID("CumulativeInterestPaid"),
	
	/**
	 * Total of paid dividends, paid on next following Interest Payment Date, which is immediately following the Dividend Pay Date, unless the Dividend Pay Date is between the Equity Valuation and Payment Date (including the Valuation Date) in which case the dividend is deferred to the following Interest Payment Date.
	 */
	@RosettaEnumValue(value = "CumulativeInterestPaidInclReset") CUMULATIVE_INTEREST_PAID_INCL_RESET("CumulativeInterestPaidInclReset"),
	
	/**
	 * Total of paid dividends, paid on next following Interest Payment Date, which is immediately following the Dividend Pay Date, unless the Dividend Pay Date is between the Equity Valuation and Payment Date (not including the Valuation Date) in which case the dividend is deferred to the following Interest Payment Date.
	 */
	@RosettaEnumValue(value = "CumulativeInterestPaidBeforeReset") CUMULATIVE_INTEREST_PAID_BEFORE_RESET("CumulativeInterestPaidBeforeReset"),
	
	/**
	 * Date on which the dividend will be paid by the issuer.
	 */
	@RosettaEnumValue(value = "DividendPaymentDate") DIVIDEND_PAYMENT_DATE("DividendPaymentDate"),
	
	/**
	 * In respect of each Dividend Period, the relevant Dividend Valuation Date.
	 */
	@RosettaEnumValue(value = "DividendValuationDate") DIVIDEND_VALUATION_DATE("DividendValuationDate"),
	
	/**
	 * Equity payment date of the swap.
	 */
	@RosettaEnumValue(value = "EquityPaymentDate") EQUITY_PAYMENT_DATE("EquityPaymentDate"),
	
	/**
	 * Date on which a holder of the security is entitled to the dividend.
	 */
	@RosettaEnumValue(value = "ExDate") EX_DATE("ExDate"),
	
	/**
	 * If &#39;Dividend Payment Date(s)&#39; is specified in the Transaction Supplement as &#39;Floating Amount Payment Date&#39;, then the Dividend Payment Date in respect of a Dividend Amount shall be the first Payment Date falling at least one Settlement Cycle after the date that the Shares have commenced trading &#39;ex&#39; the relevant dividend on the Exchange.
	 */
	@RosettaEnumValue(value = "FloatingAmountPaymentDate") FLOATING_AMOUNT_PAYMENT_DATE("FloatingAmountPaymentDate"),
	
	/**
	 * The next payment date of the swap.
	 */
	@RosettaEnumValue(value = "FollowingPaymentDate") FOLLOWING_PAYMENT_DATE("FollowingPaymentDate"),
	
	/**
	 * Date on which the dividend will be recorded in the books of the paying agent.
	 */
	@RosettaEnumValue(value = "RecordDate") RECORD_DATE("RecordDate"),
	
	/**
	 * If &#39;Dividend Payment Date(s)&#39; is specified in the Transaction Supplement as &#39;Share Payment&#39;, then the Dividend Payment Date in respect of a Dividend Amount shall fall on a date on or before the date that is two (or any other number that is specified in the Transaction Supplement) Currency Business Days following the day on which the Issuer of the Shares pays the relevant dividend to holders of record of the Shares.
	 */
	@RosettaEnumValue(value = "SharePayment") SHARE_PAYMENT("SharePayment"),
	
	/**
	 * Termination date of the swap.
	 */
	@RosettaEnumValue(value = "TerminationDate") TERMINATION_DATE("TerminationDate"),
	
	/**
	 * Trade date of the swap
	 */
	@RosettaEnumValue(value = "TradeDate") TRADE_DATE("TradeDate"),
	
	/**
	 * Pays a fraction of the Dividend Amount on each Unwind Trade Settlement Date which occurs after the Dividend Ex Date, until position is fully unwound OR on the next Equity Pay Date after the Dividend Pay Date. This will be whichever date comes first or a combination of both.
	 */
	@RosettaEnumValue(value = "UnwindOrEquityExDiv") UNWIND_OR_EQUITY_EX_DIV("UnwindOrEquityExDiv"),
	
	/**
	 * Pays a fraction of the Dividend Amount on each Unwind Trade Settlement Date which occurs after the Dividend Pay Date, until position is fully unwound OR on the next Equity Pay Date after the Dividend Pay Date. This will be whichever date comes first or a combination of both.
	 */
	@RosettaEnumValue(value = "UnwindOrEquityPaid") UNWIND_OR_EQUITY_PAID("UnwindOrEquityPaid"),
	
	/**
	 * Pays a fraction of the Dividend Amount on each Unwind Trade Settlement Date which occurs after the Dividend Ex Date, until position is fully unwound OR on the next Interest Pay Date after the Dividend Ex Date. This will be whichever date comes first or a combination of both.
	 */
	@RosettaEnumValue(value = "UnwindOrInterestExDiv") UNWIND_OR_INTEREST_EX_DIV("UnwindOrInterestExDiv"),
	
	/**
	 * Pays a fraction of the Dividend Amount on each Unwind Trade Settlement Date which occurs after the Dividend Pay Date, until position is fully unwound OR on the next Interest Pay Date after the Dividend Pay Date. This will be whichever date comes first or a combination of both.
	 */
	@RosettaEnumValue(value = "UnwindOrInterestPaid") UNWIND_OR_INTEREST_PAID("UnwindOrInterestPaid"),
	
	/**
	 * Pays a fraction of the total on each Unwind Trade Settlement Date which occurs after the Dividend Ex Date, until trade is fully unwound.
	 */
	@RosettaEnumValue(value = "UnwindExDiv") UNWIND_EX_DIV("UnwindExDiv"),
	
	/**
	 * Pays a fraction of the total on each Unwind Trade Settlement Date which occurs after the Dividend Pay Date, until trade is fully unwound.
	 */
	@RosettaEnumValue(value = "UnwindPaid") UNWIND_PAID("UnwindPaid")
;
	private static Map<String, DividendDateReferenceEnum> values;
	static {
        Map<String, DividendDateReferenceEnum> map = new ConcurrentHashMap<>();
		for (DividendDateReferenceEnum instance : DividendDateReferenceEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DividendDateReferenceEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DividendDateReferenceEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DividendDateReferenceEnum fromDisplayName(String name) {
		DividendDateReferenceEnum value = values.get(name);
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
