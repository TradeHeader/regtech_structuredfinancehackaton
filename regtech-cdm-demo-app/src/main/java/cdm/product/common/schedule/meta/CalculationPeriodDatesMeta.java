package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.validation.CalculationPeriodDatesTypeFormatValidator;
import cdm.product.common.schedule.validation.CalculationPeriodDatesValidator;
import cdm.product.common.schedule.validation.exists.CalculationPeriodDatesOnlyExistsValidator;
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
@RosettaMeta(model=CalculationPeriodDates.class)
public class CalculationPeriodDatesMeta implements RosettaMetaData<CalculationPeriodDates> {

	@Override
	public List<Validator<? super CalculationPeriodDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodDatesFpMLIrd16.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodDatesFpMLIrd17.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodDatesFpMLIrd18.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodDatesFpMLIrd20.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodDatesFpMLIrd21.class),
			factory.create(cdm.product.common.schedule.validation.datarule.CalculationPeriodDatesFpMLIrd22.class)
		);
	}
	
	@Override
	public List<Function<? super CalculationPeriodDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationPeriodDates> validator() {
		return new CalculationPeriodDatesValidator();
	}

	@Override
	public Validator<? super CalculationPeriodDates> typeFormatValidator() {
		return new CalculationPeriodDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationPeriodDates, Set<String>> onlyExistsValidator() {
		return new CalculationPeriodDatesOnlyExistsValidator();
	}
}
