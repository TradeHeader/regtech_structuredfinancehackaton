package cdm.observable.asset.meta;

import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.validation.CreditNotationTypeFormatValidator;
import cdm.observable.asset.validation.CreditNotationValidator;
import cdm.observable.asset.validation.exists.CreditNotationOnlyExistsValidator;
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
@RosettaMeta(model=CreditNotation.class)
public class CreditNotationMeta implements RosettaMetaData<CreditNotation> {

	@Override
	public List<Validator<? super CreditNotation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CreditNotation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditNotation> validator() {
		return new CreditNotationValidator();
	}

	@Override
	public Validator<? super CreditNotation> typeFormatValidator() {
		return new CreditNotationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditNotation, Set<String>> onlyExistsValidator() {
		return new CreditNotationOnlyExistsValidator();
	}
}
