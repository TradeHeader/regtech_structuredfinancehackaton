package cdm.product.asset.meta;

import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.validation.CreditDefaultPayoutTypeFormatValidator;
import cdm.product.asset.validation.CreditDefaultPayoutValidator;
import cdm.product.asset.validation.exists.CreditDefaultPayoutOnlyExistsValidator;
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
@RosettaMeta(model=CreditDefaultPayout.class)
public class CreditDefaultPayoutMeta implements RosettaMetaData<CreditDefaultPayout> {

	@Override
	public List<Validator<? super CreditDefaultPayout>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.CreditDefaultPayoutFpMLCd12.class),
			factory.create(cdm.product.asset.validation.datarule.CreditDefaultPayoutQuantity.class)
		);
	}
	
	@Override
	public List<Function<? super CreditDefaultPayout, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditDefaultPayout> validator() {
		return new CreditDefaultPayoutValidator();
	}

	@Override
	public Validator<? super CreditDefaultPayout> typeFormatValidator() {
		return new CreditDefaultPayoutTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditDefaultPayout, Set<String>> onlyExistsValidator() {
		return new CreditDefaultPayoutOnlyExistsValidator();
	}
}
