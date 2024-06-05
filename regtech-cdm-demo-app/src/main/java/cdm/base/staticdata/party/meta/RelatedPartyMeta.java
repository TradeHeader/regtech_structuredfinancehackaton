package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.RelatedParty;
import cdm.base.staticdata.party.validation.RelatedPartyTypeFormatValidator;
import cdm.base.staticdata.party.validation.RelatedPartyValidator;
import cdm.base.staticdata.party.validation.exists.RelatedPartyOnlyExistsValidator;
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
@RosettaMeta(model=RelatedParty.class)
public class RelatedPartyMeta implements RosettaMetaData<RelatedParty> {

	@Override
	public List<Validator<? super RelatedParty>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super RelatedParty, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RelatedParty> validator() {
		return new RelatedPartyValidator();
	}

	@Override
	public Validator<? super RelatedParty> typeFormatValidator() {
		return new RelatedPartyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RelatedParty, Set<String>> onlyExistsValidator() {
		return new RelatedPartyOnlyExistsValidator();
	}
}
