package cdm.product.asset.meta;

import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import cdm.product.asset.validation.CalculationScheduleDeliveryPeriodsTypeFormatValidator;
import cdm.product.asset.validation.CalculationScheduleDeliveryPeriodsValidator;
import cdm.product.asset.validation.exists.CalculationScheduleDeliveryPeriodsOnlyExistsValidator;
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
@RosettaMeta(model=CalculationScheduleDeliveryPeriods.class)
public class CalculationScheduleDeliveryPeriodsMeta implements RosettaMetaData<CalculationScheduleDeliveryPeriods> {

	@Override
	public List<Validator<? super CalculationScheduleDeliveryPeriods>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculationScheduleDeliveryPeriods, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationScheduleDeliveryPeriods> validator() {
		return new CalculationScheduleDeliveryPeriodsValidator();
	}

	@Override
	public Validator<? super CalculationScheduleDeliveryPeriods> typeFormatValidator() {
		return new CalculationScheduleDeliveryPeriodsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationScheduleDeliveryPeriods, Set<String>> onlyExistsValidator() {
		return new CalculationScheduleDeliveryPeriodsOnlyExistsValidator();
	}
}
