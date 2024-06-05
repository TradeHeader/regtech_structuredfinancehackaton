package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.validation.ResolvablePriceQuantityTypeFormatValidator;
import cdm.product.common.settlement.validation.ResolvablePriceQuantityValidator;
import cdm.product.common.settlement.validation.exists.ResolvablePriceQuantityOnlyExistsValidator;
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
@RosettaMeta(model=ResolvablePriceQuantity.class)
public class ResolvablePriceQuantityMeta implements RosettaMetaData<ResolvablePriceQuantity> {

	@Override
	public List<Validator<? super ResolvablePriceQuantity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.ResolvablePriceQuantityQuantityMultiplier.class)
		);
	}
	
	@Override
	public List<Function<? super ResolvablePriceQuantity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ResolvablePriceQuantity> validator() {
		return new ResolvablePriceQuantityValidator();
	}

	@Override
	public Validator<? super ResolvablePriceQuantity> typeFormatValidator() {
		return new ResolvablePriceQuantityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ResolvablePriceQuantity, Set<String>> onlyExistsValidator() {
		return new ResolvablePriceQuantityOnlyExistsValidator();
	}
}
