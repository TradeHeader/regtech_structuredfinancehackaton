package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.validation.SettlementProvisionTypeFormatValidator;
import cdm.product.common.settlement.validation.SettlementProvisionValidator;
import cdm.product.common.settlement.validation.exists.SettlementProvisionOnlyExistsValidator;
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
@RosettaMeta(model=SettlementProvision.class)
public class SettlementProvisionMeta implements RosettaMetaData<SettlementProvision> {

	@Override
	public List<Validator<? super SettlementProvision>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SettlementProvision, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SettlementProvision> validator() {
		return new SettlementProvisionValidator();
	}

	@Override
	public Validator<? super SettlementProvision> typeFormatValidator() {
		return new SettlementProvisionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SettlementProvision, Set<String>> onlyExistsValidator() {
		return new SettlementProvisionOnlyExistsValidator();
	}
}
