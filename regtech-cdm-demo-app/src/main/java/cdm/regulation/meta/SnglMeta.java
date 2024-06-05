package cdm.regulation.meta;

import cdm.regulation.Sngl;
import cdm.regulation.validation.SnglTypeFormatValidator;
import cdm.regulation.validation.SnglValidator;
import cdm.regulation.validation.exists.SnglOnlyExistsValidator;
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
@RosettaMeta(model=Sngl.class)
public class SnglMeta implements RosettaMetaData<Sngl> {

	@Override
	public List<Validator<? super Sngl>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Sngl, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Sngl> validator() {
		return new SnglValidator();
	}

	@Override
	public Validator<? super Sngl> typeFormatValidator() {
		return new SnglTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Sngl, Set<String>> onlyExistsValidator() {
		return new SnglOnlyExistsValidator();
	}
}
