package cdm.product.template.meta;

import cdm.product.template.OptionFeature;
import cdm.product.template.validation.OptionFeatureTypeFormatValidator;
import cdm.product.template.validation.OptionFeatureValidator;
import cdm.product.template.validation.exists.OptionFeatureOnlyExistsValidator;
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
@RosettaMeta(model=OptionFeature.class)
public class OptionFeatureMeta implements RosettaMetaData<OptionFeature> {

	@Override
	public List<Validator<? super OptionFeature>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super OptionFeature, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super OptionFeature> validator() {
		return new OptionFeatureValidator();
	}

	@Override
	public Validator<? super OptionFeature> typeFormatValidator() {
		return new OptionFeatureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super OptionFeature, Set<String>> onlyExistsValidator() {
		return new OptionFeatureOnlyExistsValidator();
	}
}
