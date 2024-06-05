package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PaymentDetail;
import cdm.product.common.settlement.PaymentRule;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PaymentDetailOnlyExistsValidator implements ValidatorWithArg<PaymentDetail, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PaymentDetail> ValidationResult<PaymentDetail> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("paymentDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getPaymentDate()))
				.put("paymentRule", ExistenceChecker.isSet((PaymentRule) o.getPaymentRule()))
				.put("paymentAmount", ExistenceChecker.isSet((Money) o.getPaymentAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PaymentDetail", ValidationType.ONLY_EXISTS, "PaymentDetail", path, "");
		}
		return failure("PaymentDetail", ValidationType.ONLY_EXISTS, "PaymentDetail", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
