package cdm.regulation.meta;

import cdm.regulation.SchmeNm;
import cdm.regulation.validation.SchmeNmTypeFormatValidator;
import cdm.regulation.validation.SchmeNmValidator;
import cdm.regulation.validation.exists.SchmeNmOnlyExistsValidator;
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
@RosettaMeta(model=SchmeNm.class)
public class SchmeNmMeta implements RosettaMetaData<SchmeNm> {

	@Override
	public List<Validator<? super SchmeNm>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SchmeNm, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SchmeNm> validator() {
		return new SchmeNmValidator();
	}

	@Override
	public Validator<? super SchmeNm> typeFormatValidator() {
		return new SchmeNmTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SchmeNm, Set<String>> onlyExistsValidator() {
		return new SchmeNmOnlyExistsValidator();
	}
}
