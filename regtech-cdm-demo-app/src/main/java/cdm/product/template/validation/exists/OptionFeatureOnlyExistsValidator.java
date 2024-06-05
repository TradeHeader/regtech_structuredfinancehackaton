package cdm.product.template.validation.exists;

import cdm.product.template.AveragingCalculation;
import cdm.product.template.Barrier;
import cdm.product.template.FxFeature;
import cdm.product.template.Knock;
import cdm.product.template.OptionFeature;
import cdm.product.template.PassThrough;
import cdm.product.template.StrategyFeature;
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

public class OptionFeatureOnlyExistsValidator implements ValidatorWithArg<OptionFeature, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends OptionFeature> ValidationResult<OptionFeature> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fxFeature", ExistenceChecker.isSet((List<? extends FxFeature>) o.getFxFeature()))
				.put("strategyFeature", ExistenceChecker.isSet((StrategyFeature) o.getStrategyFeature()))
				.put("averagingFeature", ExistenceChecker.isSet((AveragingCalculation) o.getAveragingFeature()))
				.put("barrier", ExistenceChecker.isSet((Barrier) o.getBarrier()))
				.put("knock", ExistenceChecker.isSet((Knock) o.getKnock()))
				.put("passThrough", ExistenceChecker.isSet((PassThrough) o.getPassThrough()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("OptionFeature", ValidationType.ONLY_EXISTS, "OptionFeature", path, "");
		}
		return failure("OptionFeature", ValidationType.ONLY_EXISTS, "OptionFeature", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
