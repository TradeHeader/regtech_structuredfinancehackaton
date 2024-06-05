package cdm.product.template.meta;

import cdm.product.template.ForwardPayout;
import cdm.product.template.validation.ForwardPayoutTypeFormatValidator;
import cdm.product.template.validation.ForwardPayoutValidator;
import cdm.product.template.validation.exists.ForwardPayoutOnlyExistsValidator;
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
@RosettaMeta(model=ForwardPayout.class)
public class ForwardPayoutMeta implements RosettaMetaData<ForwardPayout> {

	@Override
	public List<Validator<? super ForwardPayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.ForwardPayoutSettlementTerms.class),
			factory.create(cdm.product.template.validation.datarule.ForwardPayoutSettlementDate.class),
			factory.create(cdm.product.template.validation.datarule.ForwardPayoutFxSettlement.class),
			factory.create(cdm.product.template.validation.datarule.ForwardPayoutDeliveryCapacity.class),
			factory.create(cdm.product.template.validation.datarule.ForwardPayoutPriceTimeIntervalQuantity.class)
		);
	}
	
	@Override
	public List<Function<? super ForwardPayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ForwardPayout> validator() {
		return new ForwardPayoutValidator();
	}

	@Override
	public Validator<? super ForwardPayout> typeFormatValidator() {
		return new ForwardPayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ForwardPayout, Set<String>> onlyExistsValidator() {
		return new ForwardPayoutOnlyExistsValidator();
	}
}
