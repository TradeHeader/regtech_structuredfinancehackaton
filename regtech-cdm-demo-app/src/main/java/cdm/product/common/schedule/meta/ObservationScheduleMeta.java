package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.ObservationSchedule;
import cdm.product.common.schedule.validation.ObservationScheduleTypeFormatValidator;
import cdm.product.common.schedule.validation.ObservationScheduleValidator;
import cdm.product.common.schedule.validation.exists.ObservationScheduleOnlyExistsValidator;
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
@RosettaMeta(model=ObservationSchedule.class)
public class ObservationScheduleMeta implements RosettaMetaData<ObservationSchedule> {

	@Override
	public List<Validator<? super ObservationSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.ObservationScheduleAdjustedDate.class)
		);
	}
	
	@Override
	public List<Function<? super ObservationSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationSchedule> validator() {
		return new ObservationScheduleValidator();
	}

	@Override
	public Validator<? super ObservationSchedule> typeFormatValidator() {
		return new ObservationScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationSchedule, Set<String>> onlyExistsValidator() {
		return new ObservationScheduleOnlyExistsValidator();
	}
}
