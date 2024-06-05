package cdm.observable.asset.validation.exists;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceOperandEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PriceCompositeOnlyExistsValidator implements ValidatorWithArg<PriceComposite, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PriceComposite> ValidationResult<PriceComposite> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("baseValue", ExistenceChecker.isSet((BigDecimal) o.getBaseValue()))
				.put("operand", ExistenceChecker.isSet((BigDecimal) o.getOperand()))
				.put("arithmeticOperator", ExistenceChecker.isSet((ArithmeticOperationEnum) o.getArithmeticOperator()))
				.put("operandType", ExistenceChecker.isSet((PriceOperandEnum) o.getOperandType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PriceComposite", ValidationType.ONLY_EXISTS, "PriceComposite", path, "");
		}
		return failure("PriceComposite", ValidationType.ONLY_EXISTS, "PriceComposite", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
