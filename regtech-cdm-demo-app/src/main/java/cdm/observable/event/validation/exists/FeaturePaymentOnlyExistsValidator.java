package cdm.observable.event.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.observable.common.TimeTypeEnum;
import cdm.observable.event.FeaturePayment;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class FeaturePaymentOnlyExistsValidator implements ValidatorWithArg<FeaturePayment, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FeaturePayment> ValidationResult<FeaturePayment> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerReceiver", ExistenceChecker.isSet((PartyReferencePayerReceiver) o.getPayerReceiver()))
				.put("levelPercentage", ExistenceChecker.isSet((BigDecimal) o.getLevelPercentage()))
				.put("amount", ExistenceChecker.isSet((BigDecimal) o.getAmount()))
				.put("time", ExistenceChecker.isSet((TimeTypeEnum) o.getTime()))
				.put("currency", ExistenceChecker.isSet((FieldWithMetaString) o.getCurrency()))
				.put("paymentDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getPaymentDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FeaturePayment", ValidationType.ONLY_EXISTS, "FeaturePayment", path, "");
		}
		return failure("FeaturePayment", ValidationType.ONLY_EXISTS, "FeaturePayment", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
