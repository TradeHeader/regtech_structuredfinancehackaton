package cdm.product.collateral.meta;

import cdm.product.collateral.ConcentrationLimitCriteria;
import cdm.product.collateral.validation.ConcentrationLimitCriteriaTypeFormatValidator;
import cdm.product.collateral.validation.ConcentrationLimitCriteriaValidator;
import cdm.product.collateral.validation.exists.ConcentrationLimitCriteriaOnlyExistsValidator;
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
@RosettaMeta(model=ConcentrationLimitCriteria.class)
public class ConcentrationLimitCriteriaMeta implements RosettaMetaData<ConcentrationLimitCriteria> {

	@Override
	public List<Validator<? super ConcentrationLimitCriteria>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.ConcentrationLimitCriteriaConcentrationLimitTypeChoice.class)
		);
	}
	
	@Override
	public List<Function<? super ConcentrationLimitCriteria, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ConcentrationLimitCriteria> validator() {
		return new ConcentrationLimitCriteriaValidator();
	}

	@Override
	public Validator<? super ConcentrationLimitCriteria> typeFormatValidator() {
		return new ConcentrationLimitCriteriaTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ConcentrationLimitCriteria, Set<String>> onlyExistsValidator() {
		return new ConcentrationLimitCriteriaOnlyExistsValidator();
	}
}
