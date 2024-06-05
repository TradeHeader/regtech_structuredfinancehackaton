package cdm.observable.asset.fro.meta;

import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults;
import cdm.observable.asset.fro.validation.FloatingRateIndexCalculationDefaultsTypeFormatValidator;
import cdm.observable.asset.fro.validation.FloatingRateIndexCalculationDefaultsValidator;
import cdm.observable.asset.fro.validation.exists.FloatingRateIndexCalculationDefaultsOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=FloatingRateIndexCalculationDefaults.class)
public class FloatingRateIndexCalculationDefaultsMeta implements RosettaMetaData<FloatingRateIndexCalculationDefaults> {

	@Override
	public List<Validator<? super FloatingRateIndexCalculationDefaults>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateIndexCalculationDefaults, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateIndexCalculationDefaults> validator() {
		return new FloatingRateIndexCalculationDefaultsValidator();
	}

	@Override
	public Validator<? super FloatingRateIndexCalculationDefaults> typeFormatValidator() {
		return new FloatingRateIndexCalculationDefaultsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateIndexCalculationDefaults, Set<String>> onlyExistsValidator() {
		return new FloatingRateIndexCalculationDefaultsOnlyExistsValidator();
	}
}
