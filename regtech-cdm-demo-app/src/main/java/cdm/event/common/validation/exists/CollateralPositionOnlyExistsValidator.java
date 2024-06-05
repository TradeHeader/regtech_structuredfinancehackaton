package cdm.event.common.validation.exists;

import cdm.event.common.CollateralPosition;
import cdm.event.common.CollateralStatusEnum;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.asset.Money;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CollateralPositionOnlyExistsValidator implements ValidatorWithArg<CollateralPosition, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralPosition> ValidationResult<CollateralPosition> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("priceQuantity", ExistenceChecker.isSet((List<? extends PriceQuantity>) o.getPriceQuantity()))
				.put("product", ExistenceChecker.isSet((Product) o.getProduct()))
				.put("cashBalance", ExistenceChecker.isSet((Money) o.getCashBalance()))
				.put("tradeReference", ExistenceChecker.isSet((ReferenceWithMetaTradeState) o.getTradeReference()))
				.put("treatment", ExistenceChecker.isSet((CollateralTreatment) o.getTreatment()))
				.put("collateralPositionStatus", ExistenceChecker.isSet((CollateralStatusEnum) o.getCollateralPositionStatus()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralPosition", ValidationType.ONLY_EXISTS, "CollateralPosition", path, "");
		}
		return failure("CollateralPosition", ValidationType.ONLY_EXISTS, "CollateralPosition", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
