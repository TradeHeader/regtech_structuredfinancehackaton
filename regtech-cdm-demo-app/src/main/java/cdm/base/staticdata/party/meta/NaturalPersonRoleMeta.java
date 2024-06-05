package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.validation.NaturalPersonRoleTypeFormatValidator;
import cdm.base.staticdata.party.validation.NaturalPersonRoleValidator;
import cdm.base.staticdata.party.validation.exists.NaturalPersonRoleOnlyExistsValidator;
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
@RosettaMeta(model=NaturalPersonRole.class)
public class NaturalPersonRoleMeta implements RosettaMetaData<NaturalPersonRole> {

	@Override
	public List<Validator<? super NaturalPersonRole>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super NaturalPersonRole, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super NaturalPersonRole> validator() {
		return new NaturalPersonRoleValidator();
	}

	@Override
	public Validator<? super NaturalPersonRole> typeFormatValidator() {
		return new NaturalPersonRoleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super NaturalPersonRole, Set<String>> onlyExistsValidator() {
		return new NaturalPersonRoleOnlyExistsValidator();
	}
}
