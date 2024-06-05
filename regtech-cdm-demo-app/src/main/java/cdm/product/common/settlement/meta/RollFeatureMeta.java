package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.RollFeature;
import cdm.product.common.settlement.validation.RollFeatureTypeFormatValidator;
import cdm.product.common.settlement.validation.RollFeatureValidator;
import cdm.product.common.settlement.validation.exists.RollFeatureOnlyExistsValidator;
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
@RosettaMeta(model=RollFeature.class)
public class RollFeatureMeta implements RosettaMetaData<RollFeature> {

	@Override
	public List<Validator<? super RollFeature>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super RollFeature, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RollFeature> validator() {
		return new RollFeatureValidator();
	}

	@Override
	public Validator<? super RollFeature> typeFormatValidator() {
		return new RollFeatureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RollFeature, Set<String>> onlyExistsValidator() {
		return new RollFeatureOnlyExistsValidator();
	}
}
