package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.AgencyRatingCriteria;
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

public class IssuerCriteriaOnlyExistsValidator implements ValidatorWithArg<IssuerCriteria, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends IssuerCriteria> ValidationResult<IssuerCriteria> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("issuerType", ExistenceChecker.isSet((List<? extends CollateralIssuerType>) o.getIssuerType()))
				.put("issuerCountryOfOrigin", ExistenceChecker.isSet((List<ISOCountryCodeEnum>) o.getIssuerCountryOfOrigin()))
				.put("issuerName", ExistenceChecker.isSet((List<? extends LegalEntity>) o.getIssuerName()))
				.put("issuerAgencyRating", ExistenceChecker.isSet((List<? extends AgencyRatingCriteria>) o.getIssuerAgencyRating()))
				.put("sovereignAgencyRating", ExistenceChecker.isSet((List<? extends AgencyRatingCriteria>) o.getSovereignAgencyRating()))
				.put("counterpartyOwnIssuePermitted", ExistenceChecker.isSet((Boolean) o.getCounterpartyOwnIssuePermitted()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("IssuerCriteria", ValidationType.ONLY_EXISTS, "IssuerCriteria", path, "");
		}
		return failure("IssuerCriteria", ValidationType.ONLY_EXISTS, "IssuerCriteria", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
