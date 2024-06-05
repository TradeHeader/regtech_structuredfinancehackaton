package cdm.product.collateral.meta;

import cdm.product.collateral.ReturnAmount;
import cdm.product.collateral.validation.ReturnAmountTypeFormatValidator;
import cdm.product.collateral.validation.ReturnAmountValidator;
import cdm.product.collateral.validation.exists.ReturnAmountOnlyExistsValidator;
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
@RosettaMeta(model=ReturnAmount.class)
public class ReturnAmountMeta implements RosettaMetaData<ReturnAmount> {

	@Override
	public List<Validator<? super ReturnAmount>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.ReturnAmountCustomElection.class)
		);
	}
	
	@Override
	public List<Function<? super ReturnAmount, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ReturnAmount> validator() {
		return new ReturnAmountValidator();
	}

	@Override
	public Validator<? super ReturnAmount> typeFormatValidator() {
		return new ReturnAmountTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ReturnAmount, Set<String>> onlyExistsValidator() {
		return new ReturnAmountOnlyExistsValidator();
	}
}
