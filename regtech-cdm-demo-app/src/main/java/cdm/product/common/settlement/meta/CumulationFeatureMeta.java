package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.CumulationFeature;
import cdm.product.common.settlement.validation.CumulationFeatureTypeFormatValidator;
import cdm.product.common.settlement.validation.CumulationFeatureValidator;
import cdm.product.common.settlement.validation.exists.CumulationFeatureOnlyExistsValidator;
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
@RosettaMeta(model=CumulationFeature.class)
public class CumulationFeatureMeta implements RosettaMetaData<CumulationFeature> {

	@Override
	public List<Validator<? super CumulationFeature>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CumulationFeature, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CumulationFeature> validator() {
		return new CumulationFeatureValidator();
	}

	@Override
	public Validator<? super CumulationFeature> typeFormatValidator() {
		return new CumulationFeatureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CumulationFeature, Set<String>> onlyExistsValidator() {
		return new CumulationFeatureOnlyExistsValidator();
	}
}
