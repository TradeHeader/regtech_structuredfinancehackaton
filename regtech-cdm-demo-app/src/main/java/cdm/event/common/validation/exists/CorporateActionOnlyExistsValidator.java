package cdm.event.common.validation.exists;

import cdm.event.common.CorporateAction;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CorporateActionOnlyExistsValidator implements ValidatorWithArg<CorporateAction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CorporateAction> ValidationResult<CorporateAction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("corporateActionType", ExistenceChecker.isSet((CorporateActionTypeEnum) o.getCorporateActionType()))
				.put("exDate", ExistenceChecker.isSet((Date) o.getExDate()))
				.put("payDate", ExistenceChecker.isSet((Date) o.getPayDate()))
				.put("underlier", ExistenceChecker.isSet((Product) o.getUnderlier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CorporateAction", ValidationType.ONLY_EXISTS, "CorporateAction", path, "");
		}
		return failure("CorporateAction", ValidationType.ONLY_EXISTS, "CorporateAction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
