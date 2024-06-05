package cdm.base.datetime.meta;

import cdm.base.datetime.RelativeDates;
import cdm.base.datetime.validation.RelativeDatesTypeFormatValidator;
import cdm.base.datetime.validation.RelativeDatesValidator;
import cdm.base.datetime.validation.exists.RelativeDatesOnlyExistsValidator;
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
@RosettaMeta(model=RelativeDates.class)
public class RelativeDatesMeta implements RosettaMetaData<RelativeDates> {

	@Override
	public List<Validator<? super RelativeDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.RelativeDatesPeriodSkipGreaterThanOne.class),
			factory.create(cdm.base.datetime.validation.datarule.OffsetDayType.class),
			factory.create(cdm.base.datetime.validation.datarule.PeriodDayPeriod.class)
		);
	}
	
	@Override
	public List<Function<? super RelativeDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RelativeDates> validator() {
		return new RelativeDatesValidator();
	}

	@Override
	public Validator<? super RelativeDates> typeFormatValidator() {
		return new RelativeDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RelativeDates, Set<String>> onlyExistsValidator() {
		return new RelativeDatesOnlyExistsValidator();
	}
}
