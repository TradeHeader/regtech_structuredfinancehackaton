package cdm.product.template.meta;

import cdm.product.template.FxFeature;
import cdm.product.template.validation.FxFeatureTypeFormatValidator;
import cdm.product.template.validation.FxFeatureValidator;
import cdm.product.template.validation.exists.FxFeatureOnlyExistsValidator;
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
@RosettaMeta(model=FxFeature.class)
public class FxFeatureMeta implements RosettaMetaData<FxFeature> {

	@Override
	public List<Validator<? super FxFeature>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.FxFeatureFxFeatureChoice.class)
		);
	}
	
	@Override
	public List<Function<? super FxFeature, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxFeature> validator() {
		return new FxFeatureValidator();
	}

	@Override
	public Validator<? super FxFeature> typeFormatValidator() {
		return new FxFeatureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxFeature, Set<String>> onlyExistsValidator() {
		return new FxFeatureOnlyExistsValidator();
	}
}
