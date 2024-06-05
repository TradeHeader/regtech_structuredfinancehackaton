package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodEnum;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters;
import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
import cdm.product.common.schedule.DateRelativeToPaymentDates;
import cdm.product.common.schedule.DateRelativeToValuationDates;
import cdm.product.common.settlement.FxFixingDate;
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

public class FxFixingDateOnlyExistsValidator implements ValidatorWithArg<FxFixingDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxFixingDate> ValidationResult<FxFixingDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("periodMultiplier", ExistenceChecker.isSet((Integer) o.getPeriodMultiplier()))
				.put("period", ExistenceChecker.isSet((PeriodEnum) o.getPeriod()))
				.put("dayType", ExistenceChecker.isSet((DayTypeEnum) o.getDayType()))
				.put("businessDayConvention", ExistenceChecker.isSet((BusinessDayConventionEnum) o.getBusinessDayConvention()))
				.put("businessCenters", ExistenceChecker.isSet((BusinessCenters) o.getBusinessCenters()))
				.put("businessCentersReference", ExistenceChecker.isSet((ReferenceWithMetaBusinessCenters) o.getBusinessCentersReference()))
				.put("dateRelativeToPaymentDates", ExistenceChecker.isSet((DateRelativeToPaymentDates) o.getDateRelativeToPaymentDates()))
				.put("dateRelativeToCalculationPeriodDates", ExistenceChecker.isSet((DateRelativeToCalculationPeriodDates) o.getDateRelativeToCalculationPeriodDates()))
				.put("dateRelativeToValuationDates", ExistenceChecker.isSet((DateRelativeToValuationDates) o.getDateRelativeToValuationDates()))
				.put("fxFixingDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getFxFixingDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxFixingDate", ValidationType.ONLY_EXISTS, "FxFixingDate", path, "");
		}
		return failure("FxFixingDate", ValidationType.ONLY_EXISTS, "FxFixingDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
