package cdm.event.position.meta;

import cdm.event.position.AggregationParameters;
import cdm.event.position.validation.AggregationParametersTypeFormatValidator;
import cdm.event.position.validation.AggregationParametersValidator;
import cdm.event.position.validation.exists.AggregationParametersOnlyExistsValidator;
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
@RosettaMeta(model=AggregationParameters.class)
public class AggregationParametersMeta implements RosettaMetaData<AggregationParameters> {

	@Override
	public List<Validator<? super AggregationParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AggregationParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AggregationParameters> validator() {
		return new AggregationParametersValidator();
	}

	@Override
	public Validator<? super AggregationParameters> typeFormatValidator() {
		return new AggregationParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AggregationParameters, Set<String>> onlyExistsValidator() {
		return new AggregationParametersOnlyExistsValidator();
	}
}
