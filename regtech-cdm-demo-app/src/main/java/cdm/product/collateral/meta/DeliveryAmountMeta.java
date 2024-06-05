package cdm.product.collateral.meta;

import cdm.product.collateral.DeliveryAmount;
import cdm.product.collateral.validation.DeliveryAmountTypeFormatValidator;
import cdm.product.collateral.validation.DeliveryAmountValidator;
import cdm.product.collateral.validation.exists.DeliveryAmountOnlyExistsValidator;
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
@RosettaMeta(model=DeliveryAmount.class)
public class DeliveryAmountMeta implements RosettaMetaData<DeliveryAmount> {

	@Override
	public List<Validator<? super DeliveryAmount>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.DeliveryAmountOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super DeliveryAmount, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DeliveryAmount> validator() {
		return new DeliveryAmountValidator();
	}

	@Override
	public Validator<? super DeliveryAmount> typeFormatValidator() {
		return new DeliveryAmountTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DeliveryAmount, Set<String>> onlyExistsValidator() {
		return new DeliveryAmountOnlyExistsValidator();
	}
}
