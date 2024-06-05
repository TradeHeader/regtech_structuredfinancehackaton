package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.StubPeriod;
import cdm.product.common.schedule.validation.StubPeriodTypeFormatValidator;
import cdm.product.common.schedule.validation.StubPeriodValidator;
import cdm.product.common.schedule.validation.exists.StubPeriodOnlyExistsValidator;
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
@RosettaMeta(model=StubPeriod.class)
public class StubPeriodMeta implements RosettaMetaData<StubPeriod> {

	@Override
	public List<Validator<? super StubPeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super StubPeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super StubPeriod> validator() {
		return new StubPeriodValidator();
	}

	@Override
	public Validator<? super StubPeriod> typeFormatValidator() {
		return new StubPeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super StubPeriod, Set<String>> onlyExistsValidator() {
		return new StubPeriodOnlyExistsValidator();
	}
}
