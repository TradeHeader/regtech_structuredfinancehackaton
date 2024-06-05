package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.validation.LegalEntityTypeFormatValidator;
import cdm.base.staticdata.party.validation.LegalEntityValidator;
import cdm.base.staticdata.party.validation.exists.LegalEntityOnlyExistsValidator;
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
@RosettaMeta(model=LegalEntity.class)
public class LegalEntityMeta implements RosettaMetaData<LegalEntity> {

	@Override
	public List<Validator<? super LegalEntity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super LegalEntity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super LegalEntity> validator() {
		return new LegalEntityValidator();
	}

	@Override
	public Validator<? super LegalEntity> typeFormatValidator() {
		return new LegalEntityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super LegalEntity, Set<String>> onlyExistsValidator() {
		return new LegalEntityOnlyExistsValidator();
	}
}
