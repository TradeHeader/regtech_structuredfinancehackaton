package cdm.base.staticdata.identifier.meta;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.validation.AssignedIdentifierTypeFormatValidator;
import cdm.base.staticdata.identifier.validation.AssignedIdentifierValidator;
import cdm.base.staticdata.identifier.validation.exists.AssignedIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=AssignedIdentifier.class)
public class AssignedIdentifierMeta implements RosettaMetaData<AssignedIdentifier> {

	@Override
	public List<Validator<? super AssignedIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssignedIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssignedIdentifier> validator() {
		return new AssignedIdentifierValidator();
	}

	@Override
	public Validator<? super AssignedIdentifier> typeFormatValidator() {
		return new AssignedIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssignedIdentifier, Set<String>> onlyExistsValidator() {
		return new AssignedIdentifierOnlyExistsValidator();
	}
}
