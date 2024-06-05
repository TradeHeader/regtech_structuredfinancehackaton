package cdm.product.collateral.meta;

import cdm.product.collateral.IssuerCriteria;
import cdm.product.collateral.validation.IssuerCriteriaTypeFormatValidator;
import cdm.product.collateral.validation.IssuerCriteriaValidator;
import cdm.product.collateral.validation.exists.IssuerCriteriaOnlyExistsValidator;
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
@RosettaMeta(model=IssuerCriteria.class)
public class IssuerCriteriaMeta implements RosettaMetaData<IssuerCriteria> {

	@Override
	public List<Validator<? super IssuerCriteria>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super IssuerCriteria, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super IssuerCriteria> validator() {
		return new IssuerCriteriaValidator();
	}

	@Override
	public Validator<? super IssuerCriteria> typeFormatValidator() {
		return new IssuerCriteriaTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super IssuerCriteria, Set<String>> onlyExistsValidator() {
		return new IssuerCriteriaOnlyExistsValidator();
	}
}
