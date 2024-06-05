package cdm.product.template.meta;

import cdm.product.template.AveragingCalculation;
import cdm.product.template.validation.AveragingCalculationTypeFormatValidator;
import cdm.product.template.validation.AveragingCalculationValidator;
import cdm.product.template.validation.exists.AveragingCalculationOnlyExistsValidator;
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
@RosettaMeta(model=AveragingCalculation.class)
public class AveragingCalculationMeta implements RosettaMetaData<AveragingCalculation> {

	@Override
	public List<Validator<? super AveragingCalculation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AveragingCalculation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AveragingCalculation> validator() {
		return new AveragingCalculationValidator();
	}

	@Override
	public Validator<? super AveragingCalculation> typeFormatValidator() {
		return new AveragingCalculationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AveragingCalculation, Set<String>> onlyExistsValidator() {
		return new AveragingCalculationOnlyExistsValidator();
	}
}
