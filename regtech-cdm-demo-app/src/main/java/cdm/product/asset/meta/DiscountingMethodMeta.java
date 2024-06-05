package cdm.product.asset.meta;

import cdm.product.asset.DiscountingMethod;
import cdm.product.asset.validation.DiscountingMethodTypeFormatValidator;
import cdm.product.asset.validation.DiscountingMethodValidator;
import cdm.product.asset.validation.exists.DiscountingMethodOnlyExistsValidator;
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
@RosettaMeta(model=DiscountingMethod.class)
public class DiscountingMethodMeta implements RosettaMetaData<DiscountingMethod> {

	@Override
	public List<Validator<? super DiscountingMethod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.DiscountingMethodDiscountRate.class)
		);
	}
	
	@Override
	public List<Function<? super DiscountingMethod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DiscountingMethod> validator() {
		return new DiscountingMethodValidator();
	}

	@Override
	public Validator<? super DiscountingMethod> typeFormatValidator() {
		return new DiscountingMethodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DiscountingMethod, Set<String>> onlyExistsValidator() {
		return new DiscountingMethodOnlyExistsValidator();
	}
}
