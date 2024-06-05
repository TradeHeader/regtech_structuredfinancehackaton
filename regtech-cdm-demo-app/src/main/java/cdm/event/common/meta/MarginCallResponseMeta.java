package cdm.event.common.meta;

import cdm.event.common.MarginCallResponse;
import cdm.event.common.validation.MarginCallResponseTypeFormatValidator;
import cdm.event.common.validation.MarginCallResponseValidator;
import cdm.event.common.validation.exists.MarginCallResponseOnlyExistsValidator;
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
@RosettaMeta(model=MarginCallResponse.class)
public class MarginCallResponseMeta implements RosettaMetaData<MarginCallResponse> {

	@Override
	public List<Validator<? super MarginCallResponse>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.MarginCallBaseRegIMRoleIMOnly.class)
		);
	}
	
	@Override
	public List<Function<? super MarginCallResponse, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MarginCallResponse> validator() {
		return new MarginCallResponseValidator();
	}

	@Override
	public Validator<? super MarginCallResponse> typeFormatValidator() {
		return new MarginCallResponseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MarginCallResponse, Set<String>> onlyExistsValidator() {
		return new MarginCallResponseOnlyExistsValidator();
	}
}
