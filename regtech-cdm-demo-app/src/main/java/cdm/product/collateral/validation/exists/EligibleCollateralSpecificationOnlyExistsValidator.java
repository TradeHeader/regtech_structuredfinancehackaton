package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecification;
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

public class EligibleCollateralSpecificationOnlyExistsValidator implements ValidatorWithArg<EligibleCollateralSpecification, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EligibleCollateralSpecification> ValidationResult<EligibleCollateralSpecification> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getIdentifier()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("counterparty", ExistenceChecker.isSet((List<? extends Counterparty>) o.getCounterparty()))
				.put("criteria", ExistenceChecker.isSet((List<? extends EligibleCollateralCriteria>) o.getCriteria()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EligibleCollateralSpecification", ValidationType.ONLY_EXISTS, "EligibleCollateralSpecification", path, "");
		}
		return failure("EligibleCollateralSpecification", ValidationType.ONLY_EXISTS, "EligibleCollateralSpecification", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
