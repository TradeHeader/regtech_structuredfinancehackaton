package cdm.event.common.validation.exists;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.event.common.TermsChangeInstruction;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
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

public class TermsChangeInstructionOnlyExistsValidator implements ValidatorWithArg<TermsChangeInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TermsChangeInstruction> ValidationResult<TermsChangeInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("product", ExistenceChecker.isSet((Product) o.getProduct()))
				.put("ancillaryParty", ExistenceChecker.isSet((List<? extends AncillaryParty>) o.getAncillaryParty()))
				.put("adjustment", ExistenceChecker.isSet((NotionalAdjustmentEnum) o.getAdjustment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TermsChangeInstruction", ValidationType.ONLY_EXISTS, "TermsChangeInstruction", path, "");
		}
		return failure("TermsChangeInstruction", ValidationType.ONLY_EXISTS, "TermsChangeInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
