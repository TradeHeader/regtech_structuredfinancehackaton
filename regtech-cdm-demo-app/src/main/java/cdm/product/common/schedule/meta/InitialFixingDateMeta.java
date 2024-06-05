package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.InitialFixingDate;
import cdm.product.common.schedule.validation.InitialFixingDateTypeFormatValidator;
import cdm.product.common.schedule.validation.InitialFixingDateValidator;
import cdm.product.common.schedule.validation.exists.InitialFixingDateOnlyExistsValidator;
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
@RosettaMeta(model=InitialFixingDate.class)
public class InitialFixingDateMeta implements RosettaMetaData<InitialFixingDate> {

	@Override
	public List<Validator<? super InitialFixingDate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.InitialFixingDateOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super InitialFixingDate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InitialFixingDate> validator() {
		return new InitialFixingDateValidator();
	}

	@Override
	public Validator<? super InitialFixingDate> typeFormatValidator() {
		return new InitialFixingDateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InitialFixingDate, Set<String>> onlyExistsValidator() {
		return new InitialFixingDateOnlyExistsValidator();
	}
}
