package cdm.product.asset.meta;

import cdm.product.asset.FloatingRate;
import cdm.product.asset.validation.FloatingRateTypeFormatValidator;
import cdm.product.asset.validation.FloatingRateValidator;
import cdm.product.asset.validation.exists.FloatingRateOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRate.class)
public class FloatingRateMeta implements RosettaMetaData<FloatingRate> {

	@Override
	public List<Validator<? super FloatingRate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRate> validator() {
		return new FloatingRateValidator();
	}

	@Override
	public Validator<? super FloatingRate> typeFormatValidator() {
		return new FloatingRateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRate, Set<String>> onlyExistsValidator() {
		return new FloatingRateOnlyExistsValidator();
	}
}
