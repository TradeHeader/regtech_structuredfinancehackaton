package cdm.product.template.meta;

import cdm.product.template.PerformancePayout;
import cdm.product.template.validation.PerformancePayoutTypeFormatValidator;
import cdm.product.template.validation.PerformancePayoutValidator;
import cdm.product.template.validation.exists.PerformancePayoutOnlyExistsValidator;
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
@RosettaMeta(model=PerformancePayout.class)
public class PerformancePayoutMeta implements RosettaMetaData<PerformancePayout> {

	@Override
	public List<Validator<? super PerformancePayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutPorfolioOrStraightReturn.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutPortofolioReturnIsMultipleReturns.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutUnderlierOfPortfolioIsBasket.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutQuantity.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutNoSharePriceDividendAdjustmentIndex.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutNoSharePriceDividendAdjustmentForeignExchange.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutCorrelationUnderlierOnlyBasket.class),
			factory.create(cdm.product.template.validation.datarule.PerformancePayoutEquitySpecificAttributes.class)
		);
	}
	
	@Override
	public List<Function<? super PerformancePayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PerformancePayout> validator() {
		return new PerformancePayoutValidator();
	}

	@Override
	public Validator<? super PerformancePayout> typeFormatValidator() {
		return new PerformancePayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PerformancePayout, Set<String>> onlyExistsValidator() {
		return new PerformancePayoutOnlyExistsValidator();
	}
}
