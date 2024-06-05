package cdm.regulation.meta;

import cdm.regulation.New;
import cdm.regulation.validation.NewTypeFormatValidator;
import cdm.regulation.validation.NewValidator;
import cdm.regulation.validation.exists.NewOnlyExistsValidator;
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
@RosettaMeta(model=New.class)
public class NewMeta implements RosettaMetaData<New> {

	@Override
	public List<Validator<? super New>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super New, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super New> validator() {
		return new NewValidator();
	}

	@Override
	public Validator<? super New> typeFormatValidator() {
		return new NewTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super New, Set<String>> onlyExistsValidator() {
		return new NewOnlyExistsValidator();
	}
}
