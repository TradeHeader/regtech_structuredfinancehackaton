package cdm.base.datetime.meta;

import cdm.base.datetime.PeriodicDates;
import cdm.base.datetime.validation.PeriodicDatesTypeFormatValidator;
import cdm.base.datetime.validation.PeriodicDatesValidator;
import cdm.base.datetime.validation.exists.PeriodicDatesOnlyExistsValidator;
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
@RosettaMeta(model=PeriodicDates.class)
public class PeriodicDatesMeta implements RosettaMetaData<PeriodicDates> {

	@Override
	public List<Validator<? super PeriodicDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PeriodicDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PeriodicDates> validator() {
		return new PeriodicDatesValidator();
	}

	@Override
	public Validator<? super PeriodicDates> typeFormatValidator() {
		return new PeriodicDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PeriodicDates, Set<String>> onlyExistsValidator() {
		return new PeriodicDatesOnlyExistsValidator();
	}
}
