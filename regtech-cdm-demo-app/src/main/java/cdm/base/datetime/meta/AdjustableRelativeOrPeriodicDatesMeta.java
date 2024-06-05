package cdm.base.datetime.meta;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.validation.AdjustableRelativeOrPeriodicDatesTypeFormatValidator;
import cdm.base.datetime.validation.AdjustableRelativeOrPeriodicDatesValidator;
import cdm.base.datetime.validation.exists.AdjustableRelativeOrPeriodicDatesOnlyExistsValidator;
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
@RosettaMeta(model=AdjustableRelativeOrPeriodicDates.class)
public class AdjustableRelativeOrPeriodicDatesMeta implements RosettaMetaData<AdjustableRelativeOrPeriodicDates> {

	@Override
	public List<Validator<? super AdjustableRelativeOrPeriodicDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.AdjustableRelativeOrPeriodicDatesAdjustableRelativeOrPeriodicDatesChoice.class)
		);
	}
	
	@Override
	public List<Function<? super AdjustableRelativeOrPeriodicDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdjustableRelativeOrPeriodicDates> validator() {
		return new AdjustableRelativeOrPeriodicDatesValidator();
	}

	@Override
	public Validator<? super AdjustableRelativeOrPeriodicDates> typeFormatValidator() {
		return new AdjustableRelativeOrPeriodicDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdjustableRelativeOrPeriodicDates, Set<String>> onlyExistsValidator() {
		return new AdjustableRelativeOrPeriodicDatesOnlyExistsValidator();
	}
}
