package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.StubCalculationPeriodAmount;
import cdm.product.common.schedule.validation.StubCalculationPeriodAmountTypeFormatValidator;
import cdm.product.common.schedule.validation.StubCalculationPeriodAmountValidator;
import cdm.product.common.schedule.validation.exists.StubCalculationPeriodAmountOnlyExistsValidator;
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
@RosettaMeta(model=StubCalculationPeriodAmount.class)
public class StubCalculationPeriodAmountMeta implements RosettaMetaData<StubCalculationPeriodAmount> {

	@Override
	public List<Validator<? super StubCalculationPeriodAmount>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super StubCalculationPeriodAmount, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super StubCalculationPeriodAmount> validator() {
		return new StubCalculationPeriodAmountValidator();
	}

	@Override
	public Validator<? super StubCalculationPeriodAmount> typeFormatValidator() {
		return new StubCalculationPeriodAmountTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super StubCalculationPeriodAmount, Set<String>> onlyExistsValidator() {
		return new StubCalculationPeriodAmountOnlyExistsValidator();
	}
}
