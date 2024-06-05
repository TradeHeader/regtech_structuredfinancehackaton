package cdm.product.template.meta;

import cdm.product.template.CalculationAgentModel;
import cdm.product.template.validation.CalculationAgentModelTypeFormatValidator;
import cdm.product.template.validation.CalculationAgentModelValidator;
import cdm.product.template.validation.exists.CalculationAgentModelOnlyExistsValidator;
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
@RosettaMeta(model=CalculationAgentModel.class)
public class CalculationAgentModelMeta implements RosettaMetaData<CalculationAgentModel> {

	@Override
	public List<Validator<? super CalculationAgentModel>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculationAgentModel, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationAgentModel> validator() {
		return new CalculationAgentModelValidator();
	}

	@Override
	public Validator<? super CalculationAgentModel> typeFormatValidator() {
		return new CalculationAgentModelTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationAgentModel, Set<String>> onlyExistsValidator() {
		return new CalculationAgentModelOnlyExistsValidator();
	}
}
