package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.math.Rounding;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.Observable;
import cdm.observable.common.TimeTypeEnum;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.ObservationDates;
import cdm.product.common.schedule.ObservationTerms;
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

public class ObservationTermsOnlyExistsValidator implements ValidatorWithArg<ObservationTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ObservationTerms> ValidationResult<ObservationTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("pricingTime", ExistenceChecker.isSet((BusinessCenterTime) o.getPricingTime()))
				.put("pricingTimeType", ExistenceChecker.isSet((TimeTypeEnum) o.getPricingTimeType()))
				.put("informationSource", ExistenceChecker.isSet((FxSpotRateSource) o.getInformationSource()))
				.put("precision", ExistenceChecker.isSet((Rounding) o.getPrecision()))
				.put("calculationPeriodDates", ExistenceChecker.isSet((CalculationPeriodDates) o.getCalculationPeriodDates()))
				.put("observable", ExistenceChecker.isSet((Observable) o.getObservable()))
				.put("observationDates", ExistenceChecker.isSet((ObservationDates) o.getObservationDates()))
				.put("numberOfObservationDates", ExistenceChecker.isSet((Integer) o.getNumberOfObservationDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ObservationTerms", ValidationType.ONLY_EXISTS, "ObservationTerms", path, "");
		}
		return failure("ObservationTerms", ValidationType.ONLY_EXISTS, "ObservationTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
