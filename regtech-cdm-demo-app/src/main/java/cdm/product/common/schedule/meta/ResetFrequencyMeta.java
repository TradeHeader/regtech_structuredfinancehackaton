package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.ResetFrequency;
import cdm.product.common.schedule.validation.ResetFrequencyTypeFormatValidator;
import cdm.product.common.schedule.validation.ResetFrequencyValidator;
import cdm.product.common.schedule.validation.exists.ResetFrequencyOnlyExistsValidator;
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
@RosettaMeta(model=ResetFrequency.class)
public class ResetFrequencyMeta implements RosettaMetaData<ResetFrequency> {

	@Override
	public List<Validator<? super ResetFrequency>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.ResetFrequencyFpMLIrd49.class),
			factory.create(cdm.base.datetime.validation.datarule.FrequencyTermPeriod.class),
			factory.create(cdm.base.datetime.validation.datarule.FrequencyPositivePeriodMultiplier.class)
		);
	}
	
	@Override
	public List<Function<? super ResetFrequency, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ResetFrequency> validator() {
		return new ResetFrequencyValidator();
	}

	@Override
	public Validator<? super ResetFrequency> typeFormatValidator() {
		return new ResetFrequencyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ResetFrequency, Set<String>> onlyExistsValidator() {
		return new ResetFrequencyOnlyExistsValidator();
	}
}
