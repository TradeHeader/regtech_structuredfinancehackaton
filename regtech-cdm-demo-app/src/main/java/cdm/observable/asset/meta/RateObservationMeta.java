package cdm.observable.asset.meta;

import cdm.observable.asset.RateObservation;
import cdm.observable.asset.validation.RateObservationTypeFormatValidator;
import cdm.observable.asset.validation.RateObservationValidator;
import cdm.observable.asset.validation.exists.RateObservationOnlyExistsValidator;
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
@RosettaMeta(model=RateObservation.class)
public class RateObservationMeta implements RosettaMetaData<RateObservation> {

	@Override
	public List<Validator<? super RateObservation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.RateObservationPositiveObservationWeight.class)
		);
	}
	
	@Override
	public List<Function<? super RateObservation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RateObservation> validator() {
		return new RateObservationValidator();
	}

	@Override
	public Validator<? super RateObservation> typeFormatValidator() {
		return new RateObservationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RateObservation, Set<String>> onlyExistsValidator() {
		return new RateObservationOnlyExistsValidator();
	}
}
