package cdm.product.collateral.meta;

import cdm.product.collateral.InterestAmountApplication;
import cdm.product.collateral.validation.InterestAmountApplicationTypeFormatValidator;
import cdm.product.collateral.validation.InterestAmountApplicationValidator;
import cdm.product.collateral.validation.exists.InterestAmountApplicationOnlyExistsValidator;
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
@RosettaMeta(model=InterestAmountApplication.class)
public class InterestAmountApplicationMeta implements RosettaMetaData<InterestAmountApplication> {

	@Override
	public List<Validator<? super InterestAmountApplication>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super InterestAmountApplication, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InterestAmountApplication> validator() {
		return new InterestAmountApplicationValidator();
	}

	@Override
	public Validator<? super InterestAmountApplication> typeFormatValidator() {
		return new InterestAmountApplicationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InterestAmountApplication, Set<String>> onlyExistsValidator() {
		return new InterestAmountApplicationOnlyExistsValidator();
	}
}
