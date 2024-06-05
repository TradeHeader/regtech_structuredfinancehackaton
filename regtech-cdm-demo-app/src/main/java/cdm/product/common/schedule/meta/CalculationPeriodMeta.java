package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.validation.CalculationPeriodTypeFormatValidator;
import cdm.product.common.schedule.validation.CalculationPeriodValidator;
import cdm.product.common.schedule.validation.exists.CalculationPeriodOnlyExistsValidator;
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
@RosettaMeta(model=CalculationPeriod.class)
public class CalculationPeriodMeta implements RosettaMetaData<CalculationPeriod> {

	@Override
	public List<Validator<? super CalculationPeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodNotionalChoice.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodRateChoice.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodStartDateChoice.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodEndDateChoice.class)
		);
	}
	
	@Override
	public List<Function<? super CalculationPeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationPeriod> validator() {
		return new CalculationPeriodValidator();
	}

	@Override
	public Validator<? super CalculationPeriod> typeFormatValidator() {
		return new CalculationPeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationPeriod, Set<String>> onlyExistsValidator() {
		return new CalculationPeriodOnlyExistsValidator();
	}
}
