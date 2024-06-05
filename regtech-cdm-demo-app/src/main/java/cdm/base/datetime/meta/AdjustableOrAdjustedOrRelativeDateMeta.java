package cdm.base.datetime.meta;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.validation.AdjustableOrAdjustedOrRelativeDateTypeFormatValidator;
import cdm.base.datetime.validation.AdjustableOrAdjustedOrRelativeDateValidator;
import cdm.base.datetime.validation.exists.AdjustableOrAdjustedOrRelativeDateOnlyExistsValidator;
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
@RosettaMeta(model=AdjustableOrAdjustedOrRelativeDate.class)
public class AdjustableOrAdjustedOrRelativeDateMeta implements RosettaMetaData<AdjustableOrAdjustedOrRelativeDate> {

	@Override
	public List<Validator<? super AdjustableOrAdjustedOrRelativeDate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.AdjustableOrAdjustedOrRelativeDateAdjustedDate.class)
		);
	}
	
	@Override
	public List<Function<? super AdjustableOrAdjustedOrRelativeDate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdjustableOrAdjustedOrRelativeDate> validator() {
		return new AdjustableOrAdjustedOrRelativeDateValidator();
	}

	@Override
	public Validator<? super AdjustableOrAdjustedOrRelativeDate> typeFormatValidator() {
		return new AdjustableOrAdjustedOrRelativeDateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdjustableOrAdjustedOrRelativeDate, Set<String>> onlyExistsValidator() {
		return new AdjustableOrAdjustedOrRelativeDateOnlyExistsValidator();
	}
}
