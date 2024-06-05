package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.MultipleValuationDates;
import cdm.observable.asset.SingleValuationDate;
import cdm.product.common.settlement.FxFixingDate;
import cdm.product.common.settlement.ValuationDate;
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

public class ValuationDateOnlyExistsValidator implements ValidatorWithArg<ValuationDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ValuationDate> ValidationResult<ValuationDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("singleValuationDate", ExistenceChecker.isSet((SingleValuationDate) o.getSingleValuationDate()))
				.put("multipleValuationDates", ExistenceChecker.isSet((MultipleValuationDates) o.getMultipleValuationDates()))
				.put("valuationDate", ExistenceChecker.isSet((RelativeDateOffset) o.getValuationDate()))
				.put("fxFixingDate", ExistenceChecker.isSet((FxFixingDate) o.getFxFixingDate()))
				.put("fxFixingSchedule", ExistenceChecker.isSet((AdjustableDates) o.getFxFixingSchedule()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ValuationDate", ValidationType.ONLY_EXISTS, "ValuationDate", path, "");
		}
		return failure("ValuationDate", ValidationType.ONLY_EXISTS, "ValuationDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
