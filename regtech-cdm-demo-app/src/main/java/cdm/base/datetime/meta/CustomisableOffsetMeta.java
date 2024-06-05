package cdm.base.datetime.meta;

import cdm.base.datetime.CustomisableOffset;
import cdm.base.datetime.validation.CustomisableOffsetTypeFormatValidator;
import cdm.base.datetime.validation.CustomisableOffsetValidator;
import cdm.base.datetime.validation.exists.CustomisableOffsetOnlyExistsValidator;
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
@RosettaMeta(model=CustomisableOffset.class)
public class CustomisableOffsetMeta implements RosettaMetaData<CustomisableOffset> {

	@Override
	public List<Validator<? super CustomisableOffset>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CustomisableOffset, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CustomisableOffset> validator() {
		return new CustomisableOffsetValidator();
	}

	@Override
	public Validator<? super CustomisableOffset> typeFormatValidator() {
		return new CustomisableOffsetTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CustomisableOffset, Set<String>> onlyExistsValidator() {
		return new CustomisableOffsetOnlyExistsValidator();
	}
}
