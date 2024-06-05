package cdm.observable.asset.validation.exists;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.CsaTypeEnum;
import cdm.observable.asset.PartyDeterminationEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CashCollateralValuationMethodOnlyExistsValidator implements ValidatorWithArg<CashCollateralValuationMethod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CashCollateralValuationMethod> ValidationResult<CashCollateralValuationMethod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("applicableCsa", ExistenceChecker.isSet((CsaTypeEnum) o.getApplicableCsa()))
				.put("cashCollateralCurrency", ExistenceChecker.isSet((String) o.getCashCollateralCurrency()))
				.put("cashCollateralInterestRate", ExistenceChecker.isSet((FieldWithMetaString) o.getCashCollateralInterestRate()))
				.put("agreedDiscountRate", ExistenceChecker.isSet((FieldWithMetaString) o.getAgreedDiscountRate()))
				.put("protectedParty", ExistenceChecker.isSet((List<PartyDeterminationEnum>) o.getProtectedParty()))
				.put("prescribedDocumentationAdjustment", ExistenceChecker.isSet((Boolean) o.getPrescribedDocumentationAdjustment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CashCollateralValuationMethod", ValidationType.ONLY_EXISTS, "CashCollateralValuationMethod", path, "");
		}
		return failure("CashCollateralValuationMethod", ValidationType.ONLY_EXISTS, "CashCollateralValuationMethod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
