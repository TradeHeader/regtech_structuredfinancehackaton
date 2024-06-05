package cdm.regulation.meta;

import cdm.regulation.AcctOwnr;
import cdm.regulation.validation.AcctOwnrTypeFormatValidator;
import cdm.regulation.validation.AcctOwnrValidator;
import cdm.regulation.validation.exists.AcctOwnrOnlyExistsValidator;
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
@RosettaMeta(model=AcctOwnr.class)
public class AcctOwnrMeta implements RosettaMetaData<AcctOwnr> {

	@Override
	public List<Validator<? super AcctOwnr>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AcctOwnr, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AcctOwnr> validator() {
		return new AcctOwnrValidator();
	}

	@Override
	public Validator<? super AcctOwnr> typeFormatValidator() {
		return new AcctOwnrTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AcctOwnr, Set<String>> onlyExistsValidator() {
		return new AcctOwnrOnlyExistsValidator();
	}
}
