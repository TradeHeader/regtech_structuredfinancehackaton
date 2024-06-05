package cdm.product.template.meta;

import cdm.product.template.InitialMargin;
import cdm.product.template.validation.InitialMarginTypeFormatValidator;
import cdm.product.template.validation.InitialMarginValidator;
import cdm.product.template.validation.exists.InitialMarginOnlyExistsValidator;
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
@RosettaMeta(model=InitialMargin.class)
public class InitialMarginMeta implements RosettaMetaData<InitialMargin> {

	@Override
	public List<Validator<? super InitialMargin>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.InitialMarginMarginThreshold.class),
			factory.create(cdm.product.template.validation.datarule.InitialMarginMinimumTransferAmount.class)
		);
	}
	
	@Override
	public List<Function<? super InitialMargin, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InitialMargin> validator() {
		return new InitialMarginValidator();
	}

	@Override
	public Validator<? super InitialMargin> typeFormatValidator() {
		return new InitialMarginTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InitialMargin, Set<String>> onlyExistsValidator() {
		return new InitialMarginOnlyExistsValidator();
	}
}
