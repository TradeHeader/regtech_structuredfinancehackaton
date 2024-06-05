package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.validation.FloatingRateCalculationParametersTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.FloatingRateCalculationParametersValidator;
import cdm.observable.asset.calculatedrate.validation.exists.FloatingRateCalculationParametersOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateCalculationParameters.class)
public class FloatingRateCalculationParametersMeta implements RosettaMetaData<FloatingRateCalculationParameters> {

	@Override
	public List<Validator<? super FloatingRateCalculationParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateCalculationParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateCalculationParameters> validator() {
		return new FloatingRateCalculationParametersValidator();
	}

	@Override
	public Validator<? super FloatingRateCalculationParameters> typeFormatValidator() {
		return new FloatingRateCalculationParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateCalculationParameters, Set<String>> onlyExistsValidator() {
		return new FloatingRateCalculationParametersOnlyExistsValidator();
	}
}
