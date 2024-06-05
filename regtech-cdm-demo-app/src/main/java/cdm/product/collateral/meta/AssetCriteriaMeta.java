package cdm.product.collateral.meta;

import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.validation.AssetCriteriaTypeFormatValidator;
import cdm.product.collateral.validation.AssetCriteriaValidator;
import cdm.product.collateral.validation.exists.AssetCriteriaOnlyExistsValidator;
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
@RosettaMeta(model=AssetCriteria.class)
public class AssetCriteriaMeta implements RosettaMetaData<AssetCriteria> {

	@Override
	public List<Validator<? super AssetCriteria>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.AssetCriteriaAssetCriteriaChoice.class)
		);
	}
	
	@Override
	public List<Function<? super AssetCriteria, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetCriteria> validator() {
		return new AssetCriteriaValidator();
	}

	@Override
	public Validator<? super AssetCriteria> typeFormatValidator() {
		return new AssetCriteriaTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetCriteria, Set<String>> onlyExistsValidator() {
		return new AssetCriteriaOnlyExistsValidator();
	}
}
