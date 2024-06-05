package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.ObservationParameters;
import cdm.observable.asset.calculatedrate.validation.ObservationParametersTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.ObservationParametersValidator;
import cdm.observable.asset.calculatedrate.validation.exists.ObservationParametersOnlyExistsValidator;
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
@RosettaMeta(model=ObservationParameters.class)
public class ObservationParametersMeta implements RosettaMetaData<ObservationParameters> {

	@Override
	public List<Validator<? super ObservationParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ObservationParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationParameters> validator() {
		return new ObservationParametersValidator();
	}

	@Override
	public Validator<? super ObservationParameters> typeFormatValidator() {
		return new ObservationParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationParameters, Set<String>> onlyExistsValidator() {
		return new ObservationParametersOnlyExistsValidator();
	}
}
