package cdm.product.asset.meta;

import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.validation.FloatingRateSpecificationTypeFormatValidator;
import cdm.product.asset.validation.FloatingRateSpecificationValidator;
import cdm.product.asset.validation.exists.FloatingRateSpecificationOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateSpecification.class)
public class FloatingRateSpecificationMeta implements RosettaMetaData<FloatingRateSpecification> {

	@Override
	public List<Validator<? super FloatingRateSpecification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateSpecification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateSpecification> validator() {
		return new FloatingRateSpecificationValidator();
	}

	@Override
	public Validator<? super FloatingRateSpecification> typeFormatValidator() {
		return new FloatingRateSpecificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateSpecification, Set<String>> onlyExistsValidator() {
		return new FloatingRateSpecificationOnlyExistsValidator();
	}
}
