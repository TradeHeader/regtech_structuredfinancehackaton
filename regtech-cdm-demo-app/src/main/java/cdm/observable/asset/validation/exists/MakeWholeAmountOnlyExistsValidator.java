package cdm.observable.asset.validation.exists;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.observable.asset.InterpolationMethodEnum;
import cdm.observable.asset.MakeWholeAmount;
import cdm.observable.asset.QuotationSideEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class MakeWholeAmountOnlyExistsValidator implements ValidatorWithArg<MakeWholeAmount, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MakeWholeAmount> ValidationResult<MakeWholeAmount> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("floatingRateIndex", ExistenceChecker.isSet((FloatingRateIndexEnum) o.getFloatingRateIndex()))
				.put("indexTenor", ExistenceChecker.isSet((Period) o.getIndexTenor()))
				.put("spread", ExistenceChecker.isSet((BigDecimal) o.getSpread()))
				.put("side", ExistenceChecker.isSet((QuotationSideEnum) o.getSide()))
				.put("interpolationMethod", ExistenceChecker.isSet((InterpolationMethodEnum) o.getInterpolationMethod()))
				.put("earlyCallDate", ExistenceChecker.isSet((FieldWithMetaDate) o.getEarlyCallDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MakeWholeAmount", ValidationType.ONLY_EXISTS, "MakeWholeAmount", path, "");
		}
		return failure("MakeWholeAmount", ValidationType.ONLY_EXISTS, "MakeWholeAmount", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
