package cdm.base.datetime.meta;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.validation.AdjustableOrRelativeDateTypeFormatValidator;
import cdm.base.datetime.validation.AdjustableOrRelativeDateValidator;
import cdm.base.datetime.validation.exists.AdjustableOrRelativeDateOnlyExistsValidator;
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
@RosettaMeta(model=AdjustableOrRelativeDate.class)
public class AdjustableOrRelativeDateMeta implements RosettaMetaData<AdjustableOrRelativeDate> {

	@Override
	public List<Validator<? super AdjustableOrRelativeDate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.AdjustableOrRelativeDateAdjustableOrRelativeDateChoice.class)
		);
	}
	
	@Override
	public List<Function<? super AdjustableOrRelativeDate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdjustableOrRelativeDate> validator() {
		return new AdjustableOrRelativeDateValidator();
	}

	@Override
	public Validator<? super AdjustableOrRelativeDate> typeFormatValidator() {
		return new AdjustableOrRelativeDateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdjustableOrRelativeDate, Set<String>> onlyExistsValidator() {
		return new AdjustableOrRelativeDateOnlyExistsValidator();
	}
}
