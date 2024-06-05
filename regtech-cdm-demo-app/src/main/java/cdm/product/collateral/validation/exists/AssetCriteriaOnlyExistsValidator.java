package cdm.product.collateral.validation.exists;

import cdm.base.datetime.PeriodRange;
import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.CollateralTaxonomy;
import cdm.base.staticdata.asset.common.CurrencyCodeEnum;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.asset.common.MaturityTypeEnum;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.ListingType;
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

public class AssetCriteriaOnlyExistsValidator implements ValidatorWithArg<AssetCriteria, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AssetCriteria> ValidationResult<AssetCriteria> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("collateralAssetType", ExistenceChecker.isSet((List<? extends AssetType>) o.getCollateralAssetType()))
				.put("assetCountryOfOrigin", ExistenceChecker.isSet((List<ISOCountryCodeEnum>) o.getAssetCountryOfOrigin()))
				.put("denominatedCurrency", ExistenceChecker.isSet((List<CurrencyCodeEnum>) o.getDenominatedCurrency()))
				.put("agencyRating", ExistenceChecker.isSet((List<? extends AgencyRatingCriteria>) o.getAgencyRating()))
				.put("maturityType", ExistenceChecker.isSet((MaturityTypeEnum) o.getMaturityType()))
				.put("maturityRange", ExistenceChecker.isSet((PeriodRange) o.getMaturityRange()))
				.put("productIdentifier", ExistenceChecker.isSet((List<? extends ProductIdentifier>) o.getProductIdentifier()))
				.put("collateralTaxonomy", ExistenceChecker.isSet((List<? extends CollateralTaxonomy>) o.getCollateralTaxonomy()))
				.put("domesticCurrencyIssued", ExistenceChecker.isSet((Boolean) o.getDomesticCurrencyIssued()))
				.put("listing", ExistenceChecker.isSet((ListingType) o.getListing()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AssetCriteria", ValidationType.ONLY_EXISTS, "AssetCriteria", path, "");
		}
		return failure("AssetCriteria", ValidationType.ONLY_EXISTS, "AssetCriteria", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
