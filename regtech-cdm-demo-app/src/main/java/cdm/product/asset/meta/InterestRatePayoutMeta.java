package cdm.product.asset.meta;

import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.validation.InterestRatePayoutTypeFormatValidator;
import cdm.product.asset.validation.InterestRatePayoutValidator;
import cdm.product.asset.validation.exists.InterestRatePayoutOnlyExistsValidator;
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
@RosettaMeta(model=InterestRatePayout.class)
public class InterestRatePayoutMeta implements RosettaMetaData<InterestRatePayout> {

	@Override
	public List<Validator<? super InterestRatePayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutQuantity.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutInterestRatePayoutChoice.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFutureValueNotional.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutTerminationDate.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutRateSpecification.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd6.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd23.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd24.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutInitialStubFinalStub.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutCashSettlementTerms.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd71.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd72.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd9.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutFpMLIrd29.class),
			factory.create(cdm.product.asset.validation.datarule.InterestRatePayoutCalculationPeriodDatesFirstCompoundingPeriodEndDate.class)
		);
	}
	
	@Override
	public List<Function<? super InterestRatePayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InterestRatePayout> validator() {
		return new InterestRatePayoutValidator();
	}

	@Override
	public Validator<? super InterestRatePayout> typeFormatValidator() {
		return new InterestRatePayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InterestRatePayout, Set<String>> onlyExistsValidator() {
		return new InterestRatePayoutOnlyExistsValidator();
	}
}
