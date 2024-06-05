package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.CurrencyCodeEnum;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.EligibilityQuery;
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

public class EligibilityQueryOnlyExistsValidator implements ValidatorWithArg<EligibilityQuery, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EligibilityQuery> ValidationResult<EligibilityQuery> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("maturity", ExistenceChecker.isSet((BigDecimal) o.getMaturity()))
				.put("collateralAssetType", ExistenceChecker.isSet((AssetType) o.getCollateralAssetType()))
				.put("assetCountryOfOrigin", ExistenceChecker.isSet((ISOCountryCodeEnum) o.getAssetCountryOfOrigin()))
				.put("denominatedCurrency", ExistenceChecker.isSet((CurrencyCodeEnum) o.getDenominatedCurrency()))
				.put("agencyRating", ExistenceChecker.isSet((AgencyRatingCriteria) o.getAgencyRating()))
				.put("issuerType", ExistenceChecker.isSet((CollateralIssuerType) o.getIssuerType()))
				.put("issuerName", ExistenceChecker.isSet((LegalEntity) o.getIssuerName()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EligibilityQuery", ValidationType.ONLY_EXISTS, "EligibilityQuery", path, "");
		}
		return failure("EligibilityQuery", ValidationType.ONLY_EXISTS, "EligibilityQuery", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
