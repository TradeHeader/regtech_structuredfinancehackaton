package cdm.base.datetime.meta;

import cdm.base.datetime.DateRange;
import cdm.base.datetime.validation.DateRangeTypeFormatValidator;
import cdm.base.datetime.validation.DateRangeValidator;
import cdm.base.datetime.validation.exists.DateRangeOnlyExistsValidator;
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
@RosettaMeta(model=DateRange.class)
public class DateRangeMeta implements RosettaMetaData<DateRange> {

	@Override
	public List<Validator<? super DateRange>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.DateRangeDatesOrdered.class)
		);
	}
	
	@Override
	public List<Function<? super DateRange, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DateRange> validator() {
		return new DateRangeValidator();
	}

	@Override
	public Validator<? super DateRange> typeFormatValidator() {
		return new DateRangeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DateRange, Set<String>> onlyExistsValidator() {
		return new DateRangeOnlyExistsValidator();
	}
}
