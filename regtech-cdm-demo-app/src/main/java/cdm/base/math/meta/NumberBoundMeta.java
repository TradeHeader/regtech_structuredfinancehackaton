package cdm.base.math.meta;

import cdm.base.math.NumberBound;
import cdm.base.math.validation.NumberBoundTypeFormatValidator;
import cdm.base.math.validation.NumberBoundValidator;
import cdm.base.math.validation.exists.NumberBoundOnlyExistsValidator;
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
@RosettaMeta(model=NumberBound.class)
public class NumberBoundMeta implements RosettaMetaData<NumberBound> {

	@Override
	public List<Validator<? super NumberBound>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super NumberBound, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super NumberBound> validator() {
		return new NumberBoundValidator();
	}

	@Override
	public Validator<? super NumberBound> typeFormatValidator() {
		return new NumberBoundTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super NumberBound, Set<String>> onlyExistsValidator() {
		return new NumberBoundOnlyExistsValidator();
	}
}
