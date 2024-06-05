package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.validation.CalculationPeriodDataTypeFormatValidator;
import cdm.product.common.schedule.validation.CalculationPeriodDataValidator;
import cdm.product.common.schedule.validation.exists.CalculationPeriodDataOnlyExistsValidator;
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
@RosettaMeta(model=CalculationPeriodData.class)
public class CalculationPeriodDataMeta implements RosettaMetaData<CalculationPeriodData> {

	@Override
	public List<Validator<? super CalculationPeriodData>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculationPeriodData, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculationPeriodData> validator() {
		return new CalculationPeriodDataValidator();
	}

	@Override
	public Validator<? super CalculationPeriodData> typeFormatValidator() {
		return new CalculationPeriodDataTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculationPeriodData, Set<String>> onlyExistsValidator() {
		return new CalculationPeriodDataOnlyExistsValidator();
	}
}
