package cdm.base.math.meta;

import cdm.base.math.Rounding;
import cdm.base.math.validation.RoundingTypeFormatValidator;
import cdm.base.math.validation.RoundingValidator;
import cdm.base.math.validation.exists.RoundingOnlyExistsValidator;
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
@RosettaMeta(model=Rounding.class)
public class RoundingMeta implements RosettaMetaData<Rounding> {

	@Override
	public List<Validator<? super Rounding>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Rounding, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Rounding> validator() {
		return new RoundingValidator();
	}

	@Override
	public Validator<? super Rounding> typeFormatValidator() {
		return new RoundingTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Rounding, Set<String>> onlyExistsValidator() {
		return new RoundingOnlyExistsValidator();
	}
}
