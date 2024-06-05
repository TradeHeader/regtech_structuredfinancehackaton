package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.validation.CashSettlementTermsTypeFormatValidator;
import cdm.product.common.settlement.validation.CashSettlementTermsValidator;
import cdm.product.common.settlement.validation.exists.CashSettlementTermsOnlyExistsValidator;
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
@RosettaMeta(model=CashSettlementTerms.class)
public class CashSettlementTermsMeta implements RosettaMetaData<CashSettlementTerms> {

	@Override
	public List<Validator<? super CashSettlementTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.CashSettlementTermsCashSettlementTermsChoice.class),
			factory.create(cdm.product.common.settlement.validation.datarule.CashSettlementTermsRecoveryFactor.class),
			factory.create(cdm.product.common.settlement.validation.datarule.CashSettlementTermsCashCollateralMethod.class),
			factory.create(cdm.product.common.settlement.validation.datarule.CashSettlementTermsMidMarketValuationMethod.class),
			factory.create(cdm.product.common.settlement.validation.datarule.CashSettlementTermsReplacementValueMethod.class),
			factory.create(cdm.product.common.settlement.validation.datarule.CashSettlementTermsFirmQuotationMethod.class)
		);
	}
	
	@Override
	public List<Function<? super CashSettlementTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CashSettlementTerms> validator() {
		return new CashSettlementTermsValidator();
	}

	@Override
	public Validator<? super CashSettlementTerms> typeFormatValidator() {
		return new CashSettlementTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CashSettlementTerms, Set<String>> onlyExistsValidator() {
		return new CashSettlementTermsOnlyExistsValidator();
	}
}
