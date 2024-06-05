package cdm.event.position.meta;

import cdm.event.position.SecurityLocate;
import cdm.event.position.validation.SecurityLocateTypeFormatValidator;
import cdm.event.position.validation.SecurityLocateValidator;
import cdm.event.position.validation.exists.SecurityLocateOnlyExistsValidator;
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
@RosettaMeta(model=SecurityLocate.class)
public class SecurityLocateMeta implements RosettaMetaData<SecurityLocate> {

	@Override
	public List<Validator<? super SecurityLocate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.position.validation.datarule.SecurityLocateRequestOneSecurityMinimum.class),
			factory.create(cdm.event.position.validation.datarule.AvailableInventoryValidPartyRole.class)
		);
	}
	
	@Override
	public List<Function<? super SecurityLocate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SecurityLocate> validator() {
		return new SecurityLocateValidator();
	}

	@Override
	public Validator<? super SecurityLocate> typeFormatValidator() {
		return new SecurityLocateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SecurityLocate, Set<String>> onlyExistsValidator() {
		return new SecurityLocateOnlyExistsValidator();
	}
}
