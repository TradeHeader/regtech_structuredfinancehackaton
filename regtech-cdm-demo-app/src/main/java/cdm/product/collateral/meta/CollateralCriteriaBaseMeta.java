package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralCriteriaBase;
import cdm.product.collateral.validation.CollateralCriteriaBaseTypeFormatValidator;
import cdm.product.collateral.validation.CollateralCriteriaBaseValidator;
import cdm.product.collateral.validation.exists.CollateralCriteriaBaseOnlyExistsValidator;
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
@RosettaMeta(model=CollateralCriteriaBase.class)
public class CollateralCriteriaBaseMeta implements RosettaMetaData<CollateralCriteriaBase> {

	@Override
	public List<Validator<? super CollateralCriteriaBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralCriteriaBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralCriteriaBase> validator() {
		return new CollateralCriteriaBaseValidator();
	}

	@Override
	public Validator<? super CollateralCriteriaBase> typeFormatValidator() {
		return new CollateralCriteriaBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralCriteriaBase, Set<String>> onlyExistsValidator() {
		return new CollateralCriteriaBaseOnlyExistsValidator();
	}
}
