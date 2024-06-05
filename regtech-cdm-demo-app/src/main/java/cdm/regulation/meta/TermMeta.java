package cdm.regulation.meta;

import cdm.regulation.Term;
import cdm.regulation.validation.TermTypeFormatValidator;
import cdm.regulation.validation.TermValidator;
import cdm.regulation.validation.exists.TermOnlyExistsValidator;
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
@RosettaMeta(model=Term.class)
public class TermMeta implements RosettaMetaData<Term> {

	@Override
	public List<Validator<? super Term>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Term, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Term> validator() {
		return new TermValidator();
	}

	@Override
	public Validator<? super Term> typeFormatValidator() {
		return new TermTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Term, Set<String>> onlyExistsValidator() {
		return new TermOnlyExistsValidator();
	}
}
