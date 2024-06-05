package cdm.product.asset.floatingrate.meta;

import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import cdm.product.asset.floatingrate.validation.FloatingRateProcessingParametersTypeFormatValidator;
import cdm.product.asset.floatingrate.validation.FloatingRateProcessingParametersValidator;
import cdm.product.asset.floatingrate.validation.exists.FloatingRateProcessingParametersOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateProcessingParameters.class)
public class FloatingRateProcessingParametersMeta implements RosettaMetaData<FloatingRateProcessingParameters> {

	@Override
	public List<Validator<? super FloatingRateProcessingParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateProcessingParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateProcessingParameters> validator() {
		return new FloatingRateProcessingParametersValidator();
	}

	@Override
	public Validator<? super FloatingRateProcessingParameters> typeFormatValidator() {
		return new FloatingRateProcessingParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateProcessingParameters, Set<String>> onlyExistsValidator() {
		return new FloatingRateProcessingParametersOnlyExistsValidator();
	}
}
