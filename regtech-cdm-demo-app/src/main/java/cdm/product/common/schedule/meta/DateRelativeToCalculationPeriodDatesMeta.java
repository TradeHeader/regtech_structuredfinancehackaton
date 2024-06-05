package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
import cdm.product.common.schedule.validation.DateRelativeToCalculationPeriodDatesTypeFormatValidator;
import cdm.product.common.schedule.validation.DateRelativeToCalculationPeriodDatesValidator;
import cdm.product.common.schedule.validation.exists.DateRelativeToCalculationPeriodDatesOnlyExistsValidator;
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
@RosettaMeta(model=DateRelativeToCalculationPeriodDates.class)
public class DateRelativeToCalculationPeriodDatesMeta implements RosettaMetaData<DateRelativeToCalculationPeriodDates> {

	@Override
	public List<Validator<? super DateRelativeToCalculationPeriodDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DateRelativeToCalculationPeriodDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DateRelativeToCalculationPeriodDates> validator() {
		return new DateRelativeToCalculationPeriodDatesValidator();
	}

	@Override
	public Validator<? super DateRelativeToCalculationPeriodDates> typeFormatValidator() {
		return new DateRelativeToCalculationPeriodDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DateRelativeToCalculationPeriodDates, Set<String>> onlyExistsValidator() {
		return new DateRelativeToCalculationPeriodDatesOnlyExistsValidator();
	}
}
