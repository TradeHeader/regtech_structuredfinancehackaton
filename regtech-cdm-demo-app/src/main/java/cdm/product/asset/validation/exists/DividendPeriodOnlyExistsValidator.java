package cdm.product.asset.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.product.asset.DividendPaymentDate;
import cdm.product.asset.DividendPeriod;
import cdm.product.template.Product;
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

public class DividendPeriodOnlyExistsValidator implements ValidatorWithArg<DividendPeriod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendPeriod> ValidationResult<DividendPeriod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("startDate", ExistenceChecker.isSet((DividendPaymentDate) o.getStartDate()))
				.put("endDate", ExistenceChecker.isSet((DividendPaymentDate) o.getEndDate()))
				.put("dateAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getDateAdjustments()))
				.put("basketConstituent", ExistenceChecker.isSet((Product) o.getBasketConstituent()))
				.put("dividendPaymentDate", ExistenceChecker.isSet((DividendPaymentDate) o.getDividendPaymentDate()))
				.put("dividendValuationDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getDividendValuationDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendPeriod", ValidationType.ONLY_EXISTS, "DividendPeriod", path, "");
		}
		return failure("DividendPeriod", ValidationType.ONLY_EXISTS, "DividendPeriod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
