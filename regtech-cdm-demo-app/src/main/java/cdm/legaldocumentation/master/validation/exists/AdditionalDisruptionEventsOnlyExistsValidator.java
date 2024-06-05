package cdm.legaldocumentation.master.validation.exists;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import cdm.legaldocumentation.master.Clause;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AdditionalDisruptionEventsOnlyExistsValidator implements ValidatorWithArg<AdditionalDisruptionEvents, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AdditionalDisruptionEvents> ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("changeInLaw", ExistenceChecker.isSet((Boolean) o.getChangeInLaw()))
				.put("failureToDeliver", ExistenceChecker.isSet((Boolean) o.getFailureToDeliver()))
				.put("insolvencyFiling", ExistenceChecker.isSet((Boolean) o.getInsolvencyFiling()))
				.put("hedgingDisruption", ExistenceChecker.isSet((Boolean) o.getHedgingDisruption()))
				.put("increasedCostOfHedging", ExistenceChecker.isSet((Boolean) o.getIncreasedCostOfHedging()))
				.put("foreignOwnershipEvent", ExistenceChecker.isSet((Boolean) o.getForeignOwnershipEvent()))
				.put("lossOfStockBorrow", ExistenceChecker.isSet((Boolean) o.getLossOfStockBorrow()))
				.put("maximumStockLoanRate", ExistenceChecker.isSet((BigDecimal) o.getMaximumStockLoanRate()))
				.put("increasedCostOfStockBorrow", ExistenceChecker.isSet((Boolean) o.getIncreasedCostOfStockBorrow()))
				.put("initialStockLoanRate", ExistenceChecker.isSet((BigDecimal) o.getInitialStockLoanRate()))
				.put("determiningParty", ExistenceChecker.isSet((AncillaryRoleEnum) o.getDeterminingParty()))
				.put("additionalBespokeTerms", ExistenceChecker.isSet((List<? extends Clause>) o.getAdditionalBespokeTerms()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AdditionalDisruptionEvents", ValidationType.ONLY_EXISTS, "AdditionalDisruptionEvents", path, "");
		}
		return failure("AdditionalDisruptionEvents", ValidationType.ONLY_EXISTS, "AdditionalDisruptionEvents", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
