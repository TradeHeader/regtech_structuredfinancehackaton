package cdm.observable.event.meta;

import cdm.observable.event.Observation;
import cdm.observable.event.validation.ObservationTypeFormatValidator;
import cdm.observable.event.validation.ObservationValidator;
import cdm.observable.event.validation.exists.ObservationOnlyExistsValidator;
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
@RosettaMeta(model=Observation.class)
public class ObservationMeta implements RosettaMetaData<Observation> {

	@Override
	public List<Validator<? super Observation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Observation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Observation> validator() {
		return new ObservationValidator();
	}

	@Override
	public Validator<? super Observation> typeFormatValidator() {
		return new ObservationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Observation, Set<String>> onlyExistsValidator() {
		return new ObservationOnlyExistsValidator();
	}
}
