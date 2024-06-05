package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.validation.PhysicalSettlementTermsTypeFormatValidator;
import cdm.product.common.settlement.validation.PhysicalSettlementTermsValidator;
import cdm.product.common.settlement.validation.exists.PhysicalSettlementTermsOnlyExistsValidator;
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
@RosettaMeta(model=PhysicalSettlementTerms.class)
public class PhysicalSettlementTermsMeta implements RosettaMetaData<PhysicalSettlementTerms> {

	@Override
	public List<Validator<? super PhysicalSettlementTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.PhysicalSettlementTermsPredeterminedClearingOrganizationParty.class)
		);
	}
	
	@Override
	public List<Function<? super PhysicalSettlementTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PhysicalSettlementTerms> validator() {
		return new PhysicalSettlementTermsValidator();
	}

	@Override
	public Validator<? super PhysicalSettlementTerms> typeFormatValidator() {
		return new PhysicalSettlementTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PhysicalSettlementTerms, Set<String>> onlyExistsValidator() {
		return new PhysicalSettlementTermsOnlyExistsValidator();
	}
}
