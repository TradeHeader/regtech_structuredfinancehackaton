package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
import cdm.legaldocumentation.master.validation.UnderlierSubstitutionProvisionTypeFormatValidator;
import cdm.legaldocumentation.master.validation.UnderlierSubstitutionProvisionValidator;
import cdm.legaldocumentation.master.validation.exists.UnderlierSubstitutionProvisionOnlyExistsValidator;
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
@RosettaMeta(model=UnderlierSubstitutionProvision.class)
public class UnderlierSubstitutionProvisionMeta implements RosettaMetaData<UnderlierSubstitutionProvision> {

	@Override
	public List<Validator<? super UnderlierSubstitutionProvision>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.legaldocumentation.master.validation.datarule.UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole.class)
		);
	}
	
	@Override
	public List<Function<? super UnderlierSubstitutionProvision, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super UnderlierSubstitutionProvision> validator() {
		return new UnderlierSubstitutionProvisionValidator();
	}

	@Override
	public Validator<? super UnderlierSubstitutionProvision> typeFormatValidator() {
		return new UnderlierSubstitutionProvisionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super UnderlierSubstitutionProvision, Set<String>> onlyExistsValidator() {
		return new UnderlierSubstitutionProvisionOnlyExistsValidator();
	}
}
