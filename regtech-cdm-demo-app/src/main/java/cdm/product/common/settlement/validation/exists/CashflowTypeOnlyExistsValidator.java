package cdm.product.common.settlement.validation.exists;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.ScheduledTransferEnum;
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

public class CashflowTypeOnlyExistsValidator implements ValidatorWithArg<CashflowType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CashflowType> ValidationResult<CashflowType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cashflowType", ExistenceChecker.isSet((ScheduledTransferEnum) o.getCashflowType()))
				.put("cashPrice", ExistenceChecker.isSet((CashPrice) o.getCashPrice()))
				.put("priceExpression", ExistenceChecker.isSet((PriceExpressionEnum) o.getPriceExpression()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CashflowType", ValidationType.ONLY_EXISTS, "CashflowType", path, "");
		}
		return failure("CashflowType", ValidationType.ONLY_EXISTS, "CashflowType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
