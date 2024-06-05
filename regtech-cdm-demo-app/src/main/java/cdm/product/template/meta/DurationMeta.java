package cdm.product.template.meta;

import cdm.product.template.Duration;
import cdm.product.template.validation.DurationTypeFormatValidator;
import cdm.product.template.validation.DurationValidator;
import cdm.product.template.validation.exists.DurationOnlyExistsValidator;
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
@RosettaMeta(model=Duration.class)
public class DurationMeta implements RosettaMetaData<Duration> {

	@Override
	public List<Validator<? super Duration>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Duration, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Duration> validator() {
		return new DurationValidator();
	}

	@Override
	public Validator<? super Duration> typeFormatValidator() {
		return new DurationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Duration, Set<String>> onlyExistsValidator() {
		return new DurationOnlyExistsValidator();
	}
}
