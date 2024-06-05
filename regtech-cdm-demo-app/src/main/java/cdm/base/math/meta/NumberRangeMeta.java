package cdm.base.math.meta;

import cdm.base.math.NumberRange;
import cdm.base.math.validation.NumberRangeTypeFormatValidator;
import cdm.base.math.validation.NumberRangeValidator;
import cdm.base.math.validation.exists.NumberRangeOnlyExistsValidator;
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
@RosettaMeta(model=NumberRange.class)
public class NumberRangeMeta implements RosettaMetaData<NumberRange> {

	@Override
	public List<Validator<? super NumberRange>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.NumberRangeAtLeastOneOf.class)
		);
	}
	
	@Override
	public List<Function<? super NumberRange, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super NumberRange> validator() {
		return new NumberRangeValidator();
	}

	@Override
	public Validator<? super NumberRange> typeFormatValidator() {
		return new NumberRangeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super NumberRange, Set<String>> onlyExistsValidator() {
		return new NumberRangeOnlyExistsValidator();
	}
}
