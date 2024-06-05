package cdm.product.template.meta;

import cdm.product.template.SchedulePeriod;
import cdm.product.template.validation.SchedulePeriodTypeFormatValidator;
import cdm.product.template.validation.SchedulePeriodValidator;
import cdm.product.template.validation.exists.SchedulePeriodOnlyExistsValidator;
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
@RosettaMeta(model=SchedulePeriod.class)
public class SchedulePeriodMeta implements RosettaMetaData<SchedulePeriod> {

	@Override
	public List<Validator<? super SchedulePeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SchedulePeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SchedulePeriod> validator() {
		return new SchedulePeriodValidator();
	}

	@Override
	public Validator<? super SchedulePeriod> typeFormatValidator() {
		return new SchedulePeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SchedulePeriod, Set<String>> onlyExistsValidator() {
		return new SchedulePeriodOnlyExistsValidator();
	}
}
