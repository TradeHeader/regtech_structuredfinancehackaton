package cdm.product.template.meta;

import cdm.product.template.AveragingStrikeFeature;
import cdm.product.template.validation.AveragingStrikeFeatureTypeFormatValidator;
import cdm.product.template.validation.AveragingStrikeFeatureValidator;
import cdm.product.template.validation.exists.AveragingStrikeFeatureOnlyExistsValidator;
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
@RosettaMeta(model=AveragingStrikeFeature.class)
public class AveragingStrikeFeatureMeta implements RosettaMetaData<AveragingStrikeFeature> {

	@Override
	public List<Validator<? super AveragingStrikeFeature>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AveragingStrikeFeature, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AveragingStrikeFeature> validator() {
		return new AveragingStrikeFeatureValidator();
	}

	@Override
	public Validator<? super AveragingStrikeFeature> typeFormatValidator() {
		return new AveragingStrikeFeatureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AveragingStrikeFeature, Set<String>> onlyExistsValidator() {
		return new AveragingStrikeFeatureOnlyExistsValidator();
	}
}
