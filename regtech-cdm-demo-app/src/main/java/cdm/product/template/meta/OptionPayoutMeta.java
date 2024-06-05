package cdm.product.template.meta;

import cdm.product.template.OptionPayout;
import cdm.product.template.validation.OptionPayoutTypeFormatValidator;
import cdm.product.template.validation.OptionPayoutValidator;
import cdm.product.template.validation.exists.OptionPayoutOnlyExistsValidator;
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
@RosettaMeta(model=OptionPayout.class)
public class OptionPayoutMeta implements RosettaMetaData<OptionPayout> {

	@Override
	public List<Validator<? super OptionPayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.OptionPayoutClearedPhysicalSettlementExists.class),
			factory.create(cdm.product.template.validation.datarule.OptionPayoutDeliveryCapacity.class),
			factory.create(cdm.product.template.validation.datarule.OptionPayoutPriceTimeIntervalQuantity.class)
		);
	}
	
	@Override
	public List<Function<? super OptionPayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super OptionPayout> validator() {
		return new OptionPayoutValidator();
	}

	@Override
	public Validator<? super OptionPayout> typeFormatValidator() {
		return new OptionPayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super OptionPayout, Set<String>> onlyExistsValidator() {
		return new OptionPayoutOnlyExistsValidator();
	}
}
