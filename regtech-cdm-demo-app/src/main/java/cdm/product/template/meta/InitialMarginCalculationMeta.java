package cdm.product.template.meta;

import cdm.product.template.InitialMarginCalculation;
import cdm.product.template.validation.InitialMarginCalculationTypeFormatValidator;
import cdm.product.template.validation.InitialMarginCalculationValidator;
import cdm.product.template.validation.exists.InitialMarginCalculationOnlyExistsValidator;
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
@RosettaMeta(model=InitialMarginCalculation.class)
public class InitialMarginCalculationMeta implements RosettaMetaData<InitialMarginCalculation> {

	@Override
	public List<Validator<? super InitialMarginCalculation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.InitialMarginCalculationInitialMarginCalculationChoice.class)
		);
	}
	
	@Override
	public List<Function<? super InitialMarginCalculation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InitialMarginCalculation> validator() {
		return new InitialMarginCalculationValidator();
	}

	@Override
	public Validator<? super InitialMarginCalculation> typeFormatValidator() {
		return new InitialMarginCalculationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InitialMarginCalculation, Set<String>> onlyExistsValidator() {
		return new InitialMarginCalculationOnlyExistsValidator();
	}
}
