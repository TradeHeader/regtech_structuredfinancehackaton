package cdm.base.staticdata.identifier.meta;

import cdm.base.staticdata.identifier.LocationIdentifier;
import cdm.base.staticdata.identifier.validation.LocationIdentifierTypeFormatValidator;
import cdm.base.staticdata.identifier.validation.LocationIdentifierValidator;
import cdm.base.staticdata.identifier.validation.exists.LocationIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=LocationIdentifier.class)
public class LocationIdentifierMeta implements RosettaMetaData<LocationIdentifier> {

	@Override
	public List<Validator<? super LocationIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.identifier.validation.datarule.LocationIdentifierIdentifierType.class),
			factory.create(cdm.base.staticdata.identifier.validation.datarule.IdentifierIssuerChoice.class)
		);
	}
	
	@Override
	public List<Function<? super LocationIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super LocationIdentifier> validator() {
		return new LocationIdentifierValidator();
	}

	@Override
	public Validator<? super LocationIdentifier> typeFormatValidator() {
		return new LocationIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super LocationIdentifier, Set<String>> onlyExistsValidator() {
		return new LocationIdentifierOnlyExistsValidator();
	}
}
