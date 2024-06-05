package cdm.base.datetime.meta;

import cdm.base.datetime.PeriodBound;
import cdm.base.datetime.validation.PeriodBoundTypeFormatValidator;
import cdm.base.datetime.validation.PeriodBoundValidator;
import cdm.base.datetime.validation.exists.PeriodBoundOnlyExistsValidator;
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
@RosettaMeta(model=PeriodBound.class)
public class PeriodBoundMeta implements RosettaMetaData<PeriodBound> {

	@Override
	public List<Validator<? super PeriodBound>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PeriodBound, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PeriodBound> validator() {
		return new PeriodBoundValidator();
	}

	@Override
	public Validator<? super PeriodBound> typeFormatValidator() {
		return new PeriodBoundTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PeriodBound, Set<String>> onlyExistsValidator() {
		return new PeriodBoundOnlyExistsValidator();
	}
}
