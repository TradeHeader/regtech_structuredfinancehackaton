package cdm.regulation.meta;

import cdm.regulation.SwpIn;
import cdm.regulation.validation.SwpInTypeFormatValidator;
import cdm.regulation.validation.SwpInValidator;
import cdm.regulation.validation.exists.SwpInOnlyExistsValidator;
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
@RosettaMeta(model=SwpIn.class)
public class SwpInMeta implements RosettaMetaData<SwpIn> {

	@Override
	public List<Validator<? super SwpIn>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SwpIn, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SwpIn> validator() {
		return new SwpInValidator();
	}

	@Override
	public Validator<? super SwpIn> typeFormatValidator() {
		return new SwpInTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SwpIn, Set<String>> onlyExistsValidator() {
		return new SwpInOnlyExistsValidator();
	}
}
