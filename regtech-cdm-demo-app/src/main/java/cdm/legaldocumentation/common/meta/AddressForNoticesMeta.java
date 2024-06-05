package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.AddressForNotices;
import cdm.legaldocumentation.common.validation.AddressForNoticesTypeFormatValidator;
import cdm.legaldocumentation.common.validation.AddressForNoticesValidator;
import cdm.legaldocumentation.common.validation.exists.AddressForNoticesOnlyExistsValidator;
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
@RosettaMeta(model=AddressForNotices.class)
public class AddressForNoticesMeta implements RosettaMetaData<AddressForNotices> {

	@Override
	public List<Validator<? super AddressForNotices>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AddressForNotices, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AddressForNotices> validator() {
		return new AddressForNoticesValidator();
	}

	@Override
	public Validator<? super AddressForNotices> typeFormatValidator() {
		return new AddressForNoticesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AddressForNotices, Set<String>> onlyExistsValidator() {
		return new AddressForNoticesOnlyExistsValidator();
	}
}
