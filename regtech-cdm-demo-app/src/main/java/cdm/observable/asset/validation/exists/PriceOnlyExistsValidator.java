package cdm.observable.asset.validation.exists;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.DatedValue;
import cdm.base.math.UnitType;
import cdm.observable.asset.CashPrice;
import cdm.observable.asset.Price;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.observable.asset.PriceTypeEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PriceOnlyExistsValidator implements ValidatorWithArg<Price, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Price> ValidationResult<Price> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("value", ExistenceChecker.isSet((BigDecimal) o.getValue()))
				.put("unit", ExistenceChecker.isSet((UnitType) o.getUnit()))
				.put("datedValue", ExistenceChecker.isSet((List<? extends DatedValue>) o.getDatedValue()))
				.put("perUnitOf", ExistenceChecker.isSet((UnitType) o.getPerUnitOf()))
				.put("priceType", ExistenceChecker.isSet((PriceTypeEnum) o.getPriceType()))
				.put("priceExpression", ExistenceChecker.isSet((PriceExpressionEnum) o.getPriceExpression()))
				.put("composite", ExistenceChecker.isSet((PriceComposite) o.getComposite()))
				.put("arithmeticOperator", ExistenceChecker.isSet((ArithmeticOperationEnum) o.getArithmeticOperator()))
				.put("cashPrice", ExistenceChecker.isSet((CashPrice) o.getCashPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Price", ValidationType.ONLY_EXISTS, "Price", path, "");
		}
		return failure("Price", ValidationType.ONLY_EXISTS, "Price", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
