package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.UmbrellaAgreementEntity;
import cdm.legaldocumentation.common.validation.UmbrellaAgreementEntityTypeFormatValidator;
import cdm.legaldocumentation.common.validation.UmbrellaAgreementEntityValidator;
import cdm.legaldocumentation.common.validation.exists.UmbrellaAgreementEntityOnlyExistsValidator;
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
@RosettaMeta(model=UmbrellaAgreementEntity.class)
public class UmbrellaAgreementEntityMeta implements RosettaMetaData<UmbrellaAgreementEntity> {

	@Override
	public List<Validator<? super UmbrellaAgreementEntity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super UmbrellaAgreementEntity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super UmbrellaAgreementEntity> validator() {
		return new UmbrellaAgreementEntityValidator();
	}

	@Override
	public Validator<? super UmbrellaAgreementEntity> typeFormatValidator() {
		return new UmbrellaAgreementEntityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super UmbrellaAgreementEntity, Set<String>> onlyExistsValidator() {
		return new UmbrellaAgreementEntityOnlyExistsValidator();
	}
}
