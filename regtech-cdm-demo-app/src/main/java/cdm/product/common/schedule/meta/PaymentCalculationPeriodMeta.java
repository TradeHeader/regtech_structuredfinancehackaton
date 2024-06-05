package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.PaymentCalculationPeriod;
import cdm.product.common.schedule.validation.PaymentCalculationPeriodTypeFormatValidator;
import cdm.product.common.schedule.validation.PaymentCalculationPeriodValidator;
import cdm.product.common.schedule.validation.exists.PaymentCalculationPeriodOnlyExistsValidator;
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
@RosettaMeta(model=PaymentCalculationPeriod.class)
public class PaymentCalculationPeriodMeta implements RosettaMetaData<PaymentCalculationPeriod> {

	@Override
	public List<Validator<? super PaymentCalculationPeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.PaymentCalculationPeriodCalculationPeriodNumberOfDays.class),
			factory.create(cdm.product.common.schedule.validation.datarule.PaymentCalculationPeriodPaymentCalculationPeriodChoice.class),
			factory.create(cdm.product.common.schedule.validation.datarule.PaymentCalculationPeriodFpMLIrd34.class)
		);
	}
	
	@Override
	public List<Function<? super PaymentCalculationPeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PaymentCalculationPeriod> validator() {
		return new PaymentCalculationPeriodValidator();
	}

	@Override
	public Validator<? super PaymentCalculationPeriod> typeFormatValidator() {
		return new PaymentCalculationPeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PaymentCalculationPeriod, Set<String>> onlyExistsValidator() {
		return new PaymentCalculationPeriodOnlyExistsValidator();
	}
}
