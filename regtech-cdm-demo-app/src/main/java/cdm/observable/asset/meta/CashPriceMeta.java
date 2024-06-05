package cdm.observable.asset.meta;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.validation.CashPriceTypeFormatValidator;
import cdm.observable.asset.validation.CashPriceValidator;
import cdm.observable.asset.validation.exists.CashPriceOnlyExistsValidator;
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
@RosettaMeta(model=CashPrice.class)
public class CashPriceMeta implements RosettaMetaData<CashPrice> {

	@Override
	public List<Validator<? super CashPrice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.CashPricePremiumType.class)
		);
	}
	
	@Override
	public List<Function<? super CashPrice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CashPrice> validator() {
		return new CashPriceValidator();
	}

	@Override
	public Validator<? super CashPrice> typeFormatValidator() {
		return new CashPriceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CashPrice, Set<String>> onlyExistsValidator() {
		return new CashPriceOnlyExistsValidator();
	}
}
