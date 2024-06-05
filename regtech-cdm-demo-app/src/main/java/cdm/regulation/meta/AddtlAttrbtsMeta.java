package cdm.regulation.meta;

import cdm.regulation.AddtlAttrbts;
import cdm.regulation.validation.AddtlAttrbtsTypeFormatValidator;
import cdm.regulation.validation.AddtlAttrbtsValidator;
import cdm.regulation.validation.exists.AddtlAttrbtsOnlyExistsValidator;
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
@RosettaMeta(model=AddtlAttrbts.class)
public class AddtlAttrbtsMeta implements RosettaMetaData<AddtlAttrbts> {

	@Override
	public List<Validator<? super AddtlAttrbts>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AddtlAttrbts, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AddtlAttrbts> validator() {
		return new AddtlAttrbtsValidator();
	}

	@Override
	public Validator<? super AddtlAttrbts> typeFormatValidator() {
		return new AddtlAttrbtsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AddtlAttrbts, Set<String>> onlyExistsValidator() {
		return new AddtlAttrbtsOnlyExistsValidator();
	}
}
