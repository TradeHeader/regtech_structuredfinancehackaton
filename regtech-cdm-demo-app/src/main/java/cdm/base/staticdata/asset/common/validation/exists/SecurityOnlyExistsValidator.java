package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.EquityTypeEnum;
import cdm.base.staticdata.asset.common.FundProductTypeEnum;
import cdm.base.staticdata.asset.common.MortgageSectorEnum;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.product.template.EconomicTerms;
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

public class SecurityOnlyExistsValidator implements ValidatorWithArg<Security, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Security> ValidationResult<Security> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("productTaxonomy", ExistenceChecker.isSet((List<? extends ProductTaxonomy>) o.getProductTaxonomy()))
				.put("productIdentifier", ExistenceChecker.isSet((List<? extends ReferenceWithMetaProductIdentifier>) o.getProductIdentifier()))
				.put("securityType", ExistenceChecker.isSet((SecurityTypeEnum) o.getSecurityType()))
				.put("debtType", ExistenceChecker.isSet((DebtType) o.getDebtType()))
				.put("equityType", ExistenceChecker.isSet((EquityTypeEnum) o.getEquityType()))
				.put("fundType", ExistenceChecker.isSet((FundProductTypeEnum) o.getFundType()))
				.put("economicTerms", ExistenceChecker.isSet((EconomicTerms) o.getEconomicTerms()))
				.put("mortgageSector", ExistenceChecker.isSet((MortgageSectorEnum) o.getMortgageSector()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Security", ValidationType.ONLY_EXISTS, "Security", path, "");
		}
		return failure("Security", ValidationType.ONLY_EXISTS, "Security", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
