package cdm.product.asset.meta;

import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.validation.EquityUnderlierProvisionsTypeFormatValidator;
import cdm.product.asset.validation.EquityUnderlierProvisionsValidator;
import cdm.product.asset.validation.exists.EquityUnderlierProvisionsOnlyExistsValidator;
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
@RosettaMeta(model=EquityUnderlierProvisions.class)
public class EquityUnderlierProvisionsMeta implements RosettaMetaData<EquityUnderlierProvisions> {

	@Override
	public List<Validator<? super EquityUnderlierProvisions>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.EquityUnderlierProvisionsComponentSecurityOrMultipleExchange.class)
		);
	}
	
	@Override
	public List<Function<? super EquityUnderlierProvisions, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EquityUnderlierProvisions> validator() {
		return new EquityUnderlierProvisionsValidator();
	}

	@Override
	public Validator<? super EquityUnderlierProvisions> typeFormatValidator() {
		return new EquityUnderlierProvisionsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EquityUnderlierProvisions, Set<String>> onlyExistsValidator() {
		return new EquityUnderlierProvisionsOnlyExistsValidator();
	}
}
