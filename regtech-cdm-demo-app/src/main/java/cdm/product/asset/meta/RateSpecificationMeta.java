package cdm.product.asset.meta;

import cdm.product.asset.RateSpecification;
import cdm.product.asset.validation.RateSpecificationTypeFormatValidator;
import cdm.product.asset.validation.RateSpecificationValidator;
import cdm.product.asset.validation.exists.RateSpecificationOnlyExistsValidator;
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
@RosettaMeta(model=RateSpecification.class)
public class RateSpecificationMeta implements RosettaMetaData<RateSpecification> {

	@Override
	public List<Validator<? super RateSpecification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.RateSpecificationOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super RateSpecification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RateSpecification> validator() {
		return new RateSpecificationValidator();
	}

	@Override
	public Validator<? super RateSpecification> typeFormatValidator() {
		return new RateSpecificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RateSpecification, Set<String>> onlyExistsValidator() {
		return new RateSpecificationOnlyExistsValidator();
	}
}
