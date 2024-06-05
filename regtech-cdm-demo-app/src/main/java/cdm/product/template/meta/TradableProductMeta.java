package cdm.product.template.meta;

import cdm.product.template.TradableProduct;
import cdm.product.template.validation.TradableProductTypeFormatValidator;
import cdm.product.template.validation.TradableProductValidator;
import cdm.product.template.validation.exists.TradableProductOnlyExistsValidator;
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
@RosettaMeta(model=TradableProduct.class)
public class TradableProductMeta implements RosettaMetaData<TradableProduct> {

	@Override
	public List<Validator<? super TradableProduct>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.TradableProductPriceQuantityTriangulation.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductNotionalAdjustment.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductPerformancePayoutExtraordinaryDividendsParty.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductOptionPayoutPredeterminedClearingOrganizationParty.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductForwardPayoutPredeterminedClearingOrganizationParty.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductPredeterminedClearingOrganizationParty.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductExerciseNoticeReceiverPartyManual.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductExerciseNoticeReceiverPartyOptionalEarlyTermination.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductExerciseNoticeReceiverPartyCancelableProvision.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductExerciseNoticeReceiverPartyExtendibleProvision.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductCalculationAgentIndependent.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductCalculationAgentOptionalEarlyTermination.class),
			factory.create(cdm.product.template.validation.datarule.TradableProductCalculationAgentMandatoryEarlyTermination.class)
		);
	}
	
	@Override
	public List<Function<? super TradableProduct, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TradableProduct> validator() {
		return new TradableProductValidator();
	}

	@Override
	public Validator<? super TradableProduct> typeFormatValidator() {
		return new TradableProductTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TradableProduct, Set<String>> onlyExistsValidator() {
		return new TradableProductOnlyExistsValidator();
	}
}
