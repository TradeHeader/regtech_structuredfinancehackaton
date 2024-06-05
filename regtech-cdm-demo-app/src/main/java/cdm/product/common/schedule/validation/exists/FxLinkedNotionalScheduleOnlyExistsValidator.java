package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.FxSpotRateSource;
import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class FxLinkedNotionalScheduleOnlyExistsValidator implements ValidatorWithArg<FxLinkedNotionalSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxLinkedNotionalSchedule> ValidationResult<FxLinkedNotionalSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("varyingNotionalCurrency", ExistenceChecker.isSet((FieldWithMetaString) o.getVaryingNotionalCurrency()))
				.put("varyingNotionalFixingDates", ExistenceChecker.isSet((RelativeDateOffset) o.getVaryingNotionalFixingDates()))
				.put("fxSpotRateSource", ExistenceChecker.isSet((FxSpotRateSource) o.getFxSpotRateSource()))
				.put("fixingTime", ExistenceChecker.isSet((BusinessCenterTime) o.getFixingTime()))
				.put("varyingNotionalInterimExchangePaymentDates", ExistenceChecker.isSet((RelativeDateOffset) o.getVaryingNotionalInterimExchangePaymentDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxLinkedNotionalSchedule", ValidationType.ONLY_EXISTS, "FxLinkedNotionalSchedule", path, "");
		}
		return failure("FxLinkedNotionalSchedule", ValidationType.ONLY_EXISTS, "FxLinkedNotionalSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
