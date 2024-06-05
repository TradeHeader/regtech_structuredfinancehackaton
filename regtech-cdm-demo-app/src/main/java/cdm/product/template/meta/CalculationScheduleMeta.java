package cdm.product.template.meta;

import cdm.product.template.CalculationSchedule;
import cdm.product.template.validation.CalculationScheduleTypeFormatValidator;
import cdm.product.template.validation.CalculationScheduleValidator;
import cdm.product.template.validation.exists.CalculationScheduleOnlyExistsValidator;
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
@RosettaMeta(model=CalculationSchedule.class)
public class CalculationScheduleMeta implements RosettaMetaData<CalculationSchedule> {

	@Override
	public List<Validator<? super CalculationSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculationSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationSchedule> validator() {
		return new CalculationScheduleValidator();
	}

	@Override
	public Validator<? super CalculationSchedule> typeFormatValidator() {
		return new CalculationScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationSchedule, Set<String>> onlyExistsValidator() {
		return new CalculationScheduleOnlyExistsValidator();
	}
}
