package cdm.product.asset.meta;

import cdm.product.asset.ForeignExchange;
import cdm.product.asset.validation.ForeignExchangeTypeFormatValidator;
import cdm.product.asset.validation.ForeignExchangeValidator;
import cdm.product.asset.validation.exists.ForeignExchangeOnlyExistsValidator;
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
@RosettaMeta(model=ForeignExchange.class)
public class ForeignExchangeMeta implements RosettaMetaData<ForeignExchange> {

	@Override
	public List<Validator<? super ForeignExchange>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ForeignExchange, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ForeignExchange> validator() {
		return new ForeignExchangeValidator();
	}

	@Override
	public Validator<? super ForeignExchange> typeFormatValidator() {
		return new ForeignExchangeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ForeignExchange, Set<String>> onlyExistsValidator() {
		return new ForeignExchangeOnlyExistsValidator();
	}
}
