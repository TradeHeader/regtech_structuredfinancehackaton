package cdm.base.datetime.meta;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.validation.AdjustableDatesTypeFormatValidator;
import cdm.base.datetime.validation.AdjustableDatesValidator;
import cdm.base.datetime.validation.exists.AdjustableDatesOnlyExistsValidator;
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
@RosettaMeta(model=AdjustableDates.class)
public class AdjustableDatesMeta implements RosettaMetaData<AdjustableDates> {

	@Override
	public List<Validator<? super AdjustableDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.AdjustableDatesAdjustedDate.class)
		);
	}
	
	@Override
	public List<Function<? super AdjustableDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdjustableDates> validator() {
		return new AdjustableDatesValidator();
	}

	@Override
	public Validator<? super AdjustableDates> typeFormatValidator() {
		return new AdjustableDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdjustableDates, Set<String>> onlyExistsValidator() {
		return new AdjustableDatesOnlyExistsValidator();
	}
}
