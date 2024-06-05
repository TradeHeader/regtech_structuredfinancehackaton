package cdm.base.datetime.meta;

import cdm.base.datetime.AveragingSchedule;
import cdm.base.datetime.validation.AveragingScheduleTypeFormatValidator;
import cdm.base.datetime.validation.AveragingScheduleValidator;
import cdm.base.datetime.validation.exists.AveragingScheduleOnlyExistsValidator;
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
@RosettaMeta(model=AveragingSchedule.class)
public class AveragingScheduleMeta implements RosettaMetaData<AveragingSchedule> {

	@Override
	public List<Validator<? super AveragingSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AveragingSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AveragingSchedule> validator() {
		return new AveragingScheduleValidator();
	}

	@Override
	public Validator<? super AveragingSchedule> typeFormatValidator() {
		return new AveragingScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AveragingSchedule, Set<String>> onlyExistsValidator() {
		return new AveragingScheduleOnlyExistsValidator();
	}
}
