package cdm.legaldocumentation.master.validation.exists;

import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import cdm.legaldocumentation.master.Clause;
import cdm.legaldocumentation.master.EquityCorporateEvents;
import cdm.legaldocumentation.master.ExtraordinaryEvents;
import cdm.legaldocumentation.master.IndexAdjustmentEvents;
import cdm.legaldocumentation.master.NationalizationOrInsolvencyOrDelistingEventEnum;
import cdm.legaldocumentation.master.Representations;
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

public class ExtraordinaryEventsOnlyExistsValidator implements ValidatorWithArg<ExtraordinaryEvents, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExtraordinaryEvents> ValidationResult<ExtraordinaryEvents> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("additionalBespokeTerms", ExistenceChecker.isSet((List<? extends Clause>) o.getAdditionalBespokeTerms()))
				.put("mergerEvents", ExistenceChecker.isSet((EquityCorporateEvents) o.getMergerEvents()))
				.put("tenderOfferEvents", ExistenceChecker.isSet((EquityCorporateEvents) o.getTenderOfferEvents()))
				.put("compositionOfCombinedConsideration", ExistenceChecker.isSet((Boolean) o.getCompositionOfCombinedConsideration()))
				.put("indexAdjustmentEvents", ExistenceChecker.isSet((IndexAdjustmentEvents) o.getIndexAdjustmentEvents()))
				.put("additionalDisruptionEvents", ExistenceChecker.isSet((AdditionalDisruptionEvents) o.getAdditionalDisruptionEvents()))
				.put("failureToDeliver", ExistenceChecker.isSet((Boolean) o.getFailureToDeliver()))
				.put("representations", ExistenceChecker.isSet((Representations) o.getRepresentations()))
				.put("nationalizationOrInsolvency", ExistenceChecker.isSet((NationalizationOrInsolvencyOrDelistingEventEnum) o.getNationalizationOrInsolvency()))
				.put("delisting", ExistenceChecker.isSet((NationalizationOrInsolvencyOrDelistingEventEnum) o.getDelisting()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExtraordinaryEvents", ValidationType.ONLY_EXISTS, "ExtraordinaryEvents", path, "");
		}
		return failure("ExtraordinaryEvents", ValidationType.ONLY_EXISTS, "ExtraordinaryEvents", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
