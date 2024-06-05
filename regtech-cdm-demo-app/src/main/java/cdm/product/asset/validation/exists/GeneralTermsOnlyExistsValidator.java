package cdm.product.asset.validation.exists;

import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.CreditIndexReferenceInformation;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.ReferenceInformation;
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

public class GeneralTermsOnlyExistsValidator implements ValidatorWithArg<GeneralTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends GeneralTerms> ValidationResult<GeneralTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("referenceInformation", ExistenceChecker.isSet((ReferenceInformation) o.getReferenceInformation()))
				.put("indexReferenceInformation", ExistenceChecker.isSet((CreditIndexReferenceInformation) o.getIndexReferenceInformation()))
				.put("basketReferenceInformation", ExistenceChecker.isSet((BasketReferenceInformation) o.getBasketReferenceInformation()))
				.put("additionalTerm", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getAdditionalTerm()))
				.put("substitution", ExistenceChecker.isSet((Boolean) o.getSubstitution()))
				.put("modifiedEquityDelivery", ExistenceChecker.isSet((Boolean) o.getModifiedEquityDelivery()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("GeneralTerms", ValidationType.ONLY_EXISTS, "GeneralTerms", path, "");
		}
		return failure("GeneralTerms", ValidationType.ONLY_EXISTS, "GeneralTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
