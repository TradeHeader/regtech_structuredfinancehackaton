package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.validation.PriceQuantityTypeFormatValidator;
import cdm.product.common.settlement.validation.PriceQuantityValidator;
import cdm.product.common.settlement.validation.exists.PriceQuantityOnlyExistsValidator;
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
@RosettaMeta(model=PriceQuantity.class)
public class PriceQuantityMeta implements RosettaMetaData<PriceQuantity> {

	@Override
	public List<Validator<? super PriceQuantity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.PriceQuantityRateOptionObservable.class),
			factory.create(cdm.product.common.settlement.validation.datarule.PriceQuantityObservableExists.class),
			factory.create(cdm.product.common.settlement.validation.datarule.PriceQuantityActualSettlement.class)
		);
	}
	
	@Override
	public List<Function<? super PriceQuantity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PriceQuantity> validator() {
		return new PriceQuantityValidator();
	}

	@Override
	public Validator<? super PriceQuantity> typeFormatValidator() {
		return new PriceQuantityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PriceQuantity, Set<String>> onlyExistsValidator() {
		return new PriceQuantityOnlyExistsValidator();
	}
}
