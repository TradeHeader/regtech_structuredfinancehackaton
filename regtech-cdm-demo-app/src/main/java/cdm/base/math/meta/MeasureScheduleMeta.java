package cdm.base.math.meta;

import cdm.base.math.MeasureSchedule;
import cdm.base.math.validation.MeasureScheduleTypeFormatValidator;
import cdm.base.math.validation.MeasureScheduleValidator;
import cdm.base.math.validation.exists.MeasureScheduleOnlyExistsValidator;
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
@RosettaMeta(model=MeasureSchedule.class)
public class MeasureScheduleMeta implements RosettaMetaData<MeasureSchedule> {

	@Override
	public List<Validator<? super MeasureSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.MeasureScheduleValueExists.class)
		);
	}
	
	@Override
	public List<Function<? super MeasureSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MeasureSchedule> validator() {
		return new MeasureScheduleValidator();
	}

	@Override
	public Validator<? super MeasureSchedule> typeFormatValidator() {
		return new MeasureScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MeasureSchedule, Set<String>> onlyExistsValidator() {
		return new MeasureScheduleOnlyExistsValidator();
	}
}
