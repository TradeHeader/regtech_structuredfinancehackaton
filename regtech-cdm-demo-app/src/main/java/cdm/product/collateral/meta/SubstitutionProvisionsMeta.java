package cdm.product.collateral.meta;

import cdm.product.collateral.SubstitutionProvisions;
import cdm.product.collateral.validation.SubstitutionProvisionsTypeFormatValidator;
import cdm.product.collateral.validation.SubstitutionProvisionsValidator;
import cdm.product.collateral.validation.exists.SubstitutionProvisionsOnlyExistsValidator;
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
@RosettaMeta(model=SubstitutionProvisions.class)
public class SubstitutionProvisionsMeta implements RosettaMetaData<SubstitutionProvisions> {

	@Override
	public List<Validator<? super SubstitutionProvisions>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SubstitutionProvisions, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SubstitutionProvisions> validator() {
		return new SubstitutionProvisionsValidator();
	}

	@Override
	public Validator<? super SubstitutionProvisions> typeFormatValidator() {
		return new SubstitutionProvisionsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SubstitutionProvisions, Set<String>> onlyExistsValidator() {
		return new SubstitutionProvisionsOnlyExistsValidator();
	}
}
