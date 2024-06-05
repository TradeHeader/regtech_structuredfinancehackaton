package cdm.base.math.meta;

import cdm.base.math.MoneyBound;
import cdm.base.math.validation.MoneyBoundTypeFormatValidator;
import cdm.base.math.validation.MoneyBoundValidator;
import cdm.base.math.validation.exists.MoneyBoundOnlyExistsValidator;
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
@RosettaMeta(model=MoneyBound.class)
public class MoneyBoundMeta implements RosettaMetaData<MoneyBound> {

	@Override
	public List<Validator<? super MoneyBound>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MoneyBound, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MoneyBound> validator() {
		return new MoneyBoundValidator();
	}

	@Override
	public Validator<? super MoneyBound> typeFormatValidator() {
		return new MoneyBoundTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MoneyBound, Set<String>> onlyExistsValidator() {
		return new MoneyBoundOnlyExistsValidator();
	}
}
