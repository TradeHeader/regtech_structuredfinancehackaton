package cdm.base.math.meta;

import cdm.base.math.DatedValue;
import cdm.base.math.validation.DatedValueTypeFormatValidator;
import cdm.base.math.validation.DatedValueValidator;
import cdm.base.math.validation.exists.DatedValueOnlyExistsValidator;
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
@RosettaMeta(model=DatedValue.class)
public class DatedValueMeta implements RosettaMetaData<DatedValue> {

	@Override
	public List<Validator<? super DatedValue>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DatedValue, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DatedValue> validator() {
		return new DatedValueValidator();
	}

	@Override
	public Validator<? super DatedValue> typeFormatValidator() {
		return new DatedValueTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DatedValue, Set<String>> onlyExistsValidator() {
		return new DatedValueOnlyExistsValidator();
	}
}
