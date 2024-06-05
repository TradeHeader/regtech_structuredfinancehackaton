package cdm.base.math.meta;

import cdm.base.math.MoneyRange;
import cdm.base.math.validation.MoneyRangeTypeFormatValidator;
import cdm.base.math.validation.MoneyRangeValidator;
import cdm.base.math.validation.exists.MoneyRangeOnlyExistsValidator;
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
@RosettaMeta(model=MoneyRange.class)
public class MoneyRangeMeta implements RosettaMetaData<MoneyRange> {

	@Override
	public List<Validator<? super MoneyRange>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.MoneyRangeAtLeastOneOf.class)
		);
	}
	
	@Override
	public List<Function<? super MoneyRange, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MoneyRange> validator() {
		return new MoneyRangeValidator();
	}

	@Override
	public Validator<? super MoneyRange> typeFormatValidator() {
		return new MoneyRangeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MoneyRange, Set<String>> onlyExistsValidator() {
		return new MoneyRangeOnlyExistsValidator();
	}
}
