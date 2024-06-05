package cdm.product.asset.meta;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.validation.FixedRateSpecificationTypeFormatValidator;
import cdm.product.asset.validation.FixedRateSpecificationValidator;
import cdm.product.asset.validation.exists.FixedRateSpecificationOnlyExistsValidator;
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
@RosettaMeta(model=FixedRateSpecification.class)
public class FixedRateSpecificationMeta implements RosettaMetaData<FixedRateSpecification> {

	@Override
	public List<Validator<? super FixedRateSpecification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FixedRateSpecification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FixedRateSpecification> validator() {
		return new FixedRateSpecificationValidator();
	}

	@Override
	public Validator<? super FixedRateSpecification> typeFormatValidator() {
		return new FixedRateSpecificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FixedRateSpecification, Set<String>> onlyExistsValidator() {
		return new FixedRateSpecificationOnlyExistsValidator();
	}
}
