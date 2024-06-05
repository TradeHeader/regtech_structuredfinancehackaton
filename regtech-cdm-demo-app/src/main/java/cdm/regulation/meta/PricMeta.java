package cdm.regulation.meta;

import cdm.regulation.Pric;
import cdm.regulation.validation.PricTypeFormatValidator;
import cdm.regulation.validation.PricValidator;
import cdm.regulation.validation.exists.PricOnlyExistsValidator;
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
@RosettaMeta(model=Pric.class)
public class PricMeta implements RosettaMetaData<Pric> {

	@Override
	public List<Validator<? super Pric>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Pric, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Pric> validator() {
		return new PricValidator();
	}

	@Override
	public Validator<? super Pric> typeFormatValidator() {
		return new PricTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Pric, Set<String>> onlyExistsValidator() {
		return new PricOnlyExistsValidator();
	}
}
