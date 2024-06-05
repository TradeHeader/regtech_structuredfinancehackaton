package cdm.observable.asset.meta;

import cdm.observable.asset.Money;
import cdm.observable.asset.validation.MoneyTypeFormatValidator;
import cdm.observable.asset.validation.MoneyValidator;
import cdm.observable.asset.validation.exists.MoneyOnlyExistsValidator;
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
@RosettaMeta(model=Money.class)
public class MoneyMeta implements RosettaMetaData<Money> {

	@Override
	public List<Validator<? super Money>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.MoneyCurrencyUnitExists.class),
			factory.create(cdm.base.math.validation.datarule.QuantityAmountOnlyExists.class),
			factory.create(cdm.base.math.validation.datarule.QuantityScheduleQuantityMultiplier.class),
			factory.create(cdm.base.math.validation.datarule.QuantityScheduleUnitOfAmountExists.class),
			factory.create(cdm.base.math.validation.datarule.MeasureScheduleValueExists.class)
		);
	}
	
	@Override
	public List<Function<? super Money, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Money> validator() {
		return new MoneyValidator();
	}

	@Override
	public Validator<? super Money> typeFormatValidator() {
		return new MoneyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Money, Set<String>> onlyExistsValidator() {
		return new MoneyOnlyExistsValidator();
	}
}
