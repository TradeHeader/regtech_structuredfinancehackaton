package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.AncillaryEntity;
import cdm.base.staticdata.party.validation.AncillaryEntityTypeFormatValidator;
import cdm.base.staticdata.party.validation.AncillaryEntityValidator;
import cdm.base.staticdata.party.validation.exists.AncillaryEntityOnlyExistsValidator;
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
@RosettaMeta(model=AncillaryEntity.class)
public class AncillaryEntityMeta implements RosettaMetaData<AncillaryEntity> {

	@Override
	public List<Validator<? super AncillaryEntity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.party.validation.datarule.AncillaryEntityOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super AncillaryEntity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AncillaryEntity> validator() {
		return new AncillaryEntityValidator();
	}

	@Override
	public Validator<? super AncillaryEntity> typeFormatValidator() {
		return new AncillaryEntityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AncillaryEntity, Set<String>> onlyExistsValidator() {
		return new AncillaryEntityOnlyExistsValidator();
	}
}
