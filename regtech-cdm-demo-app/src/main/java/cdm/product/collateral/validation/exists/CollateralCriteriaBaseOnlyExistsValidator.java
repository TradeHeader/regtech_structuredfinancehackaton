package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.CollateralCriteriaBase;
import cdm.product.collateral.IssuerCriteria;
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

public class CollateralCriteriaBaseOnlyExistsValidator implements ValidatorWithArg<CollateralCriteriaBase, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralCriteriaBase> ValidationResult<CollateralCriteriaBase> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("issuer", ExistenceChecker.isSet((List<? extends IssuerCriteria>) o.getIssuer()))
				.put("asset", ExistenceChecker.isSet((List<? extends AssetCriteria>) o.getAsset()))
				.put("appliesTo", ExistenceChecker.isSet((List<CounterpartyRoleEnum>) o.getAppliesTo()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralCriteriaBase", ValidationType.ONLY_EXISTS, "CollateralCriteriaBase", path, "");
		}
		return failure("CollateralCriteriaBase", ValidationType.ONLY_EXISTS, "CollateralCriteriaBase", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
