package cdm.event.position.validation.exists;

import cdm.event.position.AggregationParameters;
import cdm.event.position.Portfolio;
import cdm.event.position.PortfolioState;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PortfolioOnlyExistsValidator implements ValidatorWithArg<Portfolio, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Portfolio> ValidationResult<Portfolio> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("aggregationParameters", ExistenceChecker.isSet((AggregationParameters) o.getAggregationParameters()))
				.put("portfolioState", ExistenceChecker.isSet((PortfolioState) o.getPortfolioState()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Portfolio", ValidationType.ONLY_EXISTS, "Portfolio", path, "");
		}
		return failure("Portfolio", ValidationType.ONLY_EXISTS, "Portfolio", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
