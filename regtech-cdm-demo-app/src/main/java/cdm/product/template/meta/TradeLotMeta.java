package cdm.product.template.meta;

import cdm.product.template.TradeLot;
import cdm.product.template.validation.TradeLotTypeFormatValidator;
import cdm.product.template.validation.TradeLotValidator;
import cdm.product.template.validation.exists.TradeLotOnlyExistsValidator;
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
@RosettaMeta(model=TradeLot.class)
public class TradeLotMeta implements RosettaMetaData<TradeLot> {

	@Override
	public List<Validator<? super TradeLot>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TradeLot, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TradeLot> validator() {
		return new TradeLotValidator();
	}

	@Override
	public Validator<? super TradeLot> typeFormatValidator() {
		return new TradeLotTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TradeLot, Set<String>> onlyExistsValidator() {
		return new TradeLotOnlyExistsValidator();
	}
}
