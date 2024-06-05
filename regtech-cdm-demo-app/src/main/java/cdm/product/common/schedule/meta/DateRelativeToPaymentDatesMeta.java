package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.DateRelativeToPaymentDates;
import cdm.product.common.schedule.validation.DateRelativeToPaymentDatesTypeFormatValidator;
import cdm.product.common.schedule.validation.DateRelativeToPaymentDatesValidator;
import cdm.product.common.schedule.validation.exists.DateRelativeToPaymentDatesOnlyExistsValidator;
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
@RosettaMeta(model=DateRelativeToPaymentDates.class)
public class DateRelativeToPaymentDatesMeta implements RosettaMetaData<DateRelativeToPaymentDates> {

	@Override
	public List<Validator<? super DateRelativeToPaymentDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DateRelativeToPaymentDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DateRelativeToPaymentDates> validator() {
		return new DateRelativeToPaymentDatesValidator();
	}

	@Override
	public Validator<? super DateRelativeToPaymentDates> typeFormatValidator() {
		return new DateRelativeToPaymentDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DateRelativeToPaymentDates, Set<String>> onlyExistsValidator() {
		return new DateRelativeToPaymentDatesOnlyExistsValidator();
	}
}
