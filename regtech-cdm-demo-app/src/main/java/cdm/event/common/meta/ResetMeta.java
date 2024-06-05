package cdm.event.common.meta;

import cdm.event.common.Reset;
import cdm.event.common.validation.ResetTypeFormatValidator;
import cdm.event.common.validation.ResetValidator;
import cdm.event.common.validation.exists.ResetOnlyExistsValidator;
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
@RosettaMeta(model=Reset.class)
public class ResetMeta implements RosettaMetaData<Reset> {

	@Override
	public List<Validator<? super Reset>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.ResetAveragingMethodologyExists.class)
		);
	}
	
	@Override
	public List<Function<? super Reset, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Reset> validator() {
		return new ResetValidator();
	}

	@Override
	public Validator<? super Reset> typeFormatValidator() {
		return new ResetTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Reset, Set<String>> onlyExistsValidator() {
		return new ResetOnlyExistsValidator();
	}
}
