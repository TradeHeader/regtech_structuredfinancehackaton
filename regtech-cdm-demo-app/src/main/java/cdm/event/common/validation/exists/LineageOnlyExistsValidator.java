package cdm.event.common.validation.exists;

import cdm.event.common.Lineage;
import cdm.event.common.metafields.ReferenceWithMetaTrade;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
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

public class LineageOnlyExistsValidator implements ValidatorWithArg<Lineage, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Lineage> ValidationResult<Lineage> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("tradeReference", ExistenceChecker.isSet((List<? extends ReferenceWithMetaTrade>) o.getTradeReference()))
				.put("eventReference", ExistenceChecker.isSet((List<? extends ReferenceWithMetaWorkflowStep>) o.getEventReference()))
				.put("portfolioStateReference", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPortfolioState>) o.getPortfolioStateReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Lineage", ValidationType.ONLY_EXISTS, "Lineage", path, "");
		}
		return failure("Lineage", ValidationType.ONLY_EXISTS, "Lineage", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
