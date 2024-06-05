package cdm.product.collateral.validation.exists;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditNotationBoundaryEnum;
import cdm.observable.asset.CreditNotationMismatchResolutionEnum;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.product.collateral.AgencyRatingCriteria;
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

public class AgencyRatingCriteriaOnlyExistsValidator implements ValidatorWithArg<AgencyRatingCriteria, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AgencyRatingCriteria> ValidationResult<AgencyRatingCriteria> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("qualifier", ExistenceChecker.isSet((QuantifierEnum) o.getQualifier()))
				.put("creditNotation", ExistenceChecker.isSet((List<? extends CreditNotation>) o.getCreditNotation()))
				.put("mismatchResolution", ExistenceChecker.isSet((CreditNotationMismatchResolutionEnum) o.getMismatchResolution()))
				.put("referenceAgency", ExistenceChecker.isSet((CreditRatingAgencyEnum) o.getReferenceAgency()))
				.put("boundary", ExistenceChecker.isSet((CreditNotationBoundaryEnum) o.getBoundary()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AgencyRatingCriteria", ValidationType.ONLY_EXISTS, "AgencyRatingCriteria", path, "");
		}
		return failure("AgencyRatingCriteria", ValidationType.ONLY_EXISTS, "AgencyRatingCriteria", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
