package cdm.regulation.meta;

import cdm.regulation.Swp;
import cdm.regulation.validation.SwpTypeFormatValidator;
import cdm.regulation.validation.SwpValidator;
import cdm.regulation.validation.exists.SwpOnlyExistsValidator;
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
@RosettaMeta(model=Swp.class)
public class SwpMeta implements RosettaMetaData<Swp> {

	@Override
	public List<Validator<? super Swp>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Swp, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Swp> validator() {
		return new SwpValidator();
	}

	@Override
	public Validator<? super Swp> typeFormatValidator() {
		return new SwpTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Swp, Set<String>> onlyExistsValidator() {
		return new SwpOnlyExistsValidator();
	}
}
