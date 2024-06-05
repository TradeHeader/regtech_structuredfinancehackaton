package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.DateRelativeToValuationDates;
import cdm.product.common.schedule.validation.DateRelativeToValuationDatesTypeFormatValidator;
import cdm.product.common.schedule.validation.DateRelativeToValuationDatesValidator;
import cdm.product.common.schedule.validation.exists.DateRelativeToValuationDatesOnlyExistsValidator;
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
@RosettaMeta(model=DateRelativeToValuationDates.class)
public class DateRelativeToValuationDatesMeta implements RosettaMetaData<DateRelativeToValuationDates> {

	@Override
	public List<Validator<? super DateRelativeToValuationDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DateRelativeToValuationDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DateRelativeToValuationDates> validator() {
		return new DateRelativeToValuationDatesValidator();
	}

	@Override
	public Validator<? super DateRelativeToValuationDates> typeFormatValidator() {
		return new DateRelativeToValuationDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DateRelativeToValuationDates, Set<String>> onlyExistsValidator() {
		return new DateRelativeToValuationDatesOnlyExistsValidator();
	}
}
