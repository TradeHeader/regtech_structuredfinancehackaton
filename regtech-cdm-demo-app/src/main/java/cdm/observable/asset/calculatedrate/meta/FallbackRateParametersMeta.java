package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.validation.FallbackRateParametersTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.FallbackRateParametersValidator;
import cdm.observable.asset.calculatedrate.validation.exists.FallbackRateParametersOnlyExistsValidator;
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
@RosettaMeta(model=FallbackRateParameters.class)
public class FallbackRateParametersMeta implements RosettaMetaData<FallbackRateParameters> {

	@Override
	public List<Validator<? super FallbackRateParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FallbackRateParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FallbackRateParameters> validator() {
		return new FallbackRateParametersValidator();
	}

	@Override
	public Validator<? super FallbackRateParameters> typeFormatValidator() {
		return new FallbackRateParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FallbackRateParameters, Set<String>> onlyExistsValidator() {
		return new FallbackRateParametersOnlyExistsValidator();
	}
}
