package cdm.event.common.meta;

import cdm.event.common.PositionIdentifier;
import cdm.event.common.validation.PositionIdentifierTypeFormatValidator;
import cdm.event.common.validation.PositionIdentifierValidator;
import cdm.event.common.validation.exists.PositionIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=PositionIdentifier.class)
public class PositionIdentifierMeta implements RosettaMetaData<PositionIdentifier> {

	@Override
	public List<Validator<? super PositionIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.identifier.validation.datarule.IdentifierIssuerChoice.class)
		);
	}
	
	@Override
	public List<Function<? super PositionIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PositionIdentifier> validator() {
		return new PositionIdentifierValidator();
	}

	@Override
	public Validator<? super PositionIdentifier> typeFormatValidator() {
		return new PositionIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PositionIdentifier, Set<String>> onlyExistsValidator() {
		return new PositionIdentifierOnlyExistsValidator();
	}
}
