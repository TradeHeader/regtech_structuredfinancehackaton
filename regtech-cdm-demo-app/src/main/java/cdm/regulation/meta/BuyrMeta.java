package cdm.regulation.meta;

import cdm.regulation.Buyr;
import cdm.regulation.validation.BuyrTypeFormatValidator;
import cdm.regulation.validation.BuyrValidator;
import cdm.regulation.validation.exists.BuyrOnlyExistsValidator;
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
@RosettaMeta(model=Buyr.class)
public class BuyrMeta implements RosettaMetaData<Buyr> {

	@Override
	public List<Validator<? super Buyr>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Buyr, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Buyr> validator() {
		return new BuyrValidator();
	}

	@Override
	public Validator<? super Buyr> typeFormatValidator() {
		return new BuyrTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Buyr, Set<String>> onlyExistsValidator() {
		return new BuyrOnlyExistsValidator();
	}
}
