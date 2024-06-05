package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.validation.QuantityMultiplierTypeFormatValidator;
import cdm.product.common.settlement.validation.QuantityMultiplierValidator;
import cdm.product.common.settlement.validation.exists.QuantityMultiplierOnlyExistsValidator;
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
@RosettaMeta(model=QuantityMultiplier.class)
public class QuantityMultiplierMeta implements RosettaMetaData<QuantityMultiplier> {

	@Override
	public List<Validator<? super QuantityMultiplier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.QuantityMultiplierOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super QuantityMultiplier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super QuantityMultiplier> validator() {
		return new QuantityMultiplierValidator();
	}

	@Override
	public Validator<? super QuantityMultiplier> typeFormatValidator() {
		return new QuantityMultiplierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super QuantityMultiplier, Set<String>> onlyExistsValidator() {
		return new QuantityMultiplierOnlyExistsValidator();
	}
}
