package cdm.event.common.meta;

import cdm.event.common.MarginCallIssuance;
import cdm.event.common.validation.MarginCallIssuanceTypeFormatValidator;
import cdm.event.common.validation.MarginCallIssuanceValidator;
import cdm.event.common.validation.exists.MarginCallIssuanceOnlyExistsValidator;
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
@RosettaMeta(model=MarginCallIssuance.class)
public class MarginCallIssuanceMeta implements RosettaMetaData<MarginCallIssuance> {

	@Override
	public List<Validator<? super MarginCallIssuance>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.MarginCallBaseRegIMRoleIMOnly.class)
		);
	}
	
	@Override
	public List<Function<? super MarginCallIssuance, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MarginCallIssuance> validator() {
		return new MarginCallIssuanceValidator();
	}

	@Override
	public Validator<? super MarginCallIssuance> typeFormatValidator() {
		return new MarginCallIssuanceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MarginCallIssuance, Set<String>> onlyExistsValidator() {
		return new MarginCallIssuanceOnlyExistsValidator();
	}
}
