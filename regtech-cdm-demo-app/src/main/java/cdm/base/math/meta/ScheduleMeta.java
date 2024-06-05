package cdm.base.math.meta;

import cdm.base.math.Schedule;
import cdm.base.math.validation.ScheduleTypeFormatValidator;
import cdm.base.math.validation.ScheduleValidator;
import cdm.base.math.validation.exists.ScheduleOnlyExistsValidator;
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
@RosettaMeta(model=Schedule.class)
public class ScheduleMeta implements RosettaMetaData<Schedule> {

	@Override
	public List<Validator<? super Schedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Schedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Schedule> validator() {
		return new ScheduleValidator();
	}

	@Override
	public Validator<? super Schedule> typeFormatValidator() {
		return new ScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Schedule, Set<String>> onlyExistsValidator() {
		return new ScheduleOnlyExistsValidator();
	}
}
