package cdm.event.workflow;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to qualify the timestamps that can be associated with a lifecycle event. The reason for such approach is that the experience of integrating the DTCC and CME data representations suggests that a wide set of timestamps are currently utilized among service providers, while there is not at present an objective set of criteria that could help suggest a defined set of timestamps as part of the CDM. Implementers are expected to evaluate the current enumeration values to determine whether those meet their requirements. If not, they are expected to engage with the CDM team to evaluate the addition of further value(s) to this enumeration, which will then participate to the development of a compendium for further evaluation at a later point in order to determine whether this modeling is appropriate.
 * @version ${project.version}
 */
@RosettaEnum("EventTimestampQualificationEnum")
public enum EventTimestampQualificationEnum {

	/**
	 * The date and time on the trade was cleared.
	 */
	@RosettaEnumValue(value = "clearingDateTime") CLEARING_DATE_TIME("clearingDateTime"),
	
	/**
	 * The date and time on which trade was confirmed as cleared.
	 */
	@RosettaEnumValue(value = "clearingConfirmationDateTime") CLEARING_CONFIRMATION_DATE_TIME("clearingConfirmationDateTime"),
	
	/**
	 * The date and time on which trade was received by Clearing Body.
	 */
	@RosettaEnumValue(value = "clearingReceiptDateTime") CLEARING_RECEIPT_DATE_TIME("clearingReceiptDateTime"),
	
	/**
	 * The date and time on which the event was submitted for clearing.
	 */
	@RosettaEnumValue(value = "clearingSubmissionDateTime") CLEARING_SUBMISSION_DATE_TIME("clearingSubmissionDateTime"),
	
	/**
	 * The date and time on which the event was confirmed.
	 */
	@RosettaEnumValue(value = "confirmationDateTime") CONFIRMATION_DATE_TIME("confirmationDateTime"),
	
	/**
	 * The date and time on which the event was created.
	 */
	@RosettaEnumValue(value = "eventCreationDateTime") EVENT_CREATION_DATE_TIME("eventCreationDateTime"),
	
	/**
	 * The date and time on which the event will be considered expired.
	 */
	@RosettaEnumValue(value = "eventExpirationDateTime") EVENT_EXPIRATION_DATE_TIME("eventExpirationDateTime"),
	
	/**
	 * The date and time on which the event was processed.
	 */
	@RosettaEnumValue(value = "eventProcessingDateTime") EVENT_PROCESSING_DATE_TIME("eventProcessingDateTime"),
	
	/**
	 * The date and time on which the event was sent.
	 */
	@RosettaEnumValue(value = "eventSentDateTime") EVENT_SENT_DATE_TIME("eventSentDateTime"),
	
	/**
	 * The date and time on which the event was submitted.
	 */
	@RosettaEnumValue(value = "eventSubmittedDateTime") EVENT_SUBMITTED_DATE_TIME("eventSubmittedDateTime"),
	
	/**
	 * The date and time on which the trade execution was performed.
	 */
	@RosettaEnumValue(value = "executionDateTime") EXECUTION_DATE_TIME("executionDateTime"),
	
	/**
	 * The date and time on which the transaction has been created. This timestamp is specified as such by the CME ClearPort Matched IRS Trade submission API specification: &#39;The transaction date time of the trade. Represents the date &amp; time on which the trade was initially generated either by CME Clearing or firm. The transaction date time may be assigned by CME Clearing at the point the trade is reported as cleared. Transaction date time can also be provided by an external submitter of the trade at the point the trade is submitted.&#39;
	 */
	@RosettaEnumValue(value = "transactionCreationDateTime") TRANSACTION_CREATION_DATE_TIME("transactionCreationDateTime")
;
	private static Map<String, EventTimestampQualificationEnum> values;
	static {
        Map<String, EventTimestampQualificationEnum> map = new ConcurrentHashMap<>();
		for (EventTimestampQualificationEnum instance : EventTimestampQualificationEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	EventTimestampQualificationEnum(String rosettaName) {
		this(rosettaName, null);
	}

	EventTimestampQualificationEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static EventTimestampQualificationEnum fromDisplayName(String name) {
		EventTimestampQualificationEnum value = values.get(name);
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
