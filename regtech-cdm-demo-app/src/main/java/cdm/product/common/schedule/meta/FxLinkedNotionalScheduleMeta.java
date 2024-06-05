package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import cdm.product.common.schedule.validation.FxLinkedNotionalScheduleTypeFormatValidator;
import cdm.product.common.schedule.validation.FxLinkedNotionalScheduleValidator;
import cdm.product.common.schedule.validation.exists.FxLinkedNotionalScheduleOnlyExistsValidator;
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
@RosettaMeta(model=FxLinkedNotionalSchedule.class)
public class FxLinkedNotionalScheduleMeta implements RosettaMetaData<FxLinkedNotionalSchedule> {

	@Override
	public List<Validator<? super FxLinkedNotionalSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FxLinkedNotionalSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxLinkedNotionalSchedule> validator() {
		return new FxLinkedNotionalScheduleValidator();
	}

	@Override
	public Validator<? super FxLinkedNotionalSchedule> typeFormatValidator() {
		return new FxLinkedNotionalScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxLinkedNotionalSchedule, Set<String>> onlyExistsValidator() {
		return new FxLinkedNotionalScheduleOnlyExistsValidator();
	}
}
