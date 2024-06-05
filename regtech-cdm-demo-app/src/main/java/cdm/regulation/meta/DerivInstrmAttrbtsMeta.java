package cdm.regulation.meta;

import cdm.regulation.DerivInstrmAttrbts;
import cdm.regulation.validation.DerivInstrmAttrbtsTypeFormatValidator;
import cdm.regulation.validation.DerivInstrmAttrbtsValidator;
import cdm.regulation.validation.exists.DerivInstrmAttrbtsOnlyExistsValidator;
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
@RosettaMeta(model=DerivInstrmAttrbts.class)
public class DerivInstrmAttrbtsMeta implements RosettaMetaData<DerivInstrmAttrbts> {

	@Override
	public List<Validator<? super DerivInstrmAttrbts>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DerivInstrmAttrbts, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DerivInstrmAttrbts> validator() {
		return new DerivInstrmAttrbtsValidator();
	}

	@Override
	public Validator<? super DerivInstrmAttrbts> typeFormatValidator() {
		return new DerivInstrmAttrbtsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DerivInstrmAttrbts, Set<String>> onlyExistsValidator() {
		return new DerivInstrmAttrbtsOnlyExistsValidator();
	}
}
