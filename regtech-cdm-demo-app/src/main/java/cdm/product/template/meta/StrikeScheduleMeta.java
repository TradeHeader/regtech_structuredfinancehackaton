package cdm.product.template.meta;

import cdm.product.template.StrikeSchedule;
import cdm.product.template.validation.StrikeScheduleTypeFormatValidator;
import cdm.product.template.validation.StrikeScheduleValidator;
import cdm.product.template.validation.exists.StrikeScheduleOnlyExistsValidator;
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
@RosettaMeta(model=StrikeSchedule.class)
public class StrikeScheduleMeta implements RosettaMetaData<StrikeSchedule> {

	@Override
	public List<Validator<? super StrikeSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super StrikeSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super StrikeSchedule> validator() {
		return new StrikeScheduleValidator();
	}

	@Override
	public Validator<? super StrikeSchedule> typeFormatValidator() {
		return new StrikeScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super StrikeSchedule, Set<String>> onlyExistsValidator() {
		return new StrikeScheduleOnlyExistsValidator();
	}
}
