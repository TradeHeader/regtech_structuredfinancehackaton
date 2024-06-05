package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.IndependentAmount;
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

public class CollateralOnlyExistsValidator implements ValidatorWithArg<Collateral, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Collateral> ValidationResult<Collateral> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("independentAmount", ExistenceChecker.isSet((IndependentAmount) o.getIndependentAmount()))
				.put("portfolioIdentifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getPortfolioIdentifier()))
				.put("collateralPortfolio", ExistenceChecker.isSet((List<? extends ReferenceWithMetaCollateralPortfolio>) o.getCollateralPortfolio()))
				.put("collateralProvisions", ExistenceChecker.isSet((CollateralProvisions) o.getCollateralProvisions()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Collateral", ValidationType.ONLY_EXISTS, "Collateral", path, "");
		}
		return failure("Collateral", ValidationType.ONLY_EXISTS, "Collateral", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
