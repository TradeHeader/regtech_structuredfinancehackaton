package cdm.event.common.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.CollateralBalance;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPosition;
import cdm.legaldocumentation.common.metafields.ReferenceWithMetaLegalAgreement;
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

public class CollateralPortfolioOnlyExistsValidator implements ValidatorWithArg<CollateralPortfolio, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralPortfolio> ValidationResult<CollateralPortfolio> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("portfolioIdentifier", ExistenceChecker.isSet((Identifier) o.getPortfolioIdentifier()))
				.put("collateralPosition", ExistenceChecker.isSet((List<? extends CollateralPosition>) o.getCollateralPosition()))
				.put("collateralBalance", ExistenceChecker.isSet((List<? extends CollateralBalance>) o.getCollateralBalance()))
				.put("legalAgreement", ExistenceChecker.isSet((ReferenceWithMetaLegalAgreement) o.getLegalAgreement()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralPortfolio", ValidationType.ONLY_EXISTS, "CollateralPortfolio", path, "");
		}
		return failure("CollateralPortfolio", ValidationType.ONLY_EXISTS, "CollateralPortfolio", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
