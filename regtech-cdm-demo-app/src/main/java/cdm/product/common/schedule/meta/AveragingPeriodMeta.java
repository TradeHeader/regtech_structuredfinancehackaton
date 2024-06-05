package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.AveragingPeriod;
import cdm.product.common.schedule.validation.AveragingPeriodTypeFormatValidator;
import cdm.product.common.schedule.validation.AveragingPeriodValidator;
import cdm.product.common.schedule.validation.exists.AveragingPeriodOnlyExistsValidator;
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
@RosettaMeta(model=AveragingPeriod.class)
public class AveragingPeriodMeta implements RosettaMetaData<AveragingPeriod> {

	@Override
	public List<Validator<? super AveragingPeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.schedule.validation.datarule.AveragingPeriodAveragingPeriodChoice.class)
		);
	}
	
	@Override
	public List<Function<? super AveragingPeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AveragingPeriod> validator() {
		return new AveragingPeriodValidator();
	}

	@Override
	public Validator<? super AveragingPeriod> typeFormatValidator() {
		return new AveragingPeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AveragingPeriod, Set<String>> onlyExistsValidator() {
		return new AveragingPeriodOnlyExistsValidator();
	}
}
